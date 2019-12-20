package by.epam.web.connectionpool;

import by.epam.web.exception.ConnectionPoolException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ConnectionPoolTest {

    @Test
    public void testDestroyConnections() throws ConnectionPoolException, InterruptedException {
        ConnectionPool pool = ConnectionPool.getInstance();
        (new Thread(() -> {
            List<ProxyConnection> connections = new ArrayList<>();
            for (int index = 0; index < 10; index++) {
                try {
                    connections.add(pool.getConnection());
                } catch (ConnectionPoolException e) {
                    e.printStackTrace();
                }
            }
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int index = 0; index < 10; index++) {
                connections.get(index).close();
            }
        })).start();
        TimeUnit.SECONDS.sleep(3);
        ConnectionPool.destroyConnections();
    }

    @Test (expectedExceptions = CloneNotSupportedException.class)
    public void testTestClone() throws ConnectionPoolException, CloneNotSupportedException {
        ConnectionPool.getInstance().clone();
    }
}