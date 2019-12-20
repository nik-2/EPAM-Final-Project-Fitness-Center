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
import by.epam.web.manager.MessageManager;
import by.epam.web.router.Router;
import by.epam.web.validator.ChangePrescribeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChangeUserDietCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ChangeUserDietCommand.class);

    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("Start change user diet command");
        HttpSession session = request.getSession();
        Router router = new Router();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(role.equals(Role.ADMIN.toString()) || role.equals(Role.GUEST.toString())){
            router.setRoute(Router.RouteType.FORWARD);
            router.setPagePath(ConfigurationManager.getProperty("path.page.start"));
            return router;
        }
        String mail;
        String newDiet = request.getParameter(StringConst.PARAM_NAME_DIET);
        String diet;
        router.setRoute(Router.RouteType.REDIRECT);
        if(role.equals(Role.COACH.toString())) {
            String change = StringConst.PARAM_NAME_PRESCRIBE_DIET;
            mail = request.getParameter(StringConst.PARAM_NAME_CLIENT_MAIL);
            if(ChangePrescribeValidator.validatePrescribe(newDiet)) {
                try {
                    dataBaseUserDao.updateUserDiet(mail, newDiet);
                } catch (DaoException | ConnectionPoolException e) {
                    logger.error("Trouble with change user diet command", e);
                    router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
                }
                router.setPagePath(ConfigurationManager.getProperty("path.page.prescribeDiet") + "?infoMess=" + MessageManager.getProperty("message.successfully"));
            }else{
                router.setPagePath(ConfigurationManager.getProperty("path.page.prescribeDiet") + "?errorMess=" + MessageManager.getProperty("message.invalidPrescribe"));
            }
            mail = session.getAttribute(StringConst.LOGIN).toString();
            List<User> users = null;
            try {
                users = dataBaseUserDao.findUsers(change, mail);
            } catch (ConnectionPoolException | DaoException e) {
                logger.error("Trouble with change user diet command", e);
                router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
            }
            session.setAttribute(StringConst.PARAM_NAME_LIST_RESULT, users);
        }
        if(role.equals(Role.USER.toString())) {
            mail = session.getAttribute(StringConst.LOGIN).toString();
            if(ChangePrescribeValidator.validatePrescribe(newDiet)) {
                router.setPagePath(ConfigurationManager.getProperty("path.page.viewDiet"));
                try {
                    dataBaseUserDao.updateUserDiet(mail, newDiet);
                    diet = dataBaseUserDao.findUserDiet(mail);
                    session.setAttribute(StringConst.PARAM_NAME_DIET, diet);
                } catch (DaoException | ConnectionPoolException e) {
                    logger.error("Trouble with change user diet command", e);
                    router.setPagePath(ConfigurationManager.getProperty("path.page.error") + "?message=" + e.getMessage());
                }
            }
            else {
                router.setPagePath(ConfigurationManager.getProperty("path.page.viewDiet") + "?errorMess=" + MessageManager.getProperty("message.invalidPrescribe"));
            }
        }
        return router;
    }
}
