package app.utils;

import app.models.dao.WarehouseDao;
import app.models.dao.WarehouseItemDao;
import app.models.entities.Warehouse;

import java.util.List;

public class WarehouseUtil
    extends
    Util<Warehouse> {
    public WarehouseUtil() {
        super("warehouses", Warehouse.class);
    }

    public List<WarehouseDao> getAllFullAttributes() {
        var query = String.format(
            "SELECT w.id, w.address, l.id AS managerId, l.name AS managerName, IFNULL(SUM(wI.quantity), 0) AS " +
                "itemsNum\n" +
                "    FROM %s w\n" +
                "             LEFT JOIN labors l ON l.id = w.managerId\n" +
                "             LEFT JOIN warehouseItems wI ON w.id = wI.warehouseId\n" +
                "    GROUP BY w.id",
            tableName);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, WarehouseDao.class)
                .getResultList();
        }
    }

    public WarehouseDao getByIdFullAttributes(
        int id) {
        var query = String
            .format(
                "SELECT w.id, w.address, l.id AS managerId, l.name AS managerName, IFNULL(SUM(wI.quantity), 0) AS " +
                            "itemsNum\n" +
                            "    FROM %s w\n" +
                            "             LEFT JOIN labors l ON l.id = w.managerId\n" +
                            "             LEFT JOIN warehouseItems wI ON w.id = wI.warehouseId\n" +
                            "    WHERE w.id = %d\n" +
                            "    GROUP BY w.id",
                    tableName,
                id);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, WarehouseDao.class)
                .getSingleResult();
        }
    }

    public void deleteManyItemsOf(
        int warehouseId) {
        var query = String.format("DELETE FROM warehouseItems WHERE warehouseId = %d",
                                  warehouseId);
        try (var session = HibernateUtil.getSession()) {
            var transaction = session.beginTransaction();
            session.createNativeQuery(query, WarehouseDao.class)
                .executeUpdate();
            transaction.commit();
        }
    }

    public List<WarehouseItemDao> getItems(
        int id) {
        var query = String.format(
            "SELECT wI.quantity, i.id, i.name\n" +
                "    FROM warehouseItems wI\n" +
                "             JOIN inventories i ON i.id = wI.inventoryId\n" +
                "    WHERE wI.warehouseId = %d",
            id);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, WarehouseItemDao.class)
                .getResultList();
        }
    }
}
