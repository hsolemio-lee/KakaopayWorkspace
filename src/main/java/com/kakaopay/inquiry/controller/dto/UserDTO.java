package com.kakaopay.inquiry.controller.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String authCode;
    private String name;
    private Instant createDate;
    private String role;
}
