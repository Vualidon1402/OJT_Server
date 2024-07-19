package com.ojt_server.modules.user.reqository;

import com.ojt_server.modules.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel ,Long> {


}
