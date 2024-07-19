package com.ojt_server.modules.category.repository;

import com.ojt_server.modules.category.CategoryModel;

import java.util.List;

public interface CategoryRepository {
    List<CategoryModel> findAll();
}
