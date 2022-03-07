package com.kakaopay.inquiry.service.impl;

import com.kakaopay.inquiry.common.exception.ServiceException;
import com.kakaopay.inquiry.controller.dto.UserDTO;
import com.kakaopay.inquiry.entity.User;
import com.kakaopay.inquiry.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    @Spy
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void _01_유저생성_OK() {
        when(userRepository.findById(anyString())).thenReturn(null);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenReturn(getMockUser());
        UserDTO result = userService.createUser(getMockUserDTO());

        verify(userRepository, times(1)).save(any());
        verify(bCryptPasswordEncoder, times(1)).encode(any());

        assertThat(result.getAuthCode()).isNull();

    }

    @Test
    public void _02_DTO_ID_존재할떄_유저생성_FAIL() {
        when(userRepository.findById(anyString())).thenReturn(getMockUser());

        assertThrows(ServiceException.class, () -> userService.createUser(getMockUserDTOWithID()));

    }

    private User getMockUser() {
        return User.builder().id("test").authCode("test").build();
    }
    private UserDTO getMockUserDTO() {
        return UserDTO.builder().authCode("test").build();
    }
    private UserDTO getMockUserDTOWithID() {
        return UserDTO.builder().id("test").authCode("test").build();
    }
}
