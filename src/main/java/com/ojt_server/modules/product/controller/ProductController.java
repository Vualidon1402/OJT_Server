package com.ojt_server.modules.product.controller;

import com.ojt_server.modules.product.ProductModel;
import com.ojt_server.modules.product.dto.request.ProductModelDTO;
import com.ojt_server.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ProductModel addProduct(@RequestBody ProductModelDTO product) {
        return productService.createProduct(product);
    }

    //Update product
    @PutMapping("/update")
    public ProductModel updateProduct(@RequestBody ProductModelDTO product) {
        return productService.updateProduct(product);
    }

    //Delete product

    //find product by id
    @GetMapping("/get/{id}")
    public ProductModel getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
