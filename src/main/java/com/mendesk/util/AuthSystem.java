package com.mendesk.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.mendesk.entity.User;
import com.mendesk.repository.UserRepo;
import com.mendesk.service.UserService;

public class AuthSystem {

	private static UserService userService;

	public static void setUserService(UserService userService) {
		AuthSystem.userService = userService;
	}

	public static boolean isLogged() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && !"anonymousUser".equals(authentication.getName());
	}

	public static String currentUsername() {
		if (!isLogged()) {
			return null;
		}
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public static User currentUser() {
		if (!isLogged() || userService == null) {
			return null;
		}
		return userService.findByUsername(currentUsername());
	}
}
