package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.StorageService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/files")
public class FileUploadController {

    private UserService userService;
    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService, UserService userService){
        this.userService = userService;
        this.storageService = storageService;
    }

    @GetMapping
    public String listUploadFiles(Model model, Authentication authentication) throws IOException{
        model.addAttribute("files", storageService.loadByUserId(userService.getUser(authentication.getName()).getUserId()).map(
                path->path)
                .collect(Collectors.toList()));

        return "_"+"files";
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/delete/{filename:.+}")
    public ModelAndView deleteFile (@PathVariable String filename,ModelMap model){
        storageService.delete(filename);

        model.addAttribute("activeTab","files");
        model.addAttribute("isAlertToBeOpened","true");
        model.addAttribute("alertType","success");
        model.addAttribute("alertForResource","file");
        model.addAttribute("alertContent","delete");

        return new ModelAndView ("redirect:/",model) ;    }

    @PostMapping("/add")
    public ModelAndView handleFileUpload(@RequestParam("fileUpload") MultipartFile file, Authentication authentication, ModelMap model){
        storageService.store(file,userService.getUser(authentication.getName()).getUserId());

        model.addAttribute("activeTab","files");
        model.addAttribute("isAlertToBeOpened","true");
        model.addAttribute("alertType","success");
        model.addAttribute("alertForResource","file");
        model.addAttribute("alertContent","create");

        return new ModelAndView ("redirect:/",model) ;
    }
}
