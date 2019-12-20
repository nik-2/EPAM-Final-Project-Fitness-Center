package by.epam.web.command.base;

import by.epam.web.manager.ConfigurationManager;
import by.epam.web.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(EmptyCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start empty command");
        Router router = new Router();
        router.setRoute(Router.RouteType.FORWARD);
        router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
        return router;
    }
}

