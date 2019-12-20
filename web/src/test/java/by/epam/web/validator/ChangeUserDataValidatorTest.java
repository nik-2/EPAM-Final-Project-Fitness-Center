package by.epam.web.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChangeUserDataValidatorTest {
    private String validName;
    private String invalidName;
    private String validSurname;
    private String invalidSurname;
    private String validBankCard;
    private String invalidBankCard;

    @BeforeTest
    private void setData(){
        validName = "Nikita";
        invalidName = "nikita";
        validSurname = "Polyakov";
        invalidSurname = "polyakov";
        validBankCard = "3333_3333_3333_3333";
        invalidBankCard = "3333_j333_3333_3333";
    }

    @Test
    public void testValidateDataTrue() {
        Assert.assertTrue(ChangeUserDataValidator.validateData(validName, validSurname, validBankCard));
    }

    @Test
    public void testValidateDataFalse1() {
        Assert.assertFalse(ChangeUserDataValidator.validateData(invalidName, validSurname, validBankCard));
    }

    @Test
    public void testValidateDataFalse2() {
        Assert.assertFalse(ChangeUserDataValidator.validateData(validName, invalidSurname, validBankCard));
    }

    @Test
    public void testValidateDataFalse3() {
        Assert.assertFalse(ChangeUserDataValidator.validateData(validName, validSurname, invalidBankCard));
    }
}