package com.ojt_server.modules.product.controller;

import com.ojt_server.modules.product.ProductModel;
import com.ojt_server.modules.product.dto.request.ProductModelDTO;
import com.ojt_server.modules.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //View all products
    @GetMapping("/findAll")
    public List<ProductModel> getAllProduct() {
        return productService.findProducts();
    }

    //Add new product
    @PostMapping("/add")
    public ProductModel addProduct(@Valid @RequestBody ProductModelDTO product) {
        return productService.createProduct(product);
    }

    //Update product
    @PutMapping("/update")
    public ProductModel updateProduct(@RequestBody ProductModelDTO product) {
        return productService.updateProduct(product);
    }

    // Update category status instead of deleting
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    //find product by id
    @GetMapping("/get/{id}")
    public ProductModel getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    //find product by status
    @GetMapping("/getByStatus/{status}/{page}/{size}")
    public Page<ProductModel> getProductByStatus(@PathVariable boolean status, @PathVariable int page, @PathVariable int size) {
        return productService.findProductByStatus(status, page, size);
    }

    //Relative product search
    @GetMapping("/search/{productName}")
    public List<ProductModel> searchProduct(@PathVariable String productName) {
        return productService.searchProduct(productName);
    }

    //Pagination product
    @GetMapping("/pagination/{page}/{size}")
    public Page<ProductModel> paginationProduct(@PathVariable int page, @PathVariable int size) {
        return productService.paginationProduct(page, size);
    }
}

