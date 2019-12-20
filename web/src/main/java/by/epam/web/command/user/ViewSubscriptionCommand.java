package by.epam.web.command.user;

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

public class ViewSubscriptionCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ViewSubscriptionCommand.class);
    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start view subscription command");
        String coach = null;
        User clientCoach = null;
        User user = null;
        HttpSession session = request.getSession();
        String mail = session.getAttribute(StringConst.LOGIN).toString();
        String role = session.getAttribute(StringConst.ROLE).toString();
        Router router = new Router();
        if(!role.equals(Role.USER.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        router.setRoute(Router.RouteType.FORWARD);
        router.setPagePath(ConfigurationManager.getProperty("path.page.viewSubscription"));
        List<User> coaches = null;
        try {
            coach = dataBaseUserDao.findClientCoach(mail);
            user = dataBaseUserDao.findUserByMail(mail);
            clientCoach = dataBaseUserDao.findUserByMail(coach);
            coaches = dataBaseUserDao.findCoaches();
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("Trouble with view subscription command", e);
            router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
        }
        if (user != null) {
            session.setAttribute(StringConst.PARAM_NAME_CLUB_BALANCE, user.getClubBalance());
            session.setAttribute(StringConst.PARAM_NAME_CARD_ID, user.getBankCardId());
            session.setAttribute(StringConst.PARAM_NAME_SUBSCRIPTION, user.getSubscription());
            session.setAttribute(StringConst.PARAM_NAME_COACH, coach);
            session.setAttribute(StringConst.PARAM_NAME_SUBSCRIPTION_END_DATA, user.getDateSub());
            if (clientCoach != null) {
                session.setAttribute(StringConst.PARAM_NAME_COACH_NAME, clientCoach.getName());
                session.setAttribute(StringConst.PARAM_NAME_COACH_SURNAME, clientCoach.getSurname());
                session.setAttribute(StringConst.PARAM_NAME_COACH, clientCoach.getMail());
            }
        }
        session.setAttribute(StringConst.PARAM_NAME_LIST_RESULT, coaches);
        return router;
    }
}

