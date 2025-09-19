package com.library.book_api.controller.vo.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseListVo<T> {

    private List<T> list;
}
