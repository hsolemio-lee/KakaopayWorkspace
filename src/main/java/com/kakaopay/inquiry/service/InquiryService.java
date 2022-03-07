package com.kakaopay.inquiry.service;

import com.kakaopay.inquiry.controller.dto.InquiryDTO;
import com.kakaopay.inquiry.controller.dto.ReplyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquiryService {

    InquiryDTO createInquiry(InquiryDTO dto);

    Page<InquiryDTO> getInquiryPage(String id, Pageable pageable);
    Page<InquiryDTO> getInquiryPage(Pageable pageable);

    InquiryDTO assignManager(String id, Long inquiryId);

    ReplyDTO createReply(ReplyDTO dto);
}
