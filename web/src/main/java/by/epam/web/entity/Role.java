package by.epam.web.entity;

public enum Role {
    ADMIN("ADMIN"),
    COACH("COACH"),
    USER("USER"),
    GUEST("GUEST");

    private final String value;

    Role(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

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
