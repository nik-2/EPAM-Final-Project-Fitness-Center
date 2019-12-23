package by.epam.web.command.base;

import by.epam.web.constant.StringConst;
import by.epam.web.dao.database.DataBaseUserDao;
import by.epam.web.dao.factory.DaoFactory;
import by.epam.web.entity.Role;
import by.epam.web.entity.User;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;
import by.epam.web.logic.ChangePasswordLogic;
import by.epam.web.manager.ConfigurationManager;
import by.epam.web.manager.MessageManager;
import by.epam.web.router.Router;
import by.epam.web.service.PasswordEncoder;
import by.epam.web.validator.ChangePasswordValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Change password command.
 */
public class ChangePasswordCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start change password command");
        HttpSession session = request.getSession();
        Router router = new Router();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(role.equals(Role.GUEST.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        String mail = session.getAttribute(StringConst.LOGIN).toString();
        String pass = request.getParameter(StringConst.PARAM_NAME_PASSWORD);
        String newPass = request.getParameter(StringConst.PARAM_NAME_PASSWORD1);
        String repeatNewPass = request.getParameter(StringConst.PARAM_NAME_PASSWORD_REPEAT);
        String encodePassword = PasswordEncoder.encode(pass);
        String encodeNewPass = PasswordEncoder.encode(newPass);
        router.setRoute(Router.RouteType.REDIRECT);
        User user = new User();
        try {
            user = dataBaseUserDao.findUserByMail(mail);
        } catch (DaoException | ConnectionPoolException e) {
            logger.error("Trouble with change password command", e);
            router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
        }
        if (ChangePasswordLogic.checkRealPass(user.getPassword(), encodePassword) && ChangePasswordLogic.checkPassword(newPass, repeatNewPass) &&
                ChangePasswordValidator.validatePassword(pass, newPass, repeatNewPass)) {
            try {
                dataBaseUserDao.updateUserPassword(mail, encodeNewPass);
            } catch (DaoException | ConnectionPoolException e) {
                logger.error("Trouble with change password command", e);
                router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
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
            if (!ChangePasswordLogic.checkRealPass(user.getPassword(), encodePassword)) {
                if(role.equals(Role.USER.toString())){
                    router.setPagePath(ConfigurationManager.getProperty("path.page.userData") + "?errorChangePassword=" + MessageManager.getProperty("message.changepassworderror") + "&stateCheck=" + "block");
                }
                if(role.equals(Role.COACH.toString())){
                    router.setPagePath(ConfigurationManager.getProperty("path.page.coachData") + "?errorChangePassword=" + MessageManager.getProperty("message.changepassworderror") + "&stateCheck=" + "block");
                }
                if(role.equals(Role.ADMIN.toString())){
                    router.setPagePath(ConfigurationManager.getProperty("path.page.adminData") + "?errorChangePassword=" + MessageManager.getProperty("message.changepassworderror") + "&stateCheck=" + "block");
                }
                return router;
            }
            if (!ChangePasswordLogic.checkPassword(newPass, repeatNewPass)){
                if(role.equals(Role.USER.toString())){
                    router.setPagePath(ConfigurationManager.getProperty("path.page.userData") + "?errorChangePassword=" + MessageManager.getProperty("message.changepasscompareerror") + "&stateCheck=" + "block");
                }
                if(role.equals(Role.COACH.toString())){
                    router.setPagePath(ConfigurationManager.getProperty("path.page.coachData") + "?errorChangePassword=" + MessageManager.getProperty("message.changepasscompareerror") + "&stateCheck=" + "block");
                }
                if(role.equals(Role.ADMIN.toString())){
                    router.setPagePath(ConfigurationManager.getProperty("path.page.adminData") + "?errorChangePassword=" + MessageManager.getProperty("message.changepasscompareerror") + "&stateCheck=" + "block");
                }
                return router;
            }
            else{
                if(role.equals(Role.USER.toString())){
                    router.setPagePath(ConfigurationManager.getProperty("path.page.userData") + "?errorChangePassword=" + MessageManager.getProperty("message.changeincorrect") + "&stateCheck=" + "block");
                }
                if(role.equals(Role.COACH.toString())){
                    router.setPagePath(ConfigurationManager.getProperty("path.page.coachData") + "?errorChangePassword=" + MessageManager.getProperty("message.changeincorrect") + "&stateCheck=" + "block");
                }
                if(role.equals(Role.ADMIN.toString())){
                    router.setPagePath(ConfigurationManager.getProperty("path.page.adminData") + "?errorChangePassword=" + MessageManager.getProperty("message.changeincorrect") + "&stateCheck=" + "block");
                }
                return router;
            }
        }
        return router;
    }
}
