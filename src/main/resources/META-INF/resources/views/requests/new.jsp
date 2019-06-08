<%@ include file="../shared/header.jsp"%>

<div class="container-fluid">
	<div class="row">
		<form:form method="POST"
			action="${pageContext.request.contextPath}/requests"
			modelAttribute="appRequest">
			<div class="col-md-6">
				<form role="form">

					<div class="form-group">
						<form:label path="subject">Subject</form:label>
						<form:input path="subject" cssClass="form-control" />
					</div>

					<div class="form-group">
						<form:label path="priority">Priority</form:label>
						<form:select path="priority" cssClass="form-control"
							items="${priorityOptions}" />
					</div>


					<div class="form-group">
						<form:label path="body">Body</form:label>
						<form:textarea path="body" cssClass="form-control" />
					</div>


					<input type="submit" class="btn btn-primary" value="Submit" />

				</form>
			</div>
			<div class="col-md-6"></div>
		</form:form>
	</div>
</div>


<%@include file="../shared/footer.jsp"%>
