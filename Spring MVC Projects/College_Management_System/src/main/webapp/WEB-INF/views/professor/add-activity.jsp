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

	<title>Project Management - Add Activity</title>

	<style>
		body {
			background-color: #f8f9fa;
		}

		.center-container {
			display: flex;
			align-items: center;
			justify-content: center;
			min-height: 100vh;
		}

		.card {
			width: 100%;
			max-width: 800px;
			box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
		}

		.card-title {
			font-weight: bold;
			font-size: 1.5rem;
		}
	</style>
</head>

<body>

	<div class="container-fluid center-container">
		<div class="card">
			<div class="card-body">
				<div class="p-4">
					<div class="d-flex justify-content-between align-items-center mb-4">
	<div>
		<h5 class="card-title mb-0">Add Student Activity</h5>
		<h6 class="text-muted">Record extracurricular achievements of the student</h6>
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/professor/dashboard" class="btn btn-secondary">
			🔙 Dashboard
		</a>
	</div>
</div>
					<form action="${pageContext.request.contextPath}/professor/add-activity" method="post"
						enctype="multipart/form-data">
						<input type="hidden" name="studentId" value="${studentId}">

						<div class="mb-3">
							<label for="type" class="form-label">Activity Type:</label>
							<select name="type" id="type" class="form-select" required onchange="updateSubtypes()">
								<option value="Sports">Sports</option>
								<option value="Cultural">Cultural</option>
								<option value="Other">Other</option>
							</select>
						</div>

						<div class="mb-3">
							<label for="subtype" class="form-label">Subtype:</label>
							<select name="subtype" id="subtype" class="form-select" required></select>
						</div>

						<div class="mb-3">
							<label for="competitionLevel" class="form-label">Competition Level:</label>
							<select name="competitionLevel" class="form-select" required>
								<option value="College">College</option>
								<option value="State">State</option>
								<option value="National">National</option>
							</select>
						</div>

						<div class="mb-3">
							<label for="rank" class="form-label">Rank Achieved:</label>
							<input type="number" class="form-control" name="rank" min="1" placeholder="Optional">
						</div>

						<div class="mb-3">
							<label for="achievement" class="form-label">Achievement Description:</label>
							<input type="text" class="form-control" name="achievement" placeholder="Optional">
						</div>

						<div class="mb-3">
							<label for="image" class="form-label">Upload Proof (Image):</label>
							<input type="file" class="form-control" name="image" accept="image/*">
						</div>

						<div class="d-grid">
							<button type="submit" class="btn btn-primary">Add Activity</button>
						</div>

					</form>

				</div> <!-- p-4 -->
			</div> <!-- card-body -->
		</div> <!-- card -->
	</div> <!-- center-container -->

	<script>
		function updateSubtypes() {
			let type = document.getElementById("type").value;
			let subtypeSelect = document.getElementById("subtype");
			subtypeSelect.innerHTML = "";

			let subtypes = {
				"Sports": ["Cricket", "Football", "Basketball", "Athletics", "Badminton"],
				"Cultural": ["Dance", "Singing", "Debate", "Drama", "Painting"],
				"Other": ["Hackathon", "Coding Contest", "Essay Writing", "Photography"]
			};

			subtypes[type].forEach(sub => {
				let option = document.createElement("option");
				option.value = sub;
				option.textContent = sub;
				subtypeSelect.appendChild(option);
			});
		}

		window.onload = function () {
			updateSubtypes();
		};
	</script>

</body>

</html>