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
    public String getHomePage(@RequestParam(required = false) String activeTab, @RequestParam(required = false) boolean isViewingCredential, @RequestParam(required = false) Integer credentialId, Model model){
        model.addAttribute("activeTab",activeTab == null? "files":activeTab);
        model.addAttribute("isViewingCredential",isViewingCredential? true : false);

        return "home";
    }

    @GetMapping("notes")
    public String getNotesTab(@ModelAttribute("newNote") Note note, Model model){
        model.addAttribute("noteList",noteService.getNotes());

        return "_" + "notes";
    }

    @PostMapping("notes/add")
    public ModelAndView createNote(@ModelAttribute("newNote") Note note, ModelMap model, Authentication authentication){
        note.setUserId(userService.getUser(authentication.getName()).getUserId());
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
    public String getCredentialsTab(@ModelAttribute("newCredential") Credential credential,Model model){
        model.addAttribute("credentialList",credentialService.getCredentials());

        return "_" + "credentials";
    }

    @GetMapping("credentials/{id}")
    public String getCredentialsTabWithCredentialBeingView(@PathVariable("id") Integer id, @ModelAttribute("newCredential") Credential credential, Model model){
        model.addAttribute("credentialList",credentialService.getCredentials());
        model.addAttribute("decryptedPasswordOfCredentialBeingViewed",credentialService.getDecryptedPasswordOfCredential(id));

        return "_" + "credentials";
    }

    @PostMapping("credentials/add")
    public ModelAndView createCredential(@ModelAttribute("newCredential") Credential credential, ModelMap model, Authentication authentication){
        credential.setUserId(userService.getUser(authentication.getName()).getUserId());
        credentialService.createCredential(credential);
        model.addAttribute("activeTab","credentials");

        return new ModelAndView ("redirect:/",model) ;
    }

    @GetMapping("credentials/getDecryptedPassword/{id}")
    public ModelAndView getDecryptedPassword(@PathVariable("id") Integer id,@ModelAttribute("newCredential") Credential credential,ModelMap model){
        model.addAttribute("activeTab","credentials");
        model.addAttribute("isViewingCredential",true);
        model.addAttribute("credentialId",id);

        return new ModelAndView ("redirect:/",model) ;
    }

    @PostMapping("credentials/update/{id}")
    public ModelAndView updateCredential(@PathVariable("id") Integer id, @ModelAttribute("newCredential") Credential credential, ModelMap model){
        credentialService.updateCredential(credential);
        model.addAttribute("activeTab","credentials");

        return new ModelAndView ("redirect:/",model) ;
    }

    @GetMapping("credentials/delete/{id}")
    public ModelAndView deleteCredential(@PathVariable("id") Integer id,ModelMap model){
        credentialService.deleteCredential(id);
        model.addAttribute("activeTab","credentials");

        return new ModelAndView ("redirect:/",model) ;
    }
}
