<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Dashboard</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
	color: #333;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	width: 600px; /* Increased from 500px to 600px */
	padding: 20px;
	background: white;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	overflow-y: auto;
	max-height: 90vh;
}

h2 {
	margin-bottom: 20px;
	color: #007bff;
}

.project-details {
	background-color: #f9f9f9;
	padding: 15px;
	border-radius: 5px;
	margin-bottom: 15px;
}

.project-status-list {
	list-style-type: none;
	padding: 0;
}

.project-status {
	background: #f1f1f1;
	padding: 15px;
	margin-bottom: 10px;
	border-radius: 5px;
	text-align: left;
}

.project-status strong {
	color: #333;
}

img {
	margin-top: 10px;
	width: 100%;
	max-width: 300px;
	border-radius: 5px;
	border: 1px solid #ccc;
}

.action-btn {
	display: block;
	padding: 10px;
	background-color: #007bff;
	color: white;
	text-decoration: none;
	border-radius: 5px;
	margin-top: 10px;
}

.action-btn:hover {
	background-color: #0056b3;
}

.links {
	margin-top: 15px;
}

.links a {
	text-decoration: none;
	color: #007bff;
	font-size: 14px;
}

.links a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Student Dashboard</h2>

		<c:choose>
			<c:when test="${not empty project}">
				<div class="project-details">
					<p>
						<strong>Project ID :</strong> ${project.id}
					</p>
					<h3>Project Title: ${project.title}</h3>
					<p>
						<strong>Description:</strong> ${project.description}
					</p>
				</div>

				<c:if test="${not empty projectStatuses}">
					<h3>Project Status Updates</h3>
					<ul class="project-status-list">
						<c:forEach var="status" items="${projectStatuses}">
							<li class="project-status">
								<p>
									<strong>Phase:</strong> ${status.phase}
								</p>
								<p>
									<strong>Update Description:</strong>
									${status.updateDescription}
								</p> <c:if test="${not empty status.uiImage}">
									<p>
										<strong>UI Image:</strong>
									</p>
									<img src="${pageContext.request.contextPath}${status.uiImage}"
										alt="Project Image">
								</c:if> <c:if test="${not empty status.feedback}">
									<p>
										<strong>Professor Feedback:</strong> ${status.feedback}
									</p>
								</c:if>

								<p>
									<strong>Updated At:</strong> ${status.updatedAt}
								</p>
							</li>
						</c:forEach>
					</ul>
				</c:if>

				<a class="action-btn"
					href="${pageContext.request.contextPath}/student/updateProjectStatus?projectId=${project.id}">
					Update Project Status </a>
			</c:when>

			<c:otherwise>
				<a class="action-btn"
					href="${pageContext.request.contextPath}/student/newProject">
					Add New Project </a>
			</c:otherwise>
		</c:choose>

		<div class="links">
			<p>
				<a href="${pageContext.request.contextPath}/index.jsp">Back to
					Home</a>
			</p>
		</div>
	</div>
</body>
</html>