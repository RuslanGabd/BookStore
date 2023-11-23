package com.ruslan.services.sinterface;

import com.ruslan.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;

public class BaseService {
  SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
}
