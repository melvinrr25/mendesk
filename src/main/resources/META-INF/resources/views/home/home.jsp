<%@include file="../shared/header.jsp"%>

<div class="page-header" style="background: transparent; border: none; margin-bottom: 0;">
	<div class="container">
		<div class="text-center py-5">
			<div class="brand-logo mx-auto mb-4" style="width: 80px; height: 80px; background: var(--primary-color); border-radius: 16px; display: flex; align-items: center; justify-content: center; font-size: 2rem; color: white;">
				<i class="fas fa-headset"></i>
			</div>
			<h1 style="font-size: 2.5rem; font-weight: 700; margin-bottom: 1rem;">Welcome to Mendesk</h1>
			<p class="text-muted" style="font-size: 1.125rem; max-width: 600px; margin: 0 auto;">A modern support ticket system for managing customer inquiries and support requests</p>
		</div>
	</div>
</div>

<div class="content-area">
	<div class="container">
		<div class="row justify-content-center mb-5">
			<div class="col-md-4">
				<div class="card h-100">
					<div class="card-body text-center">
						<div class="stat-icon mx-auto mb-3" style="width: 64px; height: 64px; background: var(--primary-light); border-radius: 12px; display: flex; align-items: center; justify-content: center; font-size: 1.5rem; color: var(--primary-color);">
							<i class="fas fa-ticket-alt"></i>
						</div>
						<h5>Create Tickets</h5>
						<p class="text-muted mb-0">Submit support requests easily and track their progress</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card h-100">
					<div class="card-body text-center">
						<div class="stat-icon mx-auto mb-3" style="width: 64px; height: 64px; background: var(--success-light); border-radius: 12px; display: flex; align-items: center; justify-content: center; font-size: 1.5rem; color: var(--success-color);">
							<i class="fas fa-comments"></i>
						</div>
						<h5>Track Updates</h5>
						<p class="text-muted mb-0">Follow your ticket conversations in real-time</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card h-100">
					<div class="card-body text-center">
						<div class="stat-icon mx-auto mb-3" style="width: 64px; height: 64px; background: var(--info-light); border-radius: 12px; display: flex; align-items: center; justify-content: center; font-size: 1.5rem; color: var(--info-color);">
							<i class="fas fa-check-circle"></i>
						</div>
						<h5>Get Solutions</h5>
						<p class="text-muted mb-0">Receive timely support and resolutions</p>
					</div>
				</div>
			</div>
		</div>
		
		<div class="text-center">
			<c:if test="${empty pageContext.request.userPrincipal}">
				<a href="/login" class="btn btn-primary btn-lg">
					<i class="fas fa-sign-in-alt"></i> Sign In
				</a>
				<a href="/register" class="btn btn-outline-primary btn-lg ml-2">
					<i class="fas fa-user-plus"></i> Create Account
				</a>
			</c:if>
			<c:if test="${not empty pageContext.request.userPrincipal}">
				<a href="/dashboard" class="btn btn-primary btn-lg">
					<i class="fas fa-tachometer-alt"></i> Go to Dashboard
				</a>
			</c:if>
		</div>
	</div>
</div>

<%@include file="../shared/footer.jsp"%>
