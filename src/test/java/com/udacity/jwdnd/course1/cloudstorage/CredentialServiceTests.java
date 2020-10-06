package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@Import({CredentialService.class,EncryptionService.class})
public class CredentialServiceTests {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private EncryptionService encryptionService;

    @Test
    public void testCredentialServiceCreateCredential(){
        Credential credential = new Credential(null,"https://brse-sharing.nhanthanhtran.com/","nhanthanhtran030395@gmail.com",null,"demoUserPassword#012",1);

        Integer credentialId = credentialService.createCredential(credential);

        assertNotNull(credentialId);

        Credential credentialAfterCreate = credentialService.getCredentialByid(credentialId);

        assertEquals(credential.getUrl(),credentialAfterCreate.getUrl());
        assertEquals(credential.getUsername(),credentialAfterCreate.getUsername());
        assertEquals(credential.getPassword(),encryptionService.decryptValue(credentialAfterCreate.getPassword(),credentialAfterCreate.getKey()));
    }

    @Test
    public void testCredentialServiceUpdateCredential(){
        Credential credential = new Credential(null,"https://brse-sharing.nhanthanhtran.com/","nhanthanhtran030395@gmail.com",null,"demoUserPassword#012",1);

        Integer credentialId = credentialService.createCredential(credential);

        assertNotNull(credentialId);

        Credential credentialAfterCreate = credentialService.getCredentialByid(credentialId);

        Credential credentialUpdate = new Credential(credentialId,"https://test.com/","testuser@test.com",credentialAfterCreate.getKey(),"testPassword",1);

        Credential credentialAfterUpdate = credentialService.updateCredential(credentialUpdate);

        assertNotNull(credentialAfterUpdate);
    }

}
