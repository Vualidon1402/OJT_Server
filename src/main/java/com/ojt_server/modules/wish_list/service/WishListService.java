package com.ojt_server.modules.wish_list.service;

import com.ojt_server.modules.product.ProductModel;
import com.ojt_server.modules.product.repository.ProductRepository;
import com.ojt_server.modules.user.model.UserModel;
import com.ojt_server.modules.user.reqository.UserRepository;
import com.ojt_server.modules.wish_list.model.WishListModel;
import com.ojt_server.modules.wish_list.repository.WishListRepository;
import com.ojt_server.util.DuplicateWishListException;
import com.ojt_server.util.ProductNotFoundException;
import com.ojt_server.util.UserNotFoundException;
import com.ojt_server.util.WishListNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WishListService {
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    //thêm danh sách yêu thích
    public WishListModel addWishList(Long userId, Long productId) throws Exception {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        // Kiểm tra xem sản phẩm đã có trong danh sách yêu thích chưa
        if (wishListRepository.existsByUserAndProduct(user, product)) {
            // Nếu sản phẩm đã tồn tại, xóa sản phẩm khỏi danh sách yêu thích
            Optional<WishListModel> optionalWishList = wishListRepository.findByUserAndProduct(user, product);
            if (optionalWishList.isPresent()) {
                WishListModel existingWishList = optionalWishList.get();
                wishListRepository.delete(existingWishList);
                return null;
            }
        }

        WishListModel wishList = new WishListModel();
        wishList.setUser(user);
        wishList.setProduct(product);
        wishList.setCreatedAt(new Date());
        return wishListRepository.save(wishList);
    }

    //hiển thị danh sách yêu thích
    public List<WishListModel> getWishList(Long userId) {
        return wishListRepository.findAllByUser(userId);
    }

    //xóa sản phẩm khỏi danh sách yêu thích
    public void removeWishList(Long productId) throws Exception {
        Optional<WishListModel> optionalWishList = wishListRepository.findById(productId);
        if (optionalWishList.isPresent()) {
            // Nếu sản phẩm tồn tại, xóa sản phẩm khỏi danh sách yêu thích
            WishListModel existingWishList = optionalWishList.get();
            wishListRepository.delete(existingWishList);
        } else {
            throw new WishListNotFoundException("Product not found in wish list");
        }
    }



}
