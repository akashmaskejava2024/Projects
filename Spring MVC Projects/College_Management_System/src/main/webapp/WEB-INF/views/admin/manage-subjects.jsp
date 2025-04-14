<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    

<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags -->
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

    <title>Project Management - Login</title>
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
        <!-- End Sidebar -->

<div class="page-wrapper">
    <div class="page-content">

        <h2>Manage Subjects for ${courseName}</h2>

        <c:forEach var="i" begin="1" end="${semester}">
            <div class="card mb-4">
                <div class="card-header">
                    <h5 class="mb-0">Semester ${i}</h5>
                </div>
                <div class="card-body table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead class="table-light">
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Course</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty subjectsBySemester[i]}">
                                    <c:forEach var="subject" items="${subjectsBySemester[i]}">
                                        <tr>
                                            <td>${subject.id}</td>
                                            <td>${subject.name}</td>
                                            <td>${courseName}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/admin/subjects/delete/${subject.id}"
                                                   class="btn btn-sm btn-danger"
                                                   onclick="return confirm('Are you sure you want to delete this subject?')">
                                                    🗑️ Delete
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="4" class="text-center text-muted">No subjects added yet.</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:forEach>

        <div class="card mt-4">
            <div class="card-header">
                <h5 class="mb-0">Bulk Add Subjects</h5>
            </div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/admin/subjects/bulk-add" method="GET" class="row g-3">
                    <div class="col-md-4">
                        <label for="semester" class="form-label">Select Semester:</label>
                        <select name="semester" id="semester" class="form-select" required>
                            <c:forEach var="i" begin="1" end="${semester}">
                                <option value="${i}">Semester ${i}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-4">
                        <label for="numSubjects" class="form-label">Number of Subjects:</label>
                        <input type="number" name="numSubjects" id="numSubjects" class="form-control" min="1" required>
                    </div>

                    <input type="hidden" name="courseId" value="${courseId}">

                    <div class="col-md-4 d-flex align-items-end">
                        <button type="submit" class="btn btn-primary">Next</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/admin/courses" class="btn btn-secondary">🔙 Back to Courses</a>
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