<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
  <title>Preskool - Dashboard</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>
  <!-- Favicon -->
  <link rel="shortcut icon" href="resources/assets/img/favicon.png">
  <!-- Fontfamily -->
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,400;0,500;0,700;0,900;1,400;1,500;1,700&amp;display=swap" rel="stylesheet">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
  <!-- Feathericon CSS -->
  <link rel="stylesheet" href="resources/assets/plugins/feather/feather.css">
  <!-- Pe7 CSS -->
  <link rel="stylesheet" href="resources/assets/plugins/icons/flags/flags.css">
  <!-- Fontawesome CSS -->
  <link rel="stylesheet" href="resources/assets/plugins/fontawesome/css/fontawesome.min.css">
  <link rel="stylesheet" href="resources/assets/plugins/fontawesome/css/all.min.css">
  <!-- Main CSS -->
  <link rel="stylesheet" href="resources/assets/css/style.css">
  <style>
    .err {
      color: red;
    }
  </style>
    
   <title>Login Page</title>
</head>
<body>
<div class="main-wrapper login-body">
  <div class="login-wrapper">
    <div class="container">
      <div class="loginbox">
        <div class="login-left">
          <img class="img-fluid" src="resources/assets/image/img4.jpg" style=" border-radius: 10px 0 0 10px;" alt="Logo">
        </div>
        <div class="login-right">
          <div class="login-right-wrap">
            <h1>Welcome to <span style="color: #3636abf0;">Library</span></h1>
            <p class="account-subtitle">Need an account? <a href="signup">Sign Up</a></p>
            <h2>Sign in</h2>
            <!-- Form -->
            <form id="loginForm" action="login" method="post" >
              <div class="form-group">
                <label>Username <span class="login-danger">*</span></label>
                <input class="form-control" type="text" id="username" name="username" required>
                <span class="profile-views"><i class="fas fa-user-circle"></i></span>
                <span id="usernameError" class="error-msg"></span>
              </div>
              <div class="form-group">
                <label>Password <span class="login-danger">*</span></label>
                <input class="form-control pass-input" type="password" id="password" name="password" required>
                 <span class="profile-views feather-eye toggle-password" onclick="togglePasswordVisibility()"></span>
                <span id="passwordError" class="error-msg"></span>
              </div>
              <div class="forgotpass">
                <div class="remember-me">
                  <label class="custom_check mr-2 mb-0 d-inline-flex remember-me"> Remember me
                    <input type="checkbox" name="radio">
                    <span class="checkmark"></span>
                  </label>
                </div>
                <a href="forgot-password.html" class="disabled" onclick="return false;">Forgot Password?</a>
              </div>
            <div id="loginError" class="err" style="display: none;">${errMsg}</div>

              <div class="form-group">
                <button class="btn btn-primary btn-block" type="submit" onclick="displayErrorMessage()">Login</button>
              </div>
            </form>
            <!-- /Form -->
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

 
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
    // Function to display error message
    function displayErrorMessage() {
        var errMsg = "${errMsg}"; // Get the value of ${errMsg}
        if (errMsg.trim() !== "" && errMsg !== "Invalid username or password.") { // Check if errMsg is not empty and not the default error message
            document.getElementById("loginError").style.display = "block"; // Display the error message
        }
    }
</script>
</body>
</html>


