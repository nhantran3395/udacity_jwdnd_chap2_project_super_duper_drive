package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FileUpload;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES")
    List<FileUpload> getFiles();

    @Select("SELECT * FROM FILES WHERE userid=#{userId}")
    List<FileUpload> getFilesByUserId(Integer userId);

    @Select("SELECT * FROM FILES WHERE filename=#{filename} AND userid=#{userId}")
    FileUpload getFilesByNameAndUserId(String filename, Integer userId);

    @Select("SELECT * FROM FILES WHERE fileid=#{fileId}")
    FileUpload getFileById(Integer fileId);

    @Insert("INSERT INTO FILES(filename,contenttype,filesize,userid) VALUES (#{fileName},#{contentType},#{fileSize},#{userId})")
    Integer createFile(FileUpload file);

    @Delete("DELETE FROM FILES WHERE filename=#{filename} AND userid=#{userId}")
    Integer deleteFileByNameAndUserId(String filename,Integer userId);
}
