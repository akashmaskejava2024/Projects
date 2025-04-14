<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="${pageContext.request.contextPath}/resources/assets/images/favicon-32x32.png" type="image/png" />

	<!-- Plugins -->
	<link href="${pageContext.request.contextPath}/resources/assets/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" />

	<!-- Loader -->
	<link href="${pageContext.request.contextPath}/resources/assets/css/pace.min.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/resources/assets/js/pace.min.js"></script>

	<!-- Bootstrap CSS -->
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-extended.css" rel="stylesheet">

	<!-- Fonts -->
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">

	<!-- App CSS -->
	<link href="${pageContext.request.contextPath}/resources/assets/css/app.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/assets/css/icons.css" rel="stylesheet">

	<title>Project Management - Admin Dashboard</title>
</head>

<body>

	<!-- Wrapper -->
	<div class="wrapper">

		<!-- Sidebar -->
		<div class="sidebar-wrapper" data-simplebar="true">
			<div class="sidebar-header">
				<div>
					<img src="${pageContext.request.contextPath}/resources/assets/images/logo-icon.png"
						class="logo-icon" alt="logo icon">
				</div>
			</div>

			<!-- Navigation -->
		<ul class="metismenu" id="menu">
			<li class="font-semibold mt-2">Admin</li>
			<li><a href="${pageContext.request.contextPath}/admin/students" class="block p-2">👨‍🎓 Manage Students</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/professors" class="block p-2 active">👨‍🏫 Manage Professors</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/parents" class="block p-2">👪 Manage Parents</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/courses" class="block p-2">📘 Manage Branches</a></li>
		</ul>
		</div>
		<!-- End Sidebar -->

		<!-- Page Content -->
		<div class="page-wrapper">

			<!-- Top Navbar -->
<nav class="navbar navbar-expand-lg navbar-light px-4">
	<div class="container-fluid">
		<span class="navbar-brand mb-0 h4">Admin Dashboard</span>
		<form action="${pageContext.request.contextPath}/admin/logout" method="get" class="ms-auto">
			<button type="submit" class="btn btn-secondary">Logout</button>
		</form>
	</div>
</nav>

			<!-- Dashboard Content -->
			<div class="page-content mt-4">

				<div class="row row-cols-1 row-cols-md-3 g-4">

					<!-- Total Students Card -->
					<div class="col">
						<div class="card radius-10">
							<div class="card-body">
								<div class="d-flex align-items-center">
									<div>
										<p class="mb-0 text-secondary">Total Students</p>
										<h4 class="my-1 text-primary">${totalStudents}</h4>
									</div>
									<div class="widgets-icons-2 rounded-circle bg-gradient-purple text-white ms-auto">
										<i class="bx bxs-user"></i>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Total Professors Card -->
					<div class="col">
						<div class="card radius-10">
							<div class="card-body">
								<div class="d-flex align-items-center">
									<div>
										<p class="mb-0 text-secondary">Total Professors</p>
										<h4 class="my-1 text-success">${totalProfessors}</h4>
									</div>
									<div class="widgets-icons-2 rounded-circle bg-gradient-success text-white ms-auto">
										<i class="bx bxs-user-voice"></i>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Total Branches Card (renamed from Courses) -->
					<div class="col">
						<div class="card radius-10">
							<div class="card-body">
								<div class="d-flex align-items-center">
									<div>
										<p class="mb-0 text-secondary">Total Branches</p>
										<h4 class="my-1 text-warning">${totalCourses}</h4>
									</div>
									<div class="widgets-icons-2 rounded-circle bg-gradient-warning text-white ms-auto">
										<i class="bx bxs-book-alt"></i>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div> <!-- End row -->
			</div> <!-- End page-content -->

		</div> <!-- End page-wrapper -->
	</div> <!-- End wrapper -->

</body>
</html>