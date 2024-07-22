package com.ojt_server.modules.product.service;

import com.ojt_server.modules.product.ProductModel;
import com.ojt_server.modules.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // View all products
    public List<ProductModel> findProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
