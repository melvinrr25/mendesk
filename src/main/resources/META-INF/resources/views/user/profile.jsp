<%@include file="../shared/header.jsp"%>

<div class="page-header">
	<div class="container">
		<h1 class="page-title">
			<i class="fas fa-user-circle"></i>
			My Profile
		</h1>
	</div>
</div>

<div class="content-area">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col-md-4 text-center">
								<div class="comment-avatar mx-auto mb-3" style="width: 100px; height: 100px; font-size: 2.5rem;">${user.initials}</div>
								<h5 class="mb-2">${user.username}</h5>
								<c:choose>
									<c:when test="${user.role == 'ADMIN'}">
										<span class="badge badge-danger">ADMIN</span>
									</c:when>
									<c:when test="${user.role == 'AGENT'}">
										<span class="badge badge-warning">AGENT</span>
									</c:when>
									<c:otherwise>
										<span class="badge badge-info">END USER</span>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-md-8">
								<table class="table mb-0">
									<tr>
										<td style="width: 100px;"><strong>Email</strong></td>
										<td>${user.email}</td>
									</tr>
									<tr>
										<td><strong>Username</strong></td>
										<td>${user.username}</td>
									</tr>
									<tr>
										<td><strong>Status</strong></td>
										<td>
											<c:if test="${user.enabled}">
												<span class="badge badge-success">Active</span>
											</c:if>
											<c:if test="${!user.enabled}">
												<span class="badge badge-secondary">Inactive</span>
											</c:if>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="../shared/footer.jsp"%>
