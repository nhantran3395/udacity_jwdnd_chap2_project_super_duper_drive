package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper){
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotes(){
        return noteMapper.getNotes();
    }
    public Note getNoteById(Integer noteId){
        return noteMapper.getNoteById(noteId);
    }

    public Integer createNote(Note note){
        return noteMapper.createNote(note);
    }
}