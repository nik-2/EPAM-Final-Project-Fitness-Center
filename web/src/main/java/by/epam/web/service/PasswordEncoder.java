package by.epam.web.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.Base64;

/**
 * The type Password encoder.
 */
public class PasswordEncoder {
    private static final Logger logger = LogManager.getLogger(PasswordEncoder.class);

    /**
     * Encode string.
     *
     * @param password the password
     * @return the string
     */
    public static String encode(String password){
        logger.debug("Start encode password");
        byte[] bytes;
        Base64.Encoder encoder = Base64.getEncoder();
        bytes = encoder.encode(password.getBytes());
        BigInteger bigInteger = new BigInteger(1, bytes);
        return bigInteger.toString(16);
    }
}
