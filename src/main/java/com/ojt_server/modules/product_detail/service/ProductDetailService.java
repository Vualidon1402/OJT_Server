package com.ojt_server.modules.product_detail.service;

import com.ojt_server.modules.color.repository.ColorRepository;
import com.ojt_server.modules.config.repository.ConfigRepository;
import com.ojt_server.modules.product.ProductModel;
import com.ojt_server.modules.product.repository.ProductRepository;
import com.ojt_server.modules.product_detail.ProductDetailModel;
import com.ojt_server.modules.product_detail.dto.request.ProductDetailDTO;
import com.ojt_server.modules.product_detail.repository.ProductDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ConfigRepository configRepository;

    // Get all product details
    public List<ProductDetailModel> findAllProductDetails() {
        return productDetailRepository.findAll();
    }

    // Add a product detail
    public ProductDetailModel createProductDetail(ProductDetailDTO productDetailDTO) {
        ProductDetailModel detail = new ProductDetailModel();
        detail.setImage(productDetailDTO.getImage());
        detail.setProductDetailName(productDetailDTO.getProductDetailName());
        detail.setStock(productDetailDTO.getStock());
        detail.setUnitPrice(productDetailDTO.getUnitPrice());
        detail.setColor(colorRepository.findById(productDetailDTO.getColorId())
                .orElseThrow(() -> new RuntimeException("Color not found")));
        detail.setProduct(productRepository.findById(productDetailDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found")));
        detail.setConfig(configRepository.findById(productDetailDTO.getConfigId())
                .orElseThrow(() -> new RuntimeException("Product not found")));
        return productDetailRepository.save(detail);
    }

    // Get product detail by id
    public ProductDetailModel findProductDetailById(Long id) {
        return productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product detail not found"));
    }

    // Update product detail
    public ProductDetailModel updateProductDetail(ProductDetailDTO productDetailDTO) {
        ProductDetailModel detail = productDetailRepository.findById(productDetailDTO.getId())
                .orElseThrow(() -> new RuntimeException("Product detail not found"));
        detail.setImage(productDetailDTO.getImage());
        detail.setProductDetailName(productDetailDTO.getProductDetailName());
        detail.setStock(productDetailDTO.getStock());
        detail.setUnitPrice(productDetailDTO.getUnitPrice());
        detail.setStatus(productDetailDTO.isStatus());
        detail.setColor(colorRepository.findById(productDetailDTO.getColorId())
                .orElseThrow(() -> new RuntimeException("Color not found")));
        detail.setConfig(configRepository.findById(productDetailDTO.getConfigId())
                .orElseThrow(() -> new RuntimeException("Config not found")));
        return productDetailRepository.save(detail);
    }

    //find product details by product id
    public List<ProductDetailModel> findProductDetailsByProductId(Long productId) {
        return productDetailRepository.findByProductId(productId);
    }

    //find product detail by product detail id
    public ProductDetailModel findProductDetailByProductDetailId(Long productDetailId) {
        return productDetailRepository.findById(productDetailId)
                .orElseThrow(() -> new RuntimeException("Product detail not found"));
    }

    // Update product detail status instead of deleting
    public ProductDetailModel deleteProductDetail(Long productDetailId) {
        ProductDetailModel productDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(() -> new RuntimeException("Product Detail not found"));
        productDetail.setStatus(false);
        return productDetailRepository.save(productDetail);
    }

}
