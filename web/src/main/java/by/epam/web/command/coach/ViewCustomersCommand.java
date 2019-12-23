package by.epam.web.command.coach;

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
 * The type View customers command.
 */
public class ViewCustomersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ViewCustomersCommand.class);

    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start view customers command");
        HttpSession session = request.getSession();
        String change = request.getParameter(StringConst.PARAM_NAME_CHANGE.toLowerCase());
        String mail = session.getAttribute(StringConst.LOGIN).toString();
        Router router = new Router();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(!role.equals(Role.COACH.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        router.setRoute(Router.RouteType.FORWARD);
        if(change.equals(StringConst.PARAM_NAME_VIEW_CUSTOMERS)) {
            router.setPagePath(ConfigurationManager.getProperty("path.page.viewClients"));
        }
        if(change.equals(StringConst.PARAM_NAME_PRESCRIBE_DIET)) {
            router.setPagePath(ConfigurationManager.getProperty("path.page.prescribeDiet"));
        }
        if(change.equals(StringConst.PARAM_NAME_PRESCRIBE_EXERCISE)) {
            router.setPagePath(ConfigurationManager.getProperty("path.page.prescribeExercise"));
        }
        List<User> users = null;
        try {
            users = dataBaseUserDao.findUsers(change, mail);
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("Trouble with view customers command", e);
            router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
        }
        session.setAttribute(StringConst.PARAM_NAME_LIST_RESULT, users);
        return router;
    }
}
