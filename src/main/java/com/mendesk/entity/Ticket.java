package com.mendesk.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Subject is required")
	@Size(max = 200, message = "Subject must be less than 200 characters")
	@Column(nullable = false, length = 200)
	private String subject;

	@NotBlank(message = "Description is required")
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Priority is required")
	@Column(nullable = false)
	private Priority priority = Priority.NORMAL;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status is required")
	@Column(nullable = false)
	private TicketStatus status = TicketStatus.NEW;

	@Size(max = 500, message = "Tags must be less than 500 characters")
	@Column(length = 500)
	private String tags;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "Requester is required")
	private User requester;

	@ManyToOne(fetch = FetchType.LAZY)
	private User assignee;

	@OneToMany(mappedBy = "ticket", cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
	@OrderBy("createdAt ASC")
	private List<Comment> comments = new ArrayList<>();

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	private LocalDateTime resolvedAt;

	public Ticket() {
	}

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
		if (status == TicketStatus.SOLVED || status == TicketStatus.CLOSED) {
			this.resolvedAt = LocalDateTime.now();
		}
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getResolvedAt() {
		return resolvedAt;
	}

	public void setResolvedAt(LocalDateTime resolvedAt) {
		this.resolvedAt = resolvedAt;
	}

	public String getTicketNumber() {
		return String.format("#%05d", id);
	}

	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setTicket(this);
	}

	public void removeComment(Comment comment) {
		comments.remove(comment);
		comment.setTicket(null);
	}
}
