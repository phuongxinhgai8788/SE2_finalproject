package app.controllers;

import app.helpers.NTValidator;
import app.models.dto.RoleDto;
import app.models.entities.Role;
import app.utils.RoleUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {
        "/roles-management",
        "/roles-management/create",
        "/roles-management/update",
        "/roles-management/delete"})
public class RolesController extends BaseController {
    private RoleUtil roleUtil;

    public void init() {
        roleUtil = new RoleUtil();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        var uri = req.getServletPath();

        switch (uri) {
            case "/roles-management/create": {
                var name = req.getParameter("name");

                var roleDto = new RoleDto(name);
                var errors = NTValidator.validate(roleDto);
                if (!errors.isEmpty()) {
                    boundValidationErrors(req, errors);
                    loadView(req, res, "roles-management/data-form.jsp");
                    return;
                }

                var role = new Role();
                role.setName((roleDto.getName()));

                roleUtil.create(role);

                redirect(req, res, "/roles-management");
            }
            break;
            case "/roles-management/update": {
                var id = Integer.parseInt(req.getParameter("id"));
                var name = req.getParameter("name");

                var roleDto = new RoleDto(name);
                var errors = NTValidator.validate(roleDto);
                if (!errors.isEmpty()) {
                    boundValidationErrors(req, errors);
                    loadView(req, res, "roles-management/data-form.jsp");
                    return;
                }

                var role = roleUtil.getById(id);
                role.setName((roleDto.getName()));
                roleUtil.update(role);
                req.setAttribute("msg", "saved!");

                var singleResult = roleUtil.getByIdWithLaborsNum(id);
                req.setAttribute("data", singleResult);

                loadView(req, res, "roles-management/data-form.jsp");
            }
            break;
            case "/roles-management/delete": {
                var id = Integer.parseInt(req.getParameter("id"));
                roleUtil.delete(id);
                redirect(req, res, "/roles-management");
            }
            break;
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        var uri = req.getServletPath();

        switch (uri) {
            case "/roles-management/create":
                loadView(req, res, "roles-management/data-form.jsp");
                break;
            case "/roles-management/update": {
                var id = Integer.parseInt(req.getParameter("id"));
                var singleResult = roleUtil.getByIdWithLaborsNum(id);
                req.setAttribute("data", singleResult);
                loadView(req, res, "roles-management/data-form.jsp");
            }
            break;
            case "/roles-management/delete": {
                var id = Integer.parseInt(req.getParameter("id"));
                var isDeleted = roleUtil.delete(id);
                redirect(req, res, "/roles-management");
                //                if (isDeleted) redirect(req,res,"/roles-management");
                //                else res.sendError(400, "nope nope");
            }
            break;
            default:
                var listResult = roleUtil.getAllWithLaborsNum();
                req.setAttribute("dataList", listResult);
                loadView(req, res, "roles-management/index.jsp");
                break;
        }
    }
}
