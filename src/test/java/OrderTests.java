import app.models.entities.Order;
import app.models.entities.OrderDetail;
import app.utils.OrderDetailUtil;
import app.utils.OrderUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTests {
    private OrderUtil orderUtil;
    private OrderDetailUtil oddUtil;

    @BeforeAll
    public void init() {
        orderUtil = new OrderUtil();
        oddUtil = new OrderDetailUtil();
    }

    @Test
    void creation() {
        var order = new Order();
        order.setId(99);
        order.setAddress("Asd");
        order.setCustomerName("adowidjoi");
        order.setStatus(OrderUtil.STATUS.COMPLETED.toString());
        order.setTransporterId(2);
        order.setTransportingUnitId(2);
        var newOrder = orderUtil.create(order);
        assertEquals(order, newOrder);
    }

    @Test
    void modification() {
        var order = orderUtil.getById(99);
        order.setNotes("2d09dj1209j");
        var modifiedOrder = orderUtil.update(order);
        assertEquals(order, modifiedOrder);
    }

    void detailsCreation() {
        var order = orderUtil.getById(99);
        var od = new OrderDetail();
        od.setItemId(2);
        od.setOrderId(order.getId());
        od.setPrice(98);
        od.setQuantity(2);
        oddUtil.create(od);
        var details = oddUtil.getByOrderId(order.getId());
        assertEquals(details.get(details.size() - 1), od);
    }
}
