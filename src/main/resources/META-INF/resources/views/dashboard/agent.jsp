<%@include file="../shared/header.jsp"%>

<div class="page-header">
	<div class="container">
		<div class="d-flex justify-content-between align-items-center">
			<h1 class="page-title">
				<i class="fas fa-tachometer-alt"></i>
				Agent Dashboard
			</h1>
			<a href="/tickets/new" class="btn btn-primary">
				<i class="fas fa-plus"></i> New Ticket
			</a>
		</div>
	</div>
</div>

<div class="content-area">
	<div class="container">
		<div class="row mb-4">
			<div class="col-md-3">
				<div class="card stat-card stat-primary">
					<div class="stat-icon">
						<i class="fas fa-ticket-alt"></i>
					</div>
					<div class="stat-value">${totalTickets}</div>
					<div class="stat-label">Total Tickets</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card stat-card stat-info">
					<div class="stat-icon">
						<i class="fas fa-plus-circle"></i>
					</div>
					<div class="stat-value">${newTickets}</div>
					<div class="stat-label">New Tickets</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card stat-card stat-warning">
					<div class="stat-icon">
						<i class="fas fa-folder-open"></i>
					</div>
					<div class="stat-value">${openTickets}</div>
					<div class="stat-label">Open Tickets</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card stat-card stat-success">
					<div class="stat-icon">
						<i class="fas fa-check-circle"></i>
					</div>
					<div class="stat-value">${solvedTickets}</div>
					<div class="stat-label">Solved Tickets</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header d-flex justify-content-between align-items-center">
						<h5 class="mb-0">
							<i class="fas fa-list"></i> My Assigned Tickets
						</h5>
						<a href="/tickets/new" class="btn btn-primary btn-sm">
							<i class="fas fa-plus"></i> New Ticket
						</a>
					</div>
					<div class="card-body p-0">
						<c:if test="${empty tickets}">
							<div class="empty-state">
								<i class="fas fa-ticket-alt"></i>
								<h5>No assigned tickets</h5>
								<p>Tickets will appear here when assigned to you</p>
							</div>
						</c:if>
						<c:if test="${not empty tickets}">
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Ticket</th>
											<th>Subject</th>
											<th>Requester</th>
											<th>Status</th>
											<th>Priority</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${tickets}" var="ticket">
											<tr>
												<td>
													<span class="ticket-number">${ticket.ticketNumber}</span>
												</td>
												<td>
													<a href="/tickets/${ticket.id}" class="ticket-subject">
														${ticket.subject}
													</a>
												</td>
												<td>
													<small>${ticket.requester.username}</small>
												</td>
												<td>
													<span class="badge badge-${ticket.status.bootstrapClass}">${ticket.status.displayName}</span>
												</td>
												<td>
													<span class="badge badge-${ticket.priority.bootstrapClass}">${ticket.priority.displayName}</span>
												</td>
												<td>
													<a href="/tickets/${ticket.id}" class="btn btn-sm btn-outline-primary">
														<i class="fas fa-eye"></i>
													</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="sidebar-card">
					<div class="card-header">
						<h6><i class="fas fa-filter mr-2"></i>Quick Filters</h6>
					</div>
					<div class="card-body">
						<a href="/tickets?status=NEW" class="quick-filter">
							<span><i class="fas fa-plus-circle text-info"></i> New Tickets</span>
							<span class="badge badge-info">${newTickets}</span>
						</a>
						<a href="/tickets?status=OPEN" class="quick-filter">
							<span><i class="fas fa-folder-open text-warning"></i> Open Tickets</span>
							<span class="badge badge-warning">${openTickets}</span>
						</a>
						<a href="/tickets?status=PENDING" class="quick-filter">
							<span><i class="fas fa-clock text-secondary"></i> Pending Tickets</span>
							<span class="badge badge-secondary">${pendingTickets}</span>
						</a>
						<a href="/tickets?status=SOLVED" class="quick-filter">
							<span><i class="fas fa-check-circle text-success"></i> Solved Tickets</span>
							<span class="badge badge-success">${solvedTickets}</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="../shared/footer.jsp"%>
