package com.ojt_server.modules.user.reqository;

import com.ojt_server.modules.user.model.Role;
import com.ojt_server.modules.user.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(RoleName name);
}
