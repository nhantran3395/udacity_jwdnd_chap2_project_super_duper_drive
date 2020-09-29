package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping({"/","/home"})
public class HomeController {
    private NoteService noteService;
    private CredentialService credentialService;

    public HomeController(NoteService noteService, CredentialService credentialService){
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String activeTab, Model model){
        model.addAttribute("activeTab",activeTab == null? "files":activeTab);

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

    @PostMapping("notes/add")
    public ModelAndView createNote(@ModelAttribute("newNote") Note note, ModelMap model){
        noteService.createNote(note);
        model.addAttribute("activeTab","notes");

        return new ModelAndView ("redirect:/",model) ;
    }

    @PostMapping("notes/update/{id}")
    public ModelAndView updateNote(@PathVariable("id") long id, @ModelAttribute("newNote") Note note, ModelMap model){
        noteService.updateNote(note);
        model.addAttribute("activeTab","notes");

        return new ModelAndView ("redirect:/",model) ;
    }

    @GetMapping("notes/delete/{id}")
    public ModelAndView deleteNote(@PathVariable("id") Integer id,ModelMap model){
        noteService.deleteNote(id);
        model.addAttribute("activeTab","notes");

        return new ModelAndView ("redirect:/",model) ;
    }

    @GetMapping("credentials")
    public String getCredentialsTab(Model model){
        model.addAttribute("credentialList",credentialService.getCredentials());

        return "_" + "credentials";
    }
}
