package app.utils;

import app.models.dao.ForecastingDao;
import app.models.dao.OrderDao;
import app.models.entities.Order;
import org.hibernate.transform.Transformers;

import java.util.List;

public class OrderUtil extends Util<Order> {
    public enum STATUS {
        PENDING,
        SHIPPING,
        FAILED,
        COMPLETED
    }

    public OrderUtil() {
        super("orders", Order.class);
    }

    public List<OrderDao> getAllFullAttributes() {
        var query = "SELECT o.id,\n" +
            "       o.customerName,\n" +
            "       o.createdDate,\n" +
            "       o.address,\n" +
            "       o.phoneNumber,\n" +
            "       o.notes,\n" +
            "       tU.name                     AS transportingUnit,\n" +
            "       SUM(oD.price * oD.quantity) AS totalCost,\n" +
            "       o.status\n" +
            "    FROM orders o\n" +
            "             LEFT JOIN transportingUnits tU ON tU.id = o" +
            ".transportingUnitId\n" +
            "             LEFT JOIN orderDetails oD ON o.id = oD.orderId\n" +
            "    GROUP BY o.id";
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, OrderDao.class)
                .getResultList();
        }
    }

    public OrderDao getByIdFullAttributes(int id) {
        var query = String.format(
            "SELECT o.id,\n" +
                "       o.customerName,\n" +
                "       o.createdDate,\n" +
                "       o.address,\n" +
                "       o.phoneNumber,\n" +
                "       o.notes,\n" +
                "       tU.name                     AS transportingUnit,\n" +
                "       SUM(oD.price * oD.quantity) AS totalCost,\n" +
                "       o.status\n" +
                "    FROM %s o\n" +
                "             LEFT JOIN transportingUnits tU ON tU.id = o" +
                ".transportingUnitId\n" +
                "             LEFT JOIN orderDetails oD ON o.id = oD" +
                ".orderId\n" +
                "    WHERE o.id = %d\n" +
                "    GROUP BY o.id", tableName, id);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, OrderDao.class)
                .getSingleResult();
        }
    }

    public List<OrderDao> getByTransporterIdFullAttributes(int transporterId) {
        var query = String.format(
            "SELECT o.id,\n" +
                "       o.customerName,\n" +
                "       o.createdDate,\n" +
                "       o.address,\n" +
                "       o.phoneNumber,\n" +
                "       o.notes,\n" +
                "       tU.name                     AS transportingUnit,\n" +
                "       SUM(oD.price * oD.quantity) AS totalCost,\n" +
                "       o.status\n" +
                "    FROM %s o\n" +
                "             LEFT JOIN transportingUnits tU ON tU.id = o" +
                ".transportingUnitId\n" +
                "             LEFT JOIN orderDetails oD ON o.id = oD" +
                ".orderId\n" +
                "    WHERE o.transporterId = %d\n" +
                "    GROUP BY o.id", tableName, transporterId);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, OrderDao.class)
                .getResultList();
        }
    }

    public List<ForecastingDao> getBoughtItemsSum() {
        var query =
            "SELECT i.id , i.name , i.tags , o.createdDate , SUM(oD.quantity)" +
                " AS sum\n" +
                "FROM orderDetails oD\n" +
                "         JOIN inventories i ON i.id = oD.itemId\n" +
                "         JOIN orders o ON o.id = oD.orderId\n" +
                "GROUP BY i.id, o.createdDate\n" +
                "ORDER BY o.createdDate\n" +
                "LIMIT 7";
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, ForecastingDao.class)
                .getResultList();
        }
    }
}
