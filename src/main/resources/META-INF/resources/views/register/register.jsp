<%@include file="../shared/header.jsp"%>

<div class="login-page">
	<div class="login-container">
		<div class="login-brand">
			<div class="brand-logo">
				<i class="fas fa-headset"></i>
			</div>
			<h1>Mendesk</h1>
			<p>Create your account</p>
		</div>
		
		<div class="login-card">
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					${errorMessage}
					<button type="button" class="close" data-dismiss="alert">
						<span>&times;</span>
					</button>
				</div>
			</c:if>

			<form action="/register" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

				<div class="form-group">
					<label for="username" class="form-label">Username</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" class="form-control form-control-lg" id="username" name="username" required minlength="3" maxlength="50" placeholder="Choose a username">
					</div>
				</div>

				<div class="form-group">
					<label for="email" class="form-label">Email</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-envelope"></i></span>
						</div>
						<input type="email" class="form-control form-control-lg" id="email" name="email" required placeholder="Enter your email">
					</div>
				</div>

				<div class="form-group">
					<label for="password" class="form-label">Password</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-lock"></i></span>
						</div>
						<input type="password" class="form-control form-control-lg" id="password" name="password" required minlength="6" placeholder="Choose a password">
					</div>
				</div>

				<div class="form-group">
					<label for="confirmPassword" class="form-label">Confirm Password</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-lock"></i></span>
						</div>
						<input type="password" class="form-control form-control-lg" id="confirmPassword" name="confirmPassword" required minlength="6" placeholder="Confirm your password">
					</div>
				</div>

				<button type="submit" class="btn btn-primary btn-lg btn-block">
					<i class="fas fa-user-plus"></i> Create Account
				</button>
			</form>
			
			<div class="text-center mt-4">
				<p class="text-muted mb-0">Already have an account? <a href="/login">Sign in</a></p>
			</div>
		</div>
	</div>
</div>

<%@include file="../shared/footer.jsp"%>
