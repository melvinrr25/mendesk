<li
	class="<%=(request.getRequestURI().contains("home") ? "active" : "")%>">
	<a href="<%=request.getContextPath()%>/"><b
		class="glyphicon glyphicon-home"></b> Home</a>
</li>

<li
	class="<%=(request.getRequestURI().contains("dashboard") ? "active" : "")%>">
	<a href="<%=request.getContextPath()%>/dashboard"><b
		class="glyphicon glyphicon-dashboard"></b> Dashboard</a>
</li>




<sec:authorize access="!isAuthenticated()">
	<li
		class="<%=(request.getRequestURI().contains("login") ? "active" : "")%>">
		<a href="<%=request.getContextPath()%>/login"><b
			class="glyphicon glyphicon-user"></b> Login</a>
	</li>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<li><a href="<%=request.getContextPath()%>/logout"><b
			class="glyphicon glyphicon-log-out"></b> Logout</a></li>
</sec:authorize>
