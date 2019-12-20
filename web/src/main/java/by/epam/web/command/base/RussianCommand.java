package by.epam.web.command.base;

import by.epam.web.constant.StringConst;
import by.epam.web.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RussianCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(RussianCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start russian command");
        Router router = new Router();
        router.setRoute(Router.RouteType.FORWARD);
        request.setAttribute(StringConst.LANGUAGE,StringConst.RU);
        router.setPagePath(request.getParameter(StringConst.PAGE));
        return router;
    }
}
