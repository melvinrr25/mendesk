<%@include file="../shared/header.jsp"%>

<div class="login-page">
	<div class="login-container">
		<div class="login-brand">
			<div class="brand-logo">
				<i class="fas fa-headset"></i>
			</div>
			<h1>Mendesk</h1>
			<p>Sign in to your account</p>
		</div>
		
		<div class="login-card">
			<c:if test="${not empty message}">
				<div class="alert alert-${messageType} alert-dismissible fade show" role="alert">
					${message}
					<button type="button" class="close" data-dismiss="alert">
						<span>&times;</span>
					</button>
				</div>
			</c:if>

			<form action="/login" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

				<div class="form-group">
					<label for="username" class="form-label">Username</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" class="form-control form-control-lg" id="username" name="username" required autofocus placeholder="Enter your username">
					</div>
				</div>

				<div class="form-group">
					<label for="password" class="form-label">Password</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-lock"></i></span>
						</div>
						<input type="password" class="form-control form-control-lg" id="password" name="password" required placeholder="Enter your password">
					</div>
				</div>

				<button type="submit" class="btn btn-primary btn-lg btn-block">
					<i class="fas fa-sign-in-alt"></i> Sign In
				</button>
			</form>
			
			<div class="text-center mt-4">
				<p class="text-muted mb-0">Don't have an account? <a href="/register">Create one</a></p>
			</div>
		</div>
	</div>
</div>

<%@include file="../shared/footer.jsp"%>
