package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.StorageService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.util.SetQueryParamsForOpeningAlertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/files")
public class FileUploadController {

    @Autowired
    private UserService userService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private SetQueryParamsForOpeningAlertUtil setQueryParamsForOpeningAlertUtil;

    @GetMapping
    public String listUploadFiles(Model model,Authentication authentication) throws IOException{
        User user  = userService.getUser(authentication.getName());

        model.addAttribute("files", storageService.loadByUserId(user.getUserId(), user.getStorageFolderName()).map(
                path->path)
                .collect(Collectors.toList()));

        return "files/files";
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename,Authentication authentication) {
        User user  = userService.getUser(authentication.getName());

        Resource file = storageService.loadAsResource(filename, user.getStorageFolderName());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/delete/{filename:.+}")
    public ModelAndView deleteFile (@PathVariable String filename,Authentication authentication){
        User user  = userService.getUser(authentication.getName());

        storageService.delete(user.getUserId(), filename, user.getStorageFolderName());

        ModelMap model = setQueryParamsForOpeningAlertUtil.setQueryParamsForAlert(true,"success","file","remove");

        return new ModelAndView ("redirect:/files",model) ;
    }

    @PostMapping("/add")
    public ModelAndView handleFileUpload(@RequestParam("fileUpload") MultipartFile file,Authentication authentication){
        User user  = userService.getUser(authentication.getName());

        storageService.store(file,user.getUserId(),user.getStorageFolderName());

        ModelMap model = setQueryParamsForOpeningAlertUtil.setQueryParamsForAlert(true,"success","file","upload");

        return new ModelAndView ("redirect:/files",model) ;
    }
}
