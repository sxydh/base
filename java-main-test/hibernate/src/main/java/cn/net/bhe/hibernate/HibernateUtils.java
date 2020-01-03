package cn.net.bhe.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtils {

    public static final SessionFactory sessionFactory;
    static Logger LOGGER = LoggerFactory.getLogger(HibernateUtils.class);
    static {
        try {
            // Create a Configuration instance using the default hibernate.cfg.xml configuration file.
            Configuration config = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new ExceptionInInitializerError(e);
        }
    }
    // ThreadLocal can isolate data sharing for multiple threads, so there is no need to synchronize threads.
    public static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    public static Session currentSession() throws HibernateException {
        Session session = threadLocal.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    public static void closeSession() throws HibernateException {
        Session session = threadLocal.get();
        if (session != null) {
            session.close();
        }
        threadLocal.set(null);
    }

}