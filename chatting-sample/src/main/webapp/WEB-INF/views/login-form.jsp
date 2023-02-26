<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%@ include file="common/navbar.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1>로그인</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-6">
			<p>아이디와 비밀번호를 입력하세요</p>
			<form id="form-register" class="border bg-light p-3" method="post" action="login">
				<div class="mb-2">
					<label class="form-label">로그인 타입</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="type" value="고객" checked="checked">
							<label class="form-check-label">고객</label>
						</div>	
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="type" value="직원">
							<label class="form-check-label">직원</label>
						</div>	
					</div>
				</div>
				<div class="mb-2">
					<label class="form-label">아이디</label>
					<input type="text" class="form-control" name="id" value=""/>
				</div>
				<div class="mb-2">
					<label class="form-label">비밀번호</label>
					<input type="password" class="form-control" name="password" value="zxcv1234"/>
				</div>
				<div class="text-end">
					<button type="submit" class="btn btn-primary">로그인</button>
				</div>
			</form>			
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	// 회원가입 폼에서 submit 이벤트가 발생하면 실행되는 이벤트 핸들러 함수를 등록한다.
	$("#form-register").submit(function() {
		let email = $("#form-register :input[name=email]").val();
		let password = $("#form-register :input[name=password]").val();
		let name = $("#form-register :input[name=name]").val();
		
		if (email === '') {
			alert("이메일은 필수입력값입니다.");
			return false;
		}
		if (password === '') {
			alert('비밀번호는 필수입력값입니다.');
			return false;
		}
		if (name === '') {
			alert('이름은 필수입력값입니다.');
			return false;
		}
		return true;
	});
})
</script>
</body>
</html>