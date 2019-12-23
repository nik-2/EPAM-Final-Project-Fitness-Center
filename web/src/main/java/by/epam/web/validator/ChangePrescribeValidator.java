package by.epam.web.validator;

import by.epam.web.constant.PatternConst;

/**
 * The type Change prescribe validator.
 */
public class ChangePrescribeValidator {
    /**
     * Validate prescribe boolean.
     *
     * @param enterPrescribe the enter prescribe
     * @return the boolean
     */
    public static boolean validatePrescribe(String enterPrescribe){
        return (enterPrescribe.matches(PatternConst.PRESCRIBE_PATTERN) && enterPrescribe.length() <= 600);
    }
}
