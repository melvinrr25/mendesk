package com.mendesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mendesk.entity.Comment;
import com.mendesk.entity.CommentType;
import com.mendesk.entity.Ticket;
import com.mendesk.entity.TicketStatus;
import com.mendesk.entity.User;
import com.mendesk.repository.CommentRepo;
import com.mendesk.repository.TicketRepo;

@Service
public class TicketService {

	@Autowired
	private TicketRepo ticketRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Transactional
	public Ticket createTicket(String subject, String description, TicketStatus status, 
			com.mendesk.entity.Priority priority, String tags, User requester) {
		Ticket ticket = new Ticket();
		ticket.setSubject(subject);
		ticket.setDescription(description);
		ticket.setStatus(status != null ? status : TicketStatus.NEW);
		ticket.setPriority(priority != null ? priority : com.mendesk.entity.Priority.NORMAL);
		ticket.setTags(tags);
		ticket.setRequester(requester);

		return ticketRepo.save(ticket);
	}

	@Transactional
	public Ticket updateTicket(Ticket ticket) {
		return ticketRepo.save(ticket);
	}

	@Transactional
	public Ticket updateTicketStatus(Integer ticketId, TicketStatus status) {
		Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));
		ticket.setStatus(status);
		return ticketRepo.save(ticket);
	}

	@Transactional
	public Ticket assignTicket(Integer ticketId, User assignee) {
		Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));
		ticket.setAssignee(assignee);
		if (assignee != null && ticket.getStatus() == TicketStatus.NEW) {
			ticket.setStatus(TicketStatus.OPEN);
		}
		return ticketRepo.save(ticket);
	}

	@Transactional
	public void deleteTicket(Integer ticketId) {
		ticketRepo.deleteById(ticketId);
	}

	public Ticket findById(Integer id) {
		return ticketRepo.findById(id).orElse(null);
	}

	public List<Ticket> findAll() {
		return ticketRepo.findAllOrderByCreatedAtDesc();
	}

	public List<Ticket> findByRequester(User requester) {
		return ticketRepo.findByRequesterOrderByCreatedAtDesc(requester);
	}

	public List<Ticket> findByAssignee(User assignee) {
		return ticketRepo.findByAssignee(assignee);
	}

	public List<Ticket> findByStatus(TicketStatus status) {
		return ticketRepo.findByStatus(status);
	}

	public List<Ticket> searchTickets(String searchTerm) {
		return ticketRepo.searchTickets(searchTerm);
	}

	public List<Ticket> findByUserInvolved(User user) {
		return ticketRepo.findByUserInvolved(user);
	}

	public long countByStatus(TicketStatus status) {
		return ticketRepo.countByStatus(status);
	}

	public long countByRequester(User user) {
		return ticketRepo.countByRequester(user);
	}

	@Transactional
	public Comment addComment(Ticket ticket, User author, String content, CommentType type) {
		Comment comment = new Comment();
		comment.setTicket(ticket);
		comment.setAuthor(author);
		comment.setContent(content);
		comment.setType(type);

		ticket.setStatus(type == CommentType.INTERNAL ? TicketStatus.OPEN : ticket.getStatus());

		commentRepo.save(comment);
		return comment;
	}

	public List<Comment> getCommentsByTicket(Ticket ticket) {
		return commentRepo.findByTicketOrderByCreatedAtAsc(ticket);
	}

	@Transactional
	public void deleteComment(Integer commentId) {
		commentRepo.deleteById(commentId);
	}
}
