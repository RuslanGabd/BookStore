package com.ruslan.database.DAO;


import com.ruslan.database.util.HibernateSessionFactoryUtil;
import com.ruslan.entity.request.Request;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RequestRepository extends RepositoryBase<Integer, Request> {

    public RequestRepository() {
        super(Request.class);
    }


    public Optional<Object> findRequestByBookId(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        var object = session
                .createSelectionQuery("select o from Request o where bookId=':id'", Request.class)
                .setParameter("id", id);
        tx1.commit();
        session.close();
        return Optional.ofNullable(object);
    }
}
