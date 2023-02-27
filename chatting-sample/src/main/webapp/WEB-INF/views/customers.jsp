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
			<h1>상담 페이지</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-6">
			<div class="card" id="card-chat">
				<div class="card-header">상담내용</div>
				<div class="card-body" style="height: 500px; overflow-y:scroll;"></div>
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
	// 웹소켓 객체를 저장하는 변수다.
	let ws = null;
	// 현재 상담중인 직원아이디가 저장되는 변수ㅏ.
	let employeeId = null;
	
	// 웹소켓 연결요청을 보내는 메소드다.
	function connect() {
		// 웹소켓 객체를 생성하고, 지정된 URI로 웹소켓 연결요청을 보낸다.
		ws = new SockJS("/chat");
		// 웹소켓 연결이 완료되면 실행된다.
		ws.onopen = function() {
			startChat();
		}
		// 웹소켓으로 서버로부터 메세지를 수신하면 실행된다.
		ws.onmessage = function(message) {
			let data = JSON.parse(message.data);
			
			if (data.cmd == 'chat') {
				let value = data.text;
				employeeId = data.receiver;
				let content = `
					<div class="w-75 float-start">
						<div class="alert alert-warning">
							\${value}
						</div>
					</div>
				`;
				$("#card-chat .card-body").append(content);
			} else if (data.cmd == 'error') {
				let value = data.text;
				let content = `
					<div class="w-75 float-start">
						<div class="alert alert-danger">
							\${value}
						</div>
					</div>
				`;
				$("#card-chat .card-body").append(content);
			}
		}
	}
	// 웹소켓 연결요청을 보낸다.
	connect();
	
	// 웹소켓 연결을 해제한다.
	function disconnect() {
		if (ws != null) {
			ws.clos();
		}
	}
	
	// 상담시작 요청을 웹소켓으로 보낸다.
	function startChat() {
		let message = {
			cmd: 'start',
			sender: '고객'
		}
		send(message);
	}
	
	// 상담중단 요청을 웹소켓으로 보낸다.
	function stopChat() {
		let message = {
			cmd: 'stop',
			receiver: employeeId,
			sender: '고객'
		}
		send(message);
	}
	
	// 상담메세지를 웹소켓으로 보낸다.
	function chat() {
		let value = $(":input[name='message']").val();
		let message = {
			cmd: 'chat',
			receiver: employeeId,
			sender: '고객',
			text: value
		}
		send(message);
		let content = `
			<div class="w-75 float-end">
				<div class="alert alert-info text-end">
					\${value}
				</div>
			</div>
		`;
		$("#card-chat .card-body").append(content);
		
		$(":input[name='message']").val("")
		
	}
	
	// 웹소켓으로 상담시작, 상담중단, 상담메세지를 서버로 보낸다.
	function send(message) {
		ws.send(JSON.stringify(message));
	}
	
	// 전송버튼을 클릭했을 때 실행되는 이벤트 핸들러 함수다.
	// chat()함수를 실행해서 상담 메세지를 웹소켓으로 서버로 보낸다.
	$("#card-chat .card-footer button").click(function() {
		chat();
	});
	
	// 입력창에서 Enter키를 눌렀을때 실행되는 이벤트 핸들러 함수다.
	// chat()함수를 실행해서 상담 메세지를 웹소켓으로 서버로 보낸다.
	$(":input[name='message']").keydown(function(event) {
		if (event.which == 13) {
			chat();
		}
	})
})
</script>
</body>
</html>