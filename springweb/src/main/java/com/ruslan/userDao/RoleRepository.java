package com.ruslan.userDao;

import com.ruslan.database.DAO.RepositoryBase;
import com.ruslan.userEntity.Role;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepository extends RepositoryBase<Integer, Role> {

    private final EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public RoleRepository(EntityManager entityManager) {
        super(Role.class);
        this.entityManager = entityManager;
    }

    public Optional<Role> findByName(String name) {
        Role role = getSession().createQuery("select name" +
                        " from Role o where name=:roleName", Role.class)
                .setParameter("roleName", name).uniqueResult();
        return Optional.ofNullable(role);
    }

}
