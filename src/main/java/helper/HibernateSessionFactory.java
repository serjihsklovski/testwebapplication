package helper;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Eager singleton. An instance gets created when the class is loaded.
 */
public final class HibernateSessionFactory {

    private static HibernateSessionFactory instance = new HibernateSessionFactory();
    private SessionFactory sessionFactory;

    private HibernateSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    public static HibernateSessionFactory getInstance() {
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
