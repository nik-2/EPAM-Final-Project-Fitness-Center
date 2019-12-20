package by.epam.web.command.base;

import by.epam.web.constant.StringConst;
import by.epam.web.dao.database.DataBaseUserDao;
import by.epam.web.dao.factory.DaoFactory;
import by.epam.web.entity.Role;
import by.epam.web.entity.User;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;
import by.epam.web.logic.RegistrationLogic;
import by.epam.web.manager.ConfigurationManager;
import by.epam.web.manager.MessageManager;
import by.epam.web.router.Router;
import by.epam.web.sender.EmailSender;
import by.epam.web.service.ConfirmCodeGenerator;
import by.epam.web.service.PasswordEncoder;
import by.epam.web.validator.RegistrationValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start registration command");
        HttpSession session = request.getSession();
        Router router = new Router();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(!role.equals(Role.GUEST.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        String mail = request.getParameter(StringConst.PARAM_NAME_MAIL);
        String name = request.getParameter(StringConst.PARAM_NAME_NAME);
        String surname = request.getParameter(StringConst.PARAM_NAME_SURNAME);
        String pass = request.getParameter(StringConst.PARAM_NAME_PASSWORD1);
        String repeatPass = request.getParameter(StringConst.PARAM_NAME_PASSWORD_REPEAT);
        String encodePassword = PasswordEncoder.encode(pass);
        String confirmCode = ConfirmCodeGenerator.generateString();
        User user = null;

        router.setRoute(Router.RouteType.REDIRECT);

        try {
            user = dataBaseUserDao.findUserByMail(mail);
        } catch (DaoException | ConnectionPoolException e) {
            logger.error("Trouble with registration command", e);
            router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
        }
        if (RegistrationLogic.checkRegistrationMail(user) && RegistrationLogic.checkRegistrationPassword(pass, repeatPass) &&
                RegistrationValidator.validateRegistration(mail, pass, repeatPass, name, surname)) {
            try {
                dataBaseUserDao.createUser(mail, name, surname, encodePassword, confirmCode);
                session.setAttribute(StringConst.LOGIN, mail);
                EmailSender.sendMail(mail, StringConst.TEXT + confirmCode, StringConst.HEADER );
            } catch (DaoException | ConnectionPoolException e) {
                logger.error("Trouble with registration command", e);
                router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
            }
            router.setPagePath(ConfigurationManager.getProperty("path.page.start") + "?state=" + "blockUp" + "&stateCheck=" + "blockConfirm");
        } else {
            if (!RegistrationLogic.checkRegistrationMail(user)) {
                router.setPagePath(ConfigurationManager.getProperty("path.page.start") + "?errorRegistrationMessage=" + MessageManager.getProperty("message.regmailerror") + "&state=" + "blockUp");
                return router;
            }
            if (!RegistrationLogic.checkRegistrationPassword(pass, repeatPass)){
                router.setPagePath(ConfigurationManager.getProperty("path.page.start") + "?errorRegistrationMessage=" + MessageManager.getProperty("message.regpasserror") + "&state=" + "blockUp");
                return router;
            }
            else{
                router.setPagePath(ConfigurationManager.getProperty("path.page.start") + "?errorRegistrationMessage=" + MessageManager.getProperty("message.regdata") + "&state=" + "blockUp");
                return router;
            }
        }
        return router;
    }
}
