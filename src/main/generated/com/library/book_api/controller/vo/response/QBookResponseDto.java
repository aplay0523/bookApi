package com.library.book_api.controller.vo.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.library.book_api.controller.vo.response.QBookResponseDto is a Querydsl Projection type for BookResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBookResponseDto extends ConstructorExpression<BookResponseDto> {

    private static final long serialVersionUID = 306456181L;

    public QBookResponseDto(com.querydsl.core.types.Expression<? extends com.library.book_api.controller.entity.Book> book, com.querydsl.core.types.Expression<? extends java.util.List<com.library.book_api.controller.entity.Category>> categoryList, com.querydsl.core.types.Expression<? extends com.library.book_api.controller.entity.BookStatus> bookStatus) {
        super(BookResponseDto.class, new Class<?>[]{com.library.book_api.controller.entity.Book.class, java.util.List.class, com.library.book_api.controller.entity.BookStatus.class}, book, categoryList, bookStatus);
    }

}

