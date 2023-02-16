<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><a class="nav-link active" href="/">홈</a></li>
			<c:if test="${not empty LOGIN_EMP }">
				<li class="nav-item"><a class="nav-link active" href="/calendar">일정관리</a></li>
			</c:if>
		</ul>
		<c:if test="${not empty LOGIN_EMP }">
			<span class="navbar-text"><strong class="text-white">${LOGIN_EMP.name }</strong> 님 환영합니다.</span>
		</c:if>
		<ul class="navbar-nav">
			<c:if test="${not empty LOGIN_EMP }">
				<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
			</c:if>
			<c:if test="${empty LOGIN_EMP }">
				<li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
			</c:if>
			<li class="nav-item"><a class="nav-link" href="/register">회원가입</a></li>
		</ul>
	</div>
</nav>