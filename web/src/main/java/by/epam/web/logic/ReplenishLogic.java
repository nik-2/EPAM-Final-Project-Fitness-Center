package by.epam.web.logic;

/**
 * The type Replenish logic.
 */
public class ReplenishLogic {
    /**
     * Check balance boolean.
     *
     * @param sum     the sum
     * @param balance the balance
     * @return the boolean
     */
    public static boolean checkBalance(int sum, int balance) {
        return balance >= sum;
    }
}
