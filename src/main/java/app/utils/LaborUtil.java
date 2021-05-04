package app.utils;

import app.models.dao.LaborDao;
import app.models.dao.ManagerDao;
import app.models.dto.AuthDto;
import app.models.entities.Labor;

import java.util.List;

public class LaborUtil extends Util<Labor> {
    public LaborUtil() {
        super("labors", Labor.class);
    }

    public boolean authenticate(AuthDto dto) {
        var query = String.format(
            "SELECT * FROM %s WHERE email = '%s' AND password = '%s'",
            tableName,
            dto.getEmail(),
            dto.getPassword());
        try (var session = HibernateUtil.getSession()) {
            var data = session.createNativeQuery(query, classType)
                .getResultStream()
                .findFirst()
                .orElse(null);
            return data != null;
        }
    }

    public Labor getByEmail(String email) {
        var query = String.format("SELECT * FROM %s WHERE email = '%s'",
                                  tableName,
                                  email);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, classType)
                .getSingleResult();
        }
    }

    public List<LaborDao> getAllWithFullAttributes() {
        var query = String.format(
            "SELECT l.id, l.name, l.phoneNumber, r.name AS role, tU.name AS transportingUnit\n" +
                "    FROM %s l\n" +
                "             LEFT JOIN roles r ON r.id = l.roleId\n" +
                "             LEFT JOIN transportingUnits tU ON tU.id = l.transportingUnitId",
            tableName);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, LaborDao.class)
                .getResultList();
        }
    }

    public List<LaborDao> getByTransportingUnitId(int transportingUnitId) {
        var query = String.format(
            "SELECT l.id, l.name, l.phoneNumber, r.name AS role, tU.name AS transportingUnit\n" +
                "    FROM %s l\n" +
                "             LEFT JOIN roles r ON r.id = l.roleId\n" +
                "             LEFT JOIN transportingUnits tU ON tU.id = l.transportingUnitId" +
                "    WHERE tU.id = %d",
            tableName,
            transportingUnitId);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, LaborDao.class)
                .getResultList();
        }
    }

    public List<ManagerDao> getManagers() {
        var query = String.format(
            "SELECT l.id, l.name, r.name AS role, tU.name AS transportingUnit\n" +
                "    FROM %s l\n" +
                "             LEFT JOIN roles r ON r.id = l.roleId\n" +
                "             LEFT JOIN transportingUnits tU ON tU.id = l.transportingUnitId\n" +
                "    WHERE LOWER(r.name) = 'manager'",
            tableName);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, ManagerDao.class)
                .getResultList();
        }
    }
}
