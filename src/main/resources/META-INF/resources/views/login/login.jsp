<%@include file="../shared/header.jsp"%>

<c:if test="${not empty errorMessage}">
	<div class="alert alert-info alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		${errorMessage}
	</div>
</c:if>

<div id="login" class="container">
	<div class="row-fluid">
		<div class="span12">
			<div class="login well well-small">
				<div class="center">
					<img class="full-image" alt="Logo"
						src="<%=request.getContextPath()%>/images/logo-transparent.png">
				</div>
				<form action="/login" style="" class="login-form" id="UserLoginForm"
					method="post" accept-charset="utf-8">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="input-group input-group-lg">
						<span class="input-group-addon" id="sizing-addon1"><b
							class="glyphicon glyphicon-user"></b></span> <input class="form-control"
							type='text' name='username' required="required"
							placeholder="Username" aria-describedby="sizing-addon1" />
					</div>
					<br />
					<div class="input-group input-group-lg">
						<span class="input-group-addon" id="sizing-addon1"><b
							class="glyphicon glyphicon-lock"></b></span> <input class="form-control"
							type='password' name='password' required="required"
							placeholder="Password" aria-describedby="sizing-addon1" />
					</div>


					<br />
					<div class="control-group">
						<input class="btn btn-info btn-lg btn-block" type="submit"
							value="Sign in">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<%@include file="../shared/footer.jsp"%>

