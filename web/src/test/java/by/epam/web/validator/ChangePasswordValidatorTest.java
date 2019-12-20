package by.epam.web.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChangePasswordValidatorTest {
    private String validPass;
    private String invalidPass;

    @BeforeTest
    private void setPass(){
        validPass = "3333";
        invalidPass = "3333.";
    }

    @Test
    public void testValidatePasswordTrue() {
        Assert.assertTrue(ChangePasswordValidator.validatePassword(validPass, validPass, validPass));
    }

    @Test
    public void testValidatePasswordFalse1() {
        Assert.assertFalse(ChangePasswordValidator.validatePassword(invalidPass, validPass, validPass));
    }

    @Test
    public void testValidatePasswordFalse2() {
        Assert.assertFalse(ChangePasswordValidator.validatePassword(validPass, invalidPass, validPass));
    }

    @Test
    public void testValidatePasswordFalse3() {
        Assert.assertFalse(ChangePasswordValidator.validatePassword(validPass, validPass, invalidPass));
    }
}