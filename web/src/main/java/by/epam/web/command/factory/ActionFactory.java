package by.epam.web.command.factory;

import by.epam.web.command.base.ActionCommand;
import by.epam.web.command.base.EmptyCommand;
import by.epam.web.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Action factory.
 */
public class ActionFactory {
    private static final Logger logger = LogManager.getLogger(ActionFactory.class);

    /**
     * Define command action command.
     *
     * @param request the request
     * @return the action command
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        logger.debug("Start action command");
        ActionCommand current = null;
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return new EmptyCommand();
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.replaceAll("-","_").toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.error("Wrong action", e);
            request.setAttribute("Wrong action", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
