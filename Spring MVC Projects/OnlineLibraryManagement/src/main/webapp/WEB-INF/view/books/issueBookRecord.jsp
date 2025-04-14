<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../modules/header.jsp" />
<%@ page isELIgnored="false"%>

<div class="page-wrapper">
	<div class="content container-fluid">
		<div class="page-header">
			<div class="row align-items-center">
				<div class="col">
					<h3 class="page-title">Issue Book Records</h3>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="card card-table">
					<div class="card-body">
						<div class="page-header">
							<div class="row align-items-center">
								<div class="col-auto text-end float-end ms-auto download-grp">
									<a href="#" class="btn btn-outline-primary me-2 d-none"><i
										class="fas fa-download "></i> Download</a> <a
										href="issueBook-form" class="btn btn-primary"><i
										class="fas fa-plus"></i></a>
								</div>
							</div>
						</div>
						<div class="table-responsive">
							<table
								class="table border-0 star-student table-hover table-center mb-0 datatable table-striped">
								<thead class="student-thread">
									<tr>
										<th>Issue Id</th>
										<th>User Id</th>
										<th>User Type</th>
										<th>Book Id</th>
										<th>Issue Date</th>
										<th>Due Date</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="issueBook" items="${issueBookList}">
										<tr>
											<td>${issueBook.issueId}</td>
											<td>${issueBook.userId}</td>
											<td>${issueBook.userType}</td>
											<td>${issueBook.bookId}</td>
											<td>${issueBook.issueDate}</td>
											<td>${issueBook.dueDate}</td>
											<td>${issueBook.status}</td>
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
<jsp:include page="../modules/footer.jsp"></jsp:include>