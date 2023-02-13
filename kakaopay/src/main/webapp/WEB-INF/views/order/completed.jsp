<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <%@ include file="../common/navbar.jsp"%>
    <div class="row mb-3">
        <div class="col">
            <h1>주문 완료</h1>
        </div>
    </div>
    <div class="row mb-3">
        <div class="col">
            <p>주문내용 정보를 확인하세요.</p>
            <div class="card">
            	<div class="card-body">
            		<h5 class="card-title">주문 정보 요약</h5>
            		<p>주문아이디 : ${order.id }</p>
            		<p>주문상태 : ${order.orderStatus }</p>
            		<p>주문금액 : <fmt:formatNumber value="${order.totalPrice }" /></p>
            		<p>주문날짜 : <fmt:formatDate value="${order.createdDate }" pattern="yyyy년 M월 d일 a h시 m분 s초"/> </p>
            	</div>
            </div>
        </div>
    </div>
    <div class="row mb-3">
    	<div class="col text-end">
    		<a href="/book/list" class="btn btn-primary btn-ls">쇼핑 계속</a>
    	</div>
    </div>
</div>
<script type="text/javascript">

</script>
</body>
</html>

