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

		<!-- Main Content -->
		<div class="page-wrapper">
			<div class="page-content">
				<div class="d-flex align-items-center justify-content-center my-5">
					<div class="container-fluid">
						<div class="row justify-content-center">
							<div class="col-lg-8">
								<div class="card">
									<div class="card-body">
										<div class="p-4">
											<div class="text-center mb-4">
												<h5 class="card-title">Edit Profile</h5>
												<p class="mb-0">Update your student profile information</p>
											</div>

											<form action="${pageContext.request.contextPath}/student/updateProfile?studentId=${student.id}" method="post">
												<div class="mb-3">
													<label class="form-label">Name</label>
													<input type="text" class="form-control" name="name"
														value="${student.name}" required>
												</div>

												<div class="mb-3">
													<label class="form-label">Email</label>
													<input type="email" class="form-control" name="email"
														value="${student.email}" required>
												</div>

												<div class="mb-3">
													<label class="form-label">Roll Number</label>
													<input type="text" class="form-control" name="rollNumber"
														value="${student.rollNumber}" required>
												</div>

												<div class="d-grid mt-4">
													<button type="submit" class="btn btn-primary">💾 Save Changes</button>
												</div>

												<div class="text-center mt-3">
													<a href="${pageContext.request.contextPath}/admin/students"
														class="btn btn-secondary">🔙 Back to Dashboard</a>
												</div>
											</form>

										</div> <!-- p-4 -->
									</div> <!-- card-body -->
								</div> <!-- card -->
							</div> <!-- col -->
						</div> <!-- row -->
					</div> <!-- container -->
				</div> <!-- flex -->
			</div> <!-- page-content -->
		</div> <!-- page-wrapper -->
	</div> <!-- wrapper -->

	<!-- Scripts -->
	<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/simplebar/js/simplebar.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/metismenu/js/metisMenu.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/app.js"></script>
</body>

</html>