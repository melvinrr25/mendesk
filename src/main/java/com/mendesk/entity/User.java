package com.mendesk.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	@Column(unique = true, nullable = false, length = 50)
	private String username;

	@NotBlank(message = "Email is required")
	@Email(message = "Please provide a valid email")
	@Column(unique = true, nullable = false, length = 100)
	private String email;

	@NotBlank(message = "Password is required")
	@Column(nullable = false, length = 100)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role = UserRole.END_USER;

	@Column(nullable = false)
	private boolean enabled = true;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "requester")
	private List<Ticket> tickets = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private List<Comment> comments = new ArrayList<>();

	public User() {
	}

	public User(String username, String email, String password, UserRole role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getFullName() {
		return username;
	}

	public String getInitials() {
		if (username != null && username.length() >= 2) {
			return username.substring(0, 2).toUpperCase();
		}
		return username != null ? username.substring(0, 1).toUpperCase() : "?";
	}
}
