<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Student Performance Dashboard</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap & Custom CSS -->
  <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/assets/css/app.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/assets/css/icons.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

  <style>
    body {
      font-family: 'Roboto', sans-serif;
      background-color: #f4f6f9;
    }

    .container {
      margin-top: 30px;
    }

    h2, h3 {
      font-weight: 500;
    }
  </style>
</head>

<body>
  <div class="container">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2>📈 Student Performance Dashboard</h2>
      <div>
        <a href="${pageContext.request.contextPath}/student/logout" class="btn btn-danger">Logout</a>
      </div>
    </div>

		<!-- Attendance -->
		<div class="card mb-4">
			<div class="card-body">
				<h3>📊 Monthly Attendance</h3>
				<canvas id="attendanceChart" height="120"></canvas>
				<div class="table-responsive mt-3">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Month</th>
								<th>Total Working Days</th>
								<th>Days Present</th>
								<th>Attendance %</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="record" items="${attendanceList}">
								<tr>
									<td>${record["month"]}</td>
									<td>${record["totalWorkingDays"]}</td>
									<td>${record["studentPresentDays"]}</td>
									<td>
										<c:choose>
											<c:when test="${record['totalWorkingDays'] > 0}">
												${record["studentPresentDays"] * 100 / record["totalWorkingDays"]}%
											</c:when>
											<c:otherwise>N/A</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
<!-- Exam Scores Grouped by Semester -->
<div class="card mb-4">
  <div class="card-body">
    <h3>📚 Subject-Wise Exam Scores</h3>
  <!-- ✅ Semester Dropdown -->
<div class="mb-3" style="width: fit-content;">
    <label for="semesterSelect">Select Semester:</label>
    <select id="semesterSelect" class="form-control w-auto" onchange="filterDataBySemester()">
        <c:set var="seenSemesters" value="" />	
        <c:forEach var="score" items="${subjectWiseScores}">
            <c:if test="${not fn:contains(seenSemesters, score.semesterNo)}">
                <option value="${score.semesterNo}">Semester ${score.semesterNo}</option>
                <c:set var="seenSemesters" value="${seenSemesters}${score.semesterNo}," />
            </c:if>
        </c:forEach>
    </select>
</div>

<!-- 📊 Chart - smaller height via style -->
<canvas id="performanceChart" style="height:200px; max-height:300px;"></canvas>

<script>
    // ✅ Prepare JS Data (only total marks)
    const allScores = [
        <c:forEach var="score" items="${subjectWiseScores}" varStatus="loop">
            {
                semester: ${score.semesterNo},
                subject: "${score.subjectName}",
                total: ${score.totalMarksObtained}
            }<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];

    let chartInstance = null;

    function filterDataBySemester() {
        const selectedSemester = document.getElementById("semesterSelect").value;
        const filtered = allScores.filter(s => s.semester == selectedSemester);

        const labels = filtered.map(s => s.subject);
        const totalData = filtered.map(s => s.total);

        renderChart(labels, totalData, selectedSemester);
    }

    function renderChart(labels, totalData, semester) {
        const ctx = document.getElementById("performanceChart").getContext("2d");
        if (chartInstance) chartInstance.destroy();

        chartInstance = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [
                    {
                        label: 'Total Marks',
                        data: totalData,
                        backgroundColor: 'rgba(54, 162, 235, 0.6)'
                    }
                ]
            },
            options: {
                responsive: true,
                plugins: {
                    title: {
                        display: true,
                        text: `Subject-wise Total Marks - Semester ${semester}`
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Total Marks'
                        }
                    }
                }
            }
        });
    }

    // Auto-load first semester on page load
    window.onload = filterDataBySemester;
