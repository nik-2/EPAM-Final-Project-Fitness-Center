package by.epam.web.command.base;

import by.epam.web.constant.StringConst;
import by.epam.web.dao.database.DataBaseUserDao;
import by.epam.web.dao.factory.DaoFactory;
import by.epam.web.entity.Role;
import by.epam.web.entity.User;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;
import by.epam.web.logic.LoginLogic;
import by.epam.web.manager.ConfigurationManager;
import by.epam.web.manager.MessageManager;
import by.epam.web.router.Router;
import by.epam.web.service.PasswordEncoder;
import by.epam.web.validator.LoginValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();


    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start login command");
        HttpSession session = request.getSession();
        Router router = new Router();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(!role.equals(Role.GUEST.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        String mail = request.getParameter(StringConst.PARAM_NAME_MAIL);
        String pass = request.getParameter(StringConst.PARAM_NAME_PASSWORD);
        String encodePassword = PasswordEncoder.encode(pass);
        String coach;
        User clientCoach = null;
        List<User> coaches = null;
        String message = null;
        User user = null;
        try {
            user = dataBaseUserDao.findUserByMail(mail);
        } catch (DaoException | ConnectionPoolException e) {
            logger.error("Trouble with login command", e);
            router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
        }
        router.setRoute(Router.RouteType.REDIRECT);
        if (LoginLogic.checkMail(user, encodePassword) && LoginValidator.validateLogin(mail, pass)
            && LoginLogic.checkBlock(user) && LoginLogic.checkConfirmed(user)) {
            session.setAttribute(StringConst.LOGIN, user.getMail());
            session.setAttribute(StringConst.ROLE, user.getRole());
            role = session.getAttribute(StringConst.ROLE).toString();
            if(role.equals(Role.ADMIN.toString())){
                router.setPagePath(ConfigurationManager.getProperty("path.page.adminProfile"));
            }
            if(role.equals(Role.USER.toString())){
                try {
                    coach = dataBaseUserDao.findClientCoach(mail);
                    user = dataBaseUserDao.findUserByMail(mail);
                    clientCoach = dataBaseUserDao.findUserByMail(coach);
                    coaches = dataBaseUserDao.findCoaches();
                } catch (ConnectionPoolException | DaoException e) {
                    logger.error("Trouble with login command", e);
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
                    if (clientCoach != null) {
                        session.setAttribute(StringConst.PARAM_NAME_COACH_NAME, clientCoach.getName());
                        session.setAttribute(StringConst.PARAM_NAME_COACH_SURNAME, clientCoach.getSurname());
                        session.setAttribute(StringConst.PARAM_NAME_COACH, clientCoach.getMail());
                    }
                }
                session.setAttribute(StringConst.PARAM_NAME_LIST_RESULT, coaches);
                router.setPagePath(ConfigurationManager.getProperty("path.page.userProfile"));
            }
            if(role.equals(Role.COACH.toString())){
                router.setPagePath(ConfigurationManager.getProperty("path.page.coachProfile"));
            }
        } else {
                if (user != null && !LoginLogic.checkConfirmed(user) && LoginLogic.checkMail(user, encodePassword) && LoginValidator.validateLogin(mail, pass)) {
                    message = "message.confirmederror";
                    session.setAttribute(StringConst.LOGIN, user.getMail());
                    router.setPagePath(ConfigurationManager.getProperty("path.page.start") + "?errorConfirmMessage=" + MessageManager.getProperty(message) + "&stateCheck=" + "blockConfirm");
                } else {
                    if (user != null && !LoginLogic.checkBlock(user)) {
                        message = "message.blockerror";
                    }
                    if (!LoginLogic.checkMail(user, encodePassword) || !LoginValidator.validateLogin(mail, pass)) {
                        message = "message.loginerror";
                    }
                    router.setPagePath(ConfigurationManager.getProperty("path.page.start") + "?errorLoginPassMessage=" + MessageManager.getProperty(message) + "&state=" + "block");
                }
        }
        return router;
    }
}
