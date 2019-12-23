package by.epam.web.command.base;

import by.epam.web.constant.StringConst;
import by.epam.web.dao.database.DataBaseUserDao;
import by.epam.web.dao.factory.DaoFactory;
import by.epam.web.entity.Role;
import by.epam.web.entity.User;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;
import by.epam.web.manager.ConfigurationManager;
import by.epam.web.manager.MessageManager;
import by.epam.web.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Reg confirm command.
 */
public class RegConfirmCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(RegConfirmCommand.class);

    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start registration confirm command");
        HttpSession session = request.getSession();
        Router router = new Router();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(!role.equals(Role.GUEST.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        String code = request.getParameter(StringConst.PARAM_NAME_CONFIRM);
        String mail = (String) session.getAttribute(StringConst.LOGIN);
        List<User> coaches = null;
        User user = null;
        try {
            user = dataBaseUserDao.findUserByMail(mail);
        } catch (DaoException | ConnectionPoolException e) {
            logger.error("Trouble with registration confirm command", e);
            router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
        }
        router.setRoute(Router.RouteType.REDIRECT);
        if(user == null){
            router.setPagePath(ConfigurationManager.getProperty("path.page.start") + "?errorRegistrationMessage=" + MessageManager.getProperty("message.confirmtimeout") + "&state=" + "blockUp");
            session.setAttribute(StringConst.LOGIN, "");
        }else {
            if(code.equals(user.getCode())){
                session.setAttribute(StringConst.ROLE, user.getRole());
                try {
                    dataBaseUserDao.updateUnconfirmedUser(mail);
                } catch (DaoException | ConnectionPoolException e) {
                    logger.error("Trouble with registration confirm command", e);
                    router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
                }
                role = session.getAttribute(StringConst.ROLE).toString();
                if(role.equals(Role.ADMIN.toString())){
                    router.setPagePath(ConfigurationManager.getProperty("path.page.adminProfile"));
                }
                if(role.equals(Role.USER.toString())){
                    try {
                        user = dataBaseUserDao.findUserByMail(mail);
                        coaches = dataBaseUserDao.findCoaches();
                    } catch (ConnectionPoolException | DaoException e) {
                        logger.error("Trouble with registration confirm command", e);
                        router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
                    }
                    if (user != null) {
                        session.setAttribute(StringConst.PARAM_NAME_CLUB_BALANCE, user.getClubBalance());
                        session.setAttribute(StringConst.PARAM_NAME_CARD_ID, user.getBankCardId());
                        session.setAttribute(StringConst.PARAM_NAME_SUBSCRIPTION, user.getSubscription());
                        session.setAttribute(StringConst.PARAM_NAME_MAIL_DATA, user.getMail());
                        session.setAttribute(StringConst.PARAM_NAME_NAME_DATA, user.getName());
                        session.setAttribute(StringConst.PARAM_NAME_SURNAME_DATA, user.getSurname());
                        session.setAttribute(StringConst.PARAM_NAME_ROLE_DATA, user.getRole());
                        session.setAttribute(StringConst.PARAM_NAME_DATE_OF_REG_DATA, user.getDateReg());
                        session.setAttribute(StringConst.PARAM_NAME_BANK_CARD_DATA, user.getBankCardId());
                        session.setAttribute(StringConst.PARAM_NAME_CLUB_BALANCE_DATA, user.getClubBalance());
                        session.setAttribute(StringConst.PARAM_NAME_SUBSCRIPTION_DATA, user.getSubscription());
                        session.setAttribute(StringConst.PARAM_NAME_SUBSCRIPTION_END_DATA, user.getDateSub());
                    }
                    session.setAttribute(StringConst.PARAM_NAME_LIST_RESULT, coaches);
                    router.setPagePath(ConfigurationManager.getProperty("path.page.userProfile"));
                }
                if(role.equals(Role.COACH.toString())){
                    router.setPagePath(ConfigurationManager.getProperty("path.page.coachProfile"));
                }
            }else{
                String message = MessageManager.getProperty("message.confirmMessageError");
                router.setPagePath(ConfigurationManager.getProperty("path.page.start") + "?stateCheck=" + "blockConfirm" + "&errorConfirmMessage=" + message);
            }
        }
        return router;
    }
}
