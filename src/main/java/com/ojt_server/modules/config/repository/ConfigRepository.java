package com.ojt_server.modules.config.repository;

import com.ojt_server.modules.config.ConfigModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<ConfigModel, Long> {
}
