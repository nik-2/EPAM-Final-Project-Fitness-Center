package by.epam.web.command.base;

import by.epam.web.constant.StringConst;
import by.epam.web.entity.Role;
import by.epam.web.manager.ConfigurationManager;
import by.epam.web.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Profile command.
 */
public class ProfileCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ProfileCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start profile command");
        HttpSession session = request.getSession();
        Router router = new Router();
        router.setRoute(Router.RouteType.FORWARD);
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(role.equals(Role.GUEST.toString())){
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
        }
        if(role.equals(Role.ADMIN.toString())){
            router.setPagePath(ConfigurationManager.getProperty("path.page.adminProfile"));
        }
        if(role.equals(Role.USER.toString())){
            router.setPagePath(ConfigurationManager.getProperty("path.page.userProfile"));
        }
        if(role.equals(Role.COACH.toString())){
            router.setPagePath(ConfigurationManager.getProperty("path.page.coachProfile"));
        }
        return router;
    }
}
