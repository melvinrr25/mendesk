<%@include file="../shared/header.jsp"%>

<div class="page-header">
	<div class="container">
		<div class="d-flex justify-content-between align-items-center">
			<h1 class="page-title">
				<i class="fas fa-ticket-alt"></i>
				All Tickets
			</h1>
			<a href="/tickets/new" class="btn btn-primary">
				<i class="fas fa-plus"></i> New Ticket
			</a>
		</div>
	</div>
</div>

<div class="content-area">
	<div class="container">
		<div class="card mb-4">
			<div class="card-body">
				<form action="/tickets" method="get" class="row align-items-end">
					<div class="col-md-4">
						<label class="form-label">Search</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-search"></i></span>
							</div>
							<input type="text" class="form-control" name="search" placeholder="Search tickets..." value="${param.search}">
						</div>
					</div>
					<div class="col-md-3">
						<label class="form-label">Status</label>
						<select class="form-control" name="status">
							<option value="">All Statuses</option>
							<c:forEach items="${statuses}" var="status">
								<option value="${status}" ${param.status == status.name() ? 'selected' : ''}>${status.displayName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-3">
						<label class="form-label">Priority</label>
						<select class="form-control" name="priority">
							<option value="">All Priorities</option>
							<c:forEach items="${priorities}" var="priority">
								<option value="${priority}" ${param.priority == priority.name() ? 'selected' : ''}>${priority.displayName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-2">
						<button type="submit" class="btn btn-outline-primary btn-block">
							<i class="fas fa-filter"></i> Filter
						</button>
					</div>
				</form>
				<c:if test="${not empty param.search or not empty param.status or not empty param.priority}">
					<div class="mt-3">
						<a href="/tickets" class="btn btn-outline-secondary btn-sm">
							<i class="fas fa-times"></i> Clear Filters
						</a>
					</div>
				</c:if>
			</div>
		</div>

		<div class="card">
			<div class="card-body p-0">
				<c:if test="${empty tickets}">
					<div class="empty-state">
						<i class="fas fa-search"></i>
						<h5>No tickets found</h5>
						<p>Try adjusting your filters or create a new ticket</p>
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
									<th>Requester</th>
									<th>Assignee</th>
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
											<small>${ticket.requester.username}</small>
										</td>
										<td>
											<c:if test="${not empty ticket.assignee}">
												<small>${ticket.assignee.username}</small>
											</c:if>
											<c:if test="${empty ticket.assignee}">
												<small class="text-muted">Unassigned</small>
											</c:if>
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
