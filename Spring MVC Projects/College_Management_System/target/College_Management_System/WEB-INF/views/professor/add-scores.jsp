<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Add Scores - ${courseName} - Semester ${semester}</title>

	<!-- Bootstrap & CSS -->
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/assets/css/app.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
	<div class="row justify-content-center">
		<div class="col-lg-10">
			<div class="card shadow">
				<div class="card-body p-4">

					<div class="d-flex justify-content-between align-items-center mb-4">
						<h4 class="mb-0">
							Add Scores - <span class="text-primary">${courseName}</span> | Semester <span class="text-success">${semester}</span>
						</h4>
						<a href="${pageContext.request.contextPath}/professor/dashboard" class="btn btn-secondary">
							🔙 Back to Dashboard
						</a>
					</div>

					<form action="${pageContext.request.contextPath}/professor/add-score" method="post">
						<input type="hidden" name="studentId" value="${student.id}" />

						<div class="table-responsive">
							<table class="table table-bordered table-striped">
								<thead class="table-light">
									<tr>
										<th>Subject Name</th>
										<th>Internal Marks (Obtained / Total)</th>
										<th>External Marks (Obtained / Total)</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="subject" items="${subjects}">
										<tr>
											<td>
												<input type="hidden" name="subjectId" value="${subject.id}">
												${subject.name}
											</td>
											<td>
												<div class="d-flex gap-2">
													<input type="number" class="form-control" name="internalMarksObtained_${subject.id}" required placeholder="Obtained">
													<span class="mt-2">/</span>
													<input type="number" class="form-control" name="internalMarksTotal_${subject.id}" required placeholder="Total">
												</div>
											</td>
											<td>
												<div class="d-flex gap-2">
													<input type="number" class="form-control" name="externalMarksObtained_${subject.id}" required placeholder="Obtained">
													<span class="mt-2">/</span>
													<input type="number" class="form-control" name="externalMarksTotal_${subject.id}" required placeholder="Total">
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

						<div class="d-grid mt-4">
							<button type="submit" class="btn btn-success">✅ Submit Scores</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</div>

<!-- Scripts -->
<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>