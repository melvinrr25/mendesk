<%@ include file="../shared/header.jsp"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>Number</th>
						<th>Status</th>
						<th>Priority</th>
						<th>Subject</th>
						<th>Creation Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requests}" var="req">
						<tr>
							<td><a
								href="${request.getContextPath()}/requests/${req.getId()}">
									<c:out value="${req.getId()}" />
							</a></td>
							<td><c:out value="${req.getSubject()}" /></td>
							<td><c:out value="${req.getState()}" /></td>
							<td><c:out value="${req.getPriority()}" /></td>
							<td><c:out value="${req.getShortBody(40)}" /></td>
							<td><c:out value="${req.getCreatedAt()}" /></td>
						</tr>

					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>

<%@include file="../shared/footer.jsp"%>
