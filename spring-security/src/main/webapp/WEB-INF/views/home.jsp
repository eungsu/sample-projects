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
<title>샘플 애플리케이션</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><a class="nav-link active" href="/">홈</a></li>
		</ul>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
			<li class="nav-item"><a class="nav-link" href="/register">회원가입</a></li>
			<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
		</ul>
	</div>
</nav>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1>샘플 애플리케이션</h1>
			<p>스프링 시큐리티로 부가적인 로그인 정보가 전달되는 로그인 샘플 프로젝트입니다.</p>
			<p>회원가입 후 로그인 메뉴를 클릭해서 확인해보세요.</p>
		</div>
	</div>
</div>
</body>
</html>