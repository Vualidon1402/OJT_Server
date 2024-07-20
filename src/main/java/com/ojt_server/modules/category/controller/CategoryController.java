package com.ojt_server.modules.category.controller;

import com.ojt_server.modules.category.CategoryModel;
import com.ojt_server.modules.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findAll")
    public List<CategoryModel> getAllCategory() {
        return categoryService.findCategories();
    }

    // Add new category
    @PostMapping("/add")
    public CategoryModel addCategory(@RequestBody CategoryModel category){
        return categoryService.addCategory(category);
    }

    // Update category
    @PutMapping("/update")
    public CategoryModel updateCategory (@RequestBody CategoryModel category){
        return categoryService.updateCategory(category);
    }

    // Update category status instead of deleting
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

}
