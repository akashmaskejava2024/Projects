<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Project Management - Students</title>

    <link rel="icon" href="${pageContext.request.contextPath}/resources/assets/images/favicon-32x32.png" type="image/png" />
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/assets/css/pace.min.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/resources/assets/js/pace.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-extended.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/assets/css/app.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/assets/css/icons.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
</head>

<body>
<div class="wrapper">
    <!-- Sidebar -->
    <div class="sidebar-wrapper" data-simplebar="true">
        <div class="sidebar-header">
            <div><img src="${pageContext.request.contextPath}/resources/assets/images/logo-icon.png" class="logo-icon" alt="logo icon"></div>
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
                <h2 class="mb-0">Manage Students</h2>
                <div class="d-flex gap-2">
                    <input type="text" id="studentSearchInput" class="form-control" placeholder="🔍 Search by student name..." onkeyup="searchStudents()" />
                    <a href="${pageContext.request.contextPath}/student/signup" class="btn btn-success">➕ Add Student</a>
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/dashboard">🔙 Dashboard</a>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/logout">🚪 Logout</a>
                </div>
            </div>

            <!-- Filter -->
            <div class="card-body">
                <form class="row g-3 mb-3">
                    <div class="col-md-3">
                        <label class="form-label">Calendar Year</label>
                        <select id="calendarYear" class="form-select">
                            <option value="">All</option>
                            <c:forEach var="year" items="${uniqueCalendarYears}">
                                <option value="${year}" ${selectedCalendarYear == year ? 'selected' : ''}>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Academic Year</label>
                        <select id="academicYear" class="form-select">
                            <option value="">All</option>
                            <c:forEach var="year" items="${uniqueAcademicYears}">
                                <option value="${year}" ${selectedAcademicYear == year ? 'selected' : ''}>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Branch</label>
                        <select id="course" class="form-select">
                            <option value="">All</option>
                            <c:forEach var="course" items="${uniqueCourses}">
                                <option value="${course}" ${selectedCourse == course ? 'selected' : ''}>${course}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3 d-flex align-items-end">
                        <button type="button" class="btn btn-primary" onclick="applyFilter()">Filter</button>
                    </div>
                </form>
            </div>

            <!-- Student Table -->
            <div class="card-body">
                <div class="table-responsive">
                    <table id="studentTable" class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Branch</th>
                                <th>Assigned Professor</th>
                                <th>Academic Year</th>
                                <th>Calendar Year</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody id="studentTableBody">
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.id}</td>
                                    <td class="student-name">${student.name}</td>
                                    <td>${courseNames[student.id]}</td>
                                    <td>${professorNames[student.id]}</td>
                                    <td>${academicYears[student.id]}</td>
                                    <td>${calendarYears[student.id]}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/student/profile/${student.id}" class="btn btn-sm btn-warning">✏ Edit</a>
                                        <a href="${pageContext.request.contextPath}/admin/students/delete/${student.id}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this student?')">🗑 Delete</a>
                                   	    <a href="${pageContext.request.contextPath}/admin/student/performance?studentId=${student.id}" class="btn btn-sm btn-info">📊 View Performance</a>
                                   
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

<script>
    function searchStudents() {
        const input = document.getElementById("studentSearchInput").value.toLowerCase();
        const rows = document.querySelectorAll("#studentTableBody tr");

        rows.forEach(row => {
            const studentName = row.querySelector(".student-name").textContent.toLowerCase();
            if (studentName.includes(input) || input.trim() === "") {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        });
    }

    function applyFilter() {
        let calendarYear = document.getElementById("calendarYear").value;
        let academicYear = document.getElementById("academicYear").value;
        let course = document.getElementById("course").value;

        let params = new URLSearchParams();
        if (calendarYear.trim() !== "") params.append("calendarYear", calendarYear);
        if (academicYear.trim() !== "") params.append("academicYear", parseInt(academicYear));
        if (course.trim() !== "") params.append("course", course);

        let url = "${pageContext.request.contextPath}/admin/sorted/students";
        if (params.toString()) {
            url += "?" + params.toString();
        }

        window.location.href = url;
    }
</script>

</body>
</html>