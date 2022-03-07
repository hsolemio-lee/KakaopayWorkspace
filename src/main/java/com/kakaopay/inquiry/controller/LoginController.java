package com.kakaopay.inquiry.controller;

import com.kakaopay.inquiry.common.auth.PrincipalDetails;
import com.kakaopay.inquiry.controller.dto.LoginDTO;
import com.kakaopay.inquiry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/v1/login")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/check")
    public ResponseEntity<LoginDTO> loginCheck(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        LoginDTO loginDTO = LoginDTO.builder()
                .userId(principalDetails.getUser().getId())
                .role(principalDetails.getUser().getRole())
                .build();
        return ResponseEntity.ok(loginDTO);
    }

}
