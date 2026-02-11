package com.mendesk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mendesk.entity.Comment;
import com.mendesk.entity.CommentType;
import com.mendesk.entity.Ticket;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

	List<Comment> findByTicketOrderByCreatedAtAsc(Ticket ticket);

	List<Comment> findByTicketId(Integer ticketId);

	List<Comment> findByAuthorId(Integer authorId);

	List<Comment> findByTicketAndType(Ticket ticket, CommentType type);
}
