package com.library.book_api.controller.vo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class BookRequestDto {

    private String title;
    private String author;
    @Size(min = 1)
    private List<Long> categoryIds;
}
