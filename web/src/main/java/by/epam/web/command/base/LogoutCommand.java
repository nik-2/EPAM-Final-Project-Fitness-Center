package by.epam.web.command.base;

import by.epam.web.manager.ConfigurationManager;
import by.epam.web.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Logout command.
 */
public class LogoutCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start logout command");
        HttpSession session = request.getSession();
        Router router = new Router();
        router.setRoute(Router.RouteType.FORWARD);
        router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
        session.invalidate();
        return router;
    }
}
