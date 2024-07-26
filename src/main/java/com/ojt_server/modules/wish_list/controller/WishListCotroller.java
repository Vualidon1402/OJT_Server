package com.ojt_server.modules.wish_list.controller;

import com.ojt_server.modules.wish_list.dto.WishListDTO;
import com.ojt_server.modules.wish_list.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishListCotroller {
    @Autowired
    private WishListService wishListService;

    //thêm danh sách yêu thích
    @PostMapping("/addWishList")
    public ResponseEntity<Object> addWishList(@RequestBody WishListDTO request) {
        System.out.println("addWishList"+request.getUserId() + " " + request.getProductId());
        try{
            wishListService.addWishList(request.getUserId(), request.getProductId());

            return ResponseEntity.ok("Add wish list successfully");
        }catch (Exception e){

            return ResponseEntity.badRequest().body("Add wish list failed");
        }
    }
}
