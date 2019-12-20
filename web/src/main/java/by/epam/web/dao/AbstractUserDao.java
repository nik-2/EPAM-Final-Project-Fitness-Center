package by.epam.web.dao;

import by.epam.web.entity.User;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;

import java.util.List;

public abstract class AbstractUserDao {
    public abstract User findUserByMail(String mail) throws DaoException, ConnectionPoolException;
    public abstract void createUser(String mail, String name, String surname, String password, String confirmCode) throws DaoException, ConnectionPoolException;
    public abstract void updateUnconfirmedUser(String mail) throws DaoException, ConnectionPoolException;
    public abstract List<User> findUsers(String change, String mail) throws DaoException, ConnectionPoolException;
    public abstract void changeUser(String selectMail, String selectRole, String change) throws DaoException, ConnectionPoolException;
    public abstract String findNumberDayReg(String dateReg) throws DaoException, ConnectionPoolException;
    public abstract void updateUserPassword(String mail, String newPass) throws DaoException, ConnectionPoolException;
    public abstract void updateUserData(String newName, String newSurname, String newBankCard, String mail) throws DaoException, ConnectionPoolException;
    public abstract List<User> findCoaches() throws DaoException, ConnectionPoolException;
    public abstract String findClientCoach(String mail) throws DaoException, ConnectionPoolException;
    public abstract void updateUserCoach(String mail, String coachMail) throws DaoException, ConnectionPoolException;
    public abstract void updateUserSubscription(String subscription, String duration, String newClubBalance, String mail) throws DaoException, ConnectionPoolException;
    public abstract String findUserDiet(String mail) throws DaoException, ConnectionPoolException;
    public abstract String findUserExercise(String mail) throws DaoException, ConnectionPoolException;
    public abstract void updateUserDiet(String mail, String newDiet) throws DaoException, ConnectionPoolException;
    public abstract void updateUserExercise(String mail, String newExercise) throws DaoException, ConnectionPoolException;
}
