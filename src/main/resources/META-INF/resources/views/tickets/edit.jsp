<%@include file="../shared/header.jsp"%>

<div class="page-header">
	<div class="container">
		<div class="d-flex justify-content-between align-items-center">
			<h1 class="page-title">
				<i class="fas fa-edit"></i>
				Edit Ticket
			</h1>
			<a href="/tickets/${ticket.id}" class="btn btn-outline-secondary">
				<i class="fas fa-arrow-left"></i> Back to Ticket
			</a>
		</div>
	</div>
</div>

<div class="content-area">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
						<form action="/tickets/${ticket.id}/edit" method="post">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

							<div class="form-group">
								<label for="subject" class="form-label">Subject</label>
								<input type="text" class="form-control form-control-lg" id="subject" name="subject" required maxlength="200" value="${ticket.subject}">
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="status" class="form-label">Status</label>
										<select class="form-control" id="status" name="status" required>
											<c:forEach items="${statuses}" var="status">
												<option value="${status}" ${ticket.status == status ? 'selected' : ''}>${status.displayName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="priority" class="form-label">Priority</label>
										<select class="form-control" id="priority" name="priority" required>
											<c:forEach items="${priorities}" var="priority">
												<option value="${priority}" ${ticket.priority == priority ? 'selected' : ''}>${priority.displayName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label for="description" class="form-label">Description</label>
								<textarea class="form-control" id="description" name="description" rows="6" required>${ticket.description}</textarea>
							</div>

							<div class="form-group">
								<label for="tags" class="form-label">Tags</label>
								<input type="text" class="form-control" id="tags" name="tags" value="${ticket.tags}" placeholder="Comma-separated tags (optional)">
							</div>

							<div class="form-group mb-0">
								<button type="submit" class="btn btn-primary btn-lg">
									<i class="fas fa-save"></i> Save Changes
								</button>
								<a href="/tickets/${ticket.id}" class="btn btn-outline-secondary btn-lg ml-2">
									Cancel
								</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="../shared/footer.jsp"%>
