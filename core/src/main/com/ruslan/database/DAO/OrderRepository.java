package com.ruslan.database.DAO;


import com.ruslan.database.util.HibernateSessionFactoryUtil;
import com.ruslan.entity.order.Order;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class OrderRepository extends RepositoryBase<Integer, Order> {


    public OrderRepository() {
        super(Order.class);
    }


    public List<Order> getCompletedOrdersForPeriod(LocalDate minusMonths, LocalDate now) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Order> ordersCompleted = session
                .createQuery("select o from Order o where status='completed' " +
                        "and dateExecution BETWEEN :firstDate and :secondDate", Order.class)
                .setParameter("firstDate", minusMonths)
                .setParameter("secondDate", now)
                .list();
        session.close();
        return ordersCompleted;

    }
}
