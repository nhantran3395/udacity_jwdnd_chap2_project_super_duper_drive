package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/","/home"})
public class IndexController {

    @GetMapping
    public String getHomePage(Model model){
        return "index";
    }
}
