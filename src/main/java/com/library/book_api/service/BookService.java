package com.library.book_api.service;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import com.library.book_api.common.ErrorCode;
import com.library.book_api.common.exception.CustomException;
import com.library.book_api.controller.entity.Book;
import com.library.book_api.controller.entity.BookRelation;
import com.library.book_api.controller.entity.BookStatus;
import com.library.book_api.controller.entity.Category;
import com.library.book_api.controller.entity.QBook;
import com.library.book_api.controller.entity.QBookRelation;
import com.library.book_api.controller.entity.QBookStatus;
import com.library.book_api.controller.entity.QCategory;
import com.library.book_api.controller.vo.response.BookResponseDto;
import com.library.book_api.repository.BookRepository;
import com.library.book_api.repository.CategoryRepository;
import com.library.book_api.repository.RelationRepository;
import com.library.book_api.repository.StatusRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final RelationRepository bookRel;
    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QBook qBook = QBook.book;
    private final QBookRelation qBookRelation = QBookRelation.bookRelation;
    private final QCategory qCategory = QCategory.category;
    private final QBookStatus qBookStatus = QBookStatus.bookStatus;

    // 도서 조회
    public List<BookResponseDto> searchBooks(String title, String author, String categoryName) {
        return jpaQueryFactory
                .selectFrom(qBook)
                .leftJoin(qBook.bookRelation, qBookRelation).fetchJoin()
                .leftJoin(qBookRelation.category, qCategory).fetchJoin()
                .leftJoin(qBook.bookStatus, qBookStatus).fetchJoin()
                .where(
                        title != null ? qBook.title.containsIgnoreCase(title) : null,
                        author != null ? qBook.author.containsIgnoreCase(author) : null,
                        categoryName != null ? qCategory.categoryName.eq(categoryName) : null
                )
                .distinct()
                .transform(
                        groupBy(qBook.bookId).list(
                                Projections.constructor(BookResponseDto.class
                                        , qBook
                                        , list(qCategory)
                                        , qBookStatus
                                )
                        )
                );
    }

    // 도서 저장
    @Transactional
    public void saveBook(Book book, List<Long> categoryIds) {
        bookRepository.save(book);

        List<Category> categories = categoryRepository.findByCategoryIdIn(categoryIds);

        if(categories.size() != categoryIds.size()){
            throw new CustomException(ErrorCode.BAD_REQUEST, "등록된 정보가 없습니다.");
        }

        for(Category category : categories) {
            book.addCategory(category);
        }

    }

    // 도서 카테고리 변경
    @Transactional
    public void updateBookCategory(Long bookId, List<Long> categoryIds) {

        Book book = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new CustomException(ErrorCode.BAD_REQUEST, "등록된 정보가 없습니다."));

        List<Category> categories = categoryRepository.findByCategoryIdIn(categoryIds);

        if(categories.size() != categoryIds.size()) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "등록된 정보가 없습니다.");
        }

        book.getBookRelation().clear();

        for(Category category : categories) {
            book.addCategory(category);
        }

    }

    // 도서 상태 변경
    public void updateBookStatus(Long bookId, String statusName, String reason) {
        Book book = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new CustomException(ErrorCode.BAD_REQUEST, "등록된 정보가 없습니다."));

        BookStatus bookStatus = book.getBookStatus();

        if(bookStatus == null) {
            bookStatus = new BookStatus();
            bookStatus.setBook(book);
            book.setBookStatus(bookStatus);
        }
        bookStatus.setStatusName(statusName);
        bookStatus.setReason(reason);

        statusRepository.save(bookStatus);
    }

    // 도서 삭제
    public void delBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
