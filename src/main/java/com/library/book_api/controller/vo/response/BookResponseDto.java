package com.library.book_api.controller.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.book_api.controller.entity.Book;
import com.library.book_api.controller.entity.BookStatus;
import com.library.book_api.controller.entity.Category;
import com.querydsl.core.annotations.QueryProjection;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookResponseDto {

    private Long bookId;
    private String title;
    private String author;
    private String status;

    @JsonProperty("categories")
    private final List<CategoryResponseDto> categoryResponseDto = new ArrayList();

    // 상태값 null 일시 사용으로 표기
    @QueryProjection
    public BookResponseDto(Book book, List<Category> categoryList, BookStatus bookStatus) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.status = book.getBookStatus() != null ? bookStatus.getStatusName() : "사용";

        categoryList.forEach(
                category -> this.categoryResponseDto.add(
                        CategoryResponseDto.builder()
                                .categoryId(category.getCategoryId())
                                .categoryName(category.getCategoryName())
                                .build()

                )
        );
    }
}
