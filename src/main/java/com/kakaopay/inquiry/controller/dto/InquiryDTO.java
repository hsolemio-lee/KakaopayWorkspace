package com.kakaopay.inquiry.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryDTO {
    Long id;
    String registerId;
    @NotBlank(message = "Title is mandatory")
    String title;
    @NotBlank(message = "Content is mandatory")
    String content;
    String managerId;
    String status;
    Instant createDate;
    ReplyDTO reply;
}
