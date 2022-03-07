package com.kakaopay.inquiry.common.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kakaopay.inquiry.common.auth.PrincipalDetails;
import com.kakaopay.inquiry.entity.User;
import com.kakaopay.inquiry.repository.UserRepository;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kakaopay.inquiry.common.constants.SolConst.AUTH_HEADER;
import static com.kakaopay.inquiry.common.constants.SolConst.TOKEN_PREFIX;

// 시큐리티가 필터를 가지고 있는데 그 중 BasicAuthenticationFilter가 있음.
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있음.
// 만약에 권한이 인증이 필요한 주소가 아니라면 이 필터를 안탐
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;

    private final Environment env;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository, Environment env) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.env = env;
    }

    //인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 타게 될 것
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwtHeader = request.getHeader(AUTH_HEADER);

        // Header가 정상인지 확인
        if(jwtHeader == null || !jwtHeader.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        //JWT 토큰 검증 후 정상적인 사용자인지 확인
        String jwtToken = jwtHeader.replace(TOKEN_PREFIX, "");

        String userId = JWT.require(Algorithm.HMAC512(env.getProperty("jwt.token.code")))
                .build()
                .verify(jwtToken)
                .getClaim("username").asString();

        // 서명이 정상적으로 됨
        if(userId != null) {
            User user = userRepository.findById(userId);
            PrincipalDetails principalDetails = new PrincipalDetails(user);

            // Jwt 토큰 서명을 통해 서명이 정상이면 Authentication 객체를 만들어준다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        chain.doFilter(request, response);

    }
}
