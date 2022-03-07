package com.kakaopay.inquiry.controller.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO {
    private Long id;
    private Long inquiryId;
    @NotBlank(message = "Title is mandatory")
    String title;
    @NotBlank(message = "Content is mandatory")
    String content;
    private String registerId;
    Instant createDate;
}
