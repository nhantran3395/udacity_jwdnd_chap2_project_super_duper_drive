package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/","/home"})
public class HomeController {
    private UserService userService;
    private NoteService noteService;
    private CredentialService credentialService;

    public HomeController(UserService userService, NoteService noteService, CredentialService credentialService){
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String activeTab, @RequestParam(required = false) boolean isViewingCredential,Model model, Authentication authentication){
        model.addAttribute("activeTab",activeTab == null? "files":activeTab);
        model.addAttribute("isViewingCredential",isViewingCredential? true : false);
        model.addAttribute("loggedInUsername",userService.getUser(authentication.getName()).getUsername());

        return "home";
    }
}
