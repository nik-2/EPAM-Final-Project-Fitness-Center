package by.epam.web.logic;

import by.epam.web.entity.Status;
import by.epam.web.entity.User;

/**
 * The type Login logic.
 */
public class LoginLogic {

    /**
     * Check mail boolean.
     *
     * @param user      the user
     * @param enterPass the enter pass
     * @return the boolean
     */
    public static boolean checkMail(User user, String enterPass) {
        if (user != null) {
            String userPassword = user.getPassword();
            return userPassword.equals(enterPass);
        } else {
            return false;
        }
    }

    /**
     * Check block boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public static boolean checkBlock(User user) {
            return !user.isBlock();
    }

    /**
     * Check confirmed boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public static boolean checkConfirmed(User user) {
        return user.getStatus() == Status.CONFIRMED;
    }

}

