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
<%@ include file="../common/navbar.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1>내 정보 보기</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-10">
			<div class="bg-light border p-3">
				<c:if test="${user.providerType ne 'local' }">
					<div class="mb-3">
						<label class='form-label'>소셜로그인 사용자</label>
						<input type="text" class="form-control" value="${user.providerType }" disabled="disabled">
					</div>
				</c:if>
				<c:if test="${user.providerType eq 'local' }">
					<div class="mb-3">
						<label class='form-label'>아이디</label>
						<input type="text" class="form-control" value="${user.id }" disabled="disabled">
					</div>
				</c:if>
				<div class="mb-3">
					<label class='form-label'>이름</label>
					<input type="text" class="form-control" value="${user.name }" disabled="disabled">
				</div>
				<c:if test="${not empty user.email }">
					<div class="mb-3">
						<label class='form-label'>이메일</label>
						<input type="text" class="form-control" value="${user.email }" disabled="disabled">
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>
</body>
</html>