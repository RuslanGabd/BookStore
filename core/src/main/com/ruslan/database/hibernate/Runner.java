package com.ruslan.database.hibernate;

import com.ruslan.database.DAO.RequestRepository;
import com.ruslan.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();
        var requestRepository = new RequestRepository();
        requestRepository.findById(3).ifPresent(System.out::println);
         transaction.commit();
    }
}
