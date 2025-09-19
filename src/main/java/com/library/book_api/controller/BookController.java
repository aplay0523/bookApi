package com.library.book_api.controller;

import com.library.book_api.controller.entity.Book;
import com.library.book_api.controller.vo.request.BookRequestDto;
import com.library.book_api.controller.vo.request.CategoryUpdateRequestDto;
import com.library.book_api.controller.vo.request.StatusRequestDto;
import com.library.book_api.controller.vo.response.ResponseBodyVo;
import com.library.book_api.controller.vo.response.ResponseListVo;
import com.library.book_api.controller.vo.response.ResponseSimpleVo;
import com.library.book_api.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "도서", description = "")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @Operation(
            summary = "도서 검색",
            description = """
                    - 도서 검색을 진행합니다.
                    
                    1. title like 도서 명
                    2. author like 지은이
                    3. categoryName = 카테고리 명
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @GetMapping(value = "/books")
    public ResponseEntity<?> getBooks(@RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "categoryName", required = false) String categoryName) {

        return ResponseEntity.ok().body(
                new ResponseBodyVo(
                        new ResponseListVo<>(bookService.searchBooks(title, author, categoryName)),
                        HttpStatus.OK.value(), "성공"
                )
        );
    }

    @Operation(
            summary = "도서 등록",
            description = """
                    - 도서 등록을 진행합니다.
                    
                    body
                    1. title : 도서 명
                    2. author : 지은이
                    3. categoryId : 카테고리 키 (1,2,3) 콤마로 구분
                    (1 - 문학, 2 - 경제경영, 3 - 인문학, 4 - IT, 5 - 과학)
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @PostMapping(value = "/book")
    public ResponseEntity<?> setBook(@Valid @RequestBody BookRequestDto bookRequestDto) {

        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());

        List<Long> categoryIds = bookRequestDto.getCategoryIds();

        bookService.saveBook(book, categoryIds);

        return ResponseEntity.ok().body(
                new ResponseSimpleVo(
                        HttpStatus.OK.value(), "성공"
                )
        );
    }

    @Operation(
            summary = "도서 카테고리 변경",
            description = """
                    - 도서 카테고리 변경을 진행합니다.
                    
                    1. bookId : 도서 키
                    2. categoryName : 카테고리 명 (문학,인문학,IT) 콤마로 구분
                    (1 - 문학, 2 - 경제경영, 3 - 인문학, 4 - IT, 5 - 과학)
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @PatchMapping("/book-category/{bookId}")
    public ResponseEntity<?> setBookCategory(@PathVariable("bookId") Long bookId, @Valid @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto) {
        List<Long> categoryIds = categoryUpdateRequestDto.getCategoryIds();
        bookService.updateBookCategory(bookId, categoryIds);

        return ResponseEntity.ok().body(
                new ResponseSimpleVo(
                        HttpStatus.OK.value(), "성공"
                )
        );
    }

    @Operation(
            summary = "도서 상태 변경",
            description = """
                    - 도서 상태 변경을 진행합니다.
                    
                    1. bookId : 도서 키
                    2. statusName : 상태 명
                    3. reason : 사유
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @PatchMapping("/book-status/{bookId}")
    public ResponseEntity<?> setBookStatus(@PathVariable("bookId") Long bookId, @RequestBody
            StatusRequestDto statusRequestDto) {

        String statusName = statusRequestDto.getStatusName();
        String reason = statusRequestDto.getReason();

        bookService.updateBookStatus(bookId, statusName, reason);

        return ResponseEntity.ok().body(
                new ResponseSimpleVo(
                        HttpStatus.OK.value(), "성공"
                )
        );
    }

    @Operation(
            summary = "도서 삭제",
            description = """
                    - 도서 삭제 진행합니다.
                    
                    - Body
                    1. bookId : 도서 키
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping(value = "/book/{bookId}")
    public ResponseEntity<?> delBook(@PathVariable("bookId") Long bookId) {

        bookService.delBook(bookId);

        return ResponseEntity.ok().body(
                new ResponseSimpleVo(
                        HttpStatus.OK.value(), "성공"
                )
        );
    }
}
