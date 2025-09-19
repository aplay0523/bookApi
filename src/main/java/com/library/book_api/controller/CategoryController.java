package com.library.book_api.controller;

import com.library.book_api.controller.entity.Category;
import com.library.book_api.controller.vo.request.CategoryRequestDto;
import com.library.book_api.controller.vo.response.ResponseBodyVo;
import com.library.book_api.controller.vo.response.ResponseListVo;
import com.library.book_api.controller.vo.response.ResponseSimpleVo;
import com.library.book_api.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "카테고리", description = "")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(
            summary = "카테고리 검색",
            description = """
                    - 카테고리 검색을 진행합니다.
                    
                    1. categoryName like 도서 명
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @GetMapping(value = "/category")
    public ResponseEntity<?> getCategory(@RequestParam(value = "categoryName",required = false) String categoryName) {

        return ResponseEntity.ok().body(
                new ResponseBodyVo(
                        new ResponseListVo<>(categoryService.searchCategory(categoryName)),
                        HttpStatus.OK.value(), "성공"
                )
        );
    }

    @Operation(
            summary = "카테고리 등록",
            description = """
                    - 카테고리 등록을 진행합니다.
                    
                    body
                    1. categoryName : 카테고리 명
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @PostMapping(value = "/category")
    public ResponseEntity<?> setCategory(@RequestBody CategoryRequestDto categoryRequestDto) {

        Category category = new Category();
        category.setCategoryName(categoryRequestDto.getCategoryName());

        categoryService.saveCategory(category);

        return ResponseEntity.ok().body(
                new ResponseSimpleVo(
                        HttpStatus.OK.value(), "성공"
                )
        );
    }

    @Operation(
            summary = "카테고리 수정",
            description = """
                    - 카테고리 수정 진행합니다.
                    
                    1. categoryId : 카테고리 키
                    2. categoryName : 카테고리 명
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @PutMapping("/category/{categoryId}")
    public ResponseEntity<?> putBookStatus(@PathVariable("categoryId") Long categoryId,@Valid @RequestBody
    CategoryRequestDto categoryRequestDto) {

        String categoryName = categoryRequestDto.getCategoryName();

        categoryService.updateCategory(categoryId, categoryName);

        return ResponseEntity.ok().body(
                new ResponseSimpleVo(
                        HttpStatus.OK.value(), "성공"
                )
        );
    }

    @Operation(
            summary = "카테고리 삭제",
            description = """
                    - 카테고리 삭제 진행합니다.
                    
                    - Body
                    1. categoryId : 카테고리 키
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping(value = "/category/{categoryId}")
    public ResponseEntity<?> delCategory(@PathVariable("categoryId") Long categoryId) {

        categoryService.delCategory(categoryId);

        return ResponseEntity.ok().body(
                new ResponseSimpleVo(
                        HttpStatus.OK.value(), "성공"
                )
        );
    }
}
