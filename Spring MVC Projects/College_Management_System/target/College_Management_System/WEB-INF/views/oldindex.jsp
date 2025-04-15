<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--favicon-->
    <link rel="icon" href="resources/assets/images/favicon-32x32.png" type="image/png"/>
    <!--plugins-->
    <link href="resources/assets/plugins/vectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet"/>
    <link href="resources/assets/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
    <link href="resources/assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />
    <link href="resources/assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet"/>
    <!-- loader-->
    <link href="resources/assets/css/pace.min.css" rel="stylesheet"/>
    <script src="resources/assets/js/pace.min.js"></script>
    <!-- Bootstrap CSS -->
    <link href="resources/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/assets/css/bootstrap-extended.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
    <link href="resources/assets/css/app.css" rel="stylesheet">
    <link href="resources/assets/css/icons.css" rel="stylesheet">
    <!-- Theme Style CSS -->
    <link rel="stylesheet" href="resources/assets/css/dark-theme.css"/>
    <link rel="stylesheet" href="resources/assets/css/semi-dark.css"/>
    <link rel="stylesheet" href="resources/assets/css/header-colors.css"/>
    <title>College Management System</title>
</head>

<body>
    <!--wrapper-->
    <div class="wrapper">
        <!--sidebar wrapper -->
        <div class="sidebar-wrapper" data-simplebar="true">
            <div class="sidebar-header">
                <div>
                    <img src="resources/assets/images/logo-icon.png" class="logo-icon" alt="logo icon">
                </div>
                <div>
                    <h4 class="logo-text">College Management</h4>
                </div>
                <div class="toggle-icon ms-auto"><i class='bx bx-arrow-back'></i></div>
            </div>

            <!--navigation-->
            <ul class="metismenu" id="menu">
            
                <!-- Login Links in Sidebar -->
                <li class="menu-label">Login</li>
                <li>
                    <a href="${pageContext.request.contextPath}/professor/login">
                        <div class="parent-icon"><i class="bx bx-user"></i></div>
                        <div class="menu-title">Professor Login</div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/student/login">
                        <div class="parent-icon"><i class="bx bx-user"></i></div>
                        <div class="menu-title">Student Login</div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/login">
                        <div class="parent-icon"><i class="bx bx-user"></i></div>
                        <div class="menu-title">Admin Login</div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/parent/login">
                        <div class="parent-icon"><i class="bx bx-user"></i></div>
                        <div class="menu-title">Parent Login</div>
                    </a>
                </li>
                
               
            </ul>
            <!--end navigation-->
        </div>
        <!--end sidebar wrapper -->
    </div>
</body>
</html>