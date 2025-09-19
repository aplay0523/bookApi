package com.library.book_api.repository;

import com.library.book_api.controller.entity.BookRelation;
import com.library.book_api.controller.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<BookRelation, Long> {

    boolean existsByCategory(Category category);
}
