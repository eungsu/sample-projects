<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><a class="nav-link" href="/">홈</a></li>
			<c:choose>
				<c:when test="${LOGIN_TYPE eq '고객' }">
					<li class="nav-item"><a class="nav-link" href="/customer-chat">고객 상담하기</a></li>
				</c:when>
				<c:when test="${LOGIN_TYPE eq '직원' }">
					<li class="nav-item"><a class="nav-link" href="/employee-chat">직원 상담하기</a></li>
				</c:when>
			</c:choose>
		</ul>
		<c:if test="${not empty LOGIN_ID }">
			<span class="navbar-text"><strong class="text-white">${LOGIN_NAME }</strong> 님 환영합니다.</span>
		</c:if>
		<ul class="navbar-nav">
			<c:if test="${not empty LOGIN_ID }">
				<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
			</c:if>
			<c:if test="${empty LOGIN_ID}">
				<li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
			</c:if>
		</ul>
	</div>
</nav>