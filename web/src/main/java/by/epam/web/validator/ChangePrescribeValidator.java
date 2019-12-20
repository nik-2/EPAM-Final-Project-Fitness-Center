package by.epam.web.validator;

import by.epam.web.constant.PatternConst;

public class ChangePrescribeValidator {
    public static boolean validatePrescribe(String enterPrescribe){
        return (enterPrescribe.matches(PatternConst.PRESCRIBE_PATTERN) && enterPrescribe.length() <= 600);
    }
}
