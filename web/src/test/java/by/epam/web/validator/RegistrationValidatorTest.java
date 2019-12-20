package by.epam.web.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegistrationValidatorTest {
    private String validMail;
    private String invalidMail;
    private String validPass;
    private String invalidPass;
    private String validName;
    private String invalidName;
    private String validSurname;
    private String invalidSurname;

    @BeforeTest
    private void setData(){
        validMail = "nikitapv47@gmail.com";
        invalidMail = "nikitapv47gmail.com";
        validPass = "3333";
        invalidPass = "333";
        validName = "Nikita";
        invalidName = "nikita";
        validSurname = "Polyakov";
        invalidSurname = "polyakov";
    }

    @Test
    public void testValidateRegistrationTrue() {
        Assert.assertTrue(RegistrationValidator.validateRegistration(validMail,validPass,validPass,validName,validSurname));
    }

    @Test
    public void testValidateRegistrationFalse1() {
        Assert.assertFalse(RegistrationValidator.validateRegistration(invalidMail,validPass,validPass,validName,validSurname));
    }

    @Test
    public void testValidateRegistrationFalse2() {
        Assert.assertFalse(RegistrationValidator.validateRegistration(validMail,invalidPass,validPass,validName,validSurname));
    }

    @Test
    public void testValidateRegistrationFalse3() {
        Assert.assertFalse(RegistrationValidator.validateRegistration(validMail,validPass,invalidPass,validName,validSurname));
    }

    @Test
    public void testValidateRegistrationFalse4() {
        Assert.assertFalse(RegistrationValidator.validateRegistration(validMail,validPass,validPass,invalidName,validSurname));
    }

    @Test
    public void testValidateRegistrationFalse5() {
        Assert.assertFalse(RegistrationValidator.validateRegistration(validMail,validPass,validPass,validName,invalidSurname));
    }
}