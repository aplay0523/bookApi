package com.library.book_api.controller.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBodyVo<T> {

    private T body;
    private Integer code;
    private String message;
}
