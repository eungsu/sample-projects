<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<title>샘플 애플리케이션</title>
</head>
<body>
<c:set var="menu" value="register" />
<%@ include file="../common/navbar.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1>직원으로 가입하기</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-6">
			<p>직원정보를 입력하세요</p>
			<form id="form-register" class="border bg-light p-3" method="post" action="register">
				<div class="mb-3">
					<label class="form-label">접근 권한</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority" value="ROLE_EMP" checked>
							<label class="form-check-label">직원</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority" value="ROLE_ADMIN" >
							<label class="form-check-label">관리자</label>
						</div>
					</div>
				</div>
				<div class="mb-2">
					<label class="form-label">아이디</label>
					<input type="text" class="form-control" name="id" />
				</div>
				<div class="mb-2">
					<label class="form-label">비밀번호</label>
					<input type="password" class="form-control" name="password" />
				</div>
				<div class="mb-2">
					<label class="form-label">이름</label>
					<input type="text" class="form-control" name="name" />
				</div>
				<div class="mb-2">
					<label class="form-label">이메일</label>
					<input type="text" class="form-control" name="email" />
				</div>
				<div class="mb-2">
					<label class="form-label">소속부서</label>
					<select class="form-select" name="deptNo">
						<c:forEach var="dept" items="${depts }">
							<option value="${dept.no }"> ${dept.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="mb-2">
					<label class="form-label">직위</label>
					<select class="form-select" name="positionNo">
						<c:forEach var="position" items="${positions }">
							<option value="${position.no }"> ${position.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="text-end">
					<button type="submit" class="btn btn-primary">직원가입</button>
				</div>
			</form>			
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>