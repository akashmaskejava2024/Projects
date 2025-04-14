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

	<title>Project Management - Manage Courses</title>
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

			<ul class="metismenu" id="menu">
			<li class="font-semibold mt-2">Admin</li>
			<li><a href="${pageContext.request.contextPath}/admin/students" class="block p-2">👨‍🎓 Manage Students</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/professors" class="block p-2 active">👨‍🏫 Manage Professors</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/parents" class="block p-2">👪 Manage Parents</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/courses" class="block p-2">📘 Manage Branches</a></li>
		</ul>
		</div>
		<!-- End Sidebar -->

		<!-- Page Wrapper -->
		<div class="page-wrapper">

			<!-- Top Navbar -->
<nav class="navbar navbar-expand-lg navbar-light px-4">
	<div class="container-fluid d-flex justify-content-between align-items-center">
		<span class="navbar-brand mb-0 h4">Admin Dashboard</span>

		<div class="d-flex align-items-center gap-2">
			<!-- Add Course Button -->
			<button class="btn btn-primary" onclick="addNewCourseRow()">➕ Add Branch</button>

			<!-- Back to Dashboard Button -->
			<a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary">🔙 Back to Dashboard</a>

			<!-- Logout Button -->
			<form action="${pageContext.request.contextPath}/admin/logout" method="get" class="d-inline">
				<button type="submit" class="btn btn-outline-secondary">Logout</button>
			</form>
		</div>
	</div>
</nav>

			<!-- Page Content -->
			<div class="page-content">
				<h2 class="mt-3">Manage Branches</h2>

				<div class="card-body">
					<div class="table-responsive">
						<div id="course_wrapper" class="dataTables_wrapper dt-bootstrap5">
							<table id="courseTable"
								class="table table-striped table-bordered dataTable"
								style="width: 100%;" role="grid">
								<thead>
									<tr>
										<th>ID</th>
										<th>Branch Name</th>
										<th>Semester</th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="course" items="${courses}">
										<tr>
											<td>${course.id}</td>
											<td>${course.name}</td>
											<td>${course.semester}</td>
											<td>
												<button onclick="confirmDelete(${course.id})" class="btn btn-sm btn-danger">🗑 Delete</button>
												<a href="${pageContext.request.contextPath}/admin/subjects/${course.id}">
													<button class="btn btn-sm btn-danger">Manage Subjects</button>
												</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						

						</div>
					</div>
				</div>
			</div> <!-- End Page Content -->
		</div> <!-- End Page Wrapper -->

		<!-- JavaScript -->
		<script>
			function addNewCourseRow() {
				let table = document.getElementById("courseTable").getElementsByTagName('tbody')[0];
				let newRow = document.createElement("tr");

				newRow.innerHTML = `
					<td>Auto</td>
					<td><input type="text" name="name" class="form-control form-control-sm" form="newCourseForm" required></td>
					<td><input type="number" name="semester" min="1" class="form-control form-control-sm" form="newCourseForm" required></td>
					<td>
						<form id="newCourseForm" method="POST" action="${pageContext.request.contextPath}/admin/courses/add" class="d-flex gap-2">
							<button type="submit" class="btn btn-sm btn-success">✅ Save</button>
							<button type="button" class="btn btn-sm btn-danger" onclick="removeRow(this)">❌ Cancel</button>
						</form>
					</td>
				`;
				table.appendChild(newRow);
			}

			function removeRow(button) {
				let row = button.closest("tr");
				row.remove();
			}

			function confirmDelete(courseId) {
				if (confirm("Are you sure you want to delete this course?")) {
					window.location.href = "${pageContext.request.contextPath}/admin/courses/delete/" + courseId;
				}
			}
		</script>

		<!-- Bootstrap JS -->
		<script
			src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.bundle.min.js"></script>

		<!-- Plugins -->
		<script
			src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/assets/plugins/simplebar/js/simplebar.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/assets/plugins/metismenu/js/metisMenu.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>

		<!-- App JS -->
		<script
			src="${pageContext.request.contextPath}/resources/assets/js/app.js"></script>

</body>
</html>