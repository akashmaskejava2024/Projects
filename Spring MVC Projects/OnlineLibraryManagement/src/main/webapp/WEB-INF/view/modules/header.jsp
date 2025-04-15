<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<html lang="en">
<style>
.Usericon{}
</style>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">
<title>Library - Dashboard</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>

<!-- Favicon -->
<link rel="shortcut icon"
	href="<c:url value='/resources/assets/img/favicon.png'/>">


<!-- Fontfamily -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,400;0,500;0,700;0,900;1,400;1,500;1,700&amp;display=swap"
	rel="stylesheet">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/bootstrap.min.css"/>">

<!-- Feathericon CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/plugins/feather/feather.css"/>">

<!-- Pe7 CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/plugins/icons/flags/flags.css"/>">

<!-- Fontawesome CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/plugins/fontawesome/css/fontawesome.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/assets/plugins/fontawesome/css/all.min.css"/>">

<!-- Main CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/assets/css/style.css"/>">
<style type="text/css">
.down {
	width: 180px;
	height: 42px;
}
</style>

<script>
    function disableBackButton() {
        // Disable back button
        window.history.forward();
        window.onunload = function() { null };
        window.onbeforeunload = function() { null };
    }
</script>

</head>
<body>

	<!-- Main Wrapper -->
	<div class="main-wrapper">

		<!-- Header -->
		<div class="header">

			<!-- Logo -->
			<div class="header-left">
				<a href="index.jsp" class="logo">
					<h2>
						Online <span style="color: #3636abf0;">Library</span>
					</h2>
				</a> <a href="index.html" class="logo logo-small"> <img
					src="<c:url value='/resources/assets/img/logo-small.png'/>"
					height="30" width="30" alt="Logo" />
				</a>
			</div>
			<!-- /Logo -->

			<div class="menu-toggle">
				<a href="javascript:void(0);" id="toggle_btn"> <i
					class="fas fa-bars"></i>
				</a>
			</div>
			<!-- Header Right Menu -->
			<ul class="nav user-menu">

				<li class="nav-item zoom-screen me-2"><a href="#"
					class="nav-link header-nav-list win-maximize"
					onclick="maximizeWindow()"> <img
						src="<c:url value='/resources/assets/img/icons/header-icon-04.svg' />"
						alt="" />

				</a></li>

				<!-- User Menu -->
				<li class="nav-item dropdown has-arrow new-user-menus"><a
					href="#" class="dropdown-toggle nav-link" data-bs-toggle="dropdown">
						<div class="user-img">
							<img src="<c:url value='/resources/assets/image/user1.jpg' />">
							<div class="user-text">
								<h6>${sessionScope.loggedInUsername}</h6>
								<p class="text-muted mb-0">Administrator</p>
							</div>
						</div>
				</a>
					<div class="dropdown-menu">
						<div class="user-header">
							<div class="avatar avatar-sm">
								<img  src="<c:url value='/resources/assets/image/user1.jpg'/>">
							</div>
							<div class="user-text">
								<h6>${sessionScope.loggedInUsername}</h6>
								<p class="text-muted mb-0">Administrator</p>
							</div>
						</div>
						<a class="dropdown-item" href="<c:url value='/logout'/>"
							onclick="disableBackButton()">Logout</a>
					</div></li>
				<!-- /User Menu -->

			</ul>
			<!-- /Header Right Menu -->

		</div>
		<!-- /Header -->

		<!-- Sidebar -->
		<div class="sidebar" id="sidebar">
			<div class="sidebar-inner slimscroll">
				<div id="sidebar-menu" class="sidebar-menu">
					<ul>
						<li class="menu-title"><span>Main Menu</span></li>
						<li class="submenu active"><a href="${pageContext.request.contextPath}/libraryindex"><i
								class="feather-grid"></i> <span> Dashboard</span> </span></a></li>
						<li class="submenu dropdown"><a data-bs-toggle="dropdown"
							href="#"> <i class="fas fa-graduation-cap"></i> <span>
									Students</span>
						</a>
							<ul class="dropdown-menu">
								<li><a class="down" href="<c:url value='/students/list'/>">List</a></li>
								<li><a class="down"
									href="<c:url value='/students/add-form'/>">Add</a></li>
							</ul></li>

						<li class="submenu dropdown"><a data-bs-toggle="dropdown"
							href="#"><i class="fas fa-chalkboard-teacher"></i> <span>
									Teachers</span></a>
							<ul class="dropdown-menu">
								<li><a class="down" href="<c:url value='/teachers/list'/>">List</a></li>
								<li><a class="down"
									href="<c:url value='/teachers/add-form'/>">Add</a></li>
							</ul></li>
						<li class="submenu dropdown"><a data-bs-toggle="dropdown"
							href="#"><i class="fas fa-book"></i> <span>Books</span></a>
							<ul class="dropdown-menu">
								<li><a class="down" href="<c:url value='/books/add-form'/>">Add</a></li>
								<li><a class="down" href="<c:url value='/books/list'/>">List</a></li>
								<li><a class="down"
									href="<c:url value='/books/issuerecord'/>">Issue Record</a></li>
							</ul></li>
						<li><a href="<c:url value='/books/issueBook-form'/>"><i
								class="fas fa-book"></i> Issue Book</a></li>


					</ul>
				</div>
			</div>
		</div>
		<!-- /Sidebar -->


		<script>
			function maximizeWindow() {
				// You can add the logic here to maximize the window
				// For example, in pure JavaScript:
				if (document.body.requestFullscreen) {
					document.body.requestFullscreen();
				} else if (document.body.mozRequestFullScreen) {
					document.body.mozRequestFullScreen();
				} else if (document.body.webkitRequestFullscreen) {
					document.body.webkitRequestFullscreen();
				} else if (document.body.msRequestFullscreen) {
					document.body.msRequestFullscreen();
				}
			}
		</script>