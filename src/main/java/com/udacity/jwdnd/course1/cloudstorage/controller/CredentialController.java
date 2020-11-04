package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.util.SetQueryParamsForOpeningAlertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private SetQueryParamsForOpeningAlertUtil setQueryParamsForOpeningAlertUtil;

    @GetMapping
    public String getCredentialsPage(@RequestParam(required = false) Integer id, @ModelAttribute("newCredential") Credential credential, Model model, Authentication authentication){
        model.addAttribute("credentialList",credentialService.getCredentialsByUserId(userService.getUser(authentication.getName()).getUserId()));
        if(id != null){
            model.addAttribute("decryptedPasswordOfCredentialBeingViewed",credentialService.getDecryptedPasswordOfCredential(id));
        }

        return "credentials/credentials";
    }

    @GetMapping("/{id}")
    public ModelAndView getCredential(@PathVariable("id") Integer id, ModelMap model){
        model.addAttribute("id",id);

        return new ModelAndView ("redirect:/credentials",model) ;
    }

    @PostMapping("/add")
    public ModelAndView createCredential(@ModelAttribute("newCredential") Credential credential,Authentication authentication){
        credential.setUserId(userService.getUser(authentication.getName()).getUserId());
        credentialService.createCredential(credential);

        ModelMap model =  setQueryParamsForOpeningAlertUtil.setQueryParamsForAlert(true,"success","credential","create");

        return new ModelAndView ("redirect:/credentials",model) ;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateCredential(@PathVariable("id") Integer id, @ModelAttribute("newCredential") Credential credential, Authentication authentication){
        credential.setUserId(userService.getUser(authentication.getName()).getUserId());
        credential.setCredentialId(id);
        credentialService.updateCredential(credential);

        ModelMap model =  setQueryParamsForOpeningAlertUtil.setQueryParamsForAlert(true,"success","credential","update");

        return new ModelAndView ("redirect:/credentials",model) ;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCredential(@PathVariable("id") Integer id){
        credentialService.deleteCredential(id);

        ModelMap model =  setQueryParamsForOpeningAlertUtil.setQueryParamsForAlert(true,"success","credential","delete");

        return new ModelAndView ("redirect:/credentials",model) ;
    }
}
