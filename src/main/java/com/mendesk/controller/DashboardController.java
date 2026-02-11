package com.mendesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mendesk.entity.Ticket;
import com.mendesk.entity.TicketStatus;
import com.mendesk.entity.User;
import com.mendesk.entity.UserRole;
import com.mendesk.service.TicketService;
import com.mendesk.service.UserService;

@Controller
public class DashboardController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private UserService userService;

	private User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
			return userService.findByUsername(auth.getName());
		}
		return null;
	}

	@GetMapping({"/", "/dashboard"})
	public String dashboard(Model model) {
		User currentUser = getCurrentUser();
		if (currentUser == null) {
			return "redirect:/login";
		}

		if (userService.isAgent(currentUser)) {
			return agentDashboard(model, currentUser);
		} else {
			return userDashboard(model, currentUser);
		}
	}

	private String userDashboard(Model model, User user) {
		List<Ticket> myTickets = ticketService.findByRequester(user);

		long totalTickets = myTickets.size();
		long newTickets = myTickets.stream().filter(t -> t.getStatus() == TicketStatus.NEW).count();
		long openTickets = myTickets.stream().filter(t -> t.getStatus() == TicketStatus.OPEN).count();
		long solvedTickets = myTickets.stream().filter(t -> t.getStatus() == TicketStatus.SOLVED).count();

		model.addAttribute("tickets", myTickets);
		model.addAttribute("totalTickets", totalTickets);
		model.addAttribute("newTickets", newTickets);
		model.addAttribute("openTickets", openTickets);
		model.addAttribute("solvedTickets", solvedTickets);

		return "dashboard/user";
	}

	private String agentDashboard(Model model, User agent) {
		List<Ticket> allTickets = ticketService.findAll();
		List<Ticket> assignedTickets = ticketService.findByAssignee(agent);

		long totalTickets = allTickets.size();
		long newTickets = allTickets.stream().filter(t -> t.getStatus() == TicketStatus.NEW).count();
		long openTickets = allTickets.stream().filter(t -> t.getStatus() == TicketStatus.OPEN).count();
		long pendingTickets = allTickets.stream().filter(t -> t.getStatus() == TicketStatus.PENDING).count();
		long solvedTickets = allTickets.stream().filter(t -> t.getStatus() == TicketStatus.SOLVED).count();
		long myOpenTickets = assignedTickets.stream().filter(t ->
				t.getStatus() == TicketStatus.OPEN || t.getStatus() == TicketStatus.PENDING).count();

		List<User> agents = userService.findAllAgents();

		model.addAttribute("tickets", assignedTickets);
		model.addAttribute("allTickets", allTickets);
		model.addAttribute("totalTickets", totalTickets);
		model.addAttribute("newTickets", newTickets);
		model.addAttribute("openTickets", openTickets);
		model.addAttribute("pendingTickets", pendingTickets);
		model.addAttribute("solvedTickets", solvedTickets);
		model.addAttribute("myOpenTickets", myOpenTickets);
		model.addAttribute("agents", agents);
		model.addAttribute("isAgent", true);

		return "dashboard/agent";
	}

	@GetMapping("/admin/users")
	public String manageUsers(Model model) {
		User currentUser = getCurrentUser();
		if (currentUser == null || !userService.isAdmin(currentUser)) {
			return "redirect:/dashboard";
		}

		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		model.addAttribute("roles", UserRole.values());

		return "admin/users";
	}

	@PostMapping("/admin/users/{id}/role")
	public String updateUserRole(
			@PathVariable Integer id,
			@RequestParam String role,
			Model model) {
		User currentUser = getCurrentUser();
		if (currentUser == null || !userService.isAdmin(currentUser)) {
			return "redirect:/dashboard";
		}

		User user = userService.findById(id);
		if (user != null) {
			try {
				user.setRole(UserRole.valueOf(role));
				userService.updateUser(user);
			} catch (Exception e) {
			}
		}

		return "redirect:/admin/users";
	}

	@PostMapping("/admin/users/{id}/delete")
	public String deleteUser(@PathVariable Integer id, Model model) {
		User currentUser = getCurrentUser();
		if (currentUser == null || !userService.isAdmin(currentUser)) {
			return "redirect:/dashboard";
		}

		if (!currentUser.getId().equals(id)) {
			userService.deleteUser(id);
		}

		return "redirect:/admin/users";
	}

	@GetMapping("/profile")
	public String userProfile(Model model) {
		User currentUser = getCurrentUser();
		if (currentUser == null) {
			return "redirect:/login";
		}

		model.addAttribute("user", currentUser);
		return "user/profile";
	}
}
