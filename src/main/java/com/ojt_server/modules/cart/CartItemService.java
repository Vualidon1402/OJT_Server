package com.ojt_server.modules.cart;


import com.ojt_server.modules.product_detail.ProductDetailModel;
import com.ojt_server.modules.product_detail.repository.ProductDetailRepository;
import com.ojt_server.modules.user.model.UserModel;
import com.ojt_server.modules.user.reqository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;


//    thêm sản phẩm vào giỏ hàng
    public Cart addToCart(Long userId, Long productDetailId, int quantity) {
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng hay chưa
        Optional<Cart> existingCartItem = cartRepository.findByUserIdAndProductDetailId(userId, productDetailId);

        if (existingCartItem.isPresent()) {
            // Nếu sản phẩm đã tồn tại, cập nhật số lượng
            Cart cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            return cartRepository.save(cartItem);
        } else {
            // Nếu sản phẩm chưa tồn tại, tạo một mục giỏ hàng mới
            Cart cartItem = new Cart();
            UserModel user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
            cartItem.setUser(user);

            // Lấy đối tượng ProductDetailModel từ cơ sở dữ liệu
            ProductDetailModel productDetail = productDetailRepository.findById(productDetailId)
                    .orElseThrow(() -> new RuntimeException("ProductDetail not found with id: " + productDetailId));
            cartItem.setProductDetail(productDetail);

            cartItem.setQuantity(quantity);
            return cartRepository.save(cartItem);
        }
    }

    //hiện thị sản phẩm trong giỏ hàng
    public List<Cart> getCartItemsByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    //update cart
    public Cart updateCart(Long cartId, int quantity) {
        Optional<Cart> existingCartItem = cartRepository.findById(cartId);
        if (existingCartItem.isPresent()) {
            Cart cartItem = existingCartItem.get();
            cartItem.setQuantity(quantity);
            return cartRepository.save(cartItem);
        } else {
            throw new RuntimeException("Cart item not found");
        }
    }
//    //tang so luong san pham trong gio hang
//    public Cart increaseQuantity(Long userId, Long productDetailId, int increaseAmount) {
//        Optional<Cart> existingCartItem = cartRepository.findByUserIdAndProductDetailId(userId, productDetailId);
//        if (existingCartItem.isPresent()) {
//            Cart cartItem = existingCartItem.get();
//            cartItem.setQuantity(cartItem.getQuantity() + increaseAmount); // Sử dụng increaseAmount
//            return cartRepository.save(cartItem);
//        } else {
//            throw new RuntimeException("Cart item not found");
//        }
//    }
}
