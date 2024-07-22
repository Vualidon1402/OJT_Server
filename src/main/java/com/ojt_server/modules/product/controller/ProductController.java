package com.ojt_server.modules.product.controller;

import com.ojt_server.modules.product.ProductModel;
import com.ojt_server.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
