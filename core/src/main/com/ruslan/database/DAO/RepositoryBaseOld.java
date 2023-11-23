package com.ruslan.database.DAO;

import com.ruslan.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBaseOld<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {
    @Getter
    private final EntityManager entityManager;
    private final Class<E> clazz;


    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        entityManager.remove(id);
        entityManager.flush();
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public Optional<E> findById(K id, Map<String, Object> properties) {
        return Optional.ofNullable(entityManager.find(clazz, id, properties));
    }

    @Override
    public List<E> findAll() {
        var criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteria.from(clazz);
        return entityManager.createQuery(criteria).getResultList();
    }

}
