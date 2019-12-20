package by.epam.web.logic;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReplenishLogicTest {
    private int lessBalance;
    private int betterBalance;
    private int sum;

    @BeforeTest
    private void setData(){
        lessBalance = 5;
        betterBalance = 20;
        sum = 10;
    }

    @Test
    public void testCheckBalanceTrue() {
        Assert.assertTrue(ReplenishLogic.checkBalance(sum, betterBalance));
    }

    @Test
    public void testCheckBalanceFalse() {
        Assert.assertFalse(ReplenishLogic.checkBalance(sum, lessBalance));
    }
}