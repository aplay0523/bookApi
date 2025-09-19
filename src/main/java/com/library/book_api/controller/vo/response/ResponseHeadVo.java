package com.library.book_api.controller.vo.response;

import lombok.Data;

@Data
public class ResponseHeadVo {

    private Integer code;
    private String message;
    private String detail;
}
