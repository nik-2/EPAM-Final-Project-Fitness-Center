package by.epam.web.logic;

import by.epam.web.entity.User;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegistrationLogicTest {
    private User nullUser;
    private User user;
    private String password;
    private String otherPassword;

    @BeforeTest
    private void setData(){
        nullUser = null;
        user = new User();
        password = "3333";
        otherPassword = "3332";
    }

    @Test
    public void testCheckRegistrationMailTrue() {
        Assert.assertTrue(RegistrationLogic.checkRegistrationMail(nullUser));
    }

    @Test
    public void testCheckRegistrationMailFalse() {
        Assert.assertFalse(RegistrationLogic.checkRegistrationMail(user));
    }

    @Test
    public void testCheckRegistrationPasswordTrue() {
        Assert.assertTrue(RegistrationLogic.checkRegistrationPassword(password, password));
    }

    @Test
    public void testCheckRegistrationPasswordFalse() {
        Assert.assertFalse(RegistrationLogic.checkRegistrationPassword(password, otherPassword));
    }
}