package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
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
@RequestMapping("/notes")
public class    NoteController {

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private SetQueryParamsForOpeningAlertUtil setQueryParamsForOpeningAlertUtil;

    @GetMapping
    public String getNotesPage(@ModelAttribute("newNote") Note note, Model model, Authentication authentication){
        model.addAttribute("noteList",noteService.getNotesByUserid(userService.getUser(authentication.getName()).getUserId()));

        return "notes/notes";
    }

    @PostMapping("/add")
    public ModelAndView createNote(@ModelAttribute("newNote") Note note, Authentication authentication){
        note.setUserId(userService.getUser(authentication.getName()).getUserId());
        noteService.createNote(note);

        ModelMap model = setQueryParamsForOpeningAlertUtil.setQueryParamsForAlert(true,"success","note","create");

        return new ModelAndView ("redirect:/notes",model) ;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateNote(@PathVariable("id") Integer id, @ModelAttribute("newNote") Note note, Authentication authentication){
        note.setNoteId(id);
        note.setUserId(userService.getUser(authentication.getName()).getUserId());
        noteService.updateNote(note);

        ModelMap model = setQueryParamsForOpeningAlertUtil.setQueryParamsForAlert(true,"success","note","update");

        return new ModelAndView ("redirect:/notes",model) ;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteNote(@PathVariable("id") Integer id){
        noteService.deleteNote(id);

        ModelMap model = setQueryParamsForOpeningAlertUtil.setQueryParamsForAlert(true,"success","note","delete");

        return new ModelAndView ("redirect:/notes",model) ;
    }

}
