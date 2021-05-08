package app.controllers;

import app.helpers.NTValidator;
import app.models.dto.ProfileUpdateDto;
import app.utils.LaborUtil;
import app.utils.OrderUtil;
import app.utils.RoleUtil;
import app.utils.TransportingUnitUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/profile")
public class ProfileController extends BaseController {
    private RoleUtil roleUtil;
    private TransportingUnitUtil transportingUnitUtil;
    private LaborUtil laborUtil;
    private OrderUtil orderUtil;

    public void init() {
        transportingUnitUtil = new TransportingUnitUtil();
        laborUtil = new LaborUtil();
        roleUtil = new RoleUtil();
        orderUtil = new OrderUtil();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        setUTF8(req, res);
        var session = req.getSession(false);
        var id = Integer.parseInt(session.getAttribute("user_id").toString());

        var user = laborUtil.getById(id);
        req.setAttribute("user", user);
        if (user.getRoleId() != null) {
            var role = roleUtil.getById(user.getRoleId());
            req.setAttribute("role", role);
        }

        var transportingUnit = transportingUnitUtil.getById(user.getTransportingUnitId());
        req.setAttribute("transportingUnit", transportingUnit);

        var orders = orderUtil.getByTransporterIdFullAttributes(id);
        req.setAttribute("orders", orders);

        loadView(req, res, "/profile.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            setUTF8(req, res);
            var name = req.getParameter("name");
            var dateOfBirth = new Date(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(req.getParameter("dateOfBirth"))
                    .getTime());
            var phoneNumber = req.getParameter("phoneNumber");
            var password = req.getParameter("password");
            var passwordConfirm = req.getParameter("passwordConfirm");

            var dto = new ProfileUpdateDto(
                    name,
                    dateOfBirth,
                    phoneNumber,
                    password,
                    passwordConfirm);
            var errors = NTValidator.validate(dto);
            if (!errors.isEmpty()) {
                boundValidationErrors(req, errors);
                loadView(req, res, "profile.jsp");
                return;
            }

            var session = req.getSession(false);
            var id = Integer.parseInt(session.getAttribute("user_id")
                    .toString());
            var labor = laborUtil.getById(id);
            labor.setName((dto.getName()));
            labor.setDateOfBirth(dto.getDateOfBirth());
            labor.setPhoneNumber(dto.getPhoneNumber());
            if (dto.getPw() != null) {
                labor.setPassword(dto.getPw().getPassword());
            }

            laborUtil.update(labor);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        redirect(req, res, "/profile");
    }

}
