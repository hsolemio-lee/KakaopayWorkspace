package com.kakaopay.inquiry.service.impl;

import com.kakaopay.inquiry.common.auth.PrincipalDetails;
import com.kakaopay.inquiry.common.exception.ServiceException;
import com.kakaopay.inquiry.controller.dto.InquiryDTO;
import com.kakaopay.inquiry.controller.dto.ReplyDTO;
import com.kakaopay.inquiry.entity.Inquiry;
import com.kakaopay.inquiry.entity.Reply;
import com.kakaopay.inquiry.entity.User;
import com.kakaopay.inquiry.repository.InquiryRepository;
import com.kakaopay.inquiry.repository.ReplyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.kakaopay.inquiry.common.constants.SolConst.ASSIGNED;
import static com.kakaopay.inquiry.common.constants.SolConst.NOT_ASSIGNED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class InquiryServiceImplTest {

    @Spy
    @InjectMocks
    private InquiryServiceImpl inquiryService;

    @Mock
    private InquiryRepository inquiryRepository;

    @Mock
    private ReplyRepository replyRepository;

    @BeforeEach
    public void beforeAll() {
        PrincipalDetails principalDetails = new PrincipalDetails(User.builder().id("test").authCode("test").role("USER").build());
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void _01_????????????_OK() {
        when(inquiryRepository.save(any())).thenReturn(getMockInquiry());

        InquiryDTO result = inquiryService.createInquiry(getMockDTO());

        verify(inquiryRepository, times(0)).findById(anyLong());
        verify(inquiryRepository, times(1)).save(any());

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getStatus()).isEqualTo(NOT_ASSIGNED);
    }

    @Test
    public void _02_ID??????_?????????????????????_????????????_OK() {
        when(inquiryRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(inquiryRepository.save(any())).thenReturn(getMockInquiry());

        InquiryDTO result = inquiryService.createInquiry(getMockDTOWithID());

        verify(inquiryRepository, times(1)).findById(anyLong());
        verify(inquiryRepository, times(1)).save(any());

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getStatus()).isEqualTo(NOT_ASSIGNED);

    }

    @Test
    public void _03_ID??????_????????????_????????????_FAIL() {
        when(inquiryRepository.findById(anyLong())).thenReturn(Optional.of(getMockInquiry()));

        assertThrows(ServiceException.class, () -> inquiryService.createInquiry(getMockDTOWithID()));
    }

    @Test
    public void _04_????????????_Without_REGISTER_ID_OK() {
        Pageable pageable = PageRequest.of(0, 10);
        when(inquiryRepository.findAll(pageable)).thenReturn(new PageImpl<>(getMockInquiryList(), pageable, 1));

        Page<InquiryDTO> result = inquiryService.getInquiryPage(pageable);

        assertThat(result.getContent().size()).isEqualTo(1);
        assertThat(result.getContent().get(0).getId()).isEqualTo(1L);
        assertThat(result.getTotalPages()).isEqualTo(1);
    }

    @Test
    public void _05_MANAGER??????_OK() {
        when(inquiryRepository.findById(anyLong())).thenReturn(Optional.of(getMockInquiry()));
        when(inquiryRepository.save(any())).thenReturn(getMockInquiry(ASSIGNED));
        InquiryDTO result = inquiryService.assignManager("test", 1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("test");
        assertThat(result.getStatus()).isEqualTo(ASSIGNED);
    }

    @Test
    public void _06_ID??????_?????????????????????_MANAGER??????_FAIL() {
        when(inquiryRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ServiceException.class, () ->  inquiryService.assignManager("test", 1L));

    }

    @Test
    public void _07_????????????_OK() {
        when(inquiryRepository.findById(anyLong())).thenReturn(Optional.of(getMockInquiry()));
        when(replyRepository.save(any())).thenReturn(getMockReply());
        when(inquiryRepository.save(any())).thenReturn(getMockInquiry());

        ReplyDTO result = inquiryService.createReply(getMockReplyDTO());

        verify(inquiryRepository, times(1)).findById(anyLong());
        verify(inquiryRepository, times(1)).save(any());
        verify(replyRepository, times(1)).save(any());

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("test");
    }

    @Test
    public void _08_ID??????_?????????????????????_????????????_FAIL() {
        when(inquiryRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ServiceException.class, () ->  inquiryService.createReply(getMockReplyDTO()));
    }

    private InquiryDTO getMockDTO() {
        return InquiryDTO.builder().title("test").content("test").build();
    }

    private InquiryDTO getMockDTOWithID() {
        return InquiryDTO.builder().id(1L).title("test").content("test").build();
    }

    private Inquiry getMockInquiry() {
        return Inquiry.builder().id(1L).title("test").content("test").status(NOT_ASSIGNED).build();
    }

    private Inquiry getMockInquiry(String status) {
        return Inquiry.builder().id(1L).title("test").content("test").status(status).build();
    }

    private List<Inquiry> getMockInquiryList() {
        return Collections.singletonList(getMockInquiry());
    }

    private Reply getMockReply() {
        return Reply.builder().id(1L).title("test").content("test").registerId("test").build();
    }

    private ReplyDTO getMockReplyDTO() {
        return ReplyDTO.builder().id(1L).inquiryId(1L).title("test").content("test").registerId("test").build();
    }
}
