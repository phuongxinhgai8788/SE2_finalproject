package app.controllers;

import app.helpers.NTValidator;
import app.models.dto.TransportingUnitDto;
import app.models.entities.TransportingUnit;
import app.utils.TransportingUnitUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet(urlPatterns = {
        "/transporting-units-management",
        "/transporting-units-management/create",
        "/transporting-units-management/update",
        "/transporting-units-management/delete"})
public class TransportingUnitsController extends BaseController {
    private TransportingUnitUtil transportingUnitUtil;

    public void init() {
        transportingUnitUtil = new TransportingUnitUtil();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        var uri = req.getServletPath();
        setUTF8(req, res);
        switch (uri) {
            case "/transporting-units-management/create": {
                var name = req.getParameter("name");

                var transportingUnitDto = new TransportingUnitDto(name);
                var errors = NTValidator.validate(transportingUnitDto);
                if (!errors.isEmpty()) {
                    boundValidationErrors(req, errors);
                    loadView(req,
                             res,
                             "transporting-units-management/data-form.jsp");
                    return;
                }

                var transportingUnit = new TransportingUnit();
                transportingUnit.setName((transportingUnitDto.getName()));

                transportingUnitUtil.create(transportingUnit);

                redirect(req, res, "/transporting-units-management");
            }
            break;
            case "/transporting-units-management/update": {
                var id = Integer.parseInt(req.getParameter("id"));
                var name = req.getParameter("name");

                var transportingUnitDto = new TransportingUnitDto(name);
                var errors = NTValidator.validate(transportingUnitDto);
                if (!errors.isEmpty()) {
                    boundValidationErrors(req, errors);
                    loadView(req,
                             res,
                             "transporting-units-management/data-form.jsp");
                    return;
                }

                var transportingUnit = transportingUnitUtil.getById(id);
                transportingUnit.setName((transportingUnitDto.getName()));
                transportingUnitUtil.update(transportingUnit);
                req.setAttribute("msg", "saved!");

                var singleResult = transportingUnitUtil.getByIdWithLaborsNum(id);
                req.setAttribute("data", singleResult);

                loadView(req,
                         res,
                         "transporting-units-management/data-form.jsp");
            }
            break;
            case "/transporting-units-management/delete": {
                var id = Integer.parseInt(req.getParameter("id"));
                transportingUnitUtil.delete(id);
                redirect(req, res, "/transporting-units-management");
            }
            break;
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        var uri = req.getServletPath();

        switch (uri) {
            case "/transporting-units-management/create":
                loadView(req,
                         res,
                         "transporting-units-management/data-form.jsp");
                break;
            case "/transporting-units-management/update": {
                var id = Integer.parseInt(req.getParameter("id"));
                var singleResult = transportingUnitUtil.getByIdWithLaborsNum(id);
                req.setAttribute("data", singleResult);
                loadView(req,
                         res,
                         "transporting-units-management/data-form.jsp");
            }
            break;
            case "/transporting-units-management/delete": {
                var id = Integer.parseInt(req.getParameter("id"));
                var isDeleted = transportingUnitUtil.delete(id);
                redirect(req, res, "/transporting-units-management");
                //                if (isDeleted) redirect(req,res,"/transporting-units-management");
                //                else res.sendError(400, "nope nope");
            }
            break;
            default:
                var listResult = transportingUnitUtil.getAllWithLaborsNum();
                req.setAttribute("dataList", listResult);
                loadView(req, res, "transporting-units-management/index.jsp");
                break;
        }
    }

}
