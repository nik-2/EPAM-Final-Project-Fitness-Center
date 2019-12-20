package by.epam.web.command.user;

import by.epam.web.command.base.ActionCommand;
import by.epam.web.constant.StringConst;
import by.epam.web.dao.database.DataBaseUserDao;
import by.epam.web.dao.factory.DaoFactory;
import by.epam.web.entity.Role;
import by.epam.web.entity.User;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;
import by.epam.web.logic.ReplenishLogic;
import by.epam.web.logic.TransferLogic;
import by.epam.web.manager.ConfigurationManager;
import by.epam.web.manager.MessageManager;
import by.epam.web.router.Router;
import by.epam.web.validator.ReplenishValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class ReplenishCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ReplenishCommand.class);
    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();
    private TransferLogic transferLogic = TransferLogic.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start replenish command");
        HttpSession session = request.getSession();
        Router router = new Router();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(!role.equals(Role.USER.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        String mail = session.getAttribute(StringConst.LOGIN).toString();
        String sumString = request.getParameter(StringConst.PARAM_NAME_VALUE);
        int sumInt = Integer.parseInt(sumString);
        int cardBalance = 0;
        router.setRoute(Router.RouteType.REDIRECT);
        boolean check = false;
        User user = null;
        try {
            user = dataBaseUserDao.findUserByMail(mail);
        } catch (DaoException | ConnectionPoolException e) {
            logger.error("Trouble with replenish command", e);
            router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
        }
        if (user != null) {
            cardBalance = user.getCardBalance();
        }
        if(!ReplenishLogic.checkBalance(sumInt, cardBalance)){
            router.setPagePath(ConfigurationManager.getProperty("path.page.viewSubscription") + "?moneyValueError=" + MessageManager.getProperty("message.transferValueError"));
        }
        else {
            if (ReplenishValidator.validateReplenish(sumString)) {
                try {
                    check = transferLogic.transfer(sumString, mail);
                } catch (SQLException e) {
                    logger.error("Trouble with replenish command", e);
                    router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
                }
                if (check) {
                    router.setPagePath(ConfigurationManager.getProperty("path.page.viewSubscription") + "?moneyValueError=" + MessageManager.getProperty("message.transferMessageSuccessful"));
                    try {
                        user = dataBaseUserDao.findUserByMail(mail);
                    } catch (DaoException | ConnectionPoolException e) {
                        logger.error("Trouble with replenish command", e);
                        router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
                    }
                    if (user != null) {
                        session.setAttribute(StringConst.PARAM_NAME_CLUB_BALANCE, user.getClubBalance());
                    }
                } else {
                    router.setPagePath(ConfigurationManager.getProperty("path.page.viewSubscription") + "?moneyValueError=" + MessageManager.getProperty("message.transferMessageError"));
                }
            } else {
                router.setPagePath(ConfigurationManager.getProperty("path.page.viewSubscription") + "?moneyValueError=" + MessageManager.getProperty("message.transferInvalid"));
            }
        }
        return router;
    }
}
