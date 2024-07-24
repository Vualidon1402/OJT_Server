package com.ojt_server.modules.category.service;

import com.ojt_server.modules.category.CategoryModel;
import com.ojt_server.modules.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> findCategories() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Add new category
    public CategoryModel addCategory(CategoryModel category) {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update category
    public CategoryModel updateCategory(CategoryModel category) {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update category status instead of deleting
    public void deleteCategory(Long id) {
        try {
            categoryRepository.findById(id).ifPresent(category -> {
                category.setStatus(false);
                categoryRepository.save(category);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}