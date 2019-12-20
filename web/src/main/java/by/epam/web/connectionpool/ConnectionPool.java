package by.epam.web.connectionpool;

import by.epam.web.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private static BlockingQueue<ProxyConnection> connectionQueue;
    private static int poolSize;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (!instanceCreated.get()) {
                    logger.info("Create connection pool instance");
                    instance = new ConnectionPool();
                    registerJDBCDriver();
                    initPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private static void registerJDBCDriver() throws ConnectionPoolException {
        try {
            logger.info("Register JDBC driver");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            logger.error("JDBC driver not found", e);
            throw new ConnectionPoolException("Class not found", e);
        }
    }

    private static void initPool() throws ConnectionPoolException {
        logger.info("Initialization pool");
        final String DATABASE_PROPERTY = "property.database";
        final String DATABASE_URL = "db.url";
        final String DATABASE_USER = "db.user";
        final String DATABASE_PASSWORD = "db.password";
        final String DATABASE_CHARACTER_ENCODING = "db.characterEncoding";
        final String DATABASE_USE_UNICODE = "db.useUnicode";
        final String DATABASE_POOL_SIZE = "db.poolSize";

        ResourceBundle resourceBundle;
        try {
            resourceBundle = ResourceBundle.getBundle(DATABASE_PROPERTY);
        } catch (MissingResourceException e) {
            logger.error("Hasn't found bundle for database",e);
            throw new ConnectionPoolException("Hasn't found bundle for database", e);
        }

        final String URL = resourceBundle.getString(DATABASE_URL);
        final String USER = resourceBundle.getString(DATABASE_USER);
        final String PASSWORD = resourceBundle.getString(DATABASE_PASSWORD);
        final String CHARACTER_ENCODING = resourceBundle.getString(DATABASE_CHARACTER_ENCODING);
        final String USE_UNICODE = resourceBundle.getString(DATABASE_USE_UNICODE);
        final String POOL_SIZE = resourceBundle.getString(DATABASE_POOL_SIZE);

        Properties properties = new Properties();
        properties.put("user", USER);
        properties.put("password", PASSWORD);
        properties.put("characterEncoding", CHARACTER_ENCODING);
        properties.put("useUnicode", USE_UNICODE);

        logger.info("Create connection queue");
        poolSize = Integer.parseInt(POOL_SIZE);
        connectionQueue = new ArrayBlockingQueue<>(poolSize);

        for (int index = 0; index < poolSize; index++) {
            Connection connection;
            try {
                logger.trace("Create connection");
                connection = DriverManager.getConnection(URL, properties);
            } catch (SQLException e) {
                logger.error("Hasn't found connection with database",e);
                throw new ConnectionPoolException("Hasn't found connection with database", e);
            }

            ProxyConnection proxyConnection = new ProxyConnection(connection);
            try {
                logger.trace("Put connection on the connection queue");
                connectionQueue.put(proxyConnection);
            } catch (InterruptedException e) {
                logger.error("We can't put connection",e);
                throw new ConnectionPoolException("We can't put connection", e);
            }
        }
    }

    public ProxyConnection getConnection() throws ConnectionPoolException {
        ProxyConnection proxyConnection;
        try {
            logger.debug("Take connection from connection pool");
            proxyConnection = connectionQueue.take();
        } catch (InterruptedException e) {
            logger.error("We can't take connection", e);
            throw new ConnectionPoolException("We can't take connection", e);
        }
        return proxyConnection;
    }

    public static void destroyConnections() throws ConnectionPoolException {
        for (int index = 0; index < poolSize; index++) {
            ProxyConnection proxyConnection = null;
            try {
                logger.debug("Take connection");
                proxyConnection = connectionQueue.take();
            } catch (InterruptedException e) {
                logger.error("We can't take connection", e);
                throw new ConnectionPoolException("We can't take connection", e);
            }
        finally {
                if (proxyConnection != null) {
                        logger.debug("Close connection");
                        proxyConnection.closeConnection();
                }
            }
        }
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                logger.debug("Take driver");
                Driver driver = drivers.nextElement();
                try {
                    logger.debug("Deregister driver");
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e) {
                    logger.error("Trouble with deregister driver", e);
                    throw new ConnectionPoolException("Trouble with deregister driver", e);
                }
            }

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        if (instanceCreated.get()) {
            logger.error("Tried to clone connection pool");
            throw new CloneNotSupportedException("Tried to clone connection pool");
        }
        return super.clone();
    }

    void releaseConnection(ProxyConnection proxyConnection) throws ConnectionPoolException {
        try {
            logger.debug("Put connection on the connection queue");
            connectionQueue.put(proxyConnection);
        } catch (InterruptedException e) {
            logger.error("We can't put connection", e);
            throw new ConnectionPoolException("We can't put connection", e);
        }
    }
}
