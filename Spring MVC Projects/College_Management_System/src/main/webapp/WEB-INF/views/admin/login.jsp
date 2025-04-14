<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Project Management - Login</title>

    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/resources/assets/images/favicon-32x32.png" type="image/png" />

    <!-- Styles -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-extended.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/assets/css/app.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/assets/css/icons.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">

    <style>
        body {
            margin: 0;
            font-family: 'Roboto', sans-serif;
            background-color: #f2f2f2;
        }

        .login-bg {
            background: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
                url('${pageContext.request.contextPath}/resources/assets/images/bg-login.jpg') no-repeat center center fixed;
            background-size: cover;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .card {
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 16px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
        }

        h5, p, label {
            color: #333;
        }
    </style>
</head>

<body>

<div class="login-bg">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-5">
                <div class="card p-4">
                    <div class="card-body">
                        <div class="text-center mb-4">
                            <h5>College Management</h5>
                            <p class="mb-0">Login</p>
                        </div>
                        <form id="loginForm" method="post">
                            <div class="mb-3">
                                <label for="role" class="form-label">Select Role</label>
                                <select class="form-select" id="role" name="role" required>
                                    <option value="">-- Select Role --</option>
                                    <option value="admin">Admin</option>
                                    <option value="student">Student</option>
                                    <option value="professor">Professor</option>
                                    <option value="parent">Parent</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="inputEmail" class="form-label">Email</label>
                                <input type="email" class="form-control" id="inputEmail" name="email" required>
                            </div>
                            <div class="mb-3">
                                <label for="inputPassword" class="form-label">Password</label>
                                <input type="password" class="form-control" id="inputPassword" name="password" required>
                            </div>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">Sign In</button>
                            </div>
                        </form>
                        <div id="error" class="text-danger mt-3 text-center d-none">Please select a valid role.</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.bundle.min.js"></script>

<script>
    document.getElementById("loginForm").addEventListener("submit", function(event) {
        const role = document.getElementById("role").value;
        if (!role) {
            event.preventDefault();
            document.getElementById("error").classList.remove("d-none");
            return;
        }

        const contextPath = "${pageContext.request.contextPath}";
        this.action = contextPath + "/" + role + "/login";
    });
</script>

</body>
</html>