package com.ruslan.userDao;

import com.ruslan.database.DAO.RepositoryBase;
import com.ruslan.userEntity.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class UserRepository extends RepositoryBase<Integer, User> {

    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        super(User.class);
        this.entityManager = entityManager;
    }

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public Optional<User> findByUserName(String username) {

        User user = getSession().createQuery("select username, password" +
                        ", role from User o where username=:username", User.class)
                .setParameter("username", username).uniqueResult();
        return Optional.ofNullable(user);
    }

    public void createUser(User user) {
        Session session =  getSession();
        session.persist(user);
    }


}
