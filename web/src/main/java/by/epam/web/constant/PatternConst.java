package by.epam.web.constant;

public class PatternConst {
    public static final String MAIL_PATTERN = "[a-z\\d_\\.]+@[a-z]+\\.[a-z]{2,4}$";
    public static final String PASSWORD_PATTERN = "[A-Za-z_\\d]{4,15}";
    public static final String NAME_SURNAME_PATTERN = "[A-Z][a-z]+(-[A-Z][a-z]+)?";
    public static final String BANK_CARD_PATTERN = "\\d{4}_\\d{4}_\\d{4}_\\d{4}";
    public static final String VALUE_PATTERN = "[1-9]\\d{0,4}";
    public static final String PRESCRIBE_PATTERN = "^[\\dA-Za-zА-Яа-я\\s,.!?;:()-]*$";
    public static final String CODE_PATTERN = "[A-Za-z\\d]{4}";
}
