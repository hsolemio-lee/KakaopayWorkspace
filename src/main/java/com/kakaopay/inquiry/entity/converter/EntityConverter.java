package com.kakaopay.inquiry.entity.converter;

import com.kakaopay.inquiry.common.util.SecurityUtil;

import java.time.Instant;
import java.util.List;

public interface EntityConverter<D, E> {
    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntity(List<D> dtos);
    List<D> toDto(List<E> entities);

    default Instant getCurrentDateTime() {
        return Instant.now();
    }
    default String getUserId() { return SecurityUtil.getUserId(); }

}
