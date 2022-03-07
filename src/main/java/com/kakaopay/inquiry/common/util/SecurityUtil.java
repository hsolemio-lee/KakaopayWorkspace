package com.kakaopay.inquiry.common.util;

import com.kakaopay.inquiry.common.auth.PrincipalDetails;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtil {
    public Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    public String getUserId() {
        return ((PrincipalDetails) getAuth().getPrincipal()).getUsername();
    }
}
