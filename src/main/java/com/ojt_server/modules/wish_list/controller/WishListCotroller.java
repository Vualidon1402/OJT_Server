package com.ojt_server.modules.wish_list.controller;

import com.ojt_server.modules.wish_list.dto.WishListDTO;
import com.ojt_server.modules.wish_list.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WishListCotroller {
    @Autowired
    private WishListService wishListService;

    //thêm danh sách yêu thích
    @PostMapping("/addWishList")
    public ResponseEntity<Object> addWishList(@RequestBody WishListDTO request) {
        System.out.println("request: " + request.getUserId() + " " + request.getProductId());

        try{
            wishListService.addWishList(request.getUserId(), request.getProductId());
            System.out.println("Add wish list successfully");

            return ResponseEntity.ok("Add wish list successfully");
        }catch (Exception e){
            System.out.println(e);

            return ResponseEntity.badRequest().body("Add wish list failed");
        }
    }

    //hiển thị danh sách yêu thích theo user
    @GetMapping("/getWishList/{userId}")
    public ResponseEntity<Object> getWishList(@PathVariable Long userId) {
        System.out.println("userId: " + userId);
        try{
            return ResponseEntity.ok(wishListService.getWishList(userId));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("Get wish list failed");
        }
    }

    //xóa sản phẩm khỏi danh sách yêu thích
    @DeleteMapping("/deleteWishList/{productId}")
    public ResponseEntity<Object> deleteWishList(@PathVariable Long productId) {
        System.out.println("productId: " + productId);
        try{
            wishListService.removeWishList(productId);
            return ResponseEntity.ok("Delete wish list successfully");
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("Delete wish list failed");
        }
    }
}
