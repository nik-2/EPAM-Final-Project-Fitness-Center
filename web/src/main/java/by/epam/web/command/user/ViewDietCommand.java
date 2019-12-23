package by.epam.web.command.user;

import by.epam.web.command.base.ActionCommand;
import by.epam.web.constant.StringConst;
import by.epam.web.dao.database.DataBaseUserDao;
import by.epam.web.dao.factory.DaoFactory;
import by.epam.web.entity.Role;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;
import by.epam.web.manager.ConfigurationManager;
import by.epam.web.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type View diet command.
 */
public class ViewDietCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ViewDietCommand.class);
    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start view diet command");
        HttpSession session = request.getSession();
        String diet;
        String mail = session.getAttribute(StringConst.LOGIN).toString();
        String role = session.getAttribute(StringConst.ROLE).toString();
        Router router = new Router();
        if(!role.equals(Role.USER.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        router.setRoute(Router.RouteType.FORWARD);
        router.setPagePath(ConfigurationManager.getProperty("path.page.viewDiet"));
        try {
            diet = dataBaseUserDao.findUserDiet(mail);
            session.setAttribute(StringConst.PARAM_NAME_DIET, diet);
        } catch (DaoException | ConnectionPoolException e) {
            logger.error("Trouble with view diet command", e);
            router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
        }
        return router;
    }
}
