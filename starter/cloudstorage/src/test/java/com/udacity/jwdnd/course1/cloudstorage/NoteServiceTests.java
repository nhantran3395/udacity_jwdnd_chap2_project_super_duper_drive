package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@Import(NoteService.class)
public class NoteServiceTests{

    @Autowired
    private NoteService noteService;

    @Test
    public void testNoteServiceGetNotes(){
        noteService.getNotes();
    }

    @Test
    public void testNoteServiceCreateNote(){
        Note note = new Note(null,"Test Note","This is a note created by a Test",1);

        Integer noteID = noteService.createNote(note);

        assertNotNull(noteID);
//        assertEquals(note.getNoteTitle(),noteService.getNoteById(noteID).getNoteTitle());
    }
}
