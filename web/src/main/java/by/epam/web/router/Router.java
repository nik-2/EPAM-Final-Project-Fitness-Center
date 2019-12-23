package by.epam.web.router;

/**
 * The type Router.
 */
public class Router {
    private String pagePath;
    private RouteType route;

    /**
     * The enum Route type.
     */
    public enum RouteType {
        /**
         * Forward route type.
         */
        FORWARD,
        /**
         * Redirect route type.
         */
        REDIRECT
}

    /**
     * Sets page path.
     *
     * @param pagePath the page path
     */
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    /**
     * Sets route.
     *
     * @param route the route
     */
    public void setRoute(RouteType route) {
        this.route = route;
    }

    /**
     * Gets page path.
     *
     * @return the page path
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Gets route.
     *
     * @return the route
     */
    public RouteType getRoute() {
        return route;
    }
}
