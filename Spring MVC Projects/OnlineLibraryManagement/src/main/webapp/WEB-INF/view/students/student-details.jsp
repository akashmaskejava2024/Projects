<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../modules/header.jsp" />

<!-- Rest of your JSP page content goes here -->
<%@ page isELIgnored="false"%>

<!-- Page Wrapper -->
<div class="page-wrapper">
	<div class="content container-fluid">

		<div class="page-header">
			<div class="row">
				<div class="col-sm-12">
					<div class="page-sub-header">
						<h3 class="page-title">Student Details</h3>
					</div>
				</div>
			</div>
		</div>

		<div class="card">
			<div class="card-body">

				<div class="row">
					<div class="col-lg-4">
						<div class="student-personals-grp">
							<div class="card">
								<div class="card-body">
									<div class="heading-detail">
										<h4>Personal Details :</h4>
									</div>
									<div class="personal-activity">
										<div class="personal-icons">
											<i class="feather-user"></i>
										</div>
										<div class="views-personal">
											<h4>Student Name</h4>
											<h5>${student.firstName}${student.lastName}</h5>
										</div>
									</div>
									<div class="personal-activity">
										<div class="personal-icons">
											<img src="resources/assets/img/icons/buliding-icon.svg"
												alt="">
										</div>
										<div class="views-personal">
											<h4>Class Name</h4>
											<h5>${student.className}</h5>
										</div>
									</div>
									<div class="personal-activity">
										<div class="personal-icons">
											<img src="resources/assets/img/icons/buliding-icon.svg"
												alt="">
										</div>
										<div class="views-personal">
											<h4>Department</h4>
											<h5>${student.department}</h5>
										</div>
									</div>
									<div class="personal-activity">
										<div class="personal-icons">
											<i class="feather-phone-call"></i>
										</div>
										<div class="views-personal">
											<h4>Mobile</h4>
											<h5>${student.contactNumber}</h5>
										</div>
									</div>
									<div class="personal-activity">
										<div class="personal-icons">
											<i class="feather-mail"></i>
										</div>
										<div class="views-personal">
											<h4 class="__cf_email__">Email</h4>
											<h5>${student.email}</h5>
										</div>
									</div>

								</div>
							</div>
						</div>

					</div>
					<div class="col-lg-8">
						<div class="student-personals-grp">
							<div class="card mb-0">
								<div class="card-body">
									<div class="table-responsive">
										<table
											class="table border-0 star-student table-hover table-center mb-0 datatable table-striped">
											<thead class="student-thread">
												<tr>
													<th>Sr.No</th>
													<th>Issue Id</th>
													<th>User Id</th>
													<th>Book Id</th>
													<th>Issue Date</th>
													<th>Status</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="issueBook" items="${issueBookList}"
													varStatus="loop">
													<tr>
														<td>${loop.index + 1}</td>
														<td>${issueBook.issueId}</td>
														<td>${issueBook.userId}</td>
														<td>${issueBook.bookId}</td>
														<td>${issueBook.issueDate}</td>
														<td>${issueBook.status}</td>
														<td>
															<form action="<c:url value='/books/returnBook'/>"
																method="post">
																<input type="hidden" name="issueId"
																	value="${issueBook.issueId}"> <input
																	type="hidden" name="bookId" value="${issueBook.bookId}">
																<c:choose>
																	<c:when test="${issueBook.status eq 'Submitted'}">
																		<!-- If status is "Submitted", disable the button -->
																		<button type="button" class="btn btn-primary" disabled>Return
																			Book</button>
																	</c:when>
																	<c:otherwise>
																		<!-- If status is not "Submitted", enable the button -->
																		<button type="submit" class="btn btn-primary">Return
																			Book</button>
																	</c:otherwise>
																</c:choose>

																<!-- <button type="submit" class="btn btn-primary">Return
																	Book</button> -->
															</form>

														</td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<jsp:include page="../modules/footer.jsp"></jsp:include>


<script src="<c:url value="resources/assets/js/jquery-3.7.1.min.js"/>"></script>

