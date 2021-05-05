package app.controllers;

import app.helpers.NTAuthSession;
import app.models.dto.RoleDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.Set;

public abstract class BaseController extends HttpServlet {
    protected NTAuthSession getSession(HttpServletRequest req) {
        var session = req.getSession();
        if (session.isNew()) return null;

        var result = new NTAuthSession(Integer.parseInt(session.getAttribute(
                "id").toString()),
                session.getAttribute("name").toString(),
                session.getAttribute("email")
                       .toString());
        return result;
    }

    protected void loadView(HttpServletRequest req,
                            HttpServletResponse res,
                            String path)
            throws ServletException, IOException {
        req.getRequestDispatcher(String.format("/WEB-INF/%s", path))
           .forward(req, res);
    }

    protected void redirect(HttpServletRequest req,
                            HttpServletResponse res,
                            String path)
            throws ServletException, IOException {
        res.sendRedirect(req.getContextPath() + path);
    }

    protected <T> void boundValidationErrors(HttpServletRequest req,
                                             Set<ConstraintViolation<T>> errors) {
        for (ConstraintViolation<T> e : errors) {
            req.setAttribute(e.getPropertyPath()
                              .toString()
                              .replaceAll("\\.", "_") + "_errmsg",
                    e.getMessage());
        }
    }

}
