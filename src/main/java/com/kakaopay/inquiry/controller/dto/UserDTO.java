package com.kakaopay.inquiry.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    @NotBlank
    private String id;
    @NotBlank
    private String authCode;
    @NotBlank
    private String name;
    private Instant createDate;
    private String role;
}
