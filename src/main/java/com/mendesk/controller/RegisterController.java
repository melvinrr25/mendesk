package com.mendesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mendesk.entity.User;
import com.mendesk.service.UserService;
import com.mendesk.util.AuthSystem;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String registerPage() {
		if (AuthSystem.isLogged()) {
			return "redirect:/dashboard";
		}
		return "register/register";
	}

	@PostMapping("/register")
	public String registerUser(
			@RequestParam String username,
			@RequestParam String email,
			@RequestParam String password,
			@RequestParam String confirmPassword,
			Model model) {

		String errorMessage = null;

		if (!password.equals(confirmPassword)) {
			errorMessage = "Passwords do not match.";
		} else if (username.length() < 3) {
			errorMessage = "Username must be at least 3 characters.";
		} else if (username.length() > 50) {
			errorMessage = "Username must be less than 50 characters.";
		} else if (password.length() < 6) {
			errorMessage = "Password must be at least 6 characters.";
		} else if (email == null || email.trim().isEmpty()) {
			errorMessage = "Email is required.";
		} else if (userService.usernameExists(username)) {
			errorMessage = "Username already exists.";
		} else if (userService.emailExists(email)) {
			errorMessage = "Email already exists.";
		} else {
			User user = userService.registerUser(username, email, password);
			if (user != null) {
				return "redirect:/login?registered=true";
			} else {
				errorMessage = "Registration failed. Please try again.";
			}
		}

		model.addAttribute("errorMessage", errorMessage);
		return "register/register";
	}
}
