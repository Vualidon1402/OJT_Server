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
        System.out.println("productDetailDTO: " + productDetailDTO);
        try{
            return productDetailService.createProductDetail(productDetailDTO);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    //findByProductId
    @GetMapping("/findByProductId/{productId}")
    public List<ProductDetailModel> findByProductId(@PathVariable Long productId) {
        System.out.println("productId: " + productId);
        return productDetailService.findProductDetailsByProductId(productId);
    }

    //find product detail by product detail id
    @GetMapping("/findById/{id}")
    public ProductDetailModel findProductDetailById(@PathVariable Long id) {
        return productDetailService.findProductDetailById(id);
    }

    // update product detail
    @PutMapping("/update")
    public ProductDetailModel updateProductDetail(@RequestBody ProductDetailDTO productDetailDTO) {
        return productDetailService.updateProductDetail(productDetailDTO);
    }

    // Update product detail status instead of deleting
    @DeleteMapping("/delete/{id}")
    public ProductDetailModel deleteProductDetail(@PathVariable Long id) {
        return productDetailService.deleteProductDetail(id);
    }
}
