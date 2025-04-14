
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
					<h3 class="page-title">Teachers</h3>
				</div>
			</div>
		</div>



		<div class="row">
			<div class="col-sm-12">

				<div class="card card-table">
					<div class="card-body">

						<div class="page-header">
							<div class="row align-items-center">
								<div
									class="col-auto text-end float-end ms-auto download-grp d-flex">
									<div class="form-group mb-0 me-2">
										<input type="text" class="form-control"
											id="contactNumberInput" style="width: 220px;"
											placeholder="Search by Contact Number">
									</div>
									<button type="button" class="btn btn-primary"
										onclick="searchContactNo()" style="min-width: 200px;">Search</button>
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

										<th>TeacherId</th>
										<th>TeacherName</th>
										<th>Email</th>
										<th>Contact Number</th>
										<th>Faculty</th>
										<th class="text-end">Action</th>
									</tr>
								</thead>
								<tbody>
									<!-- Loop through teacher objects -->
									<!-- Student data populated dynamically -->
									<c:if test="${not empty teacherList}">
										<c:forEach var="teacher" items="${teacherList}">
											<tr>

												<!-- Display teacher details -->
												<td>${teacher.teacherId}</td>
												<td><a href="teacher-details/${teacher.teacherId}">${teacher.firstName}
														${teacher.lastName}</a></td>
												<td>${teacher.email}</td>
												<td>${teacher.contactNumber}</td>
												<td>${teacher.faculty}</td>

												<!-- Actions column -->
												<td class="text-end">
													<div class="actions">

														<!-- Edit teacher button -->
														<a
															href="update?TeacherId=${teacher.teacherId}&FirstName=${teacher.firstName}&LastName=${teacher.lastName}
									&Email=${teacher.email}&ContactNumber=${teacher.contactNumber}&faculty=${teacher.faculty}"
															class="btn btn-sm bg-danger-light"> <i
															class="feather-edit"></i></a> <a
															class="btn btn-sm bg-danger-light" href="#"
															onclick="confirmDelete(${teacher.teacherId});"> <i
															class="feather-trash-2"></i>
														</a>
													</div>
												</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${empty teacherList}">
										<tr>
											<td colspan="8" class="text-center">No teacher found</td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</div>


						<script>
    function confirmDelete(teacherId) {
        if (confirm('Are you sure you want to delete this record?')) {
            window.location.href = 'delete?teacherId=' + teacherId;
        }
    }
</script>

						<!-- JavaScript code -->
						<script>
    function searchContactNo() {
        let filter = document.getElementById('contactNumberInput').value.trim();
        let tableRows = document.querySelectorAll('.datatable tbody tr');

        tableRows.forEach(row => {
            let cells = row.cells;
            let contactCell = cells[3]; // Assuming contact number is the fourth column (index 3)
            if (contactCell) {
                let contactText = contactCell.textContent || contactCell.innerText;
                if (contactText.includes(filter)) {
                    row.style.display = ""; // Show row if contact number matches filter
                } else {
                    row.style.display = "none"; // Hide row if contact number does not match filter
                }
            }
        });
    }
</script>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<script src="/plugins/datatables/datatables.min.js"></script>

<jsp:include page="../modules/footer.jsp"></jsp:include>



