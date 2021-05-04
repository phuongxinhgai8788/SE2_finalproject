package app.utils;

import app.models.entities.OrderDeliveryDetail;

import java.util.List;

public class OrderDeliveryDetailUtil extends Util<OrderDeliveryDetail> {
    public OrderDeliveryDetailUtil() {
        super("orderDeliveryDetails", OrderDeliveryDetail.class);
    }

    public List<OrderDeliveryDetail> getByOrderId(int orderId) {
        var query = String.format(
            "SELECT * FROM %s WHERE orderId = %d ORDER BY updatedDate DESC",
            tableName,
            orderId);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, classType).getResultList();
        }
    }
}
