package by.epam.web.command.user;

import by.epam.web.command.base.ActionCommand;
import by.epam.web.constant.StringConst;
import by.epam.web.entity.Role;
import by.epam.web.manager.ConfigurationManager;
import by.epam.web.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Link card command.
 */
public class LinkCardCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(LinkCardCommand.class);


    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start link card command");
        HttpSession session = request.getSession();
        String role = session.getAttribute(StringConst.ROLE).toString();
        request.setAttribute("changeBankCard","changeBankCard()");
        Router router = new Router();
        if(!role.equals(Role.USER.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        router.setRoute(Router.RouteType.FORWARD);
        router.setPagePath(ConfigurationManager.getProperty("path.page.userData"));
        return router;
    }
}
