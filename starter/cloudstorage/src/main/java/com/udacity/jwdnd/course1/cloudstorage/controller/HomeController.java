package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/","/home"})
public class HomeController {
    private NoteService noteService;

    public HomeController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping
    public String getHomePage(@ModelAttribute("newNote") Note note, Model model){
        model.addAttribute("noteList",noteService.getNotes());

        return "home";
    }

    @PostMapping
    public String createItem(@ModelAttribute("newNote") Note note, Model model){
        noteService.createNote(note);
        model.addAttribute("noteList",noteService.getNotes());

        return "home";
    }
}
