package com.library.book_api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.library.book_api.controller.entity.Book;
import com.library.book_api.controller.entity.Category;
import com.library.book_api.repository.BookRepository;
import com.library.book_api.repository.CategoryRepository;
import com.library.book_api.repository.StatusRepository;
import com.library.book_api.service.BookService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class bookTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void 도서_저장() {
        Book book = new Book(1L, "문학 도서", "지은이", null, new ArrayList<>());

        Category category1 = new Category(1L, "문학");
        Category category2 = new Category(2L, "역사");

        List<Long> categoryIds = List.of(1L, 2L);

        // findByCategoryIdIn 대신 category1, category2 반환
        when(categoryRepository.findByCategoryIdIn(categoryIds))
                .thenReturn(List.of(category1, category2));
        bookService.saveBook(book, categoryIds);

        // 1회 호출 검증
        verify(bookRepository, times(1)).save(book);

        assertThat(book.getBookRelation())
                .hasSize(2)
                .anySatisfy(rel -> {
                    assertThat(rel.getBook()).isSameAs(book);
                    assertThat(rel.getCategory()).isSameAs(category1);
                })
                .anySatisfy(rel -> {
                    assertThat(rel.getBook()).isSameAs(book);
                    assertThat(rel.getCategory()).isSameAs(category2);
                });
    }

    @Test
    void 도서_카테고리_변경() {
        Book book = new Book(1L, "문학 도서", "지은이", null, new ArrayList<>());

        Category oldCategory = new Category(1L, "문학");
        book.addCategory(oldCategory);

        Category newCategory1 = new Category(2L, "역사");
        Category newCategory2 = new Category(3L, "과학");
        List<Long> categoryIds = List.of(2L, 3L);

        when(bookRepository.findByBookId(1L)).thenReturn(Optional.of(book));
        when(categoryRepository.findByCategoryIdIn(categoryIds)).thenReturn(List.of(newCategory1, newCategory2));

        bookService.updateBookCategory(1L, categoryIds);

        verify(bookRepository, times(1)).findByBookId(1L);
        verify(categoryRepository, times(1)).findByCategoryIdIn(categoryIds);

        assertThat(book.getBookRelation())
                .hasSize(2)
                .anySatisfy(rel -> {
                    assertThat(rel.getBook()).isSameAs(book);
                    assertThat(rel.getCategory()).isSameAs(newCategory1);
                })
                .anySatisfy(rel -> {
                    assertThat(rel.getBook()).isSameAs(book);
                    assertThat(rel.getCategory()).isSameAs(newCategory2);
                });
    }

    @Test
    void 도서_상태변경() {
        Book book = new Book(1L, "문학 도서", "지은이", null, new ArrayList<>());
        String statusName = "훼손";
        String reason = "x월x일 훼손";

        when(bookRepository.findByBookId(1L)).thenReturn(Optional.of(book));

        bookService.updateBookStatus(1L, statusName, reason);

        verify(bookRepository, times(1)).findByBookId(1L);
        verify(statusRepository, times(1)).save(book.getBookStatus());

        assertThat(book.getBookStatus()).isNotNull();
        assertThat(book.getBookStatus().getStatusName()).isEqualTo(statusName);
        assertThat(book.getBookStatus().getReason()).isEqualTo(reason);

    }

    @Test
    void 도서_삭제() {
        Long bookId = 1L;

        bookService.delBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
