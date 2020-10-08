package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private UserService userService;
    private NoteService noteService;

    public NoteController(UserService userService, NoteService noteService){
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping
    public String getNotesTab(@ModelAttribute("newNote") Note note,Model model, Authentication authentication){
        model.addAttribute("noteList",noteService.getNotesByUserid(userService.getUser(authentication.getName()).getUserId()));

        return "_" + "notes";
    }

    @PostMapping("/add")
    public ModelAndView createNote(@ModelAttribute("newNote") Note note, ModelMap model, Authentication authentication){
        note.setUserId(userService.getUser(authentication.getName()).getUserId());
        noteService.createNote(note);
        model.addAttribute("activeTab","notes");
        model.addAttribute("noteCreateSuccess","true");

        return new ModelAndView ("redirect:/",model) ;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateNote(@PathVariable("id") long id, @ModelAttribute("newNote") Note note, ModelMap model, Authentication authentication){
        note.setUserId(userService.getUser(authentication.getName()).getUserId());
        noteService.updateNote(note);
        model.addAttribute("activeTab","notes");

        return new ModelAndView ("redirect:/",model) ;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteNote(@PathVariable("id") Integer id,ModelMap model){
        noteService.deleteNote(id);
        model.addAttribute("activeTab","notes");

        return new ModelAndView ("redirect:/",model) ;
    }
}
