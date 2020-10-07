package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/credentials")
public class CredentialController {
    private UserService userService;
    private CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService){
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String getCredentialsTab(@ModelAttribute("newCredential") Credential credential, Model model, Authentication authentication){
        model.addAttribute("credentialList",credentialService.getCredentialsByUserId(userService.getUser(authentication.getName()).getUserId()));

        return "_" + "credentials";
    }

    @GetMapping("/{id}")
    public String getCredentialsTabWithCredentialBeingView(@PathVariable("id") Integer id, @ModelAttribute("newCredential") Credential credential, Model model, Authentication authentication){
        model.addAttribute("credentialList",credentialService.getCredentialsByUserId(userService.getUser(authentication.getName()).getUserId()));
        model.addAttribute("decryptedPasswordOfCredentialBeingViewed",credentialService.getDecryptedPasswordOfCredential(id));

        return "_" + "credentials";
    }

    @PostMapping("/add")
    public ModelAndView createCredential(@ModelAttribute("newCredential") Credential credential, ModelMap model, Authentication authentication){
        credential.setUserId(userService.getUser(authentication.getName()).getUserId());
        credentialService.createCredential(credential);
        model.addAttribute("activeTab","credentials");

        return new ModelAndView ("redirect:/",model) ;
    }

    @GetMapping("/getDecryptedPassword/{id}")
    public ModelAndView getDecryptedPassword(@PathVariable("id") Integer id,@ModelAttribute("newCredential") Credential credential,ModelMap model){
        model.addAttribute("activeTab","credentials");
        model.addAttribute("isViewingCredential",true);
        model.addAttribute("credentialId",id);

        return new ModelAndView ("redirect:/",model) ;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateCredential(@PathVariable("id") Integer id, @ModelAttribute("newCredential") Credential credential, ModelMap model, Authentication authentication){
        credential.setUserId(userService.getUser(authentication.getName()).getUserId());
        credentialService.updateCredential(credential);
        model.addAttribute("activeTab","credentials");

        return new ModelAndView ("redirect:/",model) ;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCredential(@PathVariable("id") Integer id,ModelMap model){
        credentialService.deleteCredential(id);
        model.addAttribute("activeTab","credentials");

        return new ModelAndView ("redirect:/",model) ;
    }
}
