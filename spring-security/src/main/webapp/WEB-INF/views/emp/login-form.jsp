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
<c:set var="menu" value="login" />
<%@ include file="../common/navbar.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1>직원 로그인</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-6">
			<p>직원아이디와 비밀번호를 입력하세요</p>
			<c:if test="${param.error eq 'fail'}">
				<div class="alert alert-danger">
					<strong>로그인 실패</strong> 아이디 혹은 비밀번호가 올바르지 않습니다.
				</div>
			</c:if>
			<form id="form-register" class="border bg-light p-3" method="post" action="/login">
				<input type="hidden" name="userType" value="직원">
				<div class="mb-2">
					<label class="form-label">직원 아이디</label>
					<input type="text" class="form-control" name="id" value="emp1"/>
				</div>
				<div class="mb-2">
					<label class="form-label">직원 비밀번호</label>
					<input type="password" class="form-control" name="password" value="Zxcv1234!"/>
				</div>
				<div class="text-end">
					<button type="submit" class="btn btn-primary">로그인</button>
				</div>
			</form>			
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>