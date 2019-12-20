package by.epam.web.validator;

import by.epam.web.constant.PatternConst;

public class RegistrationValidator {
    public static boolean validateRegistration(String enterMail, String enterPass, String enterRepeatPass, String enterName, String enterSurname) {
        return validateMail(enterMail) && validateName(enterName) && validateSurname(enterSurname) && validatePassword( enterPass) && validateRepeatPassword( enterRepeatPass);
    }

    private static boolean validateMail(String enterMail){
        return enterMail.matches(PatternConst.MAIL_PATTERN) && enterMail.length() <= 30;
    }

    private static boolean validateName(String enterName){
        return enterName.matches(PatternConst.NAME_SURNAME_PATTERN) && enterName.length() <= 20;
    }

    private static boolean validateSurname(String enterSurname){
        return enterSurname.matches(PatternConst.NAME_SURNAME_PATTERN) && enterSurname.length() <= 30;
    }

    private static boolean validatePassword(String enterPassword){
        return enterPassword.matches(PatternConst.PASSWORD_PATTERN) && enterPassword.length() > 3 && enterPassword.length() <= 15;
    }

    private static boolean validateRepeatPassword(String enterRepeatPassword){
        return enterRepeatPassword.matches(PatternConst.PASSWORD_PATTERN) && enterRepeatPassword.length() > 3 && enterRepeatPassword.length() <= 15;
    }
}
