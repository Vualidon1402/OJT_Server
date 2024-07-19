package com.ojt_server.modules.category.repository;

import com.ojt_server.modules.category.CategoryModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CategoryModel> findAll() {
        return entityManager.createQuery("select c from CategoryModel c ORDER BY c.id DESC", CategoryModel.class)
                .getResultList();
    }
}