</script>
    
    <c:set var="currentSemester" value="-1" />
    <c:set var="semesterTotalObtained" value="0" />
    <c:set var="semesterTotalMax" value="0" />

    <c:forEach var="score" items="${subjectWiseScores}" varStatus="status">
    
      <c:if test="${score['semesterNo'] != currentSemester}">
        <c:if test="${status.index != 0}">
              </tbody>
            </table>

            <!-- 📌 Semester Percentage -->
            <p><strong>📌 Semester Percentage:</strong>
              <c:choose>
                <c:when test="${semesterTotalMax > 0}">
                  <fmt:formatNumber 
                    value="${semesterTotalObtained * 100.0 / semesterTotalMax}" 
                    type="number" 
                    maxFractionDigits="2" 
                  />%
                </c:when>
                <c:otherwise>N/A</c:otherwise>
              </c:choose>
            </p>

          </div> <!-- table-responsive -->
        </div> <!-- semester-table -->
        <hr/>
        </c:if>

        <!-- New Semester Starts -->
        <c:set var="currentSemester" value="${score['semesterNo']}" />
        <c:set var="semesterTotalObtained" value="0" />
        <c:set var="semesterTotalMax" value="0" />
        
        <div class="semester-table">
          <h4>📘 Semester ${currentSemester}</h4>
          <div class="table-responsive mt-3">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>Subject Name</th>
                  <th>Internal Marks</th>
                  <th>Total Internal</th>
                  <th>External Marks</th>
                  <th>Total External</th>
                  <th>Total Obtained</th>
                  <th>Total Max</th>
                </tr>
              </thead>
              <tbody>
      </c:if>

      <!-- Row -->
      <tr>
        <td>${score["subjectName"]}</td>
        <td>${score["internalMarks"]}</td>
        <td>${score["internalMarksTotal"]}</td>
        <td>${score["externalMarks"]}</td>
        <td>${score["externalMarksTotal"]}</td>
        <td>${score["totalMarksObtained"]}</td>
        <td>${score["totalMarksMax"]}</td>
      </tr>

      <!-- Accumulate totals -->
      <c:set var="semesterTotalObtained" value="${semesterTotalObtained + score['totalMarksObtained']}" />
      <c:set var="semesterTotalMax" value="${semesterTotalMax + score['totalMarksMax']}" />

      <!-- Final Semester Ends -->
      <c:if test="${status.last}">
            </tbody>
          </table>

          <!-- 📌 Semester Percentage -->
          <p><strong>📌 Semester Percentage:</strong>
            <c:choose>
              <c:when test="${semesterTotalMax > 0}">
                <fmt:formatNumber 
                  value="${semesterTotalObtained * 100.0 / semesterTotalMax}" 
                  type="number" 
                  maxFractionDigits="2" 
                />%
              </c:when>
              <c:otherwise>N/A</c:otherwise>
            </c:choose>
          </p>

        </div> <!-- table-responsive -->
      </div> <!-- semester-table -->
      </c:if>

    </c:forEach>
  </div>
</div>
		<!-- Activities -->
		<div class="card mb-4">
			<div class="card-body">
				<h3>🏆 Activities</h3>
				<canvas id="activitiesChart" height="120"></canvas>
				<div class="table-responsive mt-3">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Type</th>
								<th>Subtype</th>
								<th>Competition Level</th>
								<th>Rank</th>
								<th>Achievement</th>
								<th>Image</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="activity" items="${activities}">
								<tr>
									<td>${activity.type}</td>
									<td>${activity.subtype}</td>
									<td>${activity.competitionLevel}</td>
									<td>
										<c:choose>
											<c:when test="${activity.rank > 0}">${activity.rank}</c:when>
											<c:otherwise>-</c:otherwise>
										</c:choose>
									</td>
									<td>${activity.achievement}</td>
									<td>
										<c:choose>
											<c:when test="${not empty activity.image}">
												<img src="${activity.image}" width="80" height="60" />
											</c:when>
											<c:otherwise>No Image</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- Attendance Chart -->
	<script>
		document.addEventListener("DOMContentLoaded", function () {
			const attendanceLabels = [];
			const attendanceData = [];

			<c:forEach var="record" items="${attendanceList}">
			attendanceLabels.push("${record.month}");
			attendanceData.push(${record.totalWorkingDays} > 0 ? (${record.studentPresentDays} * 100 / ${record.totalWorkingDays}).toFixed(2) : 0);
			</c:forEach>

			new Chart(document.getElementById("attendanceChart"), {
				type: "bar",
				data: {
					labels: attendanceLabels,
					datasets: [{
						label: "Attendance %",
						data: attendanceData,
						backgroundColor: "rgba(75, 192, 192, 0.5)",
						borderColor: "rgba(75, 192, 192, 1)",
						borderWidth: 1
					}]
				},
				options: {
					scales: {
						y: {
							beginAtZero: true,
							max: 100,
							title: {
								display: true,
								text: 'Percentage (%)'
							}
						}
					}
				}
			});
		});
	</script>

	<!-- Exam Chart -->
	<script>
    const rawSubjectScores = ${subjectWiseScoresJson}; // This is a JS array of maps

    function groupBySemester(data) {
        const result = {};
        data.forEach(item => {
            const sem = item.semester;
            if (!result[sem]) result[sem] = [];
            result[sem].push(item);
        });
        return result;
    }

    const scoresBySemester = groupBySemester(rawSubjectScores);

    function filterChartData() {
        const selectedSem = document.getElementById("semesterSelect").value;
        const chartData = selectedSem ? scoresBySemester[selectedSem] : rawSubjectScores;

        // Now build chart data
        const labels = chartData.map(d => d.subjectName);
        const internalMarks = chartData.map(d => d.internalMarks);
        const externalMarks = chartData.map(d => d.externalMarks);

        drawChart(labels, internalMarks, externalMarks);
    }

    function drawChart(labels, internal, external) {
        const ctx = document.getElementById('scoreChart').getContext('2d');
        if (window.chartInstance) window.chartInstance.destroy();

        window.chartInstance = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [
                    {
                        label: 'Internal Marks',
                        data: internal,
                        backgroundColor: 'rgba(75, 192, 192, 0.6)'
                    },
                    {
                        label: 'External Marks',
                        data: external,
                        backgroundColor: 'rgba(255, 99, 132, 0.6)'
                    }
                ]
            },
            options: {
                responsive: true,
                plugins: {
                    title: {
                        display: true,
                        text: 'Subject-wise Performance'
                    }
                }
            }
        });
    }

    // Initial chart for All Semesters
    window.onload = filterChartData;
</script>
</body>
</html>