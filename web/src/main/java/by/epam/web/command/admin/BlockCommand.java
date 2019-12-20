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

public class BlockCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(BlockCommand.class);

    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start block command");
        HttpSession session = request.getSession();
        Router router = new Router();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(!role.equals(Role.ADMIN.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        session.setAttribute(StringConst.PARAM_NAME_CHANGE,StringConst.PARAM_NAME_BLOCK);
        String mail = session.getAttribute(StringConst.LOGIN).toString();
        String change = session.getAttribute(StringConst.PARAM_NAME_CHANGE).toString();
        router.setRoute(Router.RouteType.FORWARD);
        router.setPagePath(ConfigurationManager.getProperty("path.page.changeUsers"));
        List<User> users = null;
        try {
            users = dataBaseUserDao.findUsers(change, mail);
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("Trouble with block command", e);
            router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
        }
        session.setAttribute(StringConst.PARAM_NAME_LIST_RESULT, users);
        return router;
    }
}
