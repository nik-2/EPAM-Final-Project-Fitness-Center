package by.epam.web.logic;

public class ChangeSubscriptionLogic {
    public static boolean checkBalance(int cost, int balance) {
        return balance >= cost;
    }

    public static int findNewBalance(int cost, int balance){
        return (balance - cost);
    }
}
