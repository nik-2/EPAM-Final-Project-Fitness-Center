package by.epam.web.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class ConfirmCodeGenerator {
    private static final Logger logger = LogManager.getLogger(ConfirmCodeGenerator.class);

    public static String generateString() {
        logger.debug("Start generate code");
        int length = 4;
        String characters = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOASDFGHJKLZXCVBNM";
        Random rnd = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rnd.nextInt(characters.length()));
        }
        return new String(text);
    }
}
