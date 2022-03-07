package com.kakaopay.inquiry.common.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인함
// 로그인 진행이 완료되면 시큐리티 session을 만들어줌 (Security ContextHolder)
// 오브젝트 => Authentication 타입 객체
// Authentication 안에 User정보 있어야됨
// User오브젝트 타입 => UserDetails 타입 객체

// Security Session => Authentication => UserDetails

import com.kakaopay.inquiry.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Data
public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
    }

    @Override
    public String getPassword() {
        return user.getAuthCode();
    }

    @Override
    public String getUsername() {
        return Optional.ofNullable(user).orElse(new User()).getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
