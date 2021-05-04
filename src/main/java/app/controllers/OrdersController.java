package app.controllers;

import app.helpers.NTValidator;
import app.models.dto.OrderCreateDto;
import app.models.dto.OrderDeliveryDetailDto;
import app.models.dto.OrderDetailDto;
import app.models.dto.OrderDto;
import app.models.entities.Order;
import app.models.entities.OrderDeliveryDetail;
import app.models.entities.OrderDetail;
import app.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {
    "/orders-management",
    "/orders-management/details",
    "/orders-management/create",
    "/orders-management/update",
    "/orders-management/delete"
})
public class OrdersController extends BaseController {
    private OrderUtil orderUtil;
    private OrderDetailUtil orderDetailUtil;
    private OrderDeliveryDetailUtil orderDeliveryDetailUtil;
    private InventoryUtil inventoryUtil;
    private TransportingUnitUtil transportingUnitUtil;
    private LaborUtil laborUtil;

    public void init() {
        orderUtil = new OrderUtil();
        orderDetailUtil = new OrderDetailUtil();
        orderDeliveryDetailUtil = new OrderDeliveryDetailUtil();
        inventoryUtil = new InventoryUtil();
        transportingUnitUtil = new TransportingUnitUtil();
        laborUtil = new LaborUtil();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        var uri = req.getServletPath();

        switch (uri) {
            case "/orders-management/create": {
                // get all order params
                var customerName = req.getParameter("customerName");
                var address = req.getParameter("address");
                var phoneNumber = req.getParameter("phoneNumber");
                var transportingUnitId = req.getParameterMap()
                    .containsKey("transportingUnitId")
                    ? Integer.parseInt(req.getParameter("transportingUnitId"))
                    : 0;
                var transporterId = req.getParameterMap()
                    .containsKey("transporterId")
                    ? Integer.parseInt(req.getParameter("transporterId"))
                    : 0;
                var notes = req.getParameter("notes");
                var status = req.getParameter("status");

                // init a data object
                var orderDto = new OrderCreateDto(customerName,
                                                  address,
                                                  phoneNumber,
                                                  transportingUnitId,
                                                  transporterId,
                                                  notes,
                                                  status);

                // get all add items params
                var orderItemIds = req.getParameterValues("od_itemIds[]");
                var orderItemPrices = req.getParameterValues("od_itemPrices[]");
                var orderItemQuantities = req.getParameterValues("od_itemQuantities[]");
                var isItemDuplicated = orderItemIds != null
                    && orderItemPrices != null
                    && orderItemQuantities != null
                    && !Arrays.stream(orderItemIds)
                    .filter(i -> Collections.frequency(Arrays.asList(orderItemIds), i) > 1)
                    .collect(Collectors.toSet())
                    .isEmpty();
                if (isItemDuplicated) {
                    req.setAttribute("items_itemId_errmsg",
                                     "items.itemId is duplicated");
                }

                // validate all params of the data object
                var orderErrors = NTValidator.validate(orderDto);
                if (!orderErrors.isEmpty() || isItemDuplicated) {
                    boundValidationErrors(req, orderErrors);
                    loadView(req, res, "orders-management/data-form.jsp");
                    return;
                }

                // add those params to OrderDataObject.items
                for (var i = 0; i < orderItemIds.length; i++) {
                    var item = new OrderDetailDto(Integer.parseInt(orderItemIds[i]),
                                                  Double.parseDouble(orderItemPrices[i]),
                                                  Integer.parseInt(orderItemQuantities[i]));
                    orderDto.getItems().add(item);
                }

                // get delivery detail params
                var deliveryNotes = req.getParameter("odd_notes");
                // init a data object
                var deliveryDetailDto =
                    new OrderDeliveryDetailDto(deliveryNotes);
                orderDto.setDeliveryDetail(deliveryDetailDto);

                // create Order first
                var order = new Order();
                order.setCustomerName(orderDto.getCustomerName());
                order.setAddress(orderDto.getAddress());
                order.setPhoneNumber(orderDto.getPhoneNumber());
                order.setTransportingUnitId(orderDto.getTransportingUnitId());
                order.setTransporterId(orderDto.getTransporterId());
                order.setNotes(orderDto.getNotes());
                order.setStatus(orderDto.getStatus());
                order.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                order = orderUtil.create(order);

                // then its items
                var items = new ArrayList<OrderDetail>();
                for (var item : orderDto.getItems()) {
                    var od = new OrderDetail();
                    od.setOrderId(order.getId());
                    od.setItemId(item.getItemId());
                    od.setPrice(item.getItemPrice());
                    od.setQuantity(item.getItemQty());
                    items.add(od);
                }
                orderDetailUtil.createMany(items);

                // then the delivery detail
                var deliveryDetail = new OrderDeliveryDetail();
                deliveryDetail.setOrderId(order.getId());
                deliveryDetail.setNotes(orderDto.getDeliveryDetail()
                                            .getNotes());
                deliveryDetail.setUpdatedDate(orderDto.getDeliveryDetail()
                                                  .getUpdatedDate());
                orderDeliveryDetailUtil.create(deliveryDetail);

                redirect(req, res, "/orders-management");
            }
            break;
            case "/orders-management/update": {
                var id = Integer.parseInt(req.getParameter("id"));
                var customerName = req.getParameter("customerName");
                var address = req.getParameter("address");
                var phoneNumber = req.getParameter("phoneNumber");
                var transportingUnitId = Integer.parseInt(req.getParameter("transportingUnitId"));
                var transporterId = Integer.parseInt(req.getParameter("transporterId"));
                var notes = req.getParameter("notes");
                var status = req.getParameter("status");

                var orderDto = new OrderDto(customerName,
                                            address,
                                            phoneNumber,
                                            transportingUnitId,
                                            transporterId,
                                            notes,
                                            status);

                // get delivery detail params
                var deliveryNotes = req.getParameter("odd_notes");
                if (deliveryNotes != null) {
                    // init a data object
                    var deliveryDetailDto =
                        new OrderDeliveryDetailDto(deliveryNotes);
                    orderDto.setDeliveryDetail(deliveryDetailDto);
                }

                var errors = NTValidator.validate(orderDto);
                if (!errors.isEmpty()) {
                    boundValidationErrors(req, errors);
                    loadView(req, res, "orders-management/data-form.jsp");
                    return;
                }

                var order = orderUtil.getById(id);
                order.setCustomerName(orderDto.getCustomerName());
                order.setAddress(orderDto.getAddress());
                order.setPhoneNumber(orderDto.getPhoneNumber());
                order.setTransporterId(orderDto.getTransporterId());
                order.setNotes(orderDto.getNotes());
                order.setStatus(orderDto.getStatus());
                order = orderUtil.update(order);
                req.setAttribute("data", order);

                if (orderDto.getDeliveryDetail() != null) {
                    var deliveryDetail = new OrderDeliveryDetail();
                    deliveryDetail.setOrderId(order.getId());
                    deliveryDetail.setNotes(orderDto.getDeliveryDetail().getNotes());
                    deliveryDetail.setUpdatedDate(orderDto.getDeliveryDetail().getUpdatedDate());
                    orderDeliveryDetailUtil.create(deliveryDetail);
                }

                var orderDetails =
                    orderDetailUtil.getByOrderIdFullAttributes(id);
                req.setAttribute("orderDetails", orderDetails);

                var deliveryDetails = orderDeliveryDetailUtil.getByOrderId(id);
                req.setAttribute("deliveryDetails", deliveryDetails);

                var transportingUnits = transportingUnitUtil.getAll();
                req.setAttribute("transportingUnits", transportingUnits);

                var transporters = laborUtil.getByTransportingUnitId(
                    order.getTransportingUnitId());
                req.setAttribute("transporters", transporters);

                loadView(req, res, "orders-management/data-form.jsp");
            }
            break;
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        var uri = req.getServletPath();

        switch (uri) {
            case "/orders-management/create": {
                var itemsList = inventoryUtil.getAll();
                req.setAttribute("inventories", itemsList);

                var transportingUnits = transportingUnitUtil.getAll();
                req.setAttribute("transportingUnits", transportingUnits);

                var transporters = laborUtil.getAll();
                req.setAttribute("transporters", transporters);

                loadView(req, res, "orders-management/data-form.jsp");
            }
            break;
            case "/orders-management/update": {
                var id = Integer.parseInt(req.getParameter("id"));

                var singleResult = orderUtil.getById(id);
                req.setAttribute("data", singleResult);

                var orderDetails =
                    orderDetailUtil.getByOrderIdFullAttributes(id);
                req.setAttribute("orderDetails", orderDetails);

                var deliveryDetails = orderDeliveryDetailUtil.getByOrderId(id);
                req.setAttribute("deliveryDetails", deliveryDetails);

                var transportingUnits = transportingUnitUtil.getAll();
                req.setAttribute("transportingUnits", transportingUnits);

                var transporters = laborUtil.getByTransportingUnitId(
                    singleResult.getTransportingUnitId());
                req.setAttribute("transporters", transporters);

                loadView(req, res, "orders-management/data-form.jsp");
            }
            break;
            case "/orders-management/details": {
                var id = Integer.parseInt(req.getParameter("id"));

                var singleResult = orderUtil.getByIdFullAttributes(id);
                req.setAttribute("data", singleResult);

                var orderDetails =
                    orderDetailUtil.getByOrderIdFullAttributes(id);
                req.setAttribute("orderDetails", orderDetails);

                var deliveryDetails = orderDeliveryDetailUtil.getByOrderId(id);
                req.setAttribute("deliveryDetails", deliveryDetails);

                loadView(req, res, "orders-management/single.jsp");
            }
            break;
            default:
                var listResult = orderUtil.getAllFullAttributes();
                req.setAttribute("dataList", listResult);
                loadView(req, res, "orders-management/index.jsp");
                break;
        }
    }
}
