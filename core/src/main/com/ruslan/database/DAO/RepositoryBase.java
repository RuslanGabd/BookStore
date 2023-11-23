package com.ruslan.database.DAO;

import com.ruslan.database.hibernate.HibernateSessionFactoryUtil;
import com.ruslan.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {

    private final Class<E> clazz;

    @Override
    public E save(E entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(entity);
        tx1.commit();
        session.close();
        return entity;
    }

    @Override
    public void delete(K id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(id);
        tx1.commit();
        session.close();
        }

    @Override
    public void update(E entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public Optional<E> findById(K id, Map<String, Object> properties) {
        return  Optional.ofNullable(HibernateSessionFactoryUtil.getSessionFactory().openSession().find(clazz, id, properties));
    }

    @Override
    public List<E> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<E> entity = (List<E>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createSelectionQuery("FROM " + clazz.getSimpleName()).list();
        tx1.commit();
        session.close();
        return entity;

           }


}
