package com.library.book_api.controller.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseSimpleVo {
    private Integer code;
    private String message;
}
