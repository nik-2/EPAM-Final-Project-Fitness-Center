package by.epam.web.logic;

import by.epam.web.entity.Status;
import by.epam.web.entity.User;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginLogicTest {
    private User userTrue = new User();
    private User userFalse = new User();
    private User nullUser = null;
    private String password;
    private String otherPassword;

    @BeforeTest
    private void setData(){
        password = "3333";
        otherPassword = "3332";
        userFalse.setPassword(otherPassword);
        userFalse.setBlock(true);
        userFalse.setStatus(Status.UNCONFIRMED);
        userTrue.setPassword(password);
        userTrue.setBlock(false);
        userTrue.setStatus(Status.CONFIRMED);
    }

    @Test
    public void testCheckMailTrue() {
        Assert.assertTrue(LoginLogic.checkMail(userTrue, password));
    }

    @Test
    public void testCheckMailFalse1() {
        Assert.assertFalse(LoginLogic.checkMail(nullUser, password));
    }

    @Test
    public void testCheckMailFalse2() {
        Assert.assertFalse(LoginLogic.checkMail(userTrue, otherPassword));
    }

    @Test
    public void testCheckBlockTrue() {
        Assert.assertTrue(LoginLogic.checkBlock(userTrue));
    }

    @Test
    public void testCheckBlockFalse() {
        Assert.assertFalse(LoginLogic.checkBlock(userFalse));
    }

    @Test
    public void testCheckConfirmedTrue() {
        Assert.assertTrue(LoginLogic.checkConfirmed(userTrue));
    }

    @Test
    public void testCheckConfirmedFalse() {
        Assert.assertFalse(LoginLogic.checkConfirmed(userFalse));
    }
}