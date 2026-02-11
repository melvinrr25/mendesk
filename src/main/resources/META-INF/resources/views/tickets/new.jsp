<%@include file="../shared/header.jsp"%>

<div class="page-header">
	<div class="container">
		<div class="d-flex justify-content-between align-items-center">
			<h1 class="page-title">
				<i class="fas fa-plus"></i>
				Create New Ticket
			</h1>
			<a href="/tickets" class="btn btn-outline-secondary">
				<i class="fas fa-arrow-left"></i> Back to Tickets
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
						<form action="/tickets" method="post">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

							<div class="form-group">
								<label for="subject" class="form-label">Subject</label>
								<input type="text" class="form-control form-control-lg" id="subject" name="subject" required maxlength="200" placeholder="Brief summary of your issue">
							</div>

							<div class="form-group">
								<label for="priority" class="form-label">Priority</label>
								<select class="form-control" id="priority" name="priority" required>
									<c:forEach items="${priorities}" var="priority">
										<option value="${priority}">${priority.displayName}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-group">
								<label for="description" class="form-label">Description</label>
								<textarea class="form-control" id="description" name="description" rows="6" required placeholder="Describe your issue in detail..."></textarea>
							</div>

							<div class="form-group">
								<label for="tags" class="form-label">Tags</label>
								<input type="text" class="form-control" id="tags" name="tags" placeholder="Comma-separated tags (optional)">
							</div>

							<div class="form-group mb-0">
								<button type="submit" class="btn btn-primary btn-lg">
									<i class="fas fa-paper-plane"></i> Submit Ticket
								</button>
								<a href="/tickets" class="btn btn-outline-secondary btn-lg ml-2">
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
