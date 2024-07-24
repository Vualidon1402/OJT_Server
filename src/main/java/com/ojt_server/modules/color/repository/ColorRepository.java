package com.ojt_server.modules.color.repository;

import com.ojt_server.modules.color.ColorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<ColorModel, Long> {
}
