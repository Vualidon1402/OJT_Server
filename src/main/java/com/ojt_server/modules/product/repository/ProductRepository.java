package com.ojt_server.modules.product.repository;

import com.ojt_server.modules.product.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    //find product by status
    List<ProductModel> findByStatus(boolean status);

    boolean existsByProductName(String productName);

    //Relative product search
    List<ProductModel> findByProductNameContaining(String productName);

    //find product by status and pagination
    Page<ProductModel> findByStatus(boolean status, Pageable pageable);


    //hiện thi sản phẩm với 8 sản phẩm mới nhất
    List<ProductModel> findTop8ByStatusOrderByCreatedAtDesc(boolean status);

    //lấy danh sách sản phẩm theo danh mục vơi status là true
    List<ProductModel> findByCategory_IdAndStatus(Long categoryId, boolean status);

}
