package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    List<Note> getNotes();

    @Select("SELECT * FROM NOTES WHERE noteid=#{noteId}")
    Note getNoteById(Integer noteId);

    @Insert("INSERT INTO NOTES(notetitle,notedescription,userid) VALUES(#{noteTitle},#{noteDescription},#{userId})")
    Integer createNote(Note note);
}
