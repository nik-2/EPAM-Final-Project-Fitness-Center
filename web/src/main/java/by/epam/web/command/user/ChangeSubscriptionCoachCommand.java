package by.epam.web.command.user;

import by.epam.web.command.base.ActionCommand;
import by.epam.web.constant.StringConst;
import by.epam.web.dao.database.DataBaseUserDao;
import by.epam.web.dao.factory.DaoFactory;
import by.epam.web.entity.Role;
import by.epam.web.entity.Subscription;
import by.epam.web.entity.User;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;
import by.epam.web.logic.ChangeSubscriptionLogic;
import by.epam.web.logic.TransferLogic;
import by.epam.web.manager.ConfigurationManager;
import by.epam.web.manager.MessageManager;
import by.epam.web.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * The type Change subscription coach command.
 */
public class ChangeSubscriptionCoachCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ChangeSubscriptionCoachCommand.class);
    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();
    private TransferLogic transferLogic = TransferLogic.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start change subscription coach command");
        HttpSession session = request.getSession();
        Router router = new Router();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(!role.equals(Role.USER.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        String subscription = session.getAttribute(StringConst.PARAM_NAME_SUBSCRIPTION).toString();
        String cost = request.getParameter(StringConst.PARAM_NAME_COST);
        String selectSubscription = request.getParameter(StringConst.PARAM_NAME_SELECT_SUB);
        String duration = request.getParameter(StringConst.PARAM_NAME_DURATION);
        String coachMail = request.getParameter(StringConst.PARAM_NAME_SELECT_MAIL);
        String mail = session.getAttribute(StringConst.LOGIN).toString();
        String balance = session.getAttribute(StringConst.PARAM_NAME_CLUB_BALANCE).toString();
        boolean check = false;
        User user = null;
        User userCoach = null;
        router.setRoute(Router.RouteType.REDIRECT);
        router.setPagePath(ConfigurationManager.getProperty("path.page.viewSubscription"));
        if (subscription.equalsIgnoreCase(String.valueOf(Subscription.NONE))) {
            if (!ChangeSubscriptionLogic.checkBalance(Integer.parseInt(cost), Integer.parseInt(balance))) {
                router.setPagePath(ConfigurationManager.getProperty("path.page.viewSubscription") + "?moneyValueError=" + MessageManager.getProperty("message.transferValueError"));
                return router;
            } else {
                try {
                    check = transferLogic.changeSubscription(selectSubscription, cost, duration, mail, coachMail);
                } catch (SQLException e) {
                    logger.error("Trouble with change subscription coach command", e);
                    router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
                }
                if(!check){
                    router.setPagePath(ConfigurationManager.getProperty("path.page.viewSubscription") + "?moneyValueError=" + MessageManager.getProperty("message.changeSubscriptionError"));
                    return router;
                }
                int newBalance = ChangeSubscriptionLogic.findNewBalance(Integer.parseInt(cost), Integer.parseInt(balance));
                session.setAttribute(StringConst.PARAM_NAME_CLUB_BALANCE, newBalance);
                session.setAttribute(StringConst.PARAM_NAME_SUBSCRIPTION, selectSubscription);
                session.setAttribute(StringConst.PARAM_NAME_COACH, coachMail);
                try {
                    user = dataBaseUserDao.findUserByMail(mail);
                    userCoach = dataBaseUserDao.findUserByMail(coachMail);
                } catch (DaoException | ConnectionPoolException e) {
                    logger.error("Trouble with change subscription coach command", e);
                    router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
                }
                if (userCoach != null) {
                    session.setAttribute(StringConst.PARAM_NAME_COACH_NAME,userCoach.getName());
                    session.setAttribute(StringConst.PARAM_NAME_COACH_SURNAME,userCoach.getSurname());
                    session.setAttribute(StringConst.PARAM_NAME_SUBSCRIPTION_END_DATA, user.getDateSub());
                }
            }
        }
        if(!subscription.equalsIgnoreCase(String.valueOf(Subscription.NONE))) {
            String coach = session.getAttribute(StringConst.PARAM_NAME_COACH).toString();
            if (coach.equalsIgnoreCase("")) {
                try {
                    String newCoachMail = request.getParameter(StringConst.PARAM_NAME_SELECT_COACH);
                    dataBaseUserDao.updateUserCoach(mail, newCoachMail);
                    userCoach = dataBaseUserDao.findUserByMail(newCoachMail);
                    user = dataBaseUserDao.findUserByMail(mail);
                    session.setAttribute(StringConst.PARAM_NAME_COACH_NAME,userCoach.getName());
                    session.setAttribute(StringConst.PARAM_NAME_COACH_SURNAME,userCoach.getSurname());
                    session.setAttribute(StringConst.PARAM_NAME_COACH, newCoachMail);
                    session.setAttribute(StringConst.PARAM_NAME_SUBSCRIPTION_END_DATA, user.getDateSub());
                } catch (DaoException | ConnectionPoolException e) {
                    logger.error("Trouble with change subscription coach command", e);
                    router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
                }

            }
        }
        return router;
    }
}
