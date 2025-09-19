package com.library.book_api.common.exception;

import com.library.book_api.common.ErrorCode;
import com.library.book_api.controller.vo.response.ResponseHeadVo;
import com.library.book_api.controller.vo.response.ResponseVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // openApi 2.5이상
public class CustomExceptionHandler {

    // 유효성 검증 실패 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> apiValidException(MethodArgumentNotValidException ex) {
        ErrorCode errorCode = ErrorCode.VALIDATION_FAILED;

        ResponseVo responseVo = new ResponseVo();
        ResponseHeadVo responseHeadVo = new ResponseHeadVo();

        responseHeadVo.setCode(errorCode.getCode());
        responseHeadVo.setMessage(errorCode.getMessage());
        responseHeadVo.setDetail(errorCode.getDetail());

        responseVo.setHead(responseHeadVo);
        responseVo.setBody(null);


        return ResponseEntity.status(errorCode.getHttpStatus()).body(responseVo);
    }

    // 커스텀 예외 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleBusinessException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        ResponseVo responseVo = new ResponseVo();
        ResponseHeadVo responseHeadVo = new ResponseHeadVo();

        responseHeadVo.setCode(errorCode.getCode());
        responseHeadVo.setMessage(errorCode.getMessage());
        responseHeadVo.setDetail(ex.getMessage());

        responseVo.setHead(responseHeadVo);
        responseVo.setBody(null);


        return ResponseEntity.status(errorCode.getHttpStatus()).body(responseVo);
    }
}
