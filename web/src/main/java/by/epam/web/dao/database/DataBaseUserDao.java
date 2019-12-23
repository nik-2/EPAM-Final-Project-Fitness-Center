package by.epam.web.dao.database;

import by.epam.web.connectionpool.ConnectionPool;
import by.epam.web.connectionpool.ProxyConnection;
import by.epam.web.constant.SqlConst;
import by.epam.web.constant.StringConst;
import by.epam.web.dao.AbstractUserDao;
import by.epam.web.entity.Role;
import by.epam.web.entity.Status;
import by.epam.web.entity.Subscription;
import by.epam.web.entity.User;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;
import by.epam.web.service.DateHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Data base user dao.
 */
public class DataBaseUserDao extends AbstractUserDao {
    private static final Logger logger = LogManager.getLogger(DataBaseUserDao.class);

    @Override
    public User findUserByMail(String mail) throws DaoException, ConnectionPoolException {
        logger.trace("Looking for user with mail= " + mail);
        User user = null;
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ResultSet resultSet;
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.FIND_USER_BY_MAIL_SQL);
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                user.setBankCardId(resultSet.getString(5));
                user.setClubBalance(resultSet.getInt(6));
                user.setCardBalance(resultSet.getInt(7));
                user.setBlock(resultSet.getBoolean(8));
                user.setRole(Role.valueOf(resultSet.getString(9)));
                user.setStatus(Status.valueOf(resultSet.getString(10)));
                user.setDateReg(resultSet.getString(11));
                user.setCode(resultSet.getString(12));
                user.setSubscription(Subscription.valueOf(resultSet.getString(13)));
                user.setDateSub(resultSet.getString(14));
            }
        } catch (SQLException e) {
            logger.error("Trouble with find user by mail", e);
            throw new DaoException("Trouble with find user by mail", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
        return user;
    }

    @Override
    public String findClientCoach(String mail) throws DaoException, ConnectionPoolException {
           logger.trace("Looking for user's coach with user mail= " + mail);
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ResultSet resultSet;
        String coach = "";
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.FIND_CLIENT_COACH_BY_MAIL_SQL);
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                coach = resultSet.getString(1);
            }
        } catch (SQLException e) {
            logger.error("Trouble with find client's coach", e);
            throw new DaoException("Trouble with find client's coach", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
        return coach;
    }

    @Override
    public String findUserDiet(String mail) throws DaoException, ConnectionPoolException {
        logger.trace("Looking for user's diet with mail= " + mail);
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ResultSet resultSet;
        String diet = "";
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.FIND_USER_DIET);
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                diet = resultSet.getString(1);
            }
        } catch (SQLException e) {
            logger.error("Trouble with find client's diet", e);
            throw new DaoException("Trouble with find client's diet", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
        return diet;
    }

    @Override
    public String findUserExercise(String mail) throws DaoException, ConnectionPoolException {
        logger.trace("Looking for user's exercise with mail= " + mail);
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ResultSet resultSet;
        String exercise = "";
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.FIND_USER_EXERCISE);
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exercise = resultSet.getString(1);
            }
        } catch (SQLException e) {
            logger.error("Trouble with find client's exercise", e);
            throw new DaoException("Trouble with find client's exercise", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
        return exercise;
    }

    @Override
    public String findNumberDayReg(String dayReg) throws DaoException, ConnectionPoolException {
        logger.debug("looking for amount day after registration");
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ResultSet resultSet;
        String numberDay = null;
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.FIND_NUMBER_DAY_REG);
            preparedStatement.setString(1, dayReg);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberDay = resultSet.getString(1);
            }
        } catch (SQLException e) {
            logger.error("Trouble with find amount day after registration", e);
            throw new DaoException("Trouble with find amount day after registration", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
        return numberDay;
    }


    @Override
    public void createUser(String mail, String name, String surname, String password, String confirmCode) throws DaoException, ConnectionPoolException {
        logger.info("Creating new user with mail = " + mail);
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.ADD_USER_SQL);
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, DateHolder.findCurrentDate());
            preparedStatement.setString(6, confirmCode);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't add new user", e);
            throw new DaoException("Can't add new user", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void updateUnconfirmedUser(String mail) throws DaoException, ConnectionPoolException {
        logger.debug("Start update unconfirmed user");
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.UPDATE_UNCONFIRMED_USER);
            preparedStatement.setString(1, mail);
            preparedStatement.execute();
            preparedStatement = proxyConnection.prepareStatement(SqlConst.INSERT_CLIENT_DIET);
            preparedStatement.setString(1,mail);
            preparedStatement.execute();
            preparedStatement = proxyConnection.prepareStatement(SqlConst.INSERT_CLIENT_EXERCISE);
            preparedStatement.setString(1,mail);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't update user", e);
            throw new DaoException("Can't update user", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void updateUserCoach(String mail, String coachMail) throws DaoException, ConnectionPoolException {
        logger.debug("Start update user's coach");
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.UPDATE_USER_COACH);
            preparedStatement.setString(1, coachMail);
            preparedStatement.setString(2, mail);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't update user's coach", e);
            throw new DaoException("Can't update user's coach", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void updateUserSubscription(String subscription, String duration, String newClubBalance, String mail) throws DaoException, ConnectionPoolException {
        logger.debug("Start update user subscription");
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.UPDATE_USER_SUBSCRIPTION);
            preparedStatement.setString(1, subscription);
            preparedStatement.setString(2, duration);
            preparedStatement.setString(3, newClubBalance);
            preparedStatement.setString(4, mail);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't update user's subscription", e);
            throw new DaoException("Can't update user's subscription", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<User> findCoaches() throws ConnectionPoolException, DaoException {
        logger.debug("Start find coaches");
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.FIND_COACHES);
            ResultSet resultSet = null;
            if (preparedStatement != null) {
                resultSet = preparedStatement.executeQuery();
            }
            if (resultSet != null) {
                while (resultSet.next()) {
                    User user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                    user.setRole(Role.valueOf(resultSet.getString(9)));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.error("Can't find coaches", e);
            throw new DaoException("Can't find coaches", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
        return users;
    }

    @Override
    public List<User> findUsers(String change, String mail) throws ConnectionPoolException, DaoException {
        logger.debug("Start find users");
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            if (change.equals(StringConst.PARAM_NAME_BLOCK)) {
                preparedStatement = proxyConnection.prepareStatement(SqlConst.FIND_ALL_UNBLOCK_USER_WITHOUT_ME);
                preparedStatement.setString(1, mail);
            }
            if (change.equals(StringConst.PARAM_NAME_UNBLOCK)) {
                preparedStatement = proxyConnection.prepareStatement(SqlConst.FIND_ALL_BLOCK_USER);
            }
            if (change.equals(StringConst.PARAM_NAME_ROLE)) {
                preparedStatement = proxyConnection.prepareStatement(SqlConst.FIND_ALL_USER_WITHOUT_ME);
                preparedStatement.setString(1, mail);
            }
            if (change.equals(StringConst.PARAM_NAME_VIEW)) {
                preparedStatement = proxyConnection.prepareStatement(SqlConst.FIND_ALL_USER);
            }
            if(change.equals(StringConst.PARAM_NAME_VIEW_CUSTOMERS) || change.equals(StringConst.PARAM_NAME_PRESCRIBE_DIET)
                || change.equals(StringConst.PARAM_NAME_PRESCRIBE_EXERCISE)){
                preparedStatement = proxyConnection.prepareStatement(SqlConst.SELECT_COACH_CLIENT);
                preparedStatement.setString(1, mail);
            }
            ResultSet resultSet = null;
            if (preparedStatement != null) {
                resultSet = preparedStatement.executeQuery();
            }
            if (resultSet != null) {
                while (resultSet.next()) {
                    User user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                    user.setBankCardId(resultSet.getString(5));
                    user.setClubBalance(resultSet.getInt(6));
                    user.setCardBalance(resultSet.getInt(7));
                    user.setBlock(resultSet.getBoolean(8));
                    user.setRole(Role.valueOf(resultSet.getString(9)));
                    user.setStatus(Status.valueOf(resultSet.getString(10)));
                    user.setDateReg(resultSet.getString(11));
                    user.setCode(resultSet.getString(12));
                    user.setSubscription(Subscription.valueOf(resultSet.getString(13)));
                    user.setDateSub(resultSet.getString(14));
                    if (change.equals(StringConst.PARAM_NAME_PRESCRIBE_DIET)) {
                        preparedStatement = proxyConnection.prepareStatement(SqlConst.SELECT_NOMINATION_DIET);
                        preparedStatement.setString(1, resultSet.getString(1));
                        ResultSet resultSetDiet = preparedStatement.executeQuery();
                        if (resultSetDiet.next()) {
                            user.setDiet(resultSetDiet.getString(1));
                        }
                    }
                    if (change.equals(StringConst.PARAM_NAME_PRESCRIBE_EXERCISE)) {
                        preparedStatement = proxyConnection.prepareStatement(SqlConst.SELECT_NOMINATION_EXERCISE);
                        preparedStatement.setString(1, resultSet.getString(1));
                        ResultSet resultSetExercise = preparedStatement.executeQuery();
                        if (resultSetExercise.next()) {
                            user.setExercise(resultSetExercise.getString(1));
                        }
                    }
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.error("Can't find users", e);
            throw new DaoException("Can't find users", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
        return users;
    }

    @Override
    public void changeUser(String selectMail, String selectRole, String change) throws DaoException, ConnectionPoolException {
        logger.debug("Start change user");
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            if (change.equals(StringConst.PARAM_NAME_BLOCK)) {
                preparedStatement = proxyConnection.prepareStatement(SqlConst.UPDATE_BLOCK_USER);
                preparedStatement.setString(1, selectMail);
            }
            if (change.equals(StringConst.PARAM_NAME_UNBLOCK)) {
                preparedStatement = proxyConnection.prepareStatement(SqlConst.UPDATE_UNBLOCK_USER);
                preparedStatement.setString(1, selectMail);
            }
            if (change.equals(StringConst.PARAM_NAME_ROLE)) {
                preparedStatement = proxyConnection.prepareStatement(SqlConst.UPDATE_ROLE_USER);
                preparedStatement.setString(1, selectRole);
                preparedStatement.setString(2, selectMail);
            }
            if (preparedStatement != null) {
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            logger.error("Trouble with change user", e);
            throw new DaoException("Trouble with change user", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void updateUserPassword(String mail, String newPass) throws DaoException, ConnectionPoolException {
        logger.debug("Start update user's password");
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.UPDATE_USER_PASSWORD);
            preparedStatement.setString(1, newPass);
            preparedStatement.setString(2, mail);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't update user's password", e);
            throw new DaoException("Can't update user's password", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void updateUserData(String newName, String  newSurname, String newBankCard, String mail) throws DaoException, ConnectionPoolException {
        logger.debug("Start update user's data");
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.UPDATE_USER_DATA);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newSurname);
            preparedStatement.setString(3, newBankCard);
            preparedStatement.setString(4, mail);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't update user's data", e);
            throw new DaoException("Can't update user's data", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void updateUserDiet(String mail, String diet) throws DaoException, ConnectionPoolException {
        logger.debug("Start update user's diet");
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.UPDATE_USER_DIET);
            preparedStatement.setString(1, diet);
            preparedStatement.setString(2, mail);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't update user's diet", e);
            throw new DaoException("Can't update user's diet", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void updateUserExercise(String mail, String exercise) throws DaoException, ConnectionPoolException {
        logger.debug("Start update user's exercise");
        PreparedStatement preparedStatement = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (ProxyConnection proxyConnection = connectionPool.getConnection()) {
            preparedStatement = proxyConnection.prepareStatement(SqlConst.UPDATE_USER_EXERCISE);
            preparedStatement.setString(1, exercise);
            preparedStatement.setString(2, mail);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't update user's exercise", e);
            throw new DaoException("Can't update user's exercise", e);
        } finally {
            releasePreparedStatement(preparedStatement);
        }
    }

    private void releasePreparedStatement(PreparedStatement preparedStatement) throws DaoException {
        if (preparedStatement != null) {
            try {
                logger.debug("Close prepared statement");
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error("Prepared statement not closed : ", e);
                throw new DaoException("Trouble with close prepared statement", e);
            }
        }
    }
}
