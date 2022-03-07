package com.kakaopay.inquiry.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
public class ErrorDescription {

    private final String errorCode;

    private final String errorMessage;

    private String invalidParam;

    public ErrorDescription() {this(null, null);}

    public ErrorDescription(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorDescription(String errorCode, String errorMessage, String invalidParam) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.invalidParam = invalidParam;
    }

    public Map<String, Object> toMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("code", errorCode);
        map.put("errorMessage", errorMessage);

        Optional.ofNullable(invalidParam).ifPresent((String v) -> map.put("invalidParam", v));

        return map;
    }

}
