package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.config.StorageProperties;
import com.udacity.jwdnd.course1.cloudstorage.exception.StorageException;
import com.udacity.jwdnd.course1.cloudstorage.exception.StorageFileNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileStorageService implements StorageService{

    @Autowired
    private StorageProperties storageProperties;

    private Path rootLocation;

    @Autowired
    private FileMapper fileMapper;

    private Stream<Path> collectionOfFilesBelongToUser;

    @PostConstruct
    public void postConstruct(){
        this.rootLocation = Paths.get(storageProperties.getLocation());
        this.collectionOfFilesBelongToUser = Stream.empty();
    }

    @Override
    public void store(MultipartFile file, Integer userId, String storageFolderName) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            if(fileMapper.getFilesByNameAndUserId(filename,userId) != null){
                throw new StorageException("File with name " + filename + " is already uploaded");
            }

            //Check if user specific storage folder exist or not. if not, create the folder.
            Path pathOfFolder = this.rootLocation.resolve(storageFolderName);
            if(!isStorageFolderForUserAlreadyExist(pathOfFolder)){
                setupStorageFolderForUser(pathOfFolder);
            }

            //Write file into user specific storage folder
            try (InputStream inputStream = file.getInputStream()) {
                Path path = pathOfFolder.resolve(filename);

                Files.copy(inputStream, path,
                        StandardCopyOption.REPLACE_EXISTING);

                fileMapper.createFile(new FileUpload(null,filename,this.getFileExtension(filename),this.getFileSize(path),userId));
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Stream<Path> loadByUserId(Integer userId,String storageFolderName){
            List<String> listOfFilesBelongToUser = new ArrayList<String>();
            fileMapper.getFilesByUserId(userId).forEach(file ->
                {
                    listOfFilesBelongToUser.add(file.getFileName());
                }
             );

            collectionOfFilesBelongToUser = Stream.empty();

            listOfFilesBelongToUser.forEach(filename->
                {
                    collectionOfFilesBelongToUser = Stream.concat(collectionOfFilesBelongToUser,Stream.of(load(filename,storageFolderName)));
                }
            );

            return collectionOfFilesBelongToUser.map(this.rootLocation.resolve(storageFolderName)::relativize);
    }

    @Override
    public Path load(String filename,String storageFolderName) {
        return rootLocation.resolve(storageFolderName).resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename, String storageFolderName) {
        try {
            Path file = load(filename,storageFolderName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void delete(Integer userId, String filename,String storageFolderName) {
        try{
            FileSystemUtils.deleteRecursively(this.load(filename,storageFolderName));
            fileMapper.deleteFileByNameAndUserId(filename,userId);
        }catch(IOException e){
            throw new StorageException("Failed to delete file " + filename, e);
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize parent storage folder", e);
        }
    }

    private boolean isStorageFolderForUserAlreadyExist(Path pathOfFolder){
         return Files.exists(pathOfFolder);
    }

    private void setupStorageFolderForUser(Path pathOfFolder){

        try {
            Files.createDirectory(pathOfFolder);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize user specific storage folder", e);
        }
    }

    private String getFileExtension(String filename){
        return FilenameUtils.getExtension(filename);
    }

    private String getFileSize(Path path) {
        try{
            return FileUtils.byteCountToDisplaySize(Files.size(path));
        }catch(IOException e){
            throw new StorageException("Failed to get file size ",e);
        }
    }
}
