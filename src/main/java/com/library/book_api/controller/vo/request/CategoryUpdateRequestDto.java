package com.library.book_api.controller.vo.request;

import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

@Data
public class CategoryUpdateRequestDto {
    @Size(min = 1)
    private List<Long> categoryIds;
}
