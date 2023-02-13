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
            <h1>도서 목록</h1>
        </div>
    </div>
    <div class="row mb-3">
		<c:forEach var="book" items="${books }">
			<div class="col-3 mb-3">
				<div class="card">
					<div class="card-body">
						<div class="border-bottom pb-2 d-flex justify-content-between">
							<span>${book.title }</span>
							<sec:authorize access="isAuthenticated()">
								<a href="/order/form?no=${book.no }"class="btn text-success"><i class="bi bi-bag-check-fill"></i></a>
							</sec:authorize>
						</div>
						<p class="mt-1 d-flex justify-content-between text-secondary">
							<span>${book.author }</span> <span>${book.publisher }</span>
						</p>
						<p class="mb-0 d-flex justify-content-between">
							<small class="text-decoration-line-through"><fmt:formatNumber value="${book.price }" pattern="#,###"/> 원</small> 
							<span><strong class="text-danger"><fmt:formatNumber value="${book.discountPrice }" pattern="#,###"/></strong> 원</span>
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
</body>
</html>
