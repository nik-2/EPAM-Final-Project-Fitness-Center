package by.epam.web.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChangePrescribeValidatorTest {
    private String validPrescribe;
    private String invalidPrescribe;

    @BeforeTest
    private void setPrescribe(){
        validPrescribe = "Понедельник - завтрак:";
        invalidPrescribe = "Понедельник%";
    }

    @Test
    public void testValidatePrescribeTrue() {
        Assert.assertTrue(ChangePrescribeValidator.validatePrescribe(validPrescribe));
    }

    @Test
    public void testValidatePrescribeFalse() {
        Assert.assertFalse(ChangePrescribeValidator.validatePrescribe(invalidPrescribe));
    }
}