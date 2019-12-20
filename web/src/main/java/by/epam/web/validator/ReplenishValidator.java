package by.epam.web.validator;

import by.epam.web.constant.PatternConst;

public class ReplenishValidator {
    public static boolean validateReplenish(String enterValue) {
        return enterValue.matches(PatternConst.VALUE_PATTERN) && enterValue.length() <= 5 && enterValue.length() >= 1;    }
}
