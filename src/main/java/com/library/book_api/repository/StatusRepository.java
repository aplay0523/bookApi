package com.library.book_api.repository;

import com.library.book_api.controller.entity.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<BookStatus, Long> {

}
