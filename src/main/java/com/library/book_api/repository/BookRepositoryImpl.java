package com.library.book_api.repository;

import com.library.book_api.controller.entity.QBook;
import com.library.book_api.controller.entity.QBookRelation;
import com.library.book_api.controller.entity.QBookStatus;
import com.library.book_api.controller.entity.QCategory;
import com.library.book_api.controller.vo.response.BookResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

// 서비스에서 구현으로 미사용
/*@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QBook book = QBook.book;
    private final QBookRelation bookRelation = QBookRelation.bookRelation;
    private final QCategory category = QCategory.category;
    private final QBookStatus bookStatus = QBookStatus.bookStatus;


    @Override
    public List<BookResponseDto> searchBooks(String title, String author, String categoryName) {

        return jpaQueryFactory
                .selectFrom(book)
                .leftJoin(book.bookRelation, bookRelation).fetchJoin()
                .leftJoin(bookRelation.category, category).fetchJoin()
                .leftJoin(book.bookStatus, bookStatus).fetchJoin()
                .where(
                        title != null ? book.title.containsIgnoreCase(title) : null,
                        author != null ? book.author.containsIgnoreCase(author) : null,
                        categoryName != null ? category.categoryName.eq(categoryName) : null
                )
                .distinct()
                .transform(
                        groupBy(book.bookId).list(
                                Projections.constructor(BookResponseDto.class
                                        , book
                                        , list(category)
                                        , bookStatus
                                )
                        )
                );
    }
}*/
