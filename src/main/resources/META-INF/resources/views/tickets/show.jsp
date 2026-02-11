<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../shared/header.jsp"%>

<div class="page-header">
	<div class="container">
		<div class="d-flex justify-content-between align-items-center">
			<div>
				<a href="/tickets" class="btn btn-outline-secondary mb-2">
					<i class="fas fa-arrow-left"></i> Back to Tickets
				</a>
			</div>
			<div>
				<a href="/tickets/${ticket.id}/edit" class="btn btn-outline-primary">
					<i class="fas fa-edit"></i> Edit
				</a>
			</div>
		</div>
	</div>
</div>

<div class="content-area">
	<div class="container">
		<div class="row">
			<div class="col-lg-8">
				<div class="card mb-4">
					<div class="card-body">
						<div class="d-flex justify-content-between align-items-start mb-3">
							<div>
								<span class="badge badge-${ticket.priority.bootstrapClass} mr-2">${ticket.priority.displayName}</span>
								<span class="badge badge-${ticket.status.bootstrapClass}">${ticket.status.displayName}</span>
							</div>
							<span class="ticket-number">${ticket.ticketNumber}</span>
						</div>
						<h3 class="card-title mb-3">${ticket.subject}</h3>
						<p class="text-muted mb-3">
							<small>
								<i class="fas fa-user"></i> Created by ${ticket.requester.username}
								<span class="mx-2">|</span>
								<i class="fas fa-calendar"></i> ${ticket.createdAt}
							</small>
						</p>
						<hr>
						<div class="card-text">
							${ticket.description}
						</div>
						<c:if test="${not empty ticket.tags}">
							<div class="mt-3">
								<c:forEach items="${fn:split(ticket.tags, ',')}" var="tag">
									<span class="badge badge-secondary mr-1">${tag}</span>
								</c:forEach>
							</div>
						</c:if>
					</div>
				</div>

				<div class="card">
					<div class="card-header">
						<h5 class="mb-0"><i class="fas fa-comments"></i> Conversation</h5>
					</div>
					<div class="card-body">
						<c:if test="${empty comments}">
							<div class="empty-state">
								<i class="fas fa-comment-dots"></i>
								<h5>No comments yet</h5>
								<p>Be the first to respond!</p>
							</div>
						</c:if>
						<c:forEach items="${comments}" var="comment">
							<div class="comment ${comment.isInternal() ? 'internal-note' : ''}">
								<div class="comment-header">
									<div class="comment-avatar">${comment.author.initials}</div>
									<div class="comment-meta">
										<div class="comment-author">
											${comment.author.username}
											<c:if test="${comment.isInternal()}">
												<span class="badge badge-warning ml-1">Internal</span>
											</c:if>
										</div>
										<div class="comment-time">${comment.createdAt}</div>
									</div>
								</div>
								<div class="comment-content">
									${comment.content}
								</div>
							</div>
						</c:forEach>

						<form action="/tickets/${ticket.id}/comment" method="post" class="mt-4">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<div class="form-group">
								<label for="content" class="form-label">Add Reply</label>
								<textarea class="form-control" id="content" name="content" rows="4" required placeholder="Write your response..."></textarea>
							</div>
							<div class="row">
								<div class="col-md-4">
									<label for="type" class="form-label">Type</label>
									<select class="form-control" id="type" name="type">
										<option value="public">Public Reply</option>
										<option value="internal">Internal Note</option>
									</select>
									<small class="text-muted">Internal notes are only visible to agents</small>
								</div>
								<div class="col-md-4">
									<label for="status" class="form-label">Update Status</label>
									<select class="form-control" id="status" name="status">
										<c:forEach items="${statuses}" var="status">
											<option value="${status}" ${ticket.status == status ? 'selected' : ''}>${status.displayName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-4 d-flex align-items-end">
									<button type="submit" class="btn btn-primary">
										<i class="fas fa-paper-plane"></i> Submit
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-lg-4">
				<div class="sidebar-card mb-4">
					<div class="card-header">
						<h6><i class="fas fa-info-circle"></i> Ticket Details</h6>
					</div>
					<div class="card-body">
						<table class="table table-sm mb-0">
							<tr>
								<td><strong>Requester</strong></td>
								<td>${ticket.requester.username}</td>
							</tr>
							<tr>
								<td><strong>Assignee</strong></td>
								<td>
									<c:if test="${not empty ticket.assignee}">
										${ticket.assignee.username}
									</c:if>
									<c:if test="${empty ticket.assignee}">
										<span class="text-muted">Unassigned</span>
									</c:if>
								</td>
							</tr>
							<tr>
								<td><strong>Created</strong></td>
								<td>${ticket.createdAt}</td>
							</tr>
							<tr>
								<td><strong>Updated</strong></td>
								<td>${ticket.updatedAt}</td>
							</tr>
						</table>
					</div>
				</div>

				<c:if test="${isAgent}">
					<div class="sidebar-card">
						<div class="card-header">
							<h6><i class="fas fa-user-plus"></i> Assign Ticket</h6>
						</div>
						<div class="card-body">
							<form action="/tickets/${ticket.id}/assign" method="post">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<div class="form-group mb-2">
									<select class="form-control" id="assigneeId" name="assigneeId">
										<option value="">Select Agent...</option>
										<c:forEach items="${agents}" var="agent">
											<option value="${agent.id}">${agent.username}</option>
										</c:forEach>
									</select>
								</div>
								<button type="submit" class="btn btn-primary btn-sm btn-block">
									<i class="fas fa-user-plus"></i> Assign
								</button>
							</form>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>

<%@include file="../shared/footer.jsp"%>
