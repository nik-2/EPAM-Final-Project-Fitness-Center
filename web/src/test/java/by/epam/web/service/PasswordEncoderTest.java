package by.epam.web.service;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class PasswordEncoderTest {
    private String password;
    private String encodePassword;
    private String currentEncodePassword;

    @BeforeTest
    private void setPassword(){
        password = "3333";
        currentEncodePassword = "4d7a4d7a4d773d3d";
        encodePassword = PasswordEncoder.encode(password);
    }

    @Test
    public void testEncode1() {
        assertNotEquals(encodePassword, password);
    }

    @Test
    public void testEncode2() {
        assertEquals(encodePassword, currentEncodePassword);
    }
}