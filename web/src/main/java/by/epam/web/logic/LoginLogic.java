package by.epam.web.logic;

import by.epam.web.entity.Status;
import by.epam.web.entity.User;

public class LoginLogic {

    public static boolean checkMail(User user, String enterPass) {
        if (user != null) {
            String userPassword = user.getPassword();
            return userPassword.equals(enterPass);
        } else {
            return false;
        }
    }

    public static boolean checkBlock(User user) {
            return !user.isBlock();
    }

    public static boolean checkConfirmed(User user) {
        return user.getStatus() == Status.CONFIRMED;
    }

}

