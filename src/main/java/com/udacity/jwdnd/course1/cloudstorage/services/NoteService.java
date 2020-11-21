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

    public List<Note> getNotesByUserid(Integer userId) {return noteMapper.getNotesByUserId(userId);}

    public Note getNoteById(Integer noteId){
        return noteMapper.getNoteById(noteId);
    }

    public Integer createNote(Note note){
        return noteMapper.createNote(note);
    }

    public Note updateNote(Note note) {
        Integer numUpdatedRecord = noteMapper.updateNote(note);

        if(numUpdatedRecord == 1) {
            return noteMapper.getNoteById(note.getNoteId());
        }

        return null;
    }

    public void deleteNote(Integer noteId){
        noteMapper.deleteNote(noteId);
    }
}
