package com.library.book_api.controller.vo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @NotBlank
    private String categoryName;

}
