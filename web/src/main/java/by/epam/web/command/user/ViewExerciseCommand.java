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
 * The type View exercise command.
 */
public class ViewExerciseCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ViewExerciseCommand.class);
    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start view exercise command");
        HttpSession session = request.getSession();
        String exercise;
        String mail = session.getAttribute(StringConst.LOGIN).toString();
        String role = session.getAttribute(StringConst.ROLE).toString();
        Router router = new Router();
        if(!role.equals(Role.USER.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        router.setRoute(Router.RouteType.FORWARD);
        router.setPagePath(ConfigurationManager.getProperty("path.page.viewExercise"));
        try {
            exercise = dataBaseUserDao.findUserExercise(mail);
            session.setAttribute(StringConst.PARAM_NAME_EXERCISE, exercise);
        } catch (DaoException | ConnectionPoolException e) {
            logger.error("Trouble with view exercise command", e);
            router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
        }
        return router;
    }
}
