package by.epam.web.service;

import by.epam.web.connectionpool.ConnectionPool;
import by.epam.web.connectionpool.ProxyConnection;
import by.epam.web.constant.SqlConst;
import by.epam.web.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CleanDBTask implements Runnable {
    private static final Logger logger = LogManager.getLogger(CleanDBTask.class);

    @Override
    public void run() {
        logger.debug("Run clean data base task");
        ProxyConnection proxyConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            proxyConnection = connectionPool.getConnection();
            preparedStatement = proxyConnection.prepareStatement(SqlConst.DELETE_UNCONFIRMED_USER);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Trouble with clean data base task", e);
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
