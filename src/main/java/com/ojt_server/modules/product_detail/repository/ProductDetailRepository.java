package com.ojt_server.modules.product_detail.repository;

import com.ojt_server.modules.product.ProductModel;
import com.ojt_server.modules.product_detail.ProductDetailModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetailModel, Long> {

    //findByProductId
    List<ProductDetailModel> findByProductId(Long productId);
}
