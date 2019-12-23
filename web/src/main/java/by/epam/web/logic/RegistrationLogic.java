package by.epam.web.logic;


import by.epam.web.entity.User;

/**
 * The type Registration logic.
 */
public class RegistrationLogic {

    /**
     * Check registration mail boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public static boolean checkRegistrationMail(User user) {
        return user == null;
    }

    /**
     * Check registration password boolean.
     *
     * @param password1 the password 1
     * @param password2 the password 2
     * @return the boolean
     */
    public static boolean checkRegistrationPassword(String password1, String password2) {
        return password1.equals(password2);
    }
}
