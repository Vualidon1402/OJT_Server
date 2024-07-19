package com.ojt_server.modules.category.controller;

import com.ojt_server.modules.category.CategoryModel;
import com.ojt_server.modules.category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/admin/category")
    public List<CategoryModel> getAllCategory() {
        return categoryService.findAll();
    }
}
