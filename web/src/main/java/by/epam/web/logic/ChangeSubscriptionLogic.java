package by.epam.web.logic;

/**
 * The type Change subscription logic.
 */
public class ChangeSubscriptionLogic {
    /**
     * Check balance boolean.
     *
     * @param cost    the cost
     * @param balance the balance
     * @return the boolean
     */
    public static boolean checkBalance(int cost, int balance) {
        return balance >= cost;
    }

    /**
     * Find new balance int.
     *
     * @param cost    the cost
     * @param balance the balance
     * @return the int
     */
    public static int findNewBalance(int cost, int balance){
        return (balance - cost);
    }
}
