package com.ojt_server.modules.wish_list.repository;

import com.ojt_server.modules.product.ProductModel;
import com.ojt_server.modules.user.model.UserModel;
import com.ojt_server.modules.wish_list.model.WishListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishListModel, Long> {
    // Phương thức này sẽ tìm kiếm WishListModel dựa trên user và product
    Optional<WishListModel> findByUserAndProduct(UserModel user, ProductModel product);

    // Phương thức này sẽ kiểm tra xem có tồn tại WishListModel với user và product cụ thể không
    boolean existsByUserAndProduct(UserModel user, ProductModel product);
    //hiển thị danh sách yêu thích
    @Query("SELECT w FROM WishListModel w WHERE w.user.id = :userId")
    List<WishListModel> findAllByUser(@Param("userId") Long userId);

}
