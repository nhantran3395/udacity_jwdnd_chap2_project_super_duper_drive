package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    List<Note> getNotes();

    @Select("SELECT * FROM NOTES WHERE userid=#{userId}")
    List<Note> getNotesByUserId(Integer userId);

    @Select("SELECT * FROM NOTES WHERE noteid=#{noteId}")
    Note getNoteById(Integer noteId);

    @Insert("INSERT INTO NOTES(notetitle,notedescription,userid) VALUES(#{noteTitle},#{noteDescription},#{userId})")
    Integer createNote(Note note);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription= #{noteDescription} WHERE noteid = #{noteId}")
    Integer updateNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid= #{noteId}")
    Integer deleteNote(Integer noteId);
}
