package by.epam.web.sender;

import by.epam.web.constant.StringConst;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {
    private static final Logger logger = LogManager.getLogger(EmailSender.class);

    public static void sendMail(String to, String text, String header) {
        final Properties properties = new Properties();
        try {
            logger.debug("Take properties for email sender");
            properties.load(EmailSender.class.getClassLoader().getResourceAsStream(StringConst.PROPERTY_PATH));
            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(StringConst.FROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(header);
            message.setText(text);
            Transport transport = mailSession.getTransport();
            transport.connect(null, StringConst.PASSWORD);
            logger.debug("Send message");
            transport.sendMessage(message, message.getAllRecipients());
            logger.debug("Close transport for email sender");
            transport.close();
        } catch (MessagingException | IOException e) {
            logger.error("Trouble with send message", e);
            e.printStackTrace();
        }
    }
}
