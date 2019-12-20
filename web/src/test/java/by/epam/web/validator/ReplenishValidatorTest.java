package by.epam.web.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReplenishValidatorTest {
    private String validValue;
    private String invalidValue;

    @BeforeTest
    private void setValue(){
        validValue = "33";
        invalidValue = "033";
    }

    @Test
    public void testValidateReplenishTrue() {
        Assert.assertTrue(ReplenishValidator.validateReplenish(validValue));
    }

    @Test
    public void testValidateReplenishFalse() {
        Assert.assertFalse(ReplenishValidator.validateReplenish(invalidValue));
    }
}