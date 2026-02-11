<%@include file="../shared/header.jsp"%>

<div class="page-header">
	<div class="container">
		<div class="d-flex justify-content-between align-items-center">
			<h1 class="page-title">
				<i class="fas fa-tachometer-alt"></i>
				My Dashboard
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

		<div class="card">
			<div class="card-header d-flex justify-content-between align-items-center">
				<h5 class="mb-0">
					<i class="fas fa-list"></i> My Tickets
				</h5>
				<a href="/tickets/new" class="btn btn-primary btn-sm">
					<i class="fas fa-plus"></i> New Ticket
				</a>
			</div>
			<div class="card-body p-0">
				<c:if test="${empty tickets}">
					<div class="empty-state">
						<i class="fas fa-ticket-alt"></i>
						<h5>No tickets yet</h5>
						<p>Create your first ticket to get started</p>
						<a href="/tickets/new" class="btn btn-primary">
							<i class="fas fa-plus"></i> Create Ticket
						</a>
					</div>
				</c:if>
				<c:if test="${not empty tickets}">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>Ticket</th>
									<th>Subject</th>
									<th>Status</th>
									<th>Priority</th>
									<th>Created</th>
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
											<span class="badge badge-${ticket.status.bootstrapClass}">${ticket.status.displayName}</span>
										</td>
										<td>
											<span class="badge badge-${ticket.priority.bootstrapClass}">${ticket.priority.displayName}</span>
										</td>
										<td>
											<small class="text-muted">${ticket.createdAt}</small>
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
</div>

<%@include file="../shared/footer.jsp"%>
