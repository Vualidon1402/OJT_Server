package com.ojt_server.modules.product.repository;

import com.ojt_server.modules.product.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
