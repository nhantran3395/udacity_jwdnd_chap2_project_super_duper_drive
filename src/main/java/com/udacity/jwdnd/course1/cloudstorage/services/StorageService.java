package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file, Integer userId);

    Stream<Path> loadAll();

    Stream<Path> loadByUserId(Integer userId);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    void delete(String filename);
}
