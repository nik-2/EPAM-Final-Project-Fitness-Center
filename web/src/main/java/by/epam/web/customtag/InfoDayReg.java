package by.epam.web.customtag;

import by.epam.web.dao.database.DataBaseUserDao;
import by.epam.web.dao.factory.DaoFactory;
import by.epam.web.entity.User;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class InfoDayReg extends SimpleTagSupport {
    private static final Logger logger = LogManager.getLogger(InfoDayReg.class);

    private DataBaseUserDao dataBaseUserDao = DaoFactory.getInstance().getUserDao();
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void doTag() throws IOException {
        User user;
        String amountDay = null;
        JspWriter out = getJspContext().getOut();
        try {
            logger.debug("Do my infoDayReg tag");
            user = dataBaseUserDao.findUserByMail(name);
            amountDay = dataBaseUserDao.findNumberDayReg(user.getDateReg());
        } catch (DaoException | ConnectionPoolException e) {
            logger.error("Trouble with do InfoDayReg tag", e);
            e.printStackTrace();
        }
        out.print(amountDay);
    }
}
