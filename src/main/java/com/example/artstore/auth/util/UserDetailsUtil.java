package com.example.artstore.auth.util;

import com.example.artstore.auth.domain.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserDetailsUtil {

    public static Long getCurrentUserId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getId();
    }

}
