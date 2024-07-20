package com.ojt_server.modules.category.repository;

import com.ojt_server.modules.category.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
}
