package by.epam.web.validator;

import by.epam.web.constant.PatternConst;

public class LoginValidator {
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
