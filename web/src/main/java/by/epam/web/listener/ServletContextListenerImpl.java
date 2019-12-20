package by.epam.web.listener;

import by.epam.web.service.CheckCoachDBTask;
import by.epam.web.service.CleanDBTask;
import by.epam.web.service.SubscriptionDBTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ServletContextListenerImpl.class);
    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("Initialize servlet context listener");
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new CleanDBTask(), 0, 5  , TimeUnit.MINUTES);
        scheduler.scheduleAtFixedRate(new SubscriptionDBTask(), 0, 1  , TimeUnit.HOURS);
        scheduler.scheduleAtFixedRate(new CheckCoachDBTask(), 0, 5  , TimeUnit.MINUTES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("Destroy servlet context listener");
        scheduler.shutdownNow();
    }
}
