package app.controllers;

import app.helpers.NTValidator;
import app.models.dto.WarehouseDto;
import app.models.dto.WarehouseItemDto;
import app.models.entities.Warehouse;
import app.models.entities.WarehouseItem;
import app.utils.InventoryUtil;
import app.utils.LaborUtil;
import app.utils.WarehouseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {
    "/warehouses-management",
    "/warehouses-management/create",
    "/warehouses-management/update",
    "/warehouses-management/delete"
})
public class WarehousesController extends BaseController {
    private WarehouseUtil warehouseUtil;
    private LaborUtil laborUtil;
    private InventoryUtil inventoryUtil;

    public void init() {
        warehouseUtil = new WarehouseUtil();
        laborUtil = new LaborUtil();
        inventoryUtil = new InventoryUtil();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        var uri = req.getServletPath();
        setUTF8(req, res);
        var managers = laborUtil.getManagers();
        req.setAttribute("managers", managers);

        switch (uri) {
            case "/warehouses-management/create": {
                var address = req.getParameter("address");
                var managerId = req
                    .getParameterMap()
                    .containsKey("managerId")
                    ? Integer.parseInt(req.getParameter("managerId"))
                    : 0;

                var warehouseDto = new WarehouseDto(address, managerId);

                var warehouseItemIds = req.getParameterValues("wi_itemIds[]");
                var warehouseItemQuantities = req.getParameterValues(
                    "wi_itemQuantities[]");
                var isItemDuplicated = warehouseItemIds != null
                    && warehouseItemQuantities != null
                    && !Arrays.stream(warehouseItemIds)
                    .filter(i -> Collections.frequency(Arrays.asList(warehouseItemIds), i) > 1)
                    .collect(Collectors.toSet())
                    .isEmpty();
                if (isItemDuplicated) {
                    req.setAttribute("items_itemId_errmsg",
                                     "items.itemId is duplicated");
                }

                var errors = NTValidator.validate(warehouseDto);
                if (!errors.isEmpty() || isItemDuplicated) {
                    boundValidationErrors(req, errors);
                    loadView(req, res, "warehouses-management/data-form.jsp");
                    return;
                }

                for (var i = 0; i < warehouseItemIds.length; i++) {
                    var item = new WarehouseItemDto(Integer.parseInt(warehouseItemIds[i]),
                                                    Integer.parseInt(warehouseItemQuantities[i]));
                    warehouseDto.getItems().add(item);
                }

                var warehouse = new Warehouse();
                warehouse.setAddress((warehouseDto.getAddress()));
                warehouse.setManagerId((warehouseDto.getManagerId()));
                warehouse = warehouseUtil.create(warehouse);

                var items = new ArrayList<WarehouseItem>();
                for (var item : warehouseDto.getItems()) {
                    var wi = new WarehouseItem();
                    wi.setWarehouseId(warehouse.getId());
                    wi.setInventoryId(item.getInventoryId());
                    wi.setQuantity(item.getQuantity());
                    items.add(wi);
                }
                warehouseUtil.createMany(items);

                redirect(req, res, "/warehouses-management");
            }
            break;
            case "/warehouses-management/update": {
                var id = Integer.parseInt(req.getParameter("id"));
                var address = req.getParameter("address");
                var managerId = req
                    .getParameterMap()
                    .containsKey("managerId")
                    ? Integer.parseInt(req.getParameter("managerId"))
                    : 0;

                var warehouseDto = new WarehouseDto(address, managerId);

                var warehouseItemIds = req.getParameterValues("wi_itemIds[]");
                var warehouseItemQuantities = req.getParameterValues(
                    "wi_itemQuantities[]");
                var isItemDuplicated = warehouseItemIds != null
                    && warehouseItemQuantities != null
                    && !Arrays.stream(warehouseItemIds)
                    .filter(i -> Collections.frequency(Arrays.asList(warehouseItemIds), i) > 1)
                    .collect(Collectors.toSet())
                    .isEmpty();
                if (isItemDuplicated) {
                    req.setAttribute("items_itemId_errmsg",
                                     "items.itemId is duplicated");
                }

                var errors = NTValidator.validate(warehouseDto);
                if (!errors.isEmpty() || isItemDuplicated) {
                    boundValidationErrors(req, errors);
                    loadView(req, res, "warehouses-management/data-form.jsp");
                    return;
                }

                for (var i = 0; i < warehouseItemIds.length; i++) {
                    var item = new WarehouseItemDto(Integer.parseInt(warehouseItemIds[i]),
                                                    Integer.parseInt(warehouseItemQuantities[i]));
                    warehouseDto.getItems().add(item);
                }

                var warehouse = warehouseUtil.getById(id);
                warehouse.setAddress((warehouseDto.getAddress()));
                warehouse.setManagerId((warehouseDto.getManagerId()));
                warehouse = warehouseUtil.update(warehouse);
                req.setAttribute("msg", "saved!");

                var items = new ArrayList<WarehouseItem>();
                for (var item : warehouseDto.getItems()) {
                    var wi = new WarehouseItem();
                    wi.setWarehouseId(warehouse.getId());
                    wi.setInventoryId(item.getInventoryId());
                    wi.setQuantity(item.getQuantity());
                    items.add(wi);
                }
                warehouseUtil.deleteManyItemsOf(id);
                warehouseUtil.createMany(items);

                var itemsInDao = warehouseUtil.getItems(id);
                req.setAttribute("items", itemsInDao);

                var singleResult = warehouseUtil.getByIdFullAttributes(id);
                req.setAttribute("data", singleResult);

                var inventories = inventoryUtil.getAll();
                req.setAttribute("inventories", inventories);

                loadView(req, res, "warehouses-management/data-form.jsp");
            }
            break;
            case "/warehouses-management/delete": {
                var id = Integer.parseInt(req.getParameter("id"));
                warehouseUtil.delete(id);
                redirect(req, res, "/warehouses-management");
            }
            break;
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        var uri = req.getServletPath();

        var managers = laborUtil.getManagers();
        req.setAttribute("managers", managers);

        switch (uri) {
            case "/warehouses-management/create": {
                var inventories = inventoryUtil.getAll();
                req.setAttribute("inventories", inventories);

                loadView(req, res, "warehouses-management/data-form.jsp");
            }
            break;
            case "/warehouses-management/update": {
                var id = Integer.parseInt(req.getParameter("id"));

                var singleResult = warehouseUtil.getByIdFullAttributes(id);
                req.setAttribute("data", singleResult);

                var inventories = inventoryUtil.getAll();
                req.setAttribute("inventories", inventories);

                var items = warehouseUtil.getItems(id);
                req.setAttribute("items", items);

                loadView(req, res, "warehouses-management/data-form.jsp");
            }
            break;
            case "/warehouses-management/delete": {
                var id = Integer.parseInt(req.getParameter("id"));
                var isDeleted = warehouseUtil.delete(id);
                redirect(req, res, "/warehouses-management");
            }
            break;
            default: {
                var listResult = warehouseUtil.getAllFullAttributes();
                req.setAttribute("dataList", listResult);

                loadView(req, res, "warehouses-management/index.jsp");
            }
            break;
        }
    }

}
