package com.mendesk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mendesk.entity.Comment;
import com.mendesk.entity.CommentType;
import com.mendesk.entity.Priority;
import com.mendesk.entity.Ticket;
import com.mendesk.entity.TicketStatus;
import com.mendesk.entity.User;
import com.mendesk.service.TicketService;
import com.mendesk.service.UserService;

@Controller
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private UserService userService;

	@GetMapping("/tickets")
	public String listTickets(
			@RequestParam(required = false) String status,
			@RequestParam(required = false) String priority,
			@RequestParam(required = false) String search,
			Model model) {
		User currentUser = getCurrentUser();
		if (currentUser == null) {
			return "redirect:/login";
		}

		List<Ticket> tickets;

		if (search != null && !search.trim().isEmpty()) {
			tickets = ticketService.searchTickets(search);
		} else if (status != null && !status.isEmpty()) {
			tickets = ticketService.findByStatus(TicketStatus.valueOf(status));
		} else if (priority != null && !priority.isEmpty()) {
			tickets = ticketService.findAll().stream()
					.filter(t -> t.getPriority().name().equals(priority))
					.toList();
		} else if (userService.isAgent(currentUser)) {
			tickets = ticketService.findAll();
		} else {
			tickets = ticketService.findByRequester(currentUser);
		}

		model.addAttribute("tickets", tickets);
		model.addAttribute("statuses", TicketStatus.values());
		model.addAttribute("priorities", Priority.values());

		return "tickets/list";
	}

	@GetMapping("/tickets/new")
	public String newTicketForm(Model model) {
		if (getCurrentUser() == null) {
			return "redirect:/login";
		}
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("priorities", Priority.values());
		return "tickets/new";
	}

	@PostMapping("/tickets")
	public String createTicket(
			@ModelAttribute Ticket ticket,
			@RequestParam String priority,
			BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("priorities", Priority.values());
			return "tickets/new";
		}

		User requester = getCurrentUser();
		if (requester == null) {
			return "redirect:/login";
		}

		ticket.setRequester(requester);
		ticket.setPriority(Priority.valueOf(priority));
		ticket.setStatus(TicketStatus.NEW);
		ticketService.createTicket(ticket.getSubject(), ticket.getDescription(),
				TicketStatus.NEW, Priority.valueOf(priority), ticket.getTags(), requester);

		return "redirect:/tickets";
	}

	@GetMapping("/tickets/{id}")
	public String viewTicket(@PathVariable Integer id, Model model) {
		Ticket ticket = ticketService.findById(id);
		if (ticket == null) {
			return "redirect:/tickets";
		}

		User currentUser = getCurrentUser();
		if (currentUser == null) {
			return "redirect:/login";
		}

		if (!userService.isAgent(currentUser) &&
				(ticket.getRequester() == null || !ticket.getRequester().getId().equals(currentUser.getId()))) {
			return "redirect:/tickets";
		}

		List<Comment> comments = ticketService.getCommentsByTicket(ticket);
		model.addAttribute("ticket", ticket);
		model.addAttribute("comments", comments);
		model.addAttribute("newComment", new Comment());
		model.addAttribute("statuses", TicketStatus.values());
		model.addAttribute("agents", userService.findAllAgents());

		return "tickets/show";
	}

	@PostMapping("/tickets/{id}/comment")
	public String addComment(
			@PathVariable Integer id,
			@RequestParam String content,
			@RequestParam(required = false) String type,
			@RequestParam(required = false) String status,
			Model model) {
		Ticket ticket = ticketService.findById(id);
		if (ticket == null) {
			return "redirect:/tickets";
		}

		User author = getCurrentUser();
		if (author == null) {
			return "redirect:/login";
		}

		CommentType commentType = "internal".equals(type) ? CommentType.INTERNAL : CommentType.PUBLIC;
		ticketService.addComment(ticket, author, content, commentType);

		if (status != null && !status.isEmpty()) {
			try {
				ticketService.updateTicketStatus(id, TicketStatus.valueOf(status));
			} catch (Exception e) {
			}
		}

		return "redirect:/tickets/" + id;
	}

	@GetMapping("/tickets/{id}/edit")
	public String editTicketForm(@PathVariable Integer id, Model model) {
		Ticket ticket = ticketService.findById(id);
		if (ticket == null) {
			return "redirect:/tickets";
		}

		User currentUser = getCurrentUser();
		if (currentUser == null) {
			return "redirect:/login";
		}

		if (!userService.isAgent(currentUser) &&
				(ticket.getRequester() == null || !ticket.getRequester().getId().equals(currentUser.getId()))) {
			return "redirect:/tickets";
		}

		model.addAttribute("ticket", ticket);
		model.addAttribute("statuses", TicketStatus.values());
		model.addAttribute("priorities", Priority.values());
		return "tickets/edit";
	}

	@PostMapping("/tickets/{id}/edit")
	public String updateTicket(
			@PathVariable Integer id,
			@ModelAttribute Ticket ticket,
			@RequestParam String status,
			@RequestParam String priority,
			BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("statuses", TicketStatus.values());
			model.addAttribute("priorities", Priority.values());
			return "tickets/edit";
		}

		Ticket existingTicket = ticketService.findById(id);
		if (existingTicket == null) {
			return "redirect:/tickets";
		}

		existingTicket.setSubject(ticket.getSubject());
		existingTicket.setDescription(ticket.getDescription());
		existingTicket.setStatus(TicketStatus.valueOf(status));
		existingTicket.setPriority(Priority.valueOf(priority));
		existingTicket.setTags(ticket.getTags());

		ticketService.updateTicket(existingTicket);
		return "redirect:/tickets/" + id;
	}

	@PostMapping("/tickets/{id}/assign")
	public String assignTicket(
			@PathVariable Integer id,
			@RequestParam Integer assigneeId,
			Model model) {
		User currentUser = getCurrentUser();
		if (currentUser == null || !userService.isAgent(currentUser)) {
			return "redirect:/tickets";
		}

		User assignee = userService.findById(assigneeId);
		if (assignee != null) {
			ticketService.assignTicket(id, assignee);
		}

		return "redirect:/tickets/" + id;
	}

	@PostMapping("/tickets/{id}/status")
	public String updateStatus(
			@PathVariable Integer id,
			@RequestParam String status,
			Model model) {
		User currentUser = getCurrentUser();
		if (currentUser == null) {
			return "redirect:/login";
		}

		try {
			TicketStatus ticketStatus = TicketStatus.valueOf(status);
			ticketService.updateTicketStatus(id, ticketStatus);
		} catch (Exception e) {
		}

		return "redirect:/tickets/" + id;
	}

	@GetMapping("/tickets/{id}/delete")
	public String deleteTicket(@PathVariable Integer id, Model model) {
		Ticket ticket = ticketService.findById(id);
		if (ticket == null) {
			return "redirect:/tickets";
		}

		User currentUser = getCurrentUser();
		if (currentUser == null) {
			return "redirect:/login";
		}

		if (!userService.isAgent(currentUser) &&
				(ticket.getRequester() == null || !ticket.getRequester().getId().equals(currentUser.getId()))) {
			return "redirect:/tickets";
		}

		ticketService.deleteTicket(id);
		return "redirect:/tickets";
	}

	private User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
			return userService.findByUsername(auth.getName());
		}
		return null;
	}
}
