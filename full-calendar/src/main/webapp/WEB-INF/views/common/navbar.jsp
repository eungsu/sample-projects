<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><a class="nav-link active" href="/">홈</a></li>
			<li class="nav-item"><a class="nav-link active" href="/calendar">일정관리</a></li>
		</ul>
		<span class="navbar-text"><strong class="text-white">홍길동</strong> 님 환영합니다.</span>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
			<li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
			<li class="nav-item"><a class="nav-link" href="/register">회원가입</a></li>
		</ul>
	</div>
</nav>