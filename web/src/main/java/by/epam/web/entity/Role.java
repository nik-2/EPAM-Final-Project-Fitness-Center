package by.epam.web.entity;

/**
 * The enum Role.
 */
public enum Role {
    /**
     * Admin role.
     */
    ADMIN("ADMIN"),
    /**
     * Coach role.
     */
    COACH("COACH"),
    /**
     * User role.
     */
    USER("USER"),
    /**
     * Guest role.
     */
    GUEST("GUEST");

    private final String value;

    Role(String value){
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * From value role.
     *
     * @param val the val
     * @return the role
     */
    public static Role fromValue(String val) {
        for (Role role: Role.values()) {
            if (role.value.equals(val)) {
                return role;
            }
        }
        throw new IllegalArgumentException(val);
    }

    @Override
    public String toString() {
        return getValue();
    }
}
