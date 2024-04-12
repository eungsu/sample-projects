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
            <h1>주문하기 폼</h1>
        </div>
    </div>
    <div class="row mb-3">
        <div class="col">
            <p>주문도서 정보를 확인하세요.</p>
            <form id="form-order">
            	<sec:csrfInput/>
            	<input type="hidden" name="no" value="${book.no }">
	            <table class="table">
	            	<thead>
	            		<tr>
	            			<th style="width: 40%;">제목</th>
	            			<th style="width: 15%;">저자</th>
	            			<th style="width: 15%;" class="text-center">구매수량</th>
	            			<th style="width: 15%;" class="text-end pe-3">가격</th>
	            			<th style="width: 15%;" class="text-end pe-3">구매금액</th>
	            		</tr>
	            	</thead>
	            	<tbody>
	            		<tr>
	            			<td class="align-middle" id="book-title" data-book-no="${book.no }">${book.title }</td>
	            			<td class="align-middle">${book.author }</td>
	            			<td class="align-middle text-center ps-3"><input type="number" class="form-control" name="quantity" value="1"/></td>
	            			<td class="align-middle text-end pe-3"><strong class="text-danger"><fmt:formatNumber value="${book.discountPrice }" pattern="#,###"/></strong> 원</td>
	            			<td class="align-middle text-end pe-3"><strong id="total-price" data-total-price="${book.discountPrice }"><fmt:formatNumber value="${book.discountPrice }" pattern="#,###"/></strong> 원</td>
	            		</tr>
	            	</tbody>
	            </table>
	            
	            <div class="mb-5 text-end">
		            <p>구매금액을 확인하고, 결재하시기 바랍니다. <button type="button" class="btn btn-primary ms-5" id="btn-pay-ready">결재하기</button></p>
	            </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function() {
	$("#btn-pay-ready").click(function(e) {
		
		$.ajax({
			type: 'get',
			url: '/order/pay/ready',
			data: {
				no: $("#book-title").attr("data-book-no"),
				quantity: $("#form-order :input[name=quantity]").val(),
				totalPrice: $("#total-price").attr("data-total-price"),
			},
			success:function(response) {
				location.href= response.next_redirect_pc_url;
			}
		});
	});
})
</script>
</body>
</html>

