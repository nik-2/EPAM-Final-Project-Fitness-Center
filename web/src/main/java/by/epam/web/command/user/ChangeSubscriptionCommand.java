package by.epam.web.command.user;

import by.epam.web.command.base.ActionCommand;
import by.epam.web.constant.StringConst;
import by.epam.web.dao.database.DataBaseUserDao;
import by.epam.web.dao.factory.DaoFactory;
import by.epam.web.entity.Role;
import by.epam.web.entity.User;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;
import by.epam.web.logic.ChangeSubscriptionLogic;
import by.epam.web.manager.ConfigurationManager;
import by.epam.web.manager.MessageManager;
import by.epam.web.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Change subscription command.
 */
public class ChangeSubscriptionCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ChangeSubscriptionCommand.class);
    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start change subscription command");
        HttpSession session = request.getSession();
        Router router = new Router();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(!role.equals(Role.USER.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        String cost = request.getParameter(StringConst.PARAM_NAME_COST);
        String selectSubscription = request.getParameter(StringConst.PARAM_NAME_SELECT_SUB);
        String duration = request.getParameter(StringConst.PARAM_NAME_DURATION);
        String mail = session.getAttribute(StringConst.LOGIN).toString();
        String balance = session.getAttribute(StringConst.PARAM_NAME_CLUB_BALANCE).toString();
        User user;
        router.setRoute(Router.RouteType.REDIRECT);
        router.setPagePath(ConfigurationManager.getProperty("path.page.viewSubscription"));
        if (!ChangeSubscriptionLogic.checkBalance(Integer.parseInt(cost), Integer.parseInt(balance))) {
            router.setPagePath(ConfigurationManager.getProperty("path.page.viewSubscription") + "?moneyValueError=" + MessageManager.getProperty("message.transferValueError"));
            return router;
        } else {
            int newBalance = ChangeSubscriptionLogic.findNewBalance(Integer.parseInt(cost), Integer.parseInt(balance));
            try {
                dataBaseUserDao.updateUserSubscription(selectSubscription, duration, String.valueOf(newBalance), mail);
                session.setAttribute(StringConst.PARAM_NAME_CLUB_BALANCE, newBalance);
                session.setAttribute(StringConst.PARAM_NAME_SUBSCRIPTION, selectSubscription);
                user = dataBaseUserDao.findUserByMail(mail);
                session.setAttribute(StringConst.PARAM_NAME_SUBSCRIPTION_END_DATA, user.getDateSub());
            } catch (DaoException | ConnectionPoolException e) {
                logger.error("Trouble with change subscription command", e);
                router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
            }
        }
        return router;
    }
}

