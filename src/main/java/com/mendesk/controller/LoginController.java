package com.mendesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginPage(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "registered", required = false) String registered,
			Model model) {

		String message = null;
		String messageType = "info";

		if (error != null) {
			message = "Your credentials are not valid.";
			messageType = "danger";
		}

		if (logout != null) {
			message = "You have been successfully logged out.";
			messageType = "success";
		}

		if (registered != null) {
			message = "Registration successful! Please login.";
			messageType = "success";
		}

		model.addAttribute("message", message);
		model.addAttribute("messageType", messageType);

		return "login/login";
	}

	@GetMapping("/logout")
	public String logoutPage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			SecurityContextHolder.clearContext();
		}
		return "redirect:/login?logout=true";
	}
}
