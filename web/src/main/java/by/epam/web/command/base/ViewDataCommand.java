package by.epam.web.command.base;

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

public class ViewDataCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ViewDataCommand.class);

    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start view data command");
        HttpSession session = request.getSession();
        String role = session.getAttribute(StringConst.ROLE).toString();
        session.setAttribute(StringConst.PARAM_NAME_CHANGE,StringConst.PARAM_NAME_VIEW);
        String mail = session.getAttribute(StringConst.LOGIN).toString();
        Router router = new Router();
        router.setRoute(Router.RouteType.FORWARD);
        if(role.equals(Role.GUEST.toString())){
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        if(role.equals(Role.USER.toString())){
            router.setPagePath(ConfigurationManager.getProperty("path.page.userData"));
        }
        if(role.equals(Role.COACH.toString())){
            router.setPagePath(ConfigurationManager.getProperty("path.page.coachData"));
        }
        if(role.equals(Role.ADMIN.toString())){
            router.setPagePath(ConfigurationManager.getProperty("path.page.adminData"));
        }
        User user = null;
        try {
            user = dataBaseUserDao.findUserByMail(mail);
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("Trouble with view data command", e);
            router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
        }
        if (user != null) {
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
        return router;
    }
}
