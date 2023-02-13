<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>애플리케이션</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <%@ include file="common/navbar.jsp"%>
    <div class="row mb-3">
        <div class="col">
            <div class="border p-3 bg-light">
                <h1 class="mb-4">샘플 애플리케이션 입니다.</h1>
                <p class="">스프링부트, JPA, 카카오페이 샘플 애플리케이션</p>

                <sec:authorize access="!isAuthenticated()">
                    <div>
                        <a href="/register" class="btn btn-primary btn-lg">회원가입</a>
                        <a href="/loginform" class="btn btn-primary btn-lg">로그인</a>
                    </div>
                </sec:authorize>
            </div>
        </div>
    </div>
</div>
</body>
</html>
