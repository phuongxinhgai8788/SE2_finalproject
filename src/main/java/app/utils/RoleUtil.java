package app.utils;

import app.models.dao.RoleDao;
import app.models.entities.Role;

import java.util.List;

public class RoleUtil extends Util<Role> {
    public RoleUtil() {
        super("roles", Role.class);
    }

    public RoleDao getByIdWithLaborsNum(int id) {
        var query = String.format("SELECT R.*, COUNT(L.id) AS laborsNum\n" +
                                      "    FROM %s AS R\n" +
                                      "             LEFT JOIN labors L ON R.id = L.roleId\n" +
                                      "    WHERE R.id = %d\n" +
                                      "    GROUP BY R.id", tableName, id);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, RoleDao.class)
                .getSingleResult();
        }
    }

    public List<RoleDao> getAllWithLaborsNum() {
        var query = String.format("SELECT R.*, COUNT(L.id) as laborsNum\n" +
                                      "    FROM %s AS R\n" +
                                      "             LEFT JOIN labors L ON R.id = L.roleId\n" +
                                      "    GROUP BY R.id", tableName);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, RoleDao.class)
                .getResultList();
        }
    }
}
