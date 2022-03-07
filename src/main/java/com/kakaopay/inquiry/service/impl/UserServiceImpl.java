package com.kakaopay.inquiry.service.impl;

import com.kakaopay.inquiry.controller.dto.UserDTO;
import com.kakaopay.inquiry.entity.User;
import com.kakaopay.inquiry.entity.converter.UserConverter;
import com.kakaopay.inquiry.repository.UserRepository;
import com.kakaopay.inquiry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO dto) {
        User entity = UserConverter.INSTANCE.toEntity(dto);
        entity = userRepository.save(entity);
        String rawPassword = entity.getAuthCode();
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        entity.setAuthCode(encodedPassword);
        return UserConverter.INSTANCE.toDto(entity);
    }


}
