package app.controllers;

import app.helpers.NTValidator;
import app.models.dto.AuthDto;
import app.utils.LaborUtil;
import app.utils.RoleUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/login")
public class LoginController extends BaseController {
    private LaborUtil laborUtil;
    private RoleUtil roleUtil;

    public void init() {
        laborUtil = new LaborUtil();
        roleUtil = new RoleUtil();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        loadView(req, res, "auth/login.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        setUTF8(req, res);
        var email = req.getParameter("email");
        var password = req.getParameter("password");

        var authDto = new AuthDto(email, password);
        var errors = NTValidator.validate(authDto);

        if (!errors.isEmpty()) {
            boundValidationErrors(req, errors);
            loadView(req, res, "auth/login.jsp");
        } else if (!laborUtil.authenticate(authDto)) {
            req.setAttribute("globalErrMsg", "Email || Password not correct");
            loadView(req, res, "auth/login.jsp");
        } else {
            var user = laborUtil.getByEmail(email);

            var session = req.getSession();
            session.setAttribute("user_id", user.getId());
            session.setAttribute("user_name", user.getName());
            session.setAttribute("user_email", user.getEmail());

            if (user.getRoleId() != null) {
                var role = roleUtil.getById(user.getRoleId());
                session.setAttribute("user_role", role.getName());
            }

            redirect(req, res, "/");
        }
    }

}