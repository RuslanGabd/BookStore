package com.ruslan.userDao;

import com.ruslan.database.DAO.RepositoryBase;
import com.ruslan.userEntity.User;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Log4j2
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

    @Query()

    public Optional<User> findByUsername(String username) {
        User user = getSession().createQuery("select u from User u where username=:username"
                        , User.class).
                setParameter("username", username).uniqueResult();

        return Optional.ofNullable(user);
    }

    public void createUser(User user) {
        Session session = getSession();
        session.persist(user);
    }
}
