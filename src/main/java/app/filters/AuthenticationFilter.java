package app.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "AuthenticationFilter",
           urlPatterns = "/*",
           initParams = {
               @WebInitParam(name = "excludedExt",
                             value = "jpeg jpg png pdf css js woff2")
           })
public class AuthenticationFilter implements Filter {
    private static Set<String> excluded;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        var excludedString = filterConfig.getInitParameter("excludedExt");
        excluded = Set.of(excludedString.split(" ", 0));
    }

    private boolean isExcluded(HttpServletRequest request) {
        var path = request.getRequestURI();
        var extension = path.substring(path.lastIndexOf('.') + 1)
            .toLowerCase();
        return excluded.contains(extension);
    }

    public void doFilter(
        ServletRequest req,
        ServletResponse res,
        FilterChain chain)
        throws ServletException, IOException {
        var httpRequest = (HttpServletRequest) req;
        var httpResponse = (HttpServletResponse) res;
        if (isExcluded(httpRequest)) {
            chain.doFilter(httpRequest, httpResponse);
            return;
        }

        var session = httpRequest.getSession();

        var isLoggedIn = session.getAttribute("user_id") != null
            && session.getAttribute("user_name") != null
            && session.getAttribute("user_email") != null;

        var loginURI = httpRequest.getContextPath() + "/login";
        var isLoginRequest = httpRequest.getRequestURI().equals(loginURI);

        if (isLoggedIn && isLoginRequest) {
            req.getRequestDispatcher("/WEB-INF/auth/login.jsp")
                .forward(req, res);
        } else if (isLoggedIn || isLoginRequest) {
            var path = httpRequest.getServletPath();
            var isUserBoss = session.getAttribute("user_role") != null
                && session.getAttribute("user_role")
                .equals("Director");
            var isUserManager = session.getAttribute("user_role") != null
                && session.getAttribute("user_role")
                .equals("Manager");
            if (!isUserBoss
                && !isUserManager
                && !path.startsWith("/index")
                && !path.startsWith("/home")
                && !path.startsWith("/profile")
                && !path.equals("/orders-management/details")
                && !path.startsWith("/login")
                && !path.startsWith("/logout")) {
                httpResponse.sendError(403, "no bro, you dont have the rights");
                return;
            }

            chain.doFilter(req, res);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }
}
