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
					<h3 class="page-title">Books</h3>
				</div>
			</div>


			<!-- /Page Header -->

			<div class="row">
				<div class="col-sm-12">

					<div class="card card-table">
						<div class="card-body">
							<!-- Page Header -->
							<div class="page-header">
								<div class="row align-items-center">

									<div class="page-header">
										<div class="row align-items-center">
											<div
												class="col-auto text-end float-end ms-auto download-grp d-flex">
												<div class="form-group mb-0 me-2">
													<input type="text" class="form-control" id="titleInput"
														style="width: 200px;" placeholder="Search by Title...">
												</div>
												<button type="button" class="btn btn-primary"
													onclick="searchBooks()" style="min-width: 200px;">Search</button>
											</div>
											<div class="col-auto">
												<a href="#" class="btn btn-outline-primary me-2 d-none"><i
													class="fas fa-download"></i> ADD</a> <a href="add-form"
													class="btn btn-primary"><i class="fas fa-plus"></i></a>
											</div>
										</div>
									</div>





									<!-- /Page Header -->
									<div class="table-responsive">
										<table
											class="table border-0 star-student table-hover table-center mb-0 datatable table-striped">
											<thead class="student-thread">
												<tr>
													<th>BookId</th>
													<th>Title</th>
													<th>Author</th>
													<th>Language</th>
													<th>ISBN</th>
													<th>Publisher</th>
													<th>Publisher City</th>
													<th>Publication Date</th>
													<th>Status</th>
													<th>AvailableQuantity</th>
													<th>TotalQuantity</th>
													<th class="text-end">Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="bk" items="${allBook }">
													<tr>
														<td>${bk.bookId}</td>
														<td>${bk.title}</td>
														<td>${bk.author}</td>
														<td>${bk.language}</td>
														<td>${bk.ISBN}</td>
														<td>${bk.publisher}</td>
														<td>${bk.publisherCity}</td>
														<td>${bk.publicationDate}</td>
														<td><c:choose>
																<c:when test="${availabilityMap[bk.bookId]}">
																	<span class="badge badge-success">Available</span>
																</c:when>
																<c:otherwise>
																	<span class="badge badge-danger">Not Available</span>
																</c:otherwise>
															</c:choose></td>

														<td>${bk.availableQuantity}</td>
														<td>${bk.totalQuantity}</td>


														<td class="text-end">
															<div class="actions">
																<a
																	href="update?BookId=${bk.bookId}&Title=${bk.title}&Author=${bk.author}&Language=${bk.language}&ISBN=${bk.ISBN}&Publisher=${bk.publisher}&PublisherCity=${bk.publisherCity}
															&PublicationDate=${bk.publicationDate}&Status=${bk.status}"
																	class="btn btn-sm bg-danger-light"><i
																	class="feather-edit"></i></a> <a
																	class="btn btn-sm bg-danger-light" href="#"
																	onclick="confirmDelete(${bk.bookId});"> <i
																	class="feather-trash-2"></i>
																</a>


															</div>
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
		<script>
    function confirmDelete(bookId) {
        if (confirm('Are you sure you want to delete this record?')) {
            window.location.href = 'delete?bookId=' + bookId;
        }
    }
</script>


		<script>
    function searchBooks() {
        let filter = document.getElementById('titleInput').value.trim().toLowerCase();
        let tableRows = document.querySelectorAll('.datatable tbody tr');

        tableRows.forEach(row =>{
            let titleCell = row.cells[1]; // Assuming "Title" is the second column
            if (titleCell) {
                let titleText = titleCell.textContent || titleCell.innerText;
                titleText = titleText.toLowerCase();
                if (titleText.indexOf(filter) > -1) {
                    row.style.display = "";
                } else {
                    row.style.display = "none";
                }
            }
        });
    }
</script>
	</div>
</div>


<!-- Datatables JS -->
<script
	src="<c:url value="/resources/assets/plugins/datatables/datatables.min.js"/>"></script>
<jsp:include page="../modules/footer.jsp"></jsp:include>