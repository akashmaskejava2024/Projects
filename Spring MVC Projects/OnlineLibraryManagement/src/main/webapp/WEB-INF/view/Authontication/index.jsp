<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../modules/header.jsp" />

<!-- Rest of your JSP page content goes here -->
<%@ page isELIgnored="false"%>


<!-- Page Wrapper -->
<div class="page-wrapper">

	<div class="content container-fluid">
		<!-- Page Header -->
		<div class="page-header">
			<div class="row">
				<div class="col-sm-12">
					<div class="page-sub-header">
						<h3 class="page-title">Welcome ${sessionScope.loggedInUsername} !</h3>
					</div>
				</div>
			</div>
		</div>
		<!-- /Page Header -->

		<!-- Overview Section -->
		<div class="row">
			<div class="col-xl-3 col-sm-6 col-12 d-flex">
				<div class="card bg-comman w-100">
					<div class="card-body">
						<div
							class="db-widgets d-flex justify-content-between align-items-center">
							<div class="db-info">
								<h6>Students</h6>
								<h3>${numberOfStudents}</h3>

							</div>
							<div class="db-icon">
								<img src="resources/assets/img/icons/dash-icon-01.svg"
									alt="Dashboard Icon">
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-xl-3 col-sm-6 col-12 d-flex">
				<div class="card bg-comman w-100">
					<div class="card-body">
						<div
							class="db-widgets d-flex justify-content-between align-items-center">
							<div class="db-info">
								<h6>Teachers</h6>
								<h3>${numberOfTeachers}</h3>
							</div>
							<div class="db-icon">
								<img src="resources/assets/image/tech.jpg"
									style="height: 58px; width: 58px;" alt="Dashboard Icon">
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-xl-3 col-sm-6 col-12 d-flex">
				<div class="card bg-comman w-100">
					<div class="card-body">
						<div
							class="db-widgets d-flex justify-content-between align-items-center">
							<div class="db-info">
								<h6>Books</h6>
								<h3>${numberOfBooks}</h3>
							</div>
							<div class="db-icon">
								<img src="resources/assets/image/book1.jpg"
									style="height: 58px; width: 58px;" alt="Dashboard Icon">
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-xl-3 col-sm-6 col-12 d-flex">
				<div class="card bg-comman w-100">
					<div class="card-body">
						<div
							class="db-widgets d-flex justify-content-between align-items-center">
							<div class="db-info">
								<h6>Available Books</h6>
								<h3>${availableBooks}</h3>
							</div>
							<div class="db-icon">
								<img src="resources/assets/image/book2.jpg"
									style="height: 58px; width: 58px;" alt="Dashboard Icon">
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- /Overview Section -->



		<div class="row">
			<div class="col-xl-9 d-flex">
				<!-- Star Students -->
				<div class="card flex-fill student-space comman-shadow">
					<div class="card-header d-flex align-items-center">
						<h5 class="card-title">Books</h5>
						<ul class="chart-list-out student-ellips">
							<li class="star-menus"><a href="javascript:;"><i
									class="fas fa-ellipsis-v"></i></a></li>
						</ul>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table
								class="table star-student table-hover table-center table-borderless table-striped">
								<thead class="thead-light">
									<tr>
										<th>BookId</th>
										<th>Title</th>
										<th>Author</th>
										<th>Language</th>
										<th>ISBN</th>
										<th>Publisher</th>
										<th>Publisher City</th>
										<th>Publication Date</th>
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
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- /Star Students -->
			</div>

			<div class="col-xl-3 d-flex">
				<!-- Feed Activity -->
				<div class="card flex-fill comman-shadow">
					<div class="card-header d-flex align-items-center">
						<h5 class="card-title ">Author Names</h5>
						<ul class="chart-list-out student-ellips">
							<li class="star-menus"><a href="javascript:;"><i
									class="fas fa-ellipsis-v"></i></a></li>
						</ul>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table
								class="table star-student table-hover table-center table-borderless table-striped">
								<thead class="thead-light">
									<tr>
										<th>ID</th>
										<th>author</th>
									</tr>
								</thead>
								<tbody>
									  <c:forEach var="bk" items="${allBook}" varStatus="loop">
                                      <tr>
                                       <td>${loop.index + 1}</td>
                                       <td>${bk.author}</td>
                                      </tr>
                                  </c:forEach>
								</tbody>


							</table>
						</div>
					</div>
				</div>
				<!-- /Feed Activity -->
			</div>
		</div>

	</div>
	<!-- /Socail Media Follows -->
</div>

<include page="../modules/footer.jsp"></include>

