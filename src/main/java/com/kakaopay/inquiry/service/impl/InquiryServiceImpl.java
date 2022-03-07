package com.kakaopay.inquiry.service.impl;

import com.kakaopay.inquiry.common.exception.ServiceException;
import com.kakaopay.inquiry.common.exception.code.ErrorCode;
import com.kakaopay.inquiry.controller.dto.InquiryDTO;
import com.kakaopay.inquiry.controller.dto.ReplyDTO;
import com.kakaopay.inquiry.entity.Inquiry;
import com.kakaopay.inquiry.entity.Reply;
import com.kakaopay.inquiry.entity.converter.InquiryConverter;
import com.kakaopay.inquiry.entity.converter.ReplyConverter;
import com.kakaopay.inquiry.repository.InquiryRepository;
import com.kakaopay.inquiry.repository.ReplyRepository;
import com.kakaopay.inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kakaopay.inquiry.common.constants.SolConst.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;

    private final ReplyRepository replyRepository;

    @Override
    public InquiryDTO createInquiry(InquiryDTO dto) {
        if(null != dto.getId() && inquiryRepository.findById(dto.getId()).isPresent()) {
            throw new ServiceException(ErrorCode.ID_EXIST);
        }
        Inquiry entity = InquiryConverter.INSTANCE.toEntity(dto);
        entity.setStatus(NOT_ASSIGNED);
        entity = inquiryRepository.save(entity);
        return InquiryConverter.INSTANCE.toDto(entity);
    }

    @Override
    public Page<InquiryDTO> getInquiryPage(String id, Pageable pageable) {
        Page<Inquiry> inquiryPage = inquiryRepository.findByRegisterId(id, pageable);
        return new PageImpl<>(InquiryConverter.INSTANCE.toDto(inquiryPage.getContent()), pageable, inquiryPage.getTotalElements());
    }

    @Override
    public Page<InquiryDTO> getInquiryPage(Pageable pageable) {
        Page<Inquiry> inquiryPage = inquiryRepository.findAll(pageable);
        return new PageImpl<>(InquiryConverter.INSTANCE.toDto(inquiryPage.getContent()), pageable, inquiryPage.getTotalElements());
    }

    @Override
    public InquiryDTO assignManager(String managerId, Long inquiryId) {
        Inquiry entity = inquiryRepository.findById(inquiryId).orElseThrow(() -> new ServiceException(ErrorCode.INQUIRY_NOT_FOUND));
        entity.setManagerId(managerId);
        entity.setStatus(ASSIGNED);
        entity = inquiryRepository.save(entity);
        return InquiryConverter.INSTANCE.toDto(entity);
    }

    @Override
    public ReplyDTO createReply(ReplyDTO dto) {
        Inquiry inquiry = inquiryRepository.findById(dto.getInquiryId()).orElseThrow(() -> new ServiceException(ErrorCode.INQUIRY_NOT_FOUND));
        inquiry.setStatus(REPLY_COMPLETE);
        Reply entity = ReplyConverter.INSTANCE.toEntity(dto);
        inquiry.setReply(entity);
        entity = replyRepository.save(entity);
        inquiryRepository.save(inquiry);
        return ReplyConverter.INSTANCE.toDto(entity);
    }
}
