package com.ruslan.database.hibernate;

import com.ruslan.entity.book.Book;
import com.ruslan.entity.order.Order;
import com.ruslan.entity.request.Request;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Book.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Request.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.err.println( "Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}