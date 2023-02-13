<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark mb-3">
    <div class="container-fluid">
        <ul class="navbar-nav me-auto">
            <li class="nav-item"><a class="nav-link" href="/">홈</a></li>
            <li class="nav-item"><a class="nav-link" href="/book/list">책</a></li>
        </ul>
        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal" var="user"/>
            <span class="navbar-text"><strong class="fw-bold text-white">${user.name }</strong>님 환영합니다.</span>
        </sec:authorize>
        <ul class="navbar-nav">
            <sec:authorize access="!isAuthenticated()">
                <li class="nav-item"><a class="nav-link" href="/loginform">로그인</a></li>
                <li class="nav-item"><a class="nav-link" href="/register">회원가입</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li class="nav-item"><a class="nav-link" href="/logout" onclick="logout(event)">로그아웃</a></li>
                <form id="form-logout" action="/logout" method="post">
                	<sec:csrfInput/>
                </form>
            </sec:authorize>
        </ul>
    </div>
</nav>
<script>
function logout(event) {
	event.preventDefault();
	document.getElementById("form-logout").submit();
}
</script>
