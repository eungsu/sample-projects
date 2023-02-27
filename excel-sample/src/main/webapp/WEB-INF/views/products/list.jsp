<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>샘플 애플리케이션</title>
</head>
<body>
<%@ include file="../common/navbar.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1>상품 목록</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<table class="table">
				<thead>
					<tr>
						<th>순번</th>
						<th>상품번호</th>
						<th>상품명</th>
						<th>제조회사</th>
						<th class="text-end">가격</th>
						<th class="text-end">할인가격</th>
						<th class="text-end">재고수량</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${products }" varStatus="loop">
						<tr>
							<td>${loop.count }</td>
							<td>${product.no }</td>
							<td>${product.name }</td>
							<td>${product.maker }</td>
							<td class="text-end"><fmt:formatNumber value="${product.price }"/> 원</td>
							<td class="text-end"><fmt:formatNumber value="${product.discountPrice }"/> 원</td>
							<td class="text-end"><fmt:formatNumber value="${product.stock }"/> 객</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col-6">
			<form class="row row-cols-lg-auto g-3 align-items-center" method="post" action="/products/upload" enctype="multipart/form-data">
				<div class="col-12">
					<div class="input-group">
						<input type="file" class="form-control form-control-sm" name="xls" placeholder="엑셀파일을 첨부하세요">
					</div>
				</div>
				<div class="col-12">
					<button type="submit" class="btn btn-dark btn-sm">엑셀 업로드</button>
				</div>
			</form>
		</div>
		<div class="col-6 text-end">
			<a href="/products/download" class="btn btn-outline-primary btn-sm">엑셀 다운로드</a>
		</div>
	</div>
</div>
</body>
</html>