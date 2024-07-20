package com.ojt_server.modules.user.reqository;

import com.ojt_server.modules.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel ,Long> {
    //tìm kiếm user theo useName
    @Query("SELECT u FROM UserModel u WHERE u.username LIKE %:username%")
    List<UserModel> findByUsernameContains(@Param("username") String username);

}
