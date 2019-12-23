package by.epam.web.logic;

/**
 * The type Change password logic.
 */
public class ChangePasswordLogic {
    /**
     * Check password boolean.
     *
     * @param password1 the password 1
     * @param password2 the password 2
     * @return the boolean
     */
    public static boolean checkPassword(String password1, String password2) {
        return password1.equals(password2);
    }

    /**
     * Check real pass boolean.
     *
     * @param realPass  the real pass
     * @param enterPass the enter pass
     * @return the boolean
     */
    public static boolean checkRealPass(String realPass, String enterPass){
        return realPass.equals(enterPass);
    }
}
