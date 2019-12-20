package by.epam.web.controller;

import by.epam.web.command.base.ActionCommand;
import by.epam.web.command.factory.ActionFactory;
import by.epam.web.connectionpool.ConnectionPool;
import by.epam.web.exception.ConnectionPoolException;
import by.epam.web.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        try {
            logger.info("Destroy connections");
            ConnectionPool.destroyConnections();
        } catch (ConnectionPoolException e) {
            logger.error("Trouble with destroy connections", e);
            e.printStackTrace();
        }
        super.destroy();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        Router router = command.execute(request);
        String page = router.getPagePath();
        if (router.getRoute() == Router.RouteType.FORWARD) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}

