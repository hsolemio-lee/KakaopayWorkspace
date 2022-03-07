package com.kakaopay.inquiry.entity.converter;

import com.kakaopay.inquiry.controller.dto.ReplyDTO;
import com.kakaopay.inquiry.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReplyConverter extends EntityConverter<ReplyDTO, Reply> {
    ReplyConverter INSTANCE = Mappers.getMapper(ReplyConverter.class);

    @Mapping(target="registerId", expression = "java(this.getUserId())")
    @Mapping(target="createDate", expression = "java(this.getCurrentDateTime())")
    Reply toEntity(ReplyDTO dto);
}
