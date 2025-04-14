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
<script>
        function updateSemesters() {
            var selectedValue = document.getElementById("courseSemesterSelect").value;
            var [selectedCourseId, selectedSemester] = selectedValue.split("_");

            document.getElementById("hiddenCourseId").value = selectedCourseId;
            document.getElementById("hiddenSemester").value = selectedSemester;
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
		</div>
		<!-- End Sidebar -->
    
    <div class="page-wrapper">
    <div class="page-content">

        <h2>Change Professor for a Course</h2>

        <div class="card-body">
            <form action="${pageContext.request.contextPath}/admin/professors/changeBatch" method="post">
                <input type="hidden" name="currentProfessorId" value="${professorId}">

                <div class="mb-3">
                    <label class="form-label">Select Course & Semester:</label>
                    <select name="courseSemester" id="courseSemesterSelect" class="form-select" required onchange="updateSemesters()">
                        <option value="">-- Select Course & Semester --</option>
                        <c:forEach var="batch" items="${currentProfessorBatches}">
                            <c:set var="courseName" value="${courseMap[batch.courseId]}" />
                            <option value="${batch.courseId}_${batch.semester}">
                                ${courseName} - Semester ${batch.semester}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Hidden Inputs -->
                <input type="hidden" name="courseId" id="hiddenCourseId">
                <input type="hidden" name="semester" id="hiddenSemester">

                <div class="mb-3">
                    <label class="form-label">Select New Professor:</label>
                    <select name="newProfessorId" class="form-select" required>
                        <option value="">-- Select Professor --</option>
                        <c:forEach var="professor" items="${availableProfessors}">
                            <option value="${professor.id}">${professor.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Update</button>
                <a href="${pageContext.request.contextPath}/admin/professors" class="btn btn-secondary">🔙 Back</a>
            </form>
        </div>

    </div>
</div>

<!-- Scripts -->
<script>
    function updateSemesters() {
        const selected = document.getElementById("courseSemesterSelect").value;
        if (selected) {
            const [courseId, semester] = selected.split("_");
            document.getElementById("hiddenCourseId").value = courseId;
            document.getElementById("hiddenSemester").value = semester;
        }
    }
</script>

<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/simplebar/js/simplebar.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/metismenu/js/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/app.js"></script>
</body>
</html>