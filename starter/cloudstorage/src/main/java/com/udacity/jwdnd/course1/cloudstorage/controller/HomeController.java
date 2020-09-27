package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping({"/","/home"})
public class HomeController {
    private NoteService noteService;

    public HomeController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("isFilesActive",true);
        model.addAttribute("isNotesActive",false);
        model.addAttribute("isCredentialsActive",false);

        return "home";
    }

    @GetMapping("files")
    public String getFilesTab(){
            return "_" + "files";
    }

    @GetMapping("notes")
    public String getNotesTab(@ModelAttribute("newNote") Note note, Model model){
        model.addAttribute("noteList",noteService.getNotes());

        return "_" + "notes";
    }

    @GetMapping("credentials")
    public String getCredentialsTab(){
            return "_" + "credentials";
    }

    @PostMapping("notes")
    public String createItem(@ModelAttribute("newNote") Note note, Model model){
        noteService.createNote(note);
        model.addAttribute("noteList",noteService.getNotes());

        model.addAttribute("isFilesActive",false);
        model.addAttribute("isNotesActive",true);
        model.addAttribute("isCredentialsActive",false);

        return "home";
    }
}
