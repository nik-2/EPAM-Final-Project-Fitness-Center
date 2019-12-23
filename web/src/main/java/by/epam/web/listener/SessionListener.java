package by.epam.web.listener;

import by.epam.web.constant.StringConst;
import by.epam.web.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Enumeration;

/**
 * The type Session listener.
 */
public class SessionListener implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger(SessionListener.class);


    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("Initialize session listener");
        HttpSession session = httpSessionEvent.getSession();
        session.setAttribute(StringConst.LOGIN, "");
        session.setAttribute(StringConst.ROLE, Role.GUEST);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.info("Destroy session listener");
        HttpSession session = httpSessionEvent.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            String attribute = attributeNames.nextElement();
            logger.trace("Remove attribute: " + attribute);
            session.removeAttribute(attribute);
        }
    }
}
