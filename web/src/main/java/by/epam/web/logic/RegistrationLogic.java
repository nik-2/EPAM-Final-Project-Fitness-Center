package by.epam.web.logic;


import by.epam.web.entity.User;

public class RegistrationLogic {

    public static boolean checkRegistrationMail(User user) {
        return user == null;
    }

    public static boolean checkRegistrationPassword(String password1, String password2) {
        return password1.equals(password2);
    }
}
