package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> getCredentials();

    @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userId}")
    List<Credential> getCredentialsByUserId(Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid=#{credentialId}")
    Credential getCredentialById(Integer credentialId);

    @Insert("INSERT INTO CREDENTIALS(url,username,key,password,userid) VALUES(#{url},#{username},#{key},#{password},#{userId})")
    Integer createCredential(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password}, userid = #{userId} WHERE credentialid = #{credentialId}")
    Integer updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid= #{credentialId}")
    Integer deleteCredential(Integer credentialId);
}

