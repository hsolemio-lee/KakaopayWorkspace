package com.kakaopay.inquiry.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PARAMETER_EMPTY("SOL400-001", "Parameter is empty"),
    PARAMETER_INVALID("SOL400-002", "Parameter is invalid"),
    ID_EXIST("SOL400-003", "ID already exists"),
    UNKNOWN_ERROR("SOL500-001", "Unknown error"),
    UNAUTHORIZED("SOL401-001", "Unauthorized"),
    FORBIDDEN("SOL403-001", "Forbidden"),
    USER_NOT_FOUND("SOL404-001", "User Not Found"),
    INQUIRY_NOT_FOUND("SOL400-003", "Inquiry Not Found"),
    ;

    private String code;
    private String message;
}
