package by.epam.web.validator;

import by.epam.web.constant.PatternConst;

/**
 * The type Change password validator.
 */
public class ChangePasswordValidator {
    /**
     * Validate password boolean.
     *
     * @param enterPassword          the enter password
     * @param enterNewPassword       the enter new password
     * @param enterNewPasswordRepeat the enter new password repeat
     * @return the boolean
     */
    public static boolean validatePassword(String enterPassword, String enterNewPassword, String enterNewPasswordRepeat){
        return (enterPassword.matches(PatternConst.PASSWORD_PATTERN) && enterPassword.length() > 3 && enterPassword.length() <= 15)
                && (enterNewPassword.matches(PatternConst.PASSWORD_PATTERN) && enterNewPassword.length() > 3 && enterNewPassword.length() <= 15)
                && (enterNewPasswordRepeat.matches(PatternConst.PASSWORD_PATTERN) && enterNewPasswordRepeat.length() > 3 && enterNewPasswordRepeat.length() <= 15);
    }
}
