package app.controllers;

import app.helpers.NTValidator;
import app.models.dto.*;
import app.models.entities.Inventory;
import app.models.entities.Order;
import app.models.entities.OrderDeliveryDetail;
import app.models.entities.OrderDetail;
import app.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {
        "/inventories-management",
        "/inventories-management/details",
        "/inventories-management/create",
        "/inventories-management/update",
        "/inventories-management/delete"
})
@MultipartConfig
public class InventoriesController extends BaseController {

    private InventoryUtil inventoryUtil;

    public void init() {

        inventoryUtil = new InventoryUtil();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        var uri = req.getServletPath();
        setUTF8(req, res);
        switch (uri) {
            case "/inventories-management/create": {

                    // get all order params
                    var name = req.getParameter("name");
                    var price = Double.parseDouble(req.getParameter("price"));
                    var source = req.getParameter("source");
                    var tags = req.getParameter("tags");
                    var file = req.getPart("thumbnailUrl");
                    String thumbnailUrl = Paths.get(file.getSubmittedFileName()).getFileName().toString();

                    // init a data object
                    var inventoryDto = new InventoryDto(name,
                            price,
                            source,
                            thumbnailUrl,
                            tags
                    );

                    // validate all params of the data object
                    var inventoryErrors = NTValidator.validate(inventoryDto);
                    if (!inventoryErrors.isEmpty()) {
                        boundValidationErrors(req, inventoryErrors);
                        loadView(req, res, "inventories-management/data-form.jsp");
                        return;
                    }
                    var inventory = new Inventory();
                    inventory.setName(inventoryDto.getName());
                    inventory.setPrice(inventoryDto.getPrice());
                    inventory.setSource(inventoryDto.getSource());
                    inventory.setTags(inventoryDto.getTags());
                    inventory.setThumbnailUrl(inventoryDto.getThumbnailUrl());
                    inventoryUtil.create(inventory);
                    redirect(req, res, "/inventories-management");
            }
            break;
            case "/inventories-management/update": {
                // get all order params

                var id = Integer.parseInt(req.getParameter("id"));
                var name = req.getParameter("name");
                var price = Double.parseDouble(req.getParameter("price"));
                var source = req.getParameter("source");
                var tags = req.getParameter("tags");
                var file = req.getPart("thumbnailUrl");
                String thumbnailUrl = Paths.get(file.getSubmittedFileName()).getFileName().toString();

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Price: " + price);
                System.out.println("Source: " + source);
                System.out.println("Tags: " + tags);
                System.out.println("thumbnailUrl: " + thumbnailUrl);

                // init a data object
                var inventoryDto = new InventoryDto(name,
                        price,
                        source,
                        thumbnailUrl,
                        tags
                );

                // validate all params of the data object
                var inventoryErrors = NTValidator.validate(inventoryDto);
                if (!inventoryErrors.isEmpty()) {
                    boundValidationErrors(req, inventoryErrors);
                    loadView(req, res, "inventories-management/data-form.jsp");
                    return;
                }
                var inventory = inventoryUtil.getById(id);
                inventory.setName(inventoryDto.getName());
                inventory.setPrice(inventoryDto.getPrice());
                inventory.setSource(inventoryDto.getSource());
                inventory.setTags(inventoryDto.getTags());
                inventory.setThumbnailUrl(inventoryDto.getThumbnailUrl());
                inventory = inventoryUtil.update(inventory);
                req.setAttribute("data", inventory);

                loadView(req, res, "inventories-management/data-form.jsp");
            }
            break;

            default:
                // TODO: fix
                var listResult = inventoryUtil.getAllWithFullAttributes();
                req.setAttribute("dataList", listResult);
                loadView(req, res, "inventories-management/index.jsp");
                break;
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        var uri = req.getServletPath();

        switch (uri) {
            case "/inventories-management/create": {

                loadView(req, res, "inventories-management/data-form.jsp");
            }
            break;
            case "/inventories-management/update": {
                var id = Integer.parseInt(req.getParameter("id"));

                var singleResult = inventoryUtil.getById(id);
                req.setAttribute("data", singleResult);

                loadView(req, res, "inventories-management/data-form.jsp");
            }
            break;

            case "/inventories-management/delete": {
                var id = Integer.parseInt(req.getParameter("id"));
                inventoryUtil.delete(id);
                redirect(req, res, "/inventories-management");
            }
            break;

            case "/inventories-management": {
                var listResult = inventoryUtil.getAllWithFullAttributes();
                req.setAttribute("dataList", listResult);
                loadView(req, res, "inventories-management/index.jsp");
            }
            break;

        }
    }

}
