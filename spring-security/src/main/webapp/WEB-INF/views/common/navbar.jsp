<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><a class="nav-link ${menu eq 'home' ? 'active' : '' }" href="/">홈</a></li>
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item"><a class="nav-link ${menu eq 'user' ? 'active' : '' }" href="/user/home">사용자</a></li>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('EMPLOYEE', 'ADMIN')">
				<li class="nav-item"><a class="nav-link ${menu eq 'emp' ? 'active' : '' }" href="/emp/home">직원</a></li>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN')">
				<li class="nav-item"><a class="nav-link ${menu eq 'admin' ? 'active' : '' }" href="/admin/home">관리자</a></li>
			</sec:authorize>
		</ul>
		<sec:authorize access="isAuthenticated()">
			<span class="navbar-text"><strong class="text-white"><sec:authentication property="principal.name"/></strong> 님 환영합니다.</span>
		</sec:authorize>
		<ul class="navbar-nav">
			<sec:authorize access="!isAuthenticated()">
				<li class="nav-item"><a class="nav-link ${menu eq 'login' ? 'active' : ''  }" href="/login">로그인</a></li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle ${menu eq 'register' ? 'active' : '' }" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						회원가입
					</a>
	 				<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="/user/register">사용자로 등록하기</a></li>
						<li><a class="dropdown-item" href="/emp/register">직원으로 등록하기</a></li>
					</ul>
				</li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
			</sec:authorize>
		</ul>
	</div>
</nav>