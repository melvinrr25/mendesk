<%@ include file="../shared/header.jsp"%>

<div class="row">
	<div class="center-block col-md-2" style="float: none;">
		<img class="home-image" alt="Logo"
			src="<%=request.getContextPath()%>/images/logo-transparent.png">
	</div>
</div>

<div class="jumbotron">
	<h1 class="display-4">Need to complain?</h1>
	<p class="lead">This is a simple hero unit, a simple
		jumbotron-style component for calling extra attention to featured
		content or information.</p>
	<hr class="my-4">
	<p>It uses utility classes for typography and spacing to space
		content out within the larger container.</p>
	<p class="lead">
		<a class="btn btn-info btn-lg" href="<%=request.getContextPath()%>/dashboard" role="button">Create a Request Now!</a>
	</p>
</div>

<%@include file="../shared/footer.jsp"%>
