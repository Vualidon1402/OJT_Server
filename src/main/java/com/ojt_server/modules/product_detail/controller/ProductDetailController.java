package com.ojt_server.modules.product_detail.controller;

import com.ojt_server.modules.product_detail.ProductDetailModel;
import com.ojt_server.modules.product_detail.dto.request.ProductDetailDTO;
import com.ojt_server.modules.product_detail.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-detail")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    // get all product details
    @GetMapping("/findAll")
    public List<ProductDetailModel> findAllProductDetails() {
        return productDetailService.findAllProductDetails();
    }

    // add a product detail
    @PostMapping("/add")
    public ProductDetailModel addProductDetail(@RequestBody ProductDetailDTO productDetailDTO) {
        return productDetailService.createProductDetail(productDetailDTO);
    }
}
