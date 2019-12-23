package by.epam.web.dao.factory;

import by.epam.web.dao.database.DataBaseUserDao;

/**
 * The type Dao factory.
 */
public class DaoFactory {
    private final static DaoFactory INSTANCE = new DaoFactory();
    private final static DataBaseUserDao DATA_BASE_USER_DAO = new DataBaseUserDao();

    /**
     * Get instance dao factory.
     *
     * @return the dao factory
     */
    public static DaoFactory getInstance(){
        return INSTANCE;
    }

    /**
     * Get user dao data base user dao.
     *
     * @return the data base user dao
     */
    public DataBaseUserDao getUserDao(){
        return DATA_BASE_USER_DAO;
    }
}
