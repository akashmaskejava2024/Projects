<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Favicon -->
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/assets/images/favicon-32x32.png"
	type="image/png" />

<!-- Plugins -->
<link
	href="${pageContext.request.contextPath}/resources/assets/plugins/simplebar/css/simplebar.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/assets/plugins/metismenu/css/metisMenu.min.css"
	rel="stylesheet" />

<!-- Loader -->
<link
	href="${pageContext.request.contextPath}/resources/assets/css/pace.min.css"
	rel="stylesheet" />
<script
	src="${pageContext.request.contextPath}/resources/assets/js/pace.min.js"></script>

<!-- Bootstrap CSS -->
<link
	href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-extended.css"
	rel="stylesheet">

<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap"
	rel="stylesheet">

<!-- App CSS -->
<link
	href="${pageContext.request.contextPath}/resources/assets/css/app.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/assets/css/icons.css"
	rel="stylesheet">

<title>Project Management - Login</title>
</head>

<body>

	<!-- Wrapper -->
	<div class="wrapper">

		<!-- Sidebar -->
		<div class="sidebar-wrapper" data-simplebar="true">
			<div class="sidebar-header">
				<div>
					<img
						src="${pageContext.request.contextPath}/resources/assets/images/logo-icon.png"
						class="logo-icon" alt="logo icon">
				</div>
			</div>

			<!-- Navigation -->
			<ul class="metismenu" id="menu">
				<li class="font-semibold mt-2">Admin</li>

				<li>
					<div class="menu-title">
						<a href="${pageContext.request.contextPath}/admin/students"
							class="block p-2 rounded-lg hover:bg-gray-200"> Manage
							Students </a>
					</div>
				</li>

				<li>
					<div class="menu-title">
						<a href="${pageContext.request.contextPath}/admin/professors"
							class="block p-2 rounded-lg hover:bg-gray-200"> Manage
							Professors </a>
					</div>
				</li>

				<li>
					<div class="menu-title">
						<a href="${pageContext.request.contextPath}/admin/parents"
							class="block p-2 rounded-lg hover:bg-gray-200"> Manage
							Parents </a>
					</div>
				</li>

				<li>
					<div class="menu-title">
						<a href="${pageContext.request.contextPath}/admin/courses"
							class="block p-2 rounded-lg hover:bg-gray-200"> Manage
							Courses </a>
					</div>
				</li>
			</ul>
		</div>
		<!-- End Sidebar -->
		<!--wrapper-->
		<div class="wrapper">
			<div class="d-flex align-items-center justify-content-center my-5">
				<div class="container-fluid">
					<div class="row row-cols-1 row-cols-lg-2 row-cols-xl-3">
						<div class="col mx-auto">
							<div class="card mb-0">
								<div class="card-body">
									<div class="p-4">
										<div class="mb-3 text-center">
											<img src="assets/images/logo-icon.png" width="60" alt="" />
										</div>
										<div class="text-center mb-4">
											<h5 class="">Student Registration</h5>
											<p class="mb-0">Create your account to get started with
												your project!</p>
										</div>
										<div class="form-body">
											<form class="row g-3" action="signup" method="post">
												<div class="col-12">
													<label for="name" class="form-label">Full Name</label> <input
														type="text" class="form-control" id="name" name="name"
														placeholder="Enter full name" required>
												</div>
												<div class="col-12">
													<label for="email" class="form-label">Email</label> <input
														type="email" class="form-control" id="email" name="email"
														placeholder="your@email.com" required>
												</div>
												<div class="col-12">
													<label for="password" class="form-label">Password</label> <input
														type="password" class="form-control" id="password"
														name="password" placeholder="Create a password" required>
												</div>
												<div class="col-12">
													<label for="rollNumber" class="form-label">Roll
														Number</label> <input type="text" class="form-control"
														id="rollNumber" name="rollNumber"
														placeholder="e.g. CS2023A001" required>
												</div>
												<div class="col-12">
    <label for="batch" class="form-label">Select Batch</label>
    <select class="form-select" name="batchId" id="batch" required>
        <option value="">Select Batch</option>
        <c:forEach var="batch" items="${batches}">
            <option value="${batch.id}">
                ${batchCourseMap[batch.id]} - Semester ${batch.semester}
            </option>
        </c:forEach>
    </select>
</div>
												<div class="col-12">
													<div class="d-grid">
														<button type="submit" class="btn btn-primary">Register</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- end col -->
					</div>
					<!-- end row -->
				</div>
			</div>
		</div>
		<!--end wrapper-->
		<p>${message}</p>
		<!-- Bootstrap JS -->
		<script src="assets/js/bootstrap.bundle.min.js"></script>
		<!--plugins-->
		<script src="assets/js/jquery.min.js"></script>
		<script src="assets/plugins/simplebar/js/simplebar.min.js"></script>
		<script src="assets/plugins/metismenu/js/metisMenu.min.js"></script>
		<script src="assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
		<!--Password show & hide js -->
		<script>
			$(document).ready(
					function() {
						$("#show_hide_password a").on(
								'click',
								function(event) {
									event.preventDefault();
									if ($('#show_hide_password input').attr(
											"type") == "text") {
										$('#show_hide_password input').attr(
												'type', 'password');
										$('#show_hide_password i').addClass(
												"bx-hide");
										$('#show_hide_password i').removeClass(
												"bx-show");
									} else if ($('#show_hide_password input')
											.attr("type") == "password") {
										$('#show_hide_password input').attr(
												'type', 'text');
										$('#show_hide_password i').removeClass(
												"bx-hide");
										$('#show_hide_password i').addClass(
												"bx-show");
									}
								});
					});
		</script>

		<!--app JS-->
		<script src="assets/js/app.js"></script>
</body>
</html>