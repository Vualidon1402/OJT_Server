package com.ojt_server.modules.image.repository;

import com.ojt_server.modules.image.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
}
