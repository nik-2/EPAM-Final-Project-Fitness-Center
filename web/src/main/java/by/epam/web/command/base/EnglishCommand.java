package by.epam.web.command.base;

import by.epam.web.constant.StringConst;
import by.epam.web.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EnglishCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(EnglishCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start english command");
        Router router = new Router();
        request.setAttribute(StringConst.LANGUAGE,StringConst.EN);
        router.setRoute(Router.RouteType.FORWARD);
        router.setPagePath(request.getParameter(StringConst.PAGE));
        return router;
    }
}
