package by.epam.web.filter;

import by.epam.web.constant.StringConst;
import by.epam.web.entity.Role;
import by.epam.web.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type User redirect security filter.
 */
@WebFilter(urlPatterns = {"/jsp/user/*"})
public class UserRedirectSecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(UserRedirectSecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Start do user redirect security filter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String role = session.getAttribute(StringConst.ROLE).toString();
        if(role.equals(Role.COACH.toString())){
            httpResponse.sendRedirect(httpRequest.getContextPath() + ConfigurationManager.getProperty("path.page.coachProfile"));
        }
        if(role.equals(Role.ADMIN.toString())){
            httpResponse.sendRedirect(httpRequest.getContextPath() + ConfigurationManager.getProperty("path.page.adminProfile"));
        }
        if(role.equals(Role.GUEST.toString())){
            httpResponse.sendRedirect(httpRequest.getContextPath() + ConfigurationManager.getProperty("path.page.start") + "?state=" + "block");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
