package com.ojt_server.modules.cart;



import com.ojt_server.modules.cart.dto.req.CartAddRequest;
import com.ojt_server.modules.cart.dto.req.CartUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemController {
    @Autowired
    private CartItemService cartService;

//    thêm sản phẩm vào giỏ hàng
@PostMapping("/add")
public ResponseEntity<Cart> addToCart(@RequestBody CartAddRequest request) {
    System.out.println("userId: " + request.getUserId());
    System.out.println("productDetailId: " + request.getProductDetailId());
    System.out.println("quantity: " + request.getQuantity());
    try {
        return ResponseEntity.ok(cartService.addToCart(request.getUserId(), request.getProductDetailId(), request.getQuantity()));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(null);
    }
}
//hiện thị sản phẩm trong giỏ hàng
@GetMapping("/user/{userId}")
public ResponseEntity<List<Cart>> getCartItemsByUserId(@PathVariable Long userId) {
    try {
        List<Cart> cartItems = cartService.getCartItemsByUserId(userId);
        if (cartItems.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cartItems);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
//update cart
@PutMapping("/update")
public ResponseEntity<Cart> updateCart(@RequestBody CartUpdateRequest request) {
    try {
        return ResponseEntity.ok(cartService.updateCart(request.getId(), request.getQuantity()));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(null);
    }
}

////tang so luong san pham trong gio hang
//@PutMapping("/increase")
//public ResponseEntity<Cart> increaseQuantity(@RequestBody CartUpdateRequest request) {
//    try {
//        return ResponseEntity.ok(cartService.increaseQuantity(request.getUserId(), request.getProductDetailId(), request.getIncreaseAmount())); // Sử dụng getIncreaseAmount()
//    } catch (Exception e) {
//        return ResponseEntity.badRequest().body(null);
//    }
//}
}
