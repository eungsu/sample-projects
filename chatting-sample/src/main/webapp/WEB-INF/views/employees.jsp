<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>샘플 애플리케이션</title>
</head>
<body>
<%@ include file="common/navbar.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1>고객 응대 페이지</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card" id="card-chat">
				<div class="card-header">상담내용</div>
				<div class="card-body" style="height: 500px; overflow-y:scroll;">
					
				</div>
				<div class='card-footer'>
					<div class="row">
						<div class="col-10">
							<input type="text" class="form-control" name="message"/>
						</div>
						<div class="col-2"><button class="btn btn-secondary">전송</button></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script type="text/javascript">
$(function() {
	let ws = null;
	// 현재 상담중인 직원아이디가 저장되는 변수ㅏ.
	let roomId = null;
	let employeeId = '${LOGIN_ID}';
	let customerId = null;
	
	function connect() {
		ws = new SockJS("/chat");
		ws.onmessage = function(message) {
			let data = JSON.parse(message.data);
			
			if (data.cmd == "chat-open-success") {
				roomId = data.roomId;
				customerId = data.customerId;
				appendChatMessage(data.text, 'float-start', 'alert-danger', 'text-start');
			} else if (data.cmd == 'chat-message') {
				appendChatMessage(data.text, 'float-start', 'alert-warning', 'text-start');
			} else if (data.cmd == 'chat-error') {
				appendChatMessage(data.text, 'float-start', 'alert-danger', 'text-start');
			}
		}
	}
	connect();
	
	function disconnect() {
		if (ws != null) {
			ws.clos();
		}
	}
	
	// 상담메세지를 웹소켓으로 보낸다.
	function chat() {
		let inputMessage = $(":input[name='message']").val();
		
		if (inputMessage) {
			let message = {
				cmd: 'chat-message',
				roomId: roomId,
				customerId: customerId,
				employeeId: employeeId,
				senderType: '직원',
				text: inputMessage
			}
			send(message);			
			appendChatMessage(inputMessage, 'float-end', 'alert-info', 'text-end');
			$(":input[name='message']").val("");
		}
	}
	
	function send(message) {
		ws.send(JSON.stringify(message));
	}
	
	function appendChatMessage(message, floating, style, align) {
		let content = `
			<div class="w-75 \${floating}">
				<div class="alert \${style} \${align}">
					\${message}
				</div>
			</div>
		`;
		$("#card-chat .card-body").append(content);
	}
	
	$("#card-chat .card-footer button").click(function() {
		chat();
	});
	
	$(":input[name='message']").keydown(function(event) {
		if (event.which == 13) {
			chat();
		}
	})
})
</script>
</body>
</html>