package by.epam.web.logic;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChangeSubscriptionLogicTest {
    private int lessCost;
    private int betterCost;
    private int balance;

    @BeforeTest
    private void setDta(){
        lessCost = 5;
        betterCost = 50;
        balance = 10;
    }

    @Test
    public void testCheckBalanceTrue() {
        Assert.assertTrue(ChangeSubscriptionLogic.checkBalance(lessCost, balance));
    }

    @Test
    public void testCheckBalanceFalse() {
        Assert.assertFalse(ChangeSubscriptionLogic.checkBalance(betterCost, balance));
    }

    @Test
    public void testFindNewBalance() {
        int result = 5;
        Assert.assertEquals(result, ChangeSubscriptionLogic.findNewBalance(lessCost, balance));
    }
}