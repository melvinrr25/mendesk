package com.mendesk.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mendesk.entity.Ticket;
import com.mendesk.entity.TicketStatus;
import com.mendesk.entity.User;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer> {

	List<Ticket> findByRequester(User requester);

	List<Ticket> findByAssignee(User assignee);

	List<Ticket> findByStatus(TicketStatus status);

	List<Ticket> findByRequesterOrderByCreatedAtDesc(User requester);

	@Query("SELECT t FROM Ticket t ORDER BY t.createdAt DESC")
	List<Ticket> findAllOrderByCreatedAtDesc();

	@Query("SELECT t FROM Ticket t WHERE t.requester = :user OR t.assignee = :user ORDER BY t.updatedAt DESC")
	List<Ticket> findByUserInvolved(@Param("user") User user);

	@Query("SELECT t FROM Ticket t WHERE " +
			"LOWER(t.subject) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
			"LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
			"LOWER(t.tags) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
	List<Ticket> searchTickets(@Param("searchTerm") String searchTerm);

	@Query("SELECT COUNT(t) FROM Ticket t WHERE t.status = :status")
	long countByStatus(@Param("status") TicketStatus status);

	@Query("SELECT COUNT(t) FROM Ticket t WHERE t.requester = :user")
	long countByRequester(@Param("user") User user);

	@Query("SELECT COUNT(t) FROM Ticket t WHERE t.assignee = :user")
	long countByAssignee(@Param("user") User user);
}
