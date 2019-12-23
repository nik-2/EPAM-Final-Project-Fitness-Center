package by.epam.web.validator;

import by.epam.web.constant.PatternConst;

/**
 * The type Replenish validator.
 */
public class ReplenishValidator {
    /**
     * Validate replenish boolean.
     *
     * @param enterValue the enter value
     * @return the boolean
     */
    public static boolean validateReplenish(String enterValue) {
        return enterValue.matches(PatternConst.VALUE_PATTERN) && enterValue.length() <= 5 && enterValue.length() >= 1;    }
}
