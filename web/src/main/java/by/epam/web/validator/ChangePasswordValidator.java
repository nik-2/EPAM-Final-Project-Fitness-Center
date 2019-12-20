package by.epam.web.validator;

import by.epam.web.constant.PatternConst;

public class ChangePasswordValidator {
    public static boolean validatePassword(String enterPassword, String enterNewPassword, String enterNewPasswordRepeat){
        return (enterPassword.matches(PatternConst.PASSWORD_PATTERN) && enterPassword.length() > 3 && enterPassword.length() <= 15)
                && (enterNewPassword.matches(PatternConst.PASSWORD_PATTERN) && enterNewPassword.length() > 3 && enterNewPassword.length() <= 15)
                && (enterNewPasswordRepeat.matches(PatternConst.PASSWORD_PATTERN) && enterNewPasswordRepeat.length() > 3 && enterNewPasswordRepeat.length() <= 15);
    }
}
