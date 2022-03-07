package com.kakaopay.inquiry.entity.converter;

import com.kakaopay.inquiry.controller.dto.UserDTO;
import com.kakaopay.inquiry.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter extends EntityConverter<UserDTO, User> {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mapping(target="createDate", expression = "java(this.getCurrentDateTime())")
    User toEntity(UserDTO dto);

    @Mapping(target = "authCode", ignore = true)
    UserDTO toDto(User entity);
}
