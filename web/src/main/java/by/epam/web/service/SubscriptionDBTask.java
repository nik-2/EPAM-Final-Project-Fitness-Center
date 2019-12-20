package by.epam.web.service;

import by.epam.web.connectionpool.ConnectionPool;
import by.epam.web.connectionpool.ProxyConnection;
import by.epam.web.constant.SqlConst;
import by.epam.web.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionDBTask implements Runnable {
    private static final Logger logger = LogManager.getLogger(SubscriptionDBTask.class);

    @Override
    public void run() {
        logger.debug("Run subscription data base task");
        ProxyConnection proxyConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            proxyConnection = connectionPool.getConnection();
            preparedStatement = proxyConnection.prepareStatement(SqlConst.SELECT_END_SUBSCRIPTION_USER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                preparedStatement = proxyConnection.prepareStatement(SqlConst.DELETE_END_SUBSCRIPTION);
                preparedStatement.setString(1, resultSet.getString(1));
                preparedStatement.executeUpdate();
            }
            preparedStatement = proxyConnection.prepareStatement(SqlConst.UPDATE_USER_END_SUBSCRIPTION);
            preparedStatement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Trouble with subscription data base task", e);
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    logger.debug("Close prepared statement");
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Trouble with close prepared statement", e);
                    e.printStackTrace();
                }
            }
            if (proxyConnection != null) {
                logger.debug("Close proxy connection");
                proxyConnection.close();
            }
        }
    }
}
