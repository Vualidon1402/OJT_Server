package com.ojt_server.modules.category.controller;

import com.ojt_server.modules.category.CategoryModel;
import com.ojt_server.modules.category.dto.request.CategoryModelDTO;
import com.ojt_server.modules.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public CategoryModel addCategory(@RequestBody CategoryModelDTO categoryDto){
        return categoryService.addCategory(categoryDto);

    }

    // Update category
    @PutMapping("/update")
    public CategoryModel updateCategory (@RequestBody CategoryModelDTO categoryDto){
        return categoryService.updateCategory(categoryDto);
    }

    // Update category status instead of deleting
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

    //hiện thị danh mục với trạng thái mặc đinh là true
    @GetMapping("/getByStatus")
    public ResponseEntity<List<CategoryModel>> getCategoryByStatus(@RequestParam(defaultValue = "true") boolean status) {
        try{
            return ResponseEntity.ok(categoryService.findCategoryByStatus(status));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }



}
