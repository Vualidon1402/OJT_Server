package com.ojt_server.modules.category.service;

import com.ojt_server.modules.category.CategoryModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryModel> findAll();
}
