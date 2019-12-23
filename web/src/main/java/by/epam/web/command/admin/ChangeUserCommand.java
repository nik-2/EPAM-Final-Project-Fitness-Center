package by.epam.web.command.admin;

import by.epam.web.command.base.ActionCommand;
import by.epam.web.constant.StringConst;
import by.epam.web.dao.database.DataBaseUserDao;
import by.epam.web.dao.factory.DaoFactory;
import by.epam.web.entity.Role;
import by.epam.web.entity.User;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;
import by.epam.web.manager.ConfigurationManager;
import by.epam.web.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Change user command.
 */
public class ChangeUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ChangeUserCommand.class);

    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start change user command");
        HttpSession session = request.getSession();
        Router router = new Router();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(!role.equals(Role.ADMIN.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        String change = session.getAttribute(StringConst.PARAM_NAME_CHANGE).toString();
        String selectMail = request.getParameter(StringConst.PARAM_NAME_SELECT_MAIL);
        String selectRole = request.getParameter(StringConst.PARAM_NAME_SELECT_ROLE);
        router.setRoute(Router.RouteType.REDIRECT);
        String mail = session.getAttribute(StringConst.LOGIN).toString();
        router.setPagePath(ConfigurationManager.getProperty("path.page.changeUsers"));
        List<User> users = null;
        try {
            dataBaseUserDao.changeUser(selectMail, selectRole, change);
            users = dataBaseUserDao.findUsers(change, mail);
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("Trouble with change user command", e);
            router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
        }
        session.setAttribute(StringConst.PARAM_NAME_LIST_RESULT, users);
        return router;
    }
}
