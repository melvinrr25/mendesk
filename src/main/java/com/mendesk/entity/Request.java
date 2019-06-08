package com.mendesk.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "requests")
public class Request {

	public Request(User user, String subject, String body, String state, String priority) {
		super();
		this.user = user;
		this.subject = subject;
		this.body = body;
		this.state = state;
		this.priority = priority;
	}

	public Request() {
	}

	@Id
	@GeneratedValue
	private Integer id;

	private String subject;

	private String body;

	private String state;

	private String priority;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", subject=" + subject + ", body=" + body + ", state=" + state + ", priority="
				+ priority + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	public String getShortBody(int at) {
		String result = "";
		try {
			result = this.body.substring(0, at) + (this.body.length() > at ? "..." : "");
		} catch (Exception e) {
			result = this.body;
		}
		return result;
	}
}
