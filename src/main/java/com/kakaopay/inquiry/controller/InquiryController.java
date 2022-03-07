package com.kakaopay.inquiry.controller;

import com.kakaopay.inquiry.common.auth.PrincipalDetails;
import com.kakaopay.inquiry.controller.dto.InquiryDTO;
import com.kakaopay.inquiry.controller.dto.ReplyDTO;
import com.kakaopay.inquiry.service.InquiryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/v1/inquiry")
@Slf4j
@RequiredArgsConstructor
@Api(value = "/rest/v1/inquiry", tags = {"문의"})
public class InquiryController {

    private final InquiryService inquiryService;

    @PostMapping
    public ResponseEntity<InquiryDTO> createInquiry(@Validated @RequestBody InquiryDTO dto) {
        return ResponseEntity.ok(inquiryService.createInquiry(dto));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<InquiryDTO>> getManagerInquiryPage(Pageable pageable) {
        return ResponseEntity.ok(inquiryService.getInquiryPage(pageable));
    }

    @PostMapping("/reply")
    public ResponseEntity<ReplyDTO> createReply(@Validated @RequestBody ReplyDTO dto) {
        return ResponseEntity.ok(inquiryService.createReply(dto));
    }

    @PostMapping("/{inquiryId}/manager")
    public ResponseEntity<InquiryDTO> assignManager(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable("inquiryId") Long inquiryId) {
        return ResponseEntity.ok(inquiryService.assignManager(principalDetails.getUser().getId(), inquiryId));
    }
}
