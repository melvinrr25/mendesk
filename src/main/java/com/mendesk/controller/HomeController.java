package com.mendesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.mendesk.repository.RequestRepo;

@Controller
public class HomeController {
	@Autowired
	RequestRepo requestRepo;

	@GetMapping("/")
	public String home(ModelMap model) {	
		return "home/home";
	}
}
