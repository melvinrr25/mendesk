<%@include file="../shared/header.jsp"%>

<div class="page-header">
	<div class="container">
		<h1 class="page-title">
			<i class="fas fa-users-cog"></i>
			User Management
		</h1>
	</div>
</div>

<div class="content-area">
	<div class="container">
		<div class="card">
			<div class="card-body p-0">
				<c:if test="${empty users}">
					<div class="empty-state">
						<i class="fas fa-users"></i>
						<h5>No users found</h5>
					</div>
				</c:if>
				<c:if test="${not empty users}">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>ID</th>
									<th>Username</th>
									<th>Email</th>
									<th>Role</th>
									<th>Status</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${users}" var="user">
									<tr>
										<td>${user.id}</td>
										<td><strong>${user.username}</strong></td>
										<td>${user.email}</td>
										<td>
											<form action="/admin/users/${user.id}/role" method="post" class="d-inline">
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
												<select name="role" class="form-control form-control-sm" onchange="this.form.submit()" style="width: auto;">
													<c:forEach items="${roles}" var="role">
														<option value="${role}" ${user.role == role ? 'selected' : ''}>${role}</option>
													</c:forEach>
												</select>
											</form>
										</td>
										<td>
											<c:if test="${user.enabled}">
												<span class="badge badge-success">Active</span>
											</c:if>
											<c:if test="${!user.enabled}">
												<span class="badge badge-secondary">Inactive</span>
											</c:if>
										</td>
										<td>
											<sec:authorize access="isAuthenticated()">
												<sec:authentication property="principal.username" var="currentUsername" />
												<c:if test="${user.username != currentUsername}">
													<form action="/admin/users/${user.id}/delete" method="post" class="d-inline" onsubmit="return confirm('Are you sure?');">
														<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
														<button type="submit" class="btn btn-sm btn-outline-danger">
															<i class="fas fa-trash"></i>
														</button>
													</form>
												</c:if>
											</sec:authorize>
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
