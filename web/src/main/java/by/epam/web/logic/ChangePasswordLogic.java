package by.epam.web.logic;

public class ChangePasswordLogic {
    public static boolean checkPassword(String password1, String password2) {
        return password1.equals(password2);
    }

    public static boolean checkRealPass(String realPass, String enterPass){
        return realPass.equals(enterPass);
    }
}
