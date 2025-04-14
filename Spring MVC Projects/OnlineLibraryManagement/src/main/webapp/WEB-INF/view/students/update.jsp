<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../modules/header.jsp" />

<!-- Rest of your JSP page content goes here -->
<%@ page isELIgnored="false"%>

<!-- Page Wrapper -->
<div class="page-wrapper">
	<div class="content container-fluid">

		<!-- Page Header -->
		<div class="page-header">
			<div class="row align-items-center">
				<div class="col-sm-12">
					<div class="page-sub-header">
						<h3 class="page-title">Edit Students</h3>
					</div>
				</div>
			</div>
		</div>
		<!-- /Page Header -->


		<div class="row">
			<div class="col-sm-12">

				<div class="card comman-shadow">
					<div class="card-body">
						<form action="updateStudent" method="post">
							<div class="row">
								<div class="col-12">
									<h5 class="form-title student-info">
										Student Information <span><a href="javascript:;"><i
												class="feather-more-vertical"></i></a></span>
									</h5>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Student Id <span class="login-danger">*</span></label>
										<input class="form-control" name="StudentId" type="text"
											value="${param.studentId}">
									</div>
								</div>

								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>First Name <span class="login-danger">*</span></label>
										<input class="form-control" name="FirstName" type="text"
											value="${param.firstName}">
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Last Name <span class="login-danger">*</span></label> <input
											class="form-control" name="LastName" type="text"
											value="${param.lastName}">
									</div>
								</div>



								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>E-Mail <span class="login-danger">*</span></label> <input
											class="form-control" name="Email" type="text"
											value="${param.email}">
									</div>
								</div>

								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Class <span class="login-danger">*</span></label> <select
											class="form-control select" name="className"
											value="${param.className}">
											<option>BA-I</option>
											<option>BA-II</option>
											<option>BA-III</option>
											<option>BCA-I</option>
											<option>BCA-II</option>
											<option>BCA-III</option>
										</select>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Department <span class="login-danger">*</span></label>
										<select class="form-control select" name="Department"
											value="${param.department}">
											<option>B.A</option>
											<option>B.com</option>
											<option>B.C.A</option>
											<option>B.C.S</option>

										</select>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Phone </label> <input class="form-control"
											name="ContactNumber" type="text"
											value="${param.contactNumber}">
									</div>
								</div>
								<div class="col-12">
									<div class="student-submit">
										<button type="submit" name="submit" class="btn btn-primary">Submit</button>
										<a href="list"><button type="button" class="btn btn-primary">Cancel</button></a>
										
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Datepicker Core JS -->
<script
	src="<c:url value="resources/assets/plugins/moment/moment.min.js"/>"></script>
<script
	src="<c:url value="resources/assets/js/bootstrap-datetimepicker.min.js"/>"></script>


<jsp:include page="../modules/footer.jsp"></jsp:include>
