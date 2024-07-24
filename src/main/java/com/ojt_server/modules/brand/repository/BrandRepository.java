package com.ojt_server.modules.brand.repository;

import com.ojt_server.modules.brand.BrandModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BrandRepository extends JpaRepository<BrandModel, Long> {
}
