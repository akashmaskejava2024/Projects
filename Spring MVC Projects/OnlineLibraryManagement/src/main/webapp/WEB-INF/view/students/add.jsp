
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
						<h3 class="page-title">Add Students</h3>
					</div>
				</div>
			</div>
		</div>
		<!-- /Page Header -->

		<div class="row">
			<div class="col-sm-12">

				<div class="card comman-shadow">
					<div class="card-body">
						<form action="add" method="post">
							<div class="row">

								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>First Name <span class="login-danger">*</span></label>
										<input class="form-control" name="FirstName" type="text" required>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Last Name <span class="login-danger">*</span></label>
										<input class="form-control" name="LastName" type="text" required>
									</div>
								</div>



								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>E-Mail <span class="login-danger">*</span></label> <input
											class="form-control" name="Email" type="text" required>
									</div>
								</div>

								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Class <span class="login-danger">*</span></label> <select
											class="form-control select" name="className">
											<option>Please Select Class</option>
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
										<select class="form-control select" name="Department">
											<option>Please Select Department</option>
											<option>B.A</option>
											<option>B.com</option>
											<option>B.C.A</option>
											<option>B.C.S</option>

										</select>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Phone<span class="login-danger">*</span> </label> <input class="form-control"
											name="ContactNumber" type="text" required>
									</div>
								</div>
								<div class="col-12">
									<div class="student-submit">
										<button type="submit" name="submit" class="btn btn-primary">Submit</button>
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


<script
	src="<c:url value="resources/assets/plugins/moment/moment.min.js"/>"></script>
<script
	src="<c:url value="resources/assets/js/bootstrap-datetimepicker.min.js"/>"></script>
	<jsp:include page="../modules/footer.jsp"></jsp:include>
