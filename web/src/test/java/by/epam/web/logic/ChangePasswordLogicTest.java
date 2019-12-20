package by.epam.web.logic;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChangePasswordLogicTest {
    private String password;
    private String otherPassword;

    @BeforeTest
    private void setPassword(){
        password = "3333";
        otherPassword = "3332";
    }

    @Test
    public void testCheckPasswordTrue() {
        Assert.assertTrue(ChangePasswordLogic.checkPassword(password, password));
    }

    @Test
    public void testCheckPasswordFalse() {
        Assert.assertFalse(ChangePasswordLogic.checkPassword(password, otherPassword));
    }
}