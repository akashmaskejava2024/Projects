<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">
<title>Preskool - Dashboard</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>

<!-- Favicon -->
<link rel="shortcut icon" href="resources/assets/img/favicon.png">

<!-- Fontfamily -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,400;0,500;0,700;0,900;1,400;1,500;1,700&amp;display=swap"
	rel="stylesheet">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">

<!-- Feathericon CSS -->
<link rel="stylesheet"
	href="resources/assets/plugins/feather/feather.css">

<!-- Pe7 CSS -->
<link rel="stylesheet"
	href="resources/assets/plugins/icons/flags/flags.css">

<!-- Fontawesome CSS -->
<link rel="stylesheet"
	href="resources/assets/plugins/fontawesome/css/fontawesome.min.css">
<link rel="stylesheet"
	href="resources/assets/plugins/fontawesome/css/all.min.css">

<!-- Main CSS -->
<link rel="stylesheet" href="resources/assets/css/style.css">
</head>

<body>

	<!-- Main Wrapper -->
	<div class="main-wrapper login-body">
		<div class="login-wrapper">
			<div class="container">
				<div class="loginbox">
					<div class="login-left">
						<img class="img-fluid" src="resources/assets/image/img4.jpg"
							style="border-radius: 10px 0 0 10px; height: 710px;" alt="Logo">
					</div>
					<div class="login-right">
						<div class="login-right-wrap">
							<h1>Sign Up</h1>
							<p class="account-subtitle">Enter details to create your
								account</p>

							<!-- Form -->
							<form action="register" method="post">

								<div class="form-group">
									<label>FirstName <span class="login-danger">*</span></label> <input
										class="form-control" type="text" name="FirstName"> <span
										class="profile-views"><i class="fas fa-user"></i></span>
								</div>
								<div class="form-group">
									<label>lastName <span class="login-danger">*</span></label> <input
										class="form-control" class=" form-control-sm" type="text"
										name="LastName"> <span class="profile-views"><i
										class="fas fa-user"></i></span>
								</div>
								<div class="form-group">
									<label>ContactNumber <span class="login-danger">*</span></label>
									<input class="form-control" type="text" name="ContactNumber">
									<span class="profile-views"><i class="fas fa-phone"></i></span>
								</div>
								<div class="form-group">
									<label>Email <span class="login-danger">*</span></label> <input
										class="form-control" type="text" name="Email"> <span
										class="profile-views"><i class="fas fa-envelope"></i></span>
								</div>
								<div class="form-group">
									<label>Username <span class="login-danger">*</span></label> <input
										class="form-control" type="text" name="Username"> <span
										class="profile-views"><i class="fas fa-user-circle"></i></span>
								</div>
								<div class="form-group">
									<label>Password <span class="login-danger">*</span></label> <input
										class="form-control pass-input" id="password" type="password"
										name="Password" >
									<span class="profile-views feather-eye toggle-password " onclick="togglePasswordVisibility()"></span>
								</div>
								<div class="form-group">
									<label>Confirm password <span class="login-danger">*</span></label>
									<input class="form-control pass-confirm" id="cpassword" type="password"
										name="ConfirmPassword" >
									<span class="profile-views feather-eye reg-toggle-password" onclick="ctogglePasswordVisibility()"></span>
								</div>
								<div class="form-group mb-0">
									<button class="btn btn-primary btn-block" type="submit">Register</button>
								</div>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /Main Wrapper -->
		<script>
			function togglePasswordVisibility() {
				var passwordInput = document.getElementById("password");
				var eyeIcon = document.querySelector(".toggle-password");

				if (passwordInput.type === "password") {
					passwordInput.type = "text";
					eyeIcon.classList.remove("feather-eye");
					eyeIcon.classList.add("feather-eye-off");
				} else {
					passwordInput.type = "password";
					eyeIcon.classList.remove("feather-eye-off");
					eyeIcon.classList.add("feather-eye");
				}
			}
		</script>
		<script>
			function ctogglePasswordVisibility() {
				var passwordInput = document.getElementById("cpassword");
				var eyeIcon = document.querySelector(".toggle-password");

				if (passwordInput.type === "password") {
					passwordInput.type = "text";
					eyeIcon.classList.remove("feather-eye");
					eyeIcon.classList.add("feather-eye-off");
				} else {
					passwordInput.type = "password";
					eyeIcon.classList.remove("feather-eye-off");
					eyeIcon.classList.add("feather-eye");
				}
			}
		</script>
		
		
		<script
	src="<c:url value="resources/assets/plugins/select2/js/select2.min.js "/>"></script>
		<!-- jQuery -->
		<script src="<c:url value="resources/assets/js/jquery-3.7.1.min.js "/>"></script>

		<script src="<c:url value="resources/assets/js/bootstrap.bundle.min.js "/>"></script>

		<!-- Feather Icon JS -->
		<script src="<c:url value="resources/assets/js/feather.min.js"/>"></script>

		<!-- Custom JS -->
		<script src="<c:url value="resources/assets/js/script.js"/>"></script>
</body>


</html>



