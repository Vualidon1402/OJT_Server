package com.ojt_server.modules.category.repository;

import com.ojt_server.modules.category.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

    //hiện thị danh mục với trạng thái là true
    List<CategoryModel> findByStatus(boolean status);

    //lấy danh sách sản phẩm theo danh mục vơi status là true

}
