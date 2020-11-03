package com.udacity.jwdnd.course1.cloudstorage.Unit.Service;

import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HashServiceTests {
    @Test
    public void testGetHashedPassword(){
        HashService hashService = new HashService();

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        String password = "ThisIsPassword";

        String hashedPassword = hashService.getHashedValue(password,encodedSalt);
        assertEquals(hashedPassword,hashService.getHashedValue(password,encodedSalt));
    }
}
