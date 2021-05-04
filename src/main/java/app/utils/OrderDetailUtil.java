package app.utils;

import app.models.dao.OrderDetailDao;
import app.models.entities.OrderDetail;

import java.util.List;

public class OrderDetailUtil extends Util<OrderDetail> {
    public OrderDetailUtil() {
        super("orderDetails", OrderDetail.class);
    }

    public List<OrderDetail> getByOrderId(int orderId) {
        var query = String.format("SELECT * FROM %s WHERE orderId = %d",
                                  tableName,
                                  orderId);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, classType).getResultList();
        }
    }

    public List<OrderDetailDao> getByOrderIdFullAttributes(int orderId) {
        var query = String.format(
            "SELECT oD.itemId, i.name, i.source, i.thumbnailUrl, oD.price, oD.quantity\n" +
                "    FROM %s oD\n" +
                "             JOIN inventories i ON i.id = oD.itemId\n" +
                "             JOIN orders o ON o.id = oD.orderId\n" +
                "WHERE oD.orderId = %d",
            tableName,
            orderId);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, OrderDetailDao.class)
                .getResultList();
        }
    }
}
