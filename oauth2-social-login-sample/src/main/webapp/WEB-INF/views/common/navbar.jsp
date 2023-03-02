<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><a class="nav-link" href="/">홈</a></li>
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item"><a class="nav-link" href="/post/list">게시판</a></li>
			</sec:authorize>
		</ul>
		<sec:authorize access="isAuthenticated()">
			<span class="navbar-text me-5"><strong class="text-white"><sec:authentication property="principal.nickname"/></strong> 님 환영합니다.</span>
		</sec:authorize>
		<ul class="navbar-nav">
			<sec:authorize access="!isAuthenticated()">
				<li class="nav-item"><a class="nav-link" href="/loginform">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="/registerform">회원가입</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item"><a class="nav-link" href="/user/info">내정보 보기</a></li>
				<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
			</sec:authorize>
		</ul>
	</div>
</nav>