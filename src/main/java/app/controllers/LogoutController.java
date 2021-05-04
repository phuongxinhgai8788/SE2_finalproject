package app.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends BaseController {
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse res)
            throws IOException, ServletException {
        req.getSession().invalidate();
        redirect(req, res, "/login");
    }
}
