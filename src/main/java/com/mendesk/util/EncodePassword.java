package com.mendesk.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodePassword {
	public static void main(String[] args) {
		String pwd = "melvin";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashpwd = encoder.encode(pwd);
		System.out.println(hashpwd);
	}
}
