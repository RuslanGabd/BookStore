package com.ruslan.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = buildConfiguration();
        configuration.configure();

        var sessionFactory = configuration.buildSessionFactory();
//        registerListeners(sessionFactory);

        return sessionFactory;
    }

//    private static void registerListeners(SessionFactory sessionFactory) {
//        var sessionFactoryImpl = sessionFactory.unwrap(SessionFactoryImpl.class);
//        var listenerRegistry = sessionFactoryImpl.getServiceRegistry().getService(EventListenerRegistry.class);
//        var auditTableListener = new AuditTableListener();
//        listenerRegistry.appendListeners(EventType.PRE_INSERT, auditTableListener);
//        listenerRegistry.appendListeners(EventType.PRE_DELETE, auditTableListener);
//    }

    public static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
//        configuration.addAnnotatedClass(User.class);
//        configuration.addAnnotatedClass(Audit.class);
//        configuration.addAnnotatedClass(Revision.class);
//        configuration.addAttributeConverter(new BirthdayConverter());
//        configuration.registerTypeOverride(new JsonBinaryType());
//        configuration.setInterceptor(new GlobalInterceptor());
        return configuration;
    }
}