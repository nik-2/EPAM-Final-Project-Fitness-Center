package by.epam.web.logic;

import by.epam.web.connectionpool.ConnectionPool;
import by.epam.web.connectionpool.ProxyConnection;
import by.epam.web.constant.SqlConst;
import by.epam.web.constant.StringConst;
import by.epam.web.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferLogic {
    private static final Logger logger = LogManager.getLogger(TransferLogic.class);

    private ProxyConnection connectionTo = null;
    private ProxyConnection connectionFrom = null;
    private static TransferLogic instance = null;

    public synchronized static TransferLogic getInstance() {
        if (instance == null) {
            logger.info("Create transfer logic instance");
            instance = new TransferLogic();
            instance.getConnectionTo();
            instance.getConnectionFrom();
        }
        return instance;
    }

    private ProxyConnection getConnectionFrom() {
        try {
            logger.debug("Take connectionFrom from connection pool");
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionFrom = connectionPool.getConnection();
            connectionFrom.setAutoCommit(false);
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Trouble with take connectionFrom", e);
            System.err.println("Trouble with take connectionFrom: " + e.getMessage());
        }
        return connectionFrom;
    }

    private ProxyConnection getConnectionTo() {
        try {
            logger.debug("Take connectionTo from connection pool");
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionTo = connectionPool.getConnection();
            connectionTo.setAutoCommit(false);
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Trouble with take connectionTo", e);
            System.err.println("Trouble with take connectionTo: " + e.getMessage());
        }
        return connectionTo;
    }

    public boolean changeSubscription(String newSubscription, String cost, String duration, String clientMail, String coachMail) throws SQLException {
        logger.debug("Start change subscription in the transfer logic");
        boolean check;
        PreparedStatement stFrom = null;
        PreparedStatement stTo = null;
        try {
            stFrom = connectionTo.prepareStatement(SqlConst.FIND_USER_CLUB_BALANCE_BY_MAIL_SQL);
            stFrom.setString(1, clientMail);
            ResultSet rsUserBalance = stFrom.executeQuery();
            int userBalance = 0;
            while (rsUserBalance.next()) {
                userBalance = rsUserBalance.getInt(1);
            }
            if (userBalance < Integer.parseInt(cost)) {
                logger.warn("User balance < subscription cost");
                throw new NumberFormatException("User balance < subscription cost");
            }
            int newUserBalance = userBalance - Integer.parseInt(cost);
            stFrom = connectionFrom.prepareStatement(SqlConst.UPDATE_USER_SUBSCRIPTION);
            stFrom.setString(1, newSubscription);
            stFrom.setString(2, duration);
            stFrom.setString(3, String.valueOf(newUserBalance));
            stFrom.setString(4, clientMail);
            stFrom.execute();
            stTo = connectionTo.prepareStatement(SqlConst.INSERT_CLIENT_COACH);
            stTo.setString(1, clientMail);
            stTo.setString(2, coachMail);
            stTo.execute();
            connectionFrom.commit();
            connectionTo.commit();
            check = true;
        } catch (SQLException e) {
            logger.error("Trouble with change subscription in the transfer logic", e);
            System.err.println("SQLState: " + e.getSQLState() + "Error Message: " + e.getMessage());
            connectionFrom.rollback();
            connectionTo.rollback();
            check = false;
        } finally {
            closeStatment(stFrom, stTo);
        }
        return check;
    }

    public boolean transfer(String summa, String mail) throws SQLException {
        logger.debug("Start transfer in the transfer logic");
        boolean check;
        PreparedStatement stFrom = null;
        PreparedStatement stTo = null;
        try {
            int sum = Integer.parseInt(summa);
            if (sum <= 0) {
                logger.warn("Less or equals zero");
                throw new NumberFormatException("Less or equals zero");
            }
            stFrom = connectionFrom.prepareStatement(SqlConst.FIND_USER_CARD_BALANCE_BY_MAIL_SQL);
            stFrom.setString(1, mail);
            ResultSet rsFrom = stFrom.executeQuery();
            stTo = connectionTo.prepareStatement(SqlConst.FIND_USER_CLUB_BALANCE_BY_MAIL_SQL);
            stTo.setString(1, mail);
            ResultSet rsToUserBalance = stTo.executeQuery();
            stTo = connectionTo.prepareStatement(SqlConst.FIND_BALANCE_BY_CLUB);
            stTo.setString(1, StringConst.FROM);
            ResultSet rsToClubBalance = stTo.executeQuery();
            int accountFrom = 0;
            while (rsFrom.next()) {
                accountFrom = rsFrom.getInt(1);
            }
            int resultFrom;
            if (accountFrom >= sum) {
                resultFrom = accountFrom - sum;
            } else {
                logger.warn("Invalid balance");
                throw new NumberFormatException("Invalid balance");
            }
            int accountToClubBalance = 0;
            while (rsToClubBalance.next()) {
                accountToClubBalance = rsToClubBalance.getInt(1);
            }
            int resultToClubBalance = accountToClubBalance + sum;
            int accountToUserBalance = 0;
            while (rsToUserBalance.next()) {
                accountToUserBalance = rsToUserBalance.getInt(1);
            }
            int resultToUserBalance = accountToUserBalance + sum;
            stTo = connectionTo.prepareStatement(SqlConst.UPDATE_BALANCE_BY_CLUB);
            stTo.setString(1, String.valueOf(resultToClubBalance));
            stTo.setString(2, StringConst.FROM);
            stTo.execute();
            stFrom = connectionFrom.prepareStatement(SqlConst.UPDATE_USER_CLUB_AND_CARD_BALANCE_BY_MAIL_SQL);
            stFrom.setString(1, String.valueOf(resultToUserBalance));
            stFrom.setString(2, String.valueOf(resultFrom));
            stFrom.setString(3, mail);
            stFrom.execute();
            connectionFrom.commit();
            connectionTo.commit();
            check = true;
        } catch (SQLException e) {
            logger.error("Trouble with transfer in the transfer logic", e);
            System.err.println("SQLState: " + e.getSQLState() + "Error Message: " + e.getMessage());
            connectionFrom.rollback();
            connectionTo.rollback();
            check = false;
        }finally {
            closeStatment(stFrom, stTo);
        }
        return check;
    }

    private void closeStatment( PreparedStatement stFrom, PreparedStatement stTo) {
        if (stFrom != null) {
            try {
                logger.debug("Close prepare statement stFrom");
                stFrom.close();
            } catch (SQLException e) {
                logger.error("Trouble with close prepare statement stFrom", e);
                System.err.println("SQLState: " + e.getSQLState() + "Error Message: " + e.getMessage());
            }
        }
        if (stTo != null) {
            try {
                logger.debug("Close prepare statement stTo");
                stTo.close();
            } catch (SQLException e) {
                logger.error("Trouble with close prepare statement stTo", e);
                System.err.println("SQLState: " + e.getSQLState() + "Error Message: " + e.getMessage());
            }
        }
    }

}