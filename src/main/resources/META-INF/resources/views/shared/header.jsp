<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mendesk - Support Ticket System</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.png" type="image/x-icon" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/site.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-lg">
	<div class="container">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/">
			<i class="fas fa-headset"></i>
			<span>Mendesk</span>
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
			<i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<sec:authorize access="!isAuthenticated()">
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath()%>/login">
							<i class="fas fa-sign-in-alt"></i> Login
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath()%>/register">
							<i class="fas fa-user-plus"></i> Register
						</a>
					</li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath()%>/dashboard">
							<i class="fas fa-home"></i> Dashboard
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath()%>/tickets">
							<i class="fas fa-ticket-alt"></i> Tickets
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath()%>/tickets/new">
							<i class="fas fa-plus"></i> New Ticket
						</a>
					</li>
					<sec:authorize access="hasRole('ADMIN')">
						<li class="nav-item">
							<a class="nav-link" href="<%=request.getContextPath()%>/admin/users">
								<i class="fas fa-users-cog"></i> Manage Users
							</a>
						</li>
					</sec:authorize>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown">
							<i class="fas fa-user-circle"></i>
							<span><sec:authentication property="principal.username" /></span>
						</a>
						<div class="dropdown-menu dropdown-menu-right">
							<a class="dropdown-item" href="<%=request.getContextPath()%>/profile">
								<i class="fas fa-user"></i> Profile
							</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="<%=request.getContextPath()%>/logout">
								<i class="fas fa-sign-out-alt"></i> Logout
							</a>
						</div>
					</li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>

<main>
