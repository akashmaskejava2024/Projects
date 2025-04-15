<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<!-- Meta -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Favicon -->
	<link rel="icon" href="${pageContext.request.contextPath}/resources/assets/images/favicon-32x32.png" type="image/png" />

	<!-- CSS -->
	<link href="${pageContext.request.contextPath}/resources/assets/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/assets/css/pace.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-extended.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/assets/css/app.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/assets/css/icons.css" rel="stylesheet">

	<script src="${pageContext.request.contextPath}/resources/assets/js/pace.min.js"></script>

	<!-- Custom Full-Height Style -->
	<style>
		html, body {
			height: 100%;
			margin: 0;
			padding: 0;
			background-color: #f8f9fa;
		}
		.page-wrapper {
			min-height: 100vh;
			display: flex;
			flex-direction: column;
		}
		.page-content {
			flex-grow: 1;
			display: flex;
			align-items: center;
			justify-content: center;
			padding: 20px;
		}
		.card {
			width: 100%;
			max-width: 900px;
		}
	</style>

	<title>Project Management - Mark Attendance</title>
</head>

<body onload="setTodayDate()">

	<!-- Main Wrapper -->
	<div class="page-wrapper">
		<div class="page-content">
			<div class="container-fluid">
				<div class="row justify-content-center">
					<div class="col-12">
						<div class="card shadow-sm">
							<div class="card-body">
								<div class="p-4">
									<!-- Header Title with Back Button -->
									<div class="d-flex justify-content-between align-items-center mb-4">
										<div>
											<h5 class="card-title mb-1">Mark Attendance</h5>
											<p class="mb-0">Mark students present for today's session</p>
										</div>
										<div>
											<a href="${pageContext.request.contextPath}/professor/dashboard" class="btn btn-secondary">🔙 Back to Dashboard</a>
										</div>
									</div>

									<form action="${pageContext.request.contextPath}/professor/mark-attendance" method="post">
										<div class="row mb-3">
											<div class="col-md-6">
												<label for="date" class="form-label">Date</label>
												<input type="date" id="date" name="date" class="form-control" readonly required>
											</div>
											<div class="col-md-6 d-flex align-items-end">
												<button type="button" class="btn btn-outline-primary" onclick="enableManualDate()">Change Date</button>
											</div>
										</div>

										<div class="table-responsive">
											<table class="table table-bordered table-striped">
												<thead class="table-light">
													<tr>
														<th>Student ID</th>
														<th>Name</th>
														<th>Mark Present</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="student" items="${students}">
														<tr>
															<td>${student.id}</td>
															<td>${student.name}</td>
															<td>
																<input class="form-check-input" type="checkbox" name="presentStudents" value="${student.id}">
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>

										<div class="d-grid mt-4">
											<button type="submit" class="btn btn-success">Submit Attendance</button>
										</div>
									</form>

									<!-- JavaScript -->
									<script>
										function setTodayDate() {
											const today = new Date().toISOString().split('T')[0];
											document.getElementById("date").value = today;
										}
										function enableManualDate() {
											document.getElementById("date").removeAttribute("readonly");
										}
									</script>

								</div> <!-- p-4 -->
							</div> <!-- card-body -->
						</div> <!-- card -->
					</div> <!-- col -->
				</div> <!-- row -->
			</div> <!-- container-fluid -->
		</div> <!-- page-content -->
	</div> <!-- page-wrapper -->

	<!-- Scripts -->
	<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/simplebar/js/simplebar.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/metismenu/js/metisMenu.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/app.js"></script>

</body>
</html>