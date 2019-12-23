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
import by.epam.web.validator.ChangeUserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Change user data command.
 */
public class ChangeUserDataCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ChangeUserDataCommand.class);

    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start change user data command");
        HttpSession session = request.getSession();
        Router router = new Router();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(role.equals(Role.GUEST.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        String mail = session.getAttribute(StringConst.LOGIN).toString();
        String newName = request.getParameter(StringConst.PARAM_NAME_NAME);
        String newSurname = request.getParameter(StringConst.PARAM_NAME_SURNAME);
        String newBankCard = request.getParameter(StringConst.PARAM_NAME_BANK_CARD);
        router.setRoute(Router.RouteType.REDIRECT);
        User user = null;

        if (ChangeUserDataValidator.validateData(newName, newSurname, newBankCard)) {
            try {
                dataBaseUserDao.updateUserData(newName, newSurname, newBankCard, mail);
                user = dataBaseUserDao.findUserByMail(mail);
            } catch (DaoException | ConnectionPoolException e) {
                logger.error("Trouble with change user data command", e);
                router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
            }
            if (user != null) {
                session.setAttribute(StringConst.PARAM_NAME_NAME_DATA, user.getName());
                session.setAttribute(StringConst.PARAM_NAME_SURNAME_DATA, user.getSurname());
                session.setAttribute(StringConst.PARAM_NAME_BANK_CARD_DATA, user.getBankCardId());
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
        } else {
            if(role.equals(Role.USER.toString())){
                router.setPagePath(ConfigurationManager.getProperty("path.page.userData") + "?errorChangeData=" + MessageManager.getProperty("message.changeincorrect"));
            }
            if(role.equals(Role.COACH.toString())){
                router.setPagePath(ConfigurationManager.getProperty("path.page.coachData") + "?errorChangeData=" + MessageManager.getProperty("message.changeincorrect"));
            }
            if(role.equals(Role.ADMIN.toString())){
                router.setPagePath(ConfigurationManager.getProperty("path.page.adminData") + "?errorChangeData=" + MessageManager.getProperty("message.changeincorrect"));
            }
            return router;
        }
        return router;
    }

}

