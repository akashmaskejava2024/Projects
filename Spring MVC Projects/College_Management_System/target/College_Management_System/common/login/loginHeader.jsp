<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--favicon-->
<link rel="icon" href="resources/assets/images/favicon-32x32.png"
	type="image/png" />
<!--plugins-->
<link
	href="resources/assets/plugins/vectormap/jquery-jvectormap-2.0.2.css"
	rel="stylesheet" />
<link href="resources/assets/plugins/simplebar/css/simplebar.css"
	rel="stylesheet" />
<link
	href="resources/assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css"
	rel="stylesheet" />
<link href="resources/assets/plugins/metismenu/css/metisMenu.min.css"
	rel="stylesheet" />
<!-- loader-->
<link href="resources/assets/css/pace.min.css" rel="stylesheet" />
<script src="resources/assets/js/pace.min.js"></script>
<!-- Bootstrap CSS -->
<link href="resources/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/assets/css/bootstrap-extended.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap"
	rel="stylesheet">
<link href="resources/assets/css/app.css" rel="stylesheet">
<link href="resources/assets/css/icons.css" rel="stylesheet">
<!-- Theme Style CSS -->
<link rel="stylesheet" href="resources/assets/css/dark-theme.css" />
<link rel="stylesheet" href="resources/assets/css/semi-dark.css" />
<link rel="stylesheet" href="resources/assets/css/header-colors.css" />
<title>Rocker - Bootstrap 5 Admin Dashboard Template</title>

</head>

<body>
	<!--wrapper-->
	<div class="wrapper">
		<!--sidebar wrapper -->
		<div class="sidebar-wrapper" data-simplebar="true">
			<div class="sidebar-header">
				<div>
					<img src="assets/images/logo-icon.png" class="logo-icon"
						alt="logo icon">
				</div>
				
			</div>
			<!--navigation-->
			<ul class="metismenu" id="menu">
				<li class="font-semibold mt-2">Login</li>
			
				<li>
						<div class="menu-title">
							<a href="${pageContext.request.contextPath}/professor/login"
								class="block p-2 rounded-lg hover:bg-gray-200"> Professor
								Login </a>
						</div>
				</li>

				<li>
						<div class="menu-title">
							<a href="${pageContext.request.contextPath}/student/login"
								class="block p-2 rounded-lg hover:bg-gray-200"> Student
								Login </a>
						</div>
			</li>
				<li>
						<div class="menu-title">
							<a href="${pageContext.request.contextPath}/admin/login"
								class="block p-2 rounded-lg bg-blue-500 text-white"> Admin
								Login </a>
						</div>
		</li>
				<li>
						
						<div class="menu-title">
							<a href="${pageContext.request.contextPath}/parent/login"
								class="block p-2 rounded-lg hover:bg-gray-200"> Parent Login
							</a>
						</div>
			</li>

			</ul>
			<!--end navigation-->
		</div>
		<!--end sidebar wrapper -->