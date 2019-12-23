package by.epam.web.constant;

/**
 * The type Pattern const.
 */
public class PatternConst {
    /**
     * The constant MAIL_PATTERN.
     */
    public static final String MAIL_PATTERN = "[a-z\\d_\\.]+@[a-z]+\\.[a-z]{2,4}$";
    /**
     * The constant PASSWORD_PATTERN.
     */
    public static final String PASSWORD_PATTERN = "[A-Za-z_\\d]{4,15}";
    /**
     * The constant NAME_SURNAME_PATTERN.
     */
    public static final String NAME_SURNAME_PATTERN = "[A-Z][a-z]+(-[A-Z][a-z]+)?";
    /**
     * The constant BANK_CARD_PATTERN.
     */
    public static final String BANK_CARD_PATTERN = "\\d{4}_\\d{4}_\\d{4}_\\d{4}";
    /**
     * The constant VALUE_PATTERN.
     */
    public static final String VALUE_PATTERN = "[1-9]\\d{0,4}";
    /**
     * The constant PRESCRIBE_PATTERN.
     */
    public static final String PRESCRIBE_PATTERN = "^[\\dA-Za-zА-Яа-я\\s,.!?;:()-]*$";
    /**
     * The constant CODE_PATTERN.
     */
    public static final String CODE_PATTERN = "[A-Za-z\\d]{4}";
}
