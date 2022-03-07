package com.kakaopay.inquiry.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {
    private String userId;
    private String authCode;
    private String role;
}
