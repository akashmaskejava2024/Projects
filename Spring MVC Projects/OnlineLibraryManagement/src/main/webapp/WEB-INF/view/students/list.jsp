<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../modules/header.jsp" />
<%@ page isELIgnored="false"%>

<div class="page-wrapper">
	<div class="content container-fluid">
		<div class="page-header">
			<div class="row">
				<div class="col-sm-12">
					<div class="page-sub-header">
						<h3 class="page-title">Students</h3>
					</div>
				</div>
			</div>
		</div>

		<!-- Students Table -->
		<div class="row">
			<div class="col-sm-12">
				<div class="card card-table comman-shadow">
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

						<!-- Table Structure -->
						<div class="table-responsive">
							<table
								class="table border-0 star-student table-hover table-center mb-0 datatable table-striped">
								<thead class="student-thread">
									<tr>
										<th>StudentId</th>
										<th>StudentName</th>
										<th>Email</th>
										<th>ClassName</th>
										<th>Department</th>
										<th>ContactNumber</th>
										<th class="text-end">Action</th>
									</tr>
								</thead>
								<tbody>
									<!-- Student data populated dynamically -->
									<c:if test="${not empty studentList}">
										<c:forEach var="student" items="${studentList}">
											<tr>
												<td>${student.studentId}</td>
												<td><a href="student-details/${student.studentId}">${student.firstName}
														${student.lastName}</a></td>
												<td>${student.email}</td>
												<td>${student.className}</td>
												<td>${student.department}</td>
												<td>${student.contactNumber}</td>
												<td class="text-end">
													<div class="actions">
														<a
															href="update?studentId=${student.studentId}&firstName=${student.firstName}&lastName=${student.lastName}&email=${student.email}&className=${student.className}&department=${student.department}&contactNumber=${student.contactNumber}"
															class="btn btn-sm bg-danger-light"><i
															class="feather-edit"></i> </a> <a
															class="btn btn-sm bg-danger-light" href="#"
															onclick="confirmDelete(${student.studentId});"><i
															class="feather-trash-2"></i></a>
													</div>
												</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${empty studentList}">
										<tr>
											<td colspan="8" class="text-center">No students found</td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</div>



					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
        function confirmDelete(studentId) {
            if (confirm('Are you sure you want to delete this record?')) {
                window.location.href = 'delete?studentId=' + studentId;
            }
        }
    </script>

	<script>
        function searchContactNo() {
            let filter = document.getElementById('contactNumberInput').value.trim();
            let tableRows = document.querySelectorAll('.datatable tbody tr');

            tableRows.forEach(row => {
                let cells = row.cells;
                let contactCell = cells[5]; 
                if (contactCell) {
                    let contactText = contactCell.textContent || contactCell.innerText;
                    if (contactText.includes(filter)) {
                        row.style.display = "";
                    } else {
                        row.style.display = "none";
                    }
                }
            });
        }
    </script>

	<jsp:include page="../modules/footer.jsp"></jsp:include>
</div>
