package com.mendesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.mendesk.entity.Request;
import com.mendesk.repository.RequestRepo;

@Controller
public class DashboardController {
	@Autowired
	 RequestRepo requestRepo;

	@GetMapping("/dashboard")
	public String dashboard(ModelMap model) {
		List<Request> requests = requestRepo.findAll();
		System.out.println(requests);
		model.addAttribute("requests", requests);
		return "dashboard/dashboard";
	}
}
