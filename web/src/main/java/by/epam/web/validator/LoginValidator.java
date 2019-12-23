package by.epam.web.validator;

import by.epam.web.constant.PatternConst;

/**
 * The type Login validator.
 */
public class LoginValidator {
    /**
     * Validate login boolean.
     *
     * @param enterMail the enter mail
     * @param enterPass the enter pass
     * @return the boolean
     */
    public static boolean validateLogin(String enterMail, String enterPass) {
        return validateMail(enterMail) && validatePassword(enterPass);
    }

    private static boolean validateMail(String enterMail){
        return enterMail.matches(PatternConst.MAIL_PATTERN) && enterMail.length() <= 30;
    }

    private static boolean validatePassword(String enterPassword){
        return enterPassword.matches(PatternConst.PASSWORD_PATTERN) && enterPassword.length() > 3 && enterPassword.length() <= 15;
    }
}
