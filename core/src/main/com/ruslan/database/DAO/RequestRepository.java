package com.ruslan.database.DAO;

import com.ruslan.database.hibernate.HibernateSessionFactoryUtil;
import com.ruslan.entity.request.Request;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RequestRepository extends RepositoryBase<Integer, Request> {

    public RequestRepository() {
        super(Request.class);
    }

//    @Override
//    public List<Request> findAll() {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        List<Request> entity = (List<Request>) session.createSelectionQuery("FROM Request").list();
//        tx1.commit();
//        session.close();
//        return entity;
//
//    }

//    public Optional<Object> findRequestByBookId(Integer id) {
////
////        return Optional.ofNullable(HibernateSessionFactoryUtil.getSessionFactory().openSession()
////                .createQuery("select o from Request o where bookId=':id'", Request.class)
////                .setParameter("id", id));
//        return Optional.ofNullable(true);
//    }

    @Override
    public List<Request> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<Request> entity = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createSelectionQuery("select r from Request r", Request.class).list();
        tx1.commit();
        session.close();
        return entity;

    }
}
