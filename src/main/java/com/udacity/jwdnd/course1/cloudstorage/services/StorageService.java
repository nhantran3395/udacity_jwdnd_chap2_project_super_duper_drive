package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file, Integer userId, String storageFolderName);

    Stream<Path> loadByUserId(Integer userId,String storageFolderName);

    Path load(String filename, String storageFolderName);

    Resource loadAsResource(String filename, String storageFolderName);

    void deleteAll();

    void delete(Integer userId, String filename,String storageFolderName);
}
