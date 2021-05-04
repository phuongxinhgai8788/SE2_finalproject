package app.utils;

import app.models.dao.TransportingUnitDao;
import app.models.entities.TransportingUnit;

import java.util.List;

public class TransportingUnitUtil extends Util<TransportingUnit> {
    public TransportingUnitUtil() {
        super("transportingUnits", TransportingUnit.class);
    }

    public TransportingUnitDao getByIdWithLaborsNum(int id) {
        var query = String.format("SELECT R.*, COUNT(L.id) AS laborsNum\n" +
                                      "    FROM %s AS R\n" +
                                      "             LEFT JOIN labors L ON R.id = L.roleId\n" +
                                      "    WHERE R.id = %d\n" +
                                      "    GROUP BY R.id", tableName, id);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, TransportingUnitDao.class)
                .getSingleResult();
        }
    }

    public List<TransportingUnitDao> getAllWithLaborsNum() {
        var query = String.format("SELECT R.*, COUNT(L.id) as laborsNum\n" +
                                      "    FROM %s AS R\n" +
                                      "             LEFT JOIN labors L ON R.id = L.roleId\n" +
                                      "    GROUP BY R.id", tableName);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, TransportingUnitDao.class)
                .getResultList();
        }
    }
}
