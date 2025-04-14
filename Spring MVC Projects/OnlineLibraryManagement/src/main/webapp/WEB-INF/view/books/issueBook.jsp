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
				<div class="col">
					<h3 class="page-title">Book Issue</h3>
				</div>
			</div>
		</div>
		<!-- /Page Header -->
		<div class="row">
			<div class="col-sm-12">

				<div class="card">
					<div class="card-body">
						<form action="issue" method="post">
							<div class="row">

								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>UserId <span class="login-danger">*</span></label> <input
											type="text" class="form-control" name="UserId">
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>UserType <span class="login-danger">*</span></label> <select
											class="form-control select" name="UserType">
											<option>Select User</option>
											<option>Teacher</option>
											<option>Student</option>
										</select>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>BookId <span class="login-danger">*</span></label> <input
											type="text" class="form-control" name="BookId">
									</div>
								</div>

								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Issue Date<span class="login-danger">*</span></label> <input
											type="date" class="form-control" name="IssueDate">
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>DueDate <span class="login-danger">*</span></label> <input
											class="form-control" name="DueDate" type="date">
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>UserType <span class="login-danger">*</span></label> <select
											class="form-control select" name="Status">
											<option>Status</option>
											<option>Pending</option>
											<option>Submitted</option>
										</select>
									</div>
								</div>

							</div>
							<div class="col-12">
								<div class="student-submit">
									<button type="submit" class="btn btn-primary">submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
<div id="alertContainer"
	style="position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);"></div>

<script type="text/javascript">
	var urlParams = new URLSearchParams(window.location.search);
	var errMsg = urlParams.get('errMsg');
	if (errMsg) {
		var alertDiv = document.createElement("div");
		alertDiv.classList.add("alert", "alert-danger");
		alertDiv.textContent = decodeURIComponent(errMsg);
		document.getElementById("alertContainer").appendChild(alertDiv);
	}
</script>



<script
	src="<c:url value='/resources/assets/plugins/select2/js/select2.min.js' />"></script>
<jsp:include page="../modules/footer.jsp"></jsp:include>