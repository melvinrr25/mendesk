<%@ include file="../shared/header.jsp"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<form:form method="POST"
				action="${pageContext.request.contextPath}/requests"
				modelAttribute="appRequest">
				<table>
					<tr>
						<td><form:label path="subject">Subject</form:label></td>
						<td><form:input path="subject" /></td>
					</tr>
					<tr>
						<td><form:label path="priority">Priority</form:label></td>
						<td><form:select path="priority" items="${priorityOptions}" /></td>
					</tr>
					<tr>
						<td><form:label path="body">Body</form:label></td>
						<td><form:textarea path="body" /></td>
					</tr>
					
					<tr>
						<td><input type="submit" value="Submit" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</div>

<%@include file="../shared/footer.jsp"%>
