package com.kakaopay.inquiry.entity.converter;

import com.kakaopay.inquiry.controller.dto.InquiryDTO;
import com.kakaopay.inquiry.entity.Inquiry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ReplyConverter.class})
public interface InquiryConverter extends EntityConverter<InquiryDTO, Inquiry> {
    InquiryConverter INSTANCE = Mappers.getMapper(InquiryConverter.class);

    @Mapping(target="registerId", expression = "java(this.getUserId())")
    @Mapping(target="createDate", expression = "java(this.getCurrentDateTime())")
    Inquiry toEntity(InquiryDTO dto);
}
