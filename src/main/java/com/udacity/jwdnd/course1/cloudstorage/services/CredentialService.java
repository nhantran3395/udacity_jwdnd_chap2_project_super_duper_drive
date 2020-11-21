package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper,EncryptionService encryptionService){
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public Integer createCredential(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        return credentialMapper.createCredential(new Credential(null,credential.getUrl(),credential.getUsername(),encodedKey,encryptedPassword,credential.getUserId()));
    }

    public List<Credential> getCredentials(){
        return credentialMapper.getCredentials();
    }

    public List<Credential> getCredentialsByUserId(Integer userId){
        return credentialMapper.getCredentialsByUserId(userId);
    }

    public Credential getCredentialByid(Integer credentialId){
        return credentialMapper.getCredentialById(credentialId);
    }

    public String getDecryptedPasswordOfCredential(Integer credentialId){
        Credential credential = credentialMapper.getCredentialById(credentialId);

        return encryptionService.decryptValue(credential.getPassword(),credential.getKey());
    }

    public Credential updateCredential(Credential credential) {
        String encodedKey = credentialMapper.getCredentialById(credential.getCredentialId()).getKey();
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

        Integer numUpdatedRecord = credentialMapper.updateCredential(new Credential(credential.getCredentialId(),credential.getUrl(),credential.getUsername(),encodedKey,encryptedPassword,credential.getUserId()));

        if(numUpdatedRecord == 1) {
            return credentialMapper.getCredentialById(credential.getCredentialId());
        }

        return null;
    }

    public void deleteCredential(Integer credentialId){
        credentialMapper.deleteCredential(credentialId);
    }
}
