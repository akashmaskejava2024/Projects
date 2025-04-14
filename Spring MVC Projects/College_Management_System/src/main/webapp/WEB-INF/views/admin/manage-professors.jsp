<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Project Management - Professors</title>

	<!-- Favicon -->
	<link rel="icon" href="${pageContext.request.contextPath}/resources/assets/images/favicon-32x32.png" type="image/png" />

	<!-- Plugins -->
	<link href="${pageContext.request.contextPath}/resources/assets/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" />

	<!-- Loader -->
	<link href="${pageContext.request.contextPath}/resources/assets/css/pace.min.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/resources/assets/js/pace.min.js"></script>

	<!-- Bootstrap & App CSS -->
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-extended.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/assets/css/app.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/assets/css/icons.css" rel="stylesheet">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">

	<!-- Semester Mapping Script -->
	<script>
		const branchSemesterMap = {
			<c:forEach var="entry" items="${courseSemesterMap}">
				${entry.key}: [<c:forEach var="sem" items="${entry.value}" varStatus="loop">
					${sem}<c:if test="${!loop.last}">,</c:if>
				</c:forEach>],
			</c:forEach>
		};

		function updateSemesters(selectElement, professorId) {
			const branchId = selectElement.value;
			const semesterDropdown = document.getElementById("semesterDropdown_" + professorId);
			semesterDropdown.innerHTML = '<option value="">Select Semester</option>';

			if (!branchSemesterMap[branchId]) return;
			branchSemesterMap[branchId].forEach(sem => {
				const option = document.createElement("option");
				option.value = sem;
				option.textContent = "Semester " + sem;
				semesterDropdown.appendChild(option);
			});
		}

		function filterProfessors() {
			const input = document.getElementById("searchInput");
			const filter = input.value.toLowerCase();
			const rows = document.querySelectorAll("tbody tr");

			rows.forEach(row => {
				const id = row.cells[0].textContent.toLowerCase();
				const name = row.cells[1].textContent.toLowerCase();
				const email = row.cells[2].textContent.toLowerCase();
				row.style.display = (id.includes(filter) || name.includes(filter) || email.includes(filter)) ? "" : "none";
			});
		}
	</script>
</head>
<body>

<div class="wrapper">

	<!-- Top Navbar -->
	<header class="top-header">
		<nav class="navbar navbar-expand gap-3">
			<div class="ms-auto d-flex align-items-center"></div>
		</nav>
	</header>

	<!-- Sidebar -->
	<div class="sidebar-wrapper" data-simplebar="true">
		<div class="sidebar-header">
			<div>
				<img src="${pageContext.request.contextPath}/resources/assets/images/logo-icon.png" class="logo-icon" alt="logo icon">
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

	<!-- Page Content -->
	<div class="page-wrapper">
		<div class="page-content">
<div class="d-flex justify-content-between align-items-center mb-3">
    <h2>👨‍🏫 All Professors</h2>
    <div class="d-flex align-items-center">
        <!-- 🔍 Search Bar -->
        <input type="text" id="searchInput" class="form-control me-2" placeholder="🔍 Search by ID, Name, or Email" onkeyup="filterProfessors()" style="width: 250px;" />
        
        <!-- ➕ Add Professor Button -->
        <a href="${pageContext.request.contextPath}/professor/signup" class="btn btn-success me-2">➕ Add Professor</a>

        <!-- 🔙 Dashboard Button -->
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary">🔙 Dashboard</a>
    </div>
</div>

			<!-- 🔍 Search Bar -->

			<div class="card"><!-- Professor Table -->
<div class="card-body">
    <div class="table-responsive">
        <table id="professorTable" class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Assigned Batches</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="professor" items="${professors}">
                    <tr>
                        <td>${professor.id}</td>
                        <td class="professor-name">${professor.name}</td>
                        <td>${professor.email}</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty professorBatchesMap[professor.id]}">
                                    <ul class="mb-0 ps-3">
                                        <c:forEach var="batch" items="${professorBatchesMap[professor.id]}">
                                            <li><strong>Batch:</strong> ${batch.id} | <strong>Branch:</strong> ${courseMap[batch.courseId]} | <strong>Sem:</strong> ${batch.semester}</li>
                                        </c:forEach>
                                    </ul>
                                </c:when>
                                <c:otherwise>No Batch Assigned</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <div class="d-flex flex-column gap-1">
                                <!-- Action Buttons -->
                                <div class="d-flex flex-wrap gap-1">
                                    <a href="${pageContext.request.contextPath}/student/profile/${professor.id}" class="btn btn-sm btn-warning">✏ Edit</a>
                                    <a href="${pageContext.request.contextPath}/admin/professors/delete/${professor.id}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this professor?')">🗑 Delete</a>
                                    <a href="${pageContext.request.contextPath}/admin/professors/changeBatch/${professor.id}" class="btn btn-sm btn-info">🔄 Change Batch</a>
                                </div>

                                <!-- Assign Batch Form -->
                                <form action="${pageContext.request.contextPath}/admin/professors/assignBatch" method="post" class="mt-2">
                                    <input type="hidden" name="professorId" value="${professor.id}">
                                    <div class="d-flex flex-column gap-1">
                                        <select name="courseId" class="form-select form-select-sm" onchange="updateSemesters(this, ${professor.id})" required>
                                            <option value="">Select Branch</option>
                                            <c:forEach var="course" items="${availableCourses}">
                                                <option value="${course.id}">${course.name}</option>
                                            </c:forEach>
                                        </select>
                                        <select name="semester" id="semesterDropdown_${professor.id}" class="form-select form-select-sm" required>
                                            <option value="">Select Semester</option>
                                        </select>
                                        <button type="submit" class="btn btn-sm btn-primary">✅ Assign Batch</button>
                                    </div>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
		</div>
	</div>

</div>

</body>
</html>