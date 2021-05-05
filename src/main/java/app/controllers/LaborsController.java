package app.controllers;

import app.helpers.NTValidator;
import app.models.dto.LaborDto;
import app.models.entities.Labor;
import app.utils.LaborUtil;
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

@WebServlet(urlPatterns = {
        "/labors-management",
        "/labors-management/create",
        "/labors-management/update",
        "/labors-management/delete"})
public class LaborsController extends BaseController {
    private LaborUtil laborUtil;
    private RoleUtil roleUtil;
    private TransportingUnitUtil transportingUnitUtil;

    public void init() {
        laborUtil = new LaborUtil();
        roleUtil = new RoleUtil();
        transportingUnitUtil = new TransportingUnitUtil();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        var uri = req.getServletPath();
        setUTF8(req, res);
        switch (uri) {
            case "/labors-management/create":
                try {
                    var name = req.getParameter("name");
                    var dateOfBirth = new Date(new SimpleDateFormat("yyyy-MM-dd")
                            .parse(req.getParameter("dateOfBirth"))
                            .getTime());
                    var phoneNumber = req.getParameter("phoneNumber");
                    var roleId = Integer.parseInt(req.getParameter("roleId"));
                    var transportingUnitId = Integer.parseInt(
                            req.getParameter("transportingUnitId"));
                    var password = req.getParameter("password");
                    var email = req.getParameter("email");

                    var laborDto = new LaborDto(
                            name,
                            dateOfBirth,
                            phoneNumber,
                            roleId,
                            transportingUnitId,
                            password,
                            email);
                    var errors = NTValidator.validate(laborDto);
                    if (!errors.isEmpty()) {
                        boundValidationErrors(req, errors);
                        loadView(req, res, "labors-management/data-form.jsp");
                        return;
                    }

                    var labor = new Labor();
                    labor.setName((laborDto.getName()));
                    labor.setDateOfBirth(laborDto.getDateOfBirth());
                    labor.setPhoneNumber(laborDto.getPhoneNumber());
                    labor.setRoleId(laborDto.getRoleId());
                    labor.setTransportingUnitId(laborDto.getTransportingUnitId());
                    labor.setPassword(laborDto.getPassword());
                    labor.setEmail(laborDto.getEmail());

                    laborUtil.create(labor);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                redirect(req, res, "/labors-management");
                break;
            case "/labors-management/update":
                try {
                    var id = Integer.parseInt(req.getParameter("id"));
                    var name = req.getParameter("name");
                    var dateOfBirth = new Date(new SimpleDateFormat("yyyy-MM-dd")
                            .parse(req.getParameter("dateOfBirth"))
                            .getTime());
                    var phoneNumber = req.getParameter("phoneNumber");
                    var roleId = Integer.parseInt(req.getParameter("roleId"));
                    var transportingUnitId = Integer.parseInt(req.getParameter(
                            "transportingUnitId"));
                    var password = req.getParameter("password");
                    var email = req.getParameter("email");

                    var laborDto = new LaborDto(name,
                            dateOfBirth,
                            phoneNumber,
                            roleId,
                            transportingUnitId,
                            password,
                            email);
                    var errors = NTValidator.validate(laborDto);
                    if (!errors.isEmpty()) {
                        boundValidationErrors(req, errors);
                        loadView(req, res, "labors-management/data-form.jsp");
                        return;
                    }

                    var labor = laborUtil.getById(id);
                    labor.setName((laborDto.getName()));
                    labor.setDateOfBirth(laborDto.getDateOfBirth());
                    labor.setPhoneNumber(laborDto.getPhoneNumber());
                    labor.setRoleId(laborDto.getRoleId());
                    labor.setTransportingUnitId(laborDto.getTransportingUnitId());
                    labor.setPassword(laborDto.getPassword());
                    labor.setEmail(laborDto.getEmail());

                    labor = laborUtil.update(labor);
                    req.setAttribute("data", labor);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                var roles = roleUtil.getAll();
                req.setAttribute("roles", roles);
                var transportingUnits = transportingUnitUtil.getAll();
                req.setAttribute("transportingUnits", transportingUnits);

                loadView(req, res, "labors-management/data-form.jsp");
                break;
            case "/labors-management/delete": {
                var id = Integer.parseInt(req.getParameter("id"));
                laborUtil.delete(id);
                redirect(req, res, "/labors-management");
            }
            break;

        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        var uri = req.getServletPath();

        switch (uri) {
            case "/labors-management/create": {
                var roles = roleUtil.getAll();
                req.setAttribute("roles", roles);
                var transportingUnits = transportingUnitUtil.getAll();
                req.setAttribute("transportingUnits", transportingUnits);

                loadView(req, res, "labors-management/data-form.jsp");
            }
            break;
            case "/labors-management/update": {
                var id = Integer.parseInt(req.getParameter("id"));
                var singleResult = laborUtil.getById(id);
                req.setAttribute("data", singleResult);

                var roles = roleUtil.getAll();
                req.setAttribute("roles", roles);
                var transportingUnits = transportingUnitUtil.getAll();
                req.setAttribute("transportingUnits", transportingUnits);

                loadView(req, res, "labors-management/data-form.jsp");
            }
            break;
            default:
                // TODO: fix
                var listResult = laborUtil.getAllWithFullAttributes();
                req.setAttribute("dataList", listResult);
                loadView(req, res, "labors-management/index.jsp");
                break;
        }
    }
    private void setUTF8(HttpServletRequest req, HttpServletResponse res){
        res.setContentType("text/html;charset=UTF-8");
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
