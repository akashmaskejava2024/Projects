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

<!-- Semester Update Script -->
<script>
	const courseSemesterMap = {
		<c:forEach var="entry" items="${courseSemesterMap}">
			${entry.key}: [<c:forEach var="sem" items="${entry.value}" varStatus="loop">
				${sem}<c:if test="${!loop.last}">,</c:if>
			</c:forEach>],
		</c:forEach>
	};

	function updateSemesters(selectElement, professorId) {
		const courseId = selectElement.value;
		const semesterDropdown = document.getElementById("semesterDropdown_" + professorId);

		semesterDropdown.innerHTML = '<option value="">Select Semester</option>';

		if (!courseSemesterMap[courseId]) return;

		courseSemesterMap[courseId].forEach(sem => {
			const option = document.createElement("option");
			option.value = sem;
			option.textContent = "Semester " + sem;
			semesterDropdown.appendChild(option);
		});
	}
</script>
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
									<img src="${pageContext.request.contextPath}/assets/images/logo-icon.png" width="60" alt="" />
								</div>
								<div class="text-center mb-4">
									<h5 class="">Update Profile</h5>
									<p class="mb-0">Edit your profile details below</p>
								</div>
								<div class="form-body">
									<form class="row g-3" action="${pageContext.request.contextPath}/professor/update-profile" method="post">
										<input type="hidden" name="id" value="${professor.id}">

										<div class="col-12">
											<label for="name" class="form-label">Name</label>
											<input type="text" class="form-control" id="name" name="name" value="${professor.name}" required>
										</div>

										<div class="col-12">
											<label for="email" class="form-label">Email</label>
											<input type="email" class="form-control" id="email" name="email" value="${professor.email}" required>
										</div>

										<div class="col-12">
											<label for="password" class="form-label">New Password</label>
											<input type="password" class="form-control" id="password" name="password" value="${professor.password}">
										</div>

										

										<div class="col-12">
											<div class="d-grid">
												<button type="submit" class="btn btn-primary">Update Profile</button>
											</div>
										</div>

										<div class="col-12">
											<div class="text-center mt-2">
												<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/professor/dashboard">Back to Dashboard</a>
											</div>
										</div>
									</form>
								</div> <!-- form-body -->
							</div> <!-- p-4 -->
						</div> <!-- card-body -->
					</div> <!-- card -->
				</div> <!-- col -->
			</div> <!-- row -->
		</div> <!-- container-fluid -->
	</div> <!-- d-flex -->
</div> <!-- wrapper -->

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
<!-- plugins -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/simplebar/js/simplebar.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/metismenu/js/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
<!-- app JS -->
<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
</body>
</html>