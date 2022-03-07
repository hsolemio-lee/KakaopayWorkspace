package com.kakaopay.inquiry.common.exception;

import com.kakaopay.inquiry.common.exception.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, List<Map<String, Object>>> responseFormat(List<FieldError> errors) {
        Map<String, List<Map<String,Object>>> responseMap = new HashMap<>();
        responseMap.put("errors", errors.stream()
                .map(error -> new ErrorDescription(ErrorCode.PARAMETER_INVALID.getCode(), ErrorCode.PARAMETER_INVALID.getMessage(), error.getField()).toMap())
                .collect(Collectors.toList()));

        return responseMap;
    }

    private Map<String, List<Map<String, Object>>> responseFormat(ErrorDescription errorDescription, Exception exception) {
        Map<String, List<Map<String,Object>>> responseMap = new HashMap<>();
        responseMap.put("errors", Collections.singletonList(errorDescription.toMap()));

        return responseMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public Map<String, List<Map<String, Object>>> serviceException(ServiceException ex) {
        ErrorDescription errorDescription = ex.getErrorDescription();
        return responseFormat(errorDescription, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Map<String, List<Map<String, Object>>> constraintException(BindException ex) {
        return responseFormat(ex.getFieldErrors());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, List<Map<String, Object>>> unknownException(Exception ex) {
        ErrorDescription errorDescription = new ErrorDescription(ErrorCode.UNKNOWN_ERROR.getCode(), ex.getMessage());
        return responseFormat(errorDescription, ex);
    }

}
