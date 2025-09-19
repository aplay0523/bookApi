package com.library.book_api.repository;

import com.library.book_api.controller.entity.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBookId(Long bookId);
}
