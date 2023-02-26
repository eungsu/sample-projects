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
	let employeeId = null;
	
	function connect() {
		ws = new SockJS("/chat");
		ws.onopen = function() {
			startChat();
		}
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
	connect();
	
	function disconnect() {
		if (ws != null) {
			ws.clos();
		}
	}
	
	function startChat() {
		let message = {
			cmd: 'start',
			sender: '고객'
		}
		send(message);
	}
	
	function stopChat() {
		let message = {
			cmd: 'stop',
			receiver: employeeId,
			sender: '고객'
		}
		send(message);
	}
	
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
	
	function send(message) {
		ws.send(JSON.stringify(message));
	}
	
	$("#card-chat .card-footer button").click(function() {
		chat();
	});
})
</script>
</body>
</html>