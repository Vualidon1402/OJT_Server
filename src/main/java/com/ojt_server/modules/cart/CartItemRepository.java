package com.ojt_server.modules.cart;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CartItemRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserIdAndProductDetailId(Long userId, Long productDetailId);
    //hiện thị sản phẩm trong giỏ hàng
    List<Cart> findAllByUserId(Long userId);
}
