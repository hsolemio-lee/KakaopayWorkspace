package com.kakaopay.inquiry.common.auth.service;

import com.kakaopay.inquiry.common.auth.PrincipalDetails;
import com.kakaopay.inquiry.common.exception.ServiceException;
import com.kakaopay.inquiry.common.exception.code.ErrorCode;
import com.kakaopay.inquiry.entity.User;
import com.kakaopay.inquiry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// 시큐리티 설정에서 loginProcessingUrl("/login") /login 요청이 오면 자동으로 UserDetailsService 타입으로  IoC 되어있는 loadUserByUsername 함수가 실행
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(id);
        return new PrincipalDetails(Optional.ofNullable(user).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND)));
    }
}
