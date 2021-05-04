package app.utils;

import java.util.List;

public abstract class Util<T> {
    protected final String tableName;
    protected final Class<T> classType;

    protected Util(String tableName, Class<T> classType) {
        this.tableName = tableName;
        this.classType = classType;
    }

    public T getById(int id) {
        var query = String.format("SELECT * FROM %s WHERE id = %d",
                                  tableName,
                                  id);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, classType)
                .getSingleResult();
        }
    }

    public List<T> getAll() {
        var query = String.format("SELECT * FROM %s", tableName);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, classType).getResultList();
        }
    }

    public T create(T ent) {
        try (var session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(ent);
            session.getTransaction().commit();
        }

        return ent;
    }

    public <T> int createMany(List<T> l) {
        try (var session = HibernateUtil.getSession()) {
            session.beginTransaction();

            for (int i = 0; i < l.size(); i++) {
                session.save(l.get(i));
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }

            session.getTransaction().commit();
        }

        return l.size();
    }

    public T update(T ent) {
        try (var session = HibernateUtil.getSession()) {
            session.beginTransaction();
            var result = (T) session.merge(ent);
            session.getTransaction().commit();

            return result;
        }
    }

    public boolean delete(int id) {
        try (var session = HibernateUtil.getSession()) {
            session.beginTransaction();
            var ent = session.get(classType, id);
            session.delete(ent);
            session.getTransaction().commit();

            return true;
        }
    }
}
