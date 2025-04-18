<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../modules/header.jsp" />

<!-- Rest of your JSP page content goes here -->
<%@ page isELIgnored="false" %>
<!-- Main Wrapper -->
        <div class="main-wrapper login-body">
            <div class="login-wrapper">
            	<div class="container">
                	<div class="loginbox">
                    	<div class="login-left">
							<img class="img-fluid" src="resources/assets/img/login.png" alt="Logo">
                        </div>
                        <div class="login-right">
							<div class="login-right-wrap">
								<h1>Welcome to Preskool</h1>
								<p class="account-subtitle">Need an account?  <a href="register.html">Sign Up</a></p>
								<h2>Sign in</h2>
								<!-- Form -->
								<form action="https://preschool.dreamstechnologies.com/template/index.html">
									<div class="form-group">
										<label >Username <span class="login-danger">*</span></label>
										<input class="form-control" type="text" >
										<span class="profile-views"><i class="fas fa-user-circle"></i></span>
									</div>
									<div class="form-group">
										<label >Password <span class="login-danger">*</span></label>
										<input class="form-control pass-input" type="text" >
										<span class="profile-views feather-eye toggle-password"></span>
									</div>
									<div class="forgotpass">
										<div class="remember-me">
											<label class="custom_check mr-2 mb-0 d-inline-flex remember-me"> Remember me
											<input type="checkbox" name="radio">
											<span class="checkmark"></span>
											</label>
										</div>
										<a href="forgot-password.html">Forgot Password?</a>
									</div>
									<div class="form-group">
										<button class="btn btn-primary btn-block" type="submit">Login</button>
									</div>
								</form>
								<!-- /Form -->
								
								<div class="login-or">
									<span class="or-line"></span>
									<span class="span-or">or</span>
								</div>
								  
								<!-- Social Login -->
								<div class="social-login">
									<a href="#" ><i class="fab fa-google-plus-g"></i></a>
									<a href="#" ><i class="fab fa-facebook-f"></i></a>
									<a href="#" ><i class="fab fa-twitter"></i></a>
									<a href="#" ><i class="fab fa-linkedin-in"></i></a>
								</div>
								<!-- /Social Login -->
								
							</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<!-- /Main Wrapper -->
	
<include page="../modules/footer.jsp"></include>
    