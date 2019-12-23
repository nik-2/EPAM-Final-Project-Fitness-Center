package by.epam.web.validator;

import by.epam.web.constant.PatternConst;

/**
 * The type Change user data validator.
 */
public class ChangeUserDataValidator {
    /**
     * Validate data boolean.
     *
     * @param enterName     the enter name
     * @param enterSurname  the enter surname
     * @param enterBankCard the enter bank card
     * @return the boolean
     */
    public static boolean validateData(String enterName, String enterSurname, String enterBankCard) {
        return validateName(enterName) && validateSurname(enterSurname) && validateBankCard(enterBankCard);
    }

    private static boolean validateName(String enterName){
        return enterName.matches(PatternConst.NAME_SURNAME_PATTERN) && enterName.length() <= 20;
    }

    private static boolean validateSurname(String enterSurname){
        return enterSurname.matches(PatternConst.NAME_SURNAME_PATTERN) && enterSurname.length() <= 30;
    }

    private static boolean validateBankCard(String enterBankCard){
        return ((enterBankCard.matches(PatternConst.BANK_CARD_PATTERN) && (enterBankCard.length() == 19)) || enterBankCard.equals(""));
    }
}
