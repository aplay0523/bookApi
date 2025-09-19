package com.library.book_api.repository;

import com.library.book_api.controller.entity.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryId(Long categoryId);

    List<Category> findByCategoryIdIn(List<Long> categoryIds);

    List<Category> findByCategoryName(String categoryName);
}
