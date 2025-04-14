<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Favicon -->
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

	<title>Project Management - Manage Parents</title>
</head>

<body>
	<!-- Wrapper -->
	<div class="wrapper">
		<!-- Sidebar -->
		<div class="sidebar-wrapper" data-simplebar="true">
			<div class="sidebar-header">
				<div>
					<img src="${pageContext.request.contextPath}/resources/assets/images/logo-icon.png" class="logo-icon" alt="logo icon">
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
	</div>
	<!-- End Sidebar -->

	<div class="page-wrapper">
		<div class="page-content">
	<!-- Heading with aligned buttons and compact search -->
<div class="d-flex justify-content-between align-items-center mb-3">
    <h2>👨‍👩‍👧 Manage Parents</h2>
    <div class="d-flex align-items-center">
        <!-- 🔍 Search Bar -->
        <input type="text" id="parentSearchInput" class="form-control me-2"
               placeholder="🔍 Search by Parent or Student name..." onkeyup="filterParents()" style="width: 250px;" />

        <!-- ➕ Add Parent Button -->
        <a href="${pageContext.request.contextPath}/parent/signup" class="btn btn-success me-2">➕ Add Parent</a>

        <!-- 🔙 Dashboard Button -->
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary me-2">🔙 Dashboard</a>

        <!-- 🚪 Logout Button -->
        <a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/logout">🚪 Logout</a>
    </div>
</div>
			<!-- Parent table -->
			<div class="card-body">
				<div class="table-responsive">
					<table id="parentTable" class="table table-striped table-bordered" style="width: 100%;">
						<thead>
							<tr>
								<th>ID</th>
								<th>Parent Name</th>
								<th>Student Name</th>
								<th>Phone Number</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="parent" items="${parents}">
								<tr>
									<td>${parent.id}</td>
									<td>${parent.name}</td>
									<td>${studentNames[parent.id]}</td>
									<td>${parent.phoneNumber}</td>
									<td>
										<a href="${pageContext.request.contextPath}/parent/updateForm?id=${parent.id}" class="btn btn-sm btn-warning">✏️ Edit</a>
										<a href="${pageContext.request.contextPath}/admin/parents/delete/${parent.id}" onclick="return confirm('Are you sure you want to delete this parent?')" class="btn btn-sm btn-danger">🗑 Delete</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>

	<!-- Scripts -->
	<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/simplebar/js/simplebar.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/metismenu/js/metisMenu.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/app.js"></script>

	<!-- Search filter script -->
	<script>
		function filterParents() {
			const input = document.getElementById("parentSearchInput").value.toLowerCase();
			const rows = document.querySelectorAll("#parentTable tbody tr");

			rows.forEach(row => {
				const parentName = row.querySelector("td:nth-child(2)").textContent.toLowerCase();
				const studentName = row.querySelector("td:nth-child(3)").textContent.toLowerCase();
				row.style.display = (parentName.includes(input) || studentName.includes(input)) ? "" : "none";
			});
		}
	</script>

</body>

</html>