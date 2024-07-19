package com.ojt_server.modules.category.service;

import com.ojt_server.modules.category.CategoryModel;
import com.ojt_server.modules.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryModel> findAll() {
        return categoryRepository.findAll();
    }
}
