package by.epam.web.dao;

import by.epam.web.entity.User;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.exception.DaoException;

import java.util.List;

/**
 * The type Abstract user dao.
 */
public abstract class AbstractUserDao {
    /**
     * Find user by mail user.
     *
     * @param mail the mail
     * @return the user
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract User findUserByMail(String mail) throws DaoException, ConnectionPoolException;

    /**
     * Create user.
     *
     * @param mail        the mail
     * @param name        the name
     * @param surname     the surname
     * @param password    the password
     * @param confirmCode the confirm code
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract void createUser(String mail, String name, String surname, String password, String confirmCode) throws DaoException, ConnectionPoolException;

    /**
     * Update unconfirmed user.
     *
     * @param mail the mail
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract void updateUnconfirmedUser(String mail) throws DaoException, ConnectionPoolException;

    /**
     * Find users list.
     *
     * @param change the change
     * @param mail   the mail
     * @return the list
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract List<User> findUsers(String change, String mail) throws DaoException, ConnectionPoolException;

    /**
     * Change user.
     *
     * @param selectMail the select mail
     * @param selectRole the select role
     * @param change     the change
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract void changeUser(String selectMail, String selectRole, String change) throws DaoException, ConnectionPoolException;

    /**
     * Find number day reg string.
     *
     * @param dateReg the date reg
     * @return the string
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract String findNumberDayReg(String dateReg) throws DaoException, ConnectionPoolException;

    /**
     * Update user password.
     *
     * @param mail    the mail
     * @param newPass the new pass
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract void updateUserPassword(String mail, String newPass) throws DaoException, ConnectionPoolException;

    /**
     * Update user data.
     *
     * @param newName     the new name
     * @param newSurname  the new surname
     * @param newBankCard the new bank card
     * @param mail        the mail
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract void updateUserData(String newName, String newSurname, String newBankCard, String mail) throws DaoException, ConnectionPoolException;

    /**
     * Find coaches list.
     *
     * @return the list
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract List<User> findCoaches() throws DaoException, ConnectionPoolException;

    /**
     * Find client coach string.
     *
     * @param mail the mail
     * @return the string
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract String findClientCoach(String mail) throws DaoException, ConnectionPoolException;

    /**
     * Update user coach.
     *
     * @param mail      the mail
     * @param coachMail the coach mail
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract void updateUserCoach(String mail, String coachMail) throws DaoException, ConnectionPoolException;

    /**
     * Update user subscription.
     *
     * @param subscription   the subscription
     * @param duration       the duration
     * @param newClubBalance the new club balance
     * @param mail           the mail
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract void updateUserSubscription(String subscription, String duration, String newClubBalance, String mail) throws DaoException, ConnectionPoolException;

    /**
     * Find user diet string.
     *
     * @param mail the mail
     * @return the string
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract String findUserDiet(String mail) throws DaoException, ConnectionPoolException;

    /**
     * Find user exercise string.
     *
     * @param mail the mail
     * @return the string
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract String findUserExercise(String mail) throws DaoException, ConnectionPoolException;

    /**
     * Update user diet.
     *
     * @param mail    the mail
     * @param newDiet the new diet
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract void updateUserDiet(String mail, String newDiet) throws DaoException, ConnectionPoolException;

    /**
     * Update user exercise.
     *
     * @param mail        the mail
     * @param newExercise the new exercise
     * @throws DaoException            the dao exception
     * @throws ConnectionPoolException the connection pool exception
     */
    public abstract void updateUserExercise(String mail, String newExercise) throws DaoException, ConnectionPoolException;
}
