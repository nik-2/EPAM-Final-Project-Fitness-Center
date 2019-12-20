package by.epam.web.dao.factory;

import by.epam.web.dao.database.DataBaseUserDao;

public class DaoFactory {
    private final static DaoFactory INSTANCE = new DaoFactory();
    private final static DataBaseUserDao DATA_BASE_USER_DAO = new DataBaseUserDao();

    public static DaoFactory getInstance(){
        return INSTANCE;
    }

    public DataBaseUserDao getUserDao(){
        return DATA_BASE_USER_DAO;
    }
}
