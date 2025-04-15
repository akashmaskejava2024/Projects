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
					<h3 class="page-title">Add Books</h3>
				</div>
			</div>
		</div>
		<!-- /Page Header -->

		<div class="row">
			<div class="col-sm-12">

				<div class="card">
					<div class="card-body">
						<form action="add" method="post">
							<div class="row">
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Title <span class="login-danger">*</span></label> <input
											type="text" class="form-control" name="Title" required>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Author <span class="login-danger">*</span></label> <input
											type="text" class="form-control" name="Author" required>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Language <span class="login-danger">*</span></label> <select
											class="form-control select" name="Language" required>
											<option>Select Language</option>
											<option>English</option>
											<option>Hindi</option>
											<option>Marathi</option>

										</select>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>ISBN <span class="login-danger">*</span></label> <input
											type="text" class="form-control" name="ISBN" required>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Publisher</label> <input type="text"
											class="form-control" name="Publisher" required>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Publisher City <span class="login-danger">*</span></label>
										<input type="text" class="form-control" name="PublisherCity" required>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Publication Date <span class="login-danger">*</span></label>
										<input type="Date" class="form-control" name="PublicationDate" required>
									</div>
								</div>

								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Status <span class="login-danger">*</span></label> <select
											class="form-control select" name="Status">
											<option>Select Status</option>
											<option>Available</option>
											<option>Unavailable</option>

										</select>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>AvailableQuantity<span class="login-danger">*</span></label>
										<input type="text" class="form-control"
											name="AvailableQuantity" id="availableQuantityInput"
											value="0">
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>TotalQuantity<span class="login-danger">*</span></label>
										<input type="text" class="form-control" name="TotalQuantity"
											id="totalQuantityInput" required>
									</div>
								</div>


								<div class="col-12">
									<div class="student-submit">
										<button type="submit" class="btn btn-primary">Submit</button>
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
<!-- /Page Wrapper -->

<script>
	document
			.getElementById('totalQuantityInput')
			.addEventListener(
					'input',
					function() {
						var totalQuantity = this.value;
						document.getElementById('availableQuantityInput').value = totalQuantity;
					});
</script>

<script
	src="<c:url value="resources/assets/plugins/select2/js/select2.min.js "/>"></script>
<jsp:include page="../modules/footer.jsp"></jsp:include>