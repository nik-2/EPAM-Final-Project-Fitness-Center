package by.epam.web.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginValidatorTest {
    private String validPass;
    private String invalidPass;
    private String validMail;
    private String invalidMail;

    @BeforeTest
    private void setData() {
        validPass = "3333";
        invalidPass = "333";
        validMail = "nikitapv47@gmail.com";
        invalidMail = "nikitapv47gmail.com";
    }

    @Test
    public void testValidateLoginTrue() {
        Assert.assertTrue(LoginValidator.validateLogin(validMail, validPass));
    }

    @Test
    public void testValidateLoginFalse1() {
        Assert.assertFalse(LoginValidator.validateLogin(invalidMail, validPass));
    }

    @Test
    public void testValidateLoginFalse2() {
        Assert.assertFalse(LoginValidator.validateLogin(validMail, invalidPass));
    }
}