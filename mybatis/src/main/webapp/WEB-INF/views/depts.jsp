<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>샘플 애플리케이션</title>
</head>
<body>
<%@ include file="common/navbar.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1>부서리스트</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<table class="table table-sm">
				<colgroup>
					<col width="20%">
					<col width="20%">
					<col width="20%">
					<col width="40%">
				</colgroup>
				<thead>
					<tr>
						<th>순번</th>
						<th>부서번호</th>
						<th>부서이름</th>
						<th>소속직원</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${dtos }" varStatus="loop">
						<tr>
							<td>${loop.count }</td>
							<td>${dto.deptId }</td>
							<td>${dto.deptName }</td>
							<td>
								<%-- <select class="form-control">
									<c:forEach var="emp" items="${dto.employees }">
										<option value="${emp.id }"> ${emp.firstName } ${emp.lastName } (${emp.jobId })</option>
									</c:forEach>
								</select> --%>
								${dto.employeeNames }
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>