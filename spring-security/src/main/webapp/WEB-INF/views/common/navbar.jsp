<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><a class="nav-link ${menu eq 'home' ? 'active' : '' }" href="/">홈</a></li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle ${menu eq 'user' ? 'active' : '' }" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
					사용자
				</a>
 				<ul class="dropdown-menu">
					<li><a class="dropdown-item" href="/user/info">내 정보보기</a></li>
					<li><a class="dropdown-item" href="/user/password">비밀번호 변경</a></li>
				</ul>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle ${menu eq 'emp' ? 'active' : '' }" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
					직원
				</a>
 				<ul class="dropdown-menu">
					<li><a class="dropdown-item" href="/emp/codes">공통코드관리</a></li>
					<li><a class="dropdown-item" href="/emp/users">사용자관리</a></li>
					<li><a class="dropdown-item" href="/emp/stats">통계</a></li>
				</ul>
			</li>
		</ul>
		<ul class="navbar-nav">
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
			<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
		</ul>
	</div>
</nav>