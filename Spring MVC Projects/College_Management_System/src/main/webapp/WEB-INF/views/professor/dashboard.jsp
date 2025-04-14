<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="icon" href="${pageContext.request.contextPath}/resources/assets/images/favicon-32x32.png" type="image/png" />

	<!-- CSS Plugins -->
	<link href="${pageContext.request.contextPath}/resources/assets/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" />

	<!-- Loader -->
	<link href="${pageContext.request.contextPath}/resources/assets/css/pace.min.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/resources/assets/js/pace.min.js"></script>

	<!-- Bootstrap + App CSS -->
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-extended.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/assets/css/app.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/assets/css/icons.css" rel="stylesheet">

	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">

	<title>Professor Dashboard</title>
	<style>
		h2 {
			font-weight: 500;
			margin-bottom: 20px;
		}
		.card {
			box-shadow: 0 0 10px rgba(0,0,0,0.1);
			border-radius: 15px;
			padding: 20px;
		}
		.table th, .table td {
			vertical-align: middle;
		}
		.page-wrapper {
			padding-left: 0 !important;
			margin-left: 0 !important;
		}
	</style>
</head>

<body>
	<div class="wrapper">
		<div class="page-wrapper">
			<div class="page-content container-fluid">

				<!-- Header -->
				<div class="d-flex justify-content-between align-items-center mb-4">
					<h2>Professor Dashboard</h2>
					<div class="d-flex gap-2">
						<a href="${pageContext.request.contextPath}/professor/mark-attendance">
							<button class="btn btn-success">📝 Mark Attendance</button>
						</a>
						<form action="${pageContext.request.contextPath}/professor/logout" method="post">
							<button type="submit" class="btn btn-danger">🚪 Logout</button>
						</form>
					</div>
				</div>

				<!-- Student Table Card -->
				<div class="card">
					<div class="table-responsive">
						<table id="studentTable" class="table table-striped table-bordered align-middle mb-0">
							<thead class="table-light">
								<tr>
									<th>Student ID</th>
									<th>Name</th>
									<th>Email</th>
									<th>Assigned Courses</th>
									<th>Semester</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="student" items="${students}">
									<tr>
										<td>${student.id}</td>
										<td>${student.name}</td>
										<td>${student.email}</td>
										<td>
											<c:choose>
												<c:when test="${not empty studentCoursesMap[student.id]}">
													<c:forEach var="course" items="${studentCoursesMap[student.id]}">
														${course.name}<br>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<span class="text-muted">Not Assigned</span><br>
													<a href="${pageContext.request.contextPath}/professor/assign-course?studentId=${student.id}">
														<button class="btn btn-sm btn-warning mt-1">➕ Assign Course</button>
													</a>
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:choose>
												<c:when test="${not empty studentSemesterMap[student.id]}">
													Semester ${studentSemesterMap[student.id]}
												</c:when>
												<c:otherwise>
													<span class="text-muted">Not Assigned</span>
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<div class="d-flex flex-column gap-1">
												<a href="${pageContext.request.contextPath}/professor/student/performance?studentId=${student.id}" class="btn btn-sm btn-info">📊 View Performance</a>
												<a href="${pageContext.request.contextPath}/professor/add-score?studentId=${student.id}&semester=${studentSemesterMap[student.id]}" class="btn btn-sm btn-primary">➕ Enter Score</a>
												<a href="${pageContext.request.contextPath}/professor/add-activity?studentId=${student.id}" class="btn btn-sm btn-secondary">📝 Add Activity</a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div> <!-- End Card -->

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

</body>
</html>