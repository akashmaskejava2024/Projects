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
                
<li>
    <div class="menu-title">
        <a href="${pageContext.request.contextPath}/admin/students" class="block p-2 rounded-lg hover:bg-gray-200">
            Manage Students
        </a>
    </div>
</li>

<li>
    <div class="menu-title">
        <a href="${pageContext.request.contextPath}/admin/professors" class="block p-2 rounded-lg hover:bg-gray-200">
            Manage Professors
        </a>
    </div>
</li>

<li>
    <div class="menu-title">
        <a href="${pageContext.request.contextPath}/admin/parents" class="block p-2 rounded-lg hover:bg-gray-200">
            Manage Parents
        </a>
    </div>
</li>

<li>
    <div class="menu-title">
        <a href="${pageContext.request.contextPath}/admin/courses" class="block p-2 rounded-lg hover:bg-gray-200">
            Manage Courses
        </a>
    </div>
</li>
            </ul>
        </div>
        <!-- End Sidebar -->

<div class="page-wrapper">
    <div class="page-content">

        <div class="card">
            <div class="card-header">
                <h4 class="mb-0">Add ${numSubjects} Subjects for Semester ${semester}</h4>
            </div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/admin/subjects/add-bulk" method="POST">
                    <input type="hidden" name="courseId" value="${courseId}">
                    <input type="hidden" name="semester" value="${semester}">

                    <div class="table-responsive">
                        <table class="table table-bordered align-middle">
                            <thead class="table-light">
                                <tr>
                                    <th>Subject Name</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach begin="1" end="${numSubjects}" var="index">
                                    <tr>
                                        <td>
                                            <input type="text" name="subjectNames" class="form-control" placeholder="Enter subject name" required>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="mt-3">
                        <button type="submit" class="btn btn-primary">➕ Submit</button>
                        <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary ms-2">🔙 Back to Subjects</a>
                    </div>
                </form>
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

</body>
</html>