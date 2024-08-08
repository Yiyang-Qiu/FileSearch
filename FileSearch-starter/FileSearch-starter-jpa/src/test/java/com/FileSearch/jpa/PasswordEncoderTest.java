package com.FileSearch.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void passwordTest() {
        String str1 = passwordEncoder.encode("123");
        String str2 = passwordEncoder.encode("123");
        System.out.println(str1);
        System.out.println(str2);
        Assertions.assertNotEquals(str1, str2);
    }
}
