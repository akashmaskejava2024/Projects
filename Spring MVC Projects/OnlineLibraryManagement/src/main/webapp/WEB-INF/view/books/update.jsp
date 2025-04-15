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
					<h3 class="page-title">Edit Books</h3>
				</div>
			</div>
		</div>
		<!-- /Page Header -->

		<div class="row">
			<div class="col-sm-12">

				<div class="card">
					<div class="card-body">
						<form action="updateBook" method="post">
							<div class="row">
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Book ID <span class="login-danger">*</span></label> <input
											type="text" class="form-control" name="BookId"
											value="${param.BookId }">
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Title<span class="login-danger">*</span></label> <input
											type="text" class="form-control" name="Title"
											value="${param.Title}">
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Author<span class="login-danger">*</span></label> <input
											type="text" class="form-control" name="Author"
											value="${param.Author}">
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Language <span class="login-danger">*</span></label> <select
											class="form-control select" name="Language"
											value="${param.Language}">
											<option>English</option>
											<option>Hindi</option>
											<option>Marathi</option>

										</select>
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>ISBN <span class="login-danger">*</span></label> <input
											type="text" class="form-control" name="ISBN"
											value="${param.ISBN}">
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Publisher <span class="login-danger">*</span></label> <input
											type="text" class="form-control" name="Publisher"
											value="${param.Publisher }">
									</div>
								</div>
								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Publication City <span class="login-danger">*</span></label>
										<input type="text" class="form-control" name="PublisherCity"
											value="${param.PublisherCity }">
									</div>
								</div>

								<div class="col-12 col-sm-4">
									<div class="form-group local-forms">
										<label>Publication Date <span class="login-danger">*</span></label>
										<input type="date" class="form-control" name="PublicationDate"
											value="${param.PublicationDate }">
									</div>
								</div>

								<div class="col-12">
									<div class="student-submit">
										<button type="submit" class="btn btn-primary">Submit</button>
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

<jsp:include page="../modules/footer.jsp"></jsp:include>


