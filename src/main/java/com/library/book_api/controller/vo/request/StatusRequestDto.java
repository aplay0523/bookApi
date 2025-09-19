package com.library.book_api.controller.vo.request;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class StatusRequestDto {

    private String statusName;
    private String reason;
}
