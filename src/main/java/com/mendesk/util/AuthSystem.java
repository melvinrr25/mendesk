package com.mendesk.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthSystem {
    public static boolean isLogged() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null != authentication && !("anonymousUser").equals(authentication.getName());
    }
    
    public static String currentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}