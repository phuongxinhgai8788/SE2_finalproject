package app.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        var standardRegistry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

        var metadata = new MetadataSources(standardRegistry)
            .getMetadataBuilder()
            .build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}
