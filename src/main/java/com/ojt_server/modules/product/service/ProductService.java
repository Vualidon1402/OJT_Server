package com.ojt_server.modules.product.service;

import com.ojt_server.modules.brand.repository.BrandRepository;
import com.ojt_server.modules.category.repository.CategoryRepository;
import com.ojt_server.modules.image.ImageModel;
import com.ojt_server.modules.image.repository.ImageRepository;
import com.ojt_server.modules.product.ProductModel;
import com.ojt_server.modules.product.dto.request.ProductModelDTO;
import com.ojt_server.modules.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ImageRepository imageRepository;

    // View all products
    public List<ProductModel> findProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProductModel createProduct(ProductModelDTO productDto) {
        ProductModel product = new ProductModel();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setUpdatedAt(new Date());
        product.setSku(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        product.setCategory(categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));
        product.setBrand(brandRepository.findById(productDto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found")));
        ProductModel savedProduct = productRepository.save(product);

        List<ImageModel> productImageModels = new ArrayList<>();
        if (productDto.getImages() != null && !productDto.getImages().isEmpty()) {
            productImageModels = productDto.getImages().stream()
                    .map(src -> {
                        ImageModel img = new ImageModel();
                        img.setSrc(src);
                        img.setProduct(savedProduct);
                        return img;
                    })
                    .collect(Collectors.toList());

            productImageModels = imageRepository.saveAll(productImageModels);
        }

        savedProduct.setImages(productImageModels);

        return savedProduct;
    }

    public ProductModel updateProduct(ProductModelDTO productDto) {
        ProductModel product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setSku(productDto.getSku());
        product.setUpdatedAt(new Date());
        product.setStatus(productDto.isStatus());
        product.setCategory(categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));
        product.setBrand(brandRepository.findById(productDto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found")));

        imageRepository.deleteAll(product.getImages());

        List<ImageModel> newImages = productDto.getImages().stream()
                .map(src -> {
                    ImageModel img = new ImageModel();
                    img.setSrc(src);
                    img.setProduct(product);
                    return img;
                })
                .collect(Collectors.toList());

        newImages = imageRepository.saveAll(newImages);
        product.setImages(newImages);

        return productRepository.save(product);
    }

    //find product by productId
    public ProductModel getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    // Update category status instead of deleting
    public ProductModel deleteProduct(Long productId) {
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStatus(false);
        return productRepository.save(product);
    }

}
