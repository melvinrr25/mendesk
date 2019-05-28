package com.mendesk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue
	private Integer id;

	private String content;

	@ManyToOne
	private Request request;
	
	@ManyToOne
	private User user;

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@Column(name = "created_at")
	private Date createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	public Comment() {
	}

	public Comment(String content, Request request, User user) {
		super();
		this.content = content;
		this.request = request;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment  [id=" + id + ", content=" + content + ", createdAt=" + createdAt + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
