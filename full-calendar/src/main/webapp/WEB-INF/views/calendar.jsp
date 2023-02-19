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
<div class="container mt-3">
	<div class="row">
		<div class="col-3">
			<div class="list-group list-group-flush mt-5">
				<a href="" class="list-group-item" id="link-my-todos">내 일정 보기</a>
				<a href="" class="list-group-item" id="link-dept-todos">부서일정 보기</a>
				<a href="" class="list-group-item" id="link-add-todo">새 일정등록</a>
				<a href="" class="list-group-item" id="link-search-todos">일정 검색하기</a>
			</div>
		</div>
		<div class="col-9">
			<div id="calendar"></div>
		</div>
	</div>
</div>
<!-- 일정 등록/수정 모달 -->
<div class="modal" tabindex="-1" id="modal-todo-info">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">일정 정보</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="card">
					<div class="card-body">
						<form class="row g-3">
							<div class="col-sm-4">
								<label class="form-label">구분</label>
								<select class="form-select form-select-sm" name="catNo">
									<c:forEach var="cat" items="${categories }">
										<option value="${cat.no }"> ${cat.name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-8">
								<label class="form-label">제목</label>
								<input type="text" class="form-control form-control-sm" name="title">
							</div>
							<div class="col-sm-12">
								<label class="form-label">하루종일</label>
								<div class="form-check form-switch d-inline float-end">
									<input class="form-check-input" type="checkbox" role="switch" name="allDay" value="Y">
								</div>
							</div>
							<div class="col-sm-6 mb-2">
								<label class="form-label">시작일자</label>
								<input type="date" class="form-control form-control-sm" name="startDate">
							</div>
							<div class="col-sm-6 mb-2">
								<label class="form-label">시작시간</label>
								<input type="time" class="form-control form-control-sm" name="startTime">
							</div>
							<div class="col-sm-6 mb-2">
								<label class="form-label">종료일자</label>
								<input type="date" class="form-control form-control-sm" name="endDate">
							</div>
							<div class="col-sm-6 mb-2">
								<label class="form-label">종료시간</label>
								<input type="time" class="form-control form-control-sm" name="endTime">
							</div>
							<div class="col-sm-12">
								<label class="form-label">내용</label>
								<textarea rows="3" class="form-control" name="description"></textarea>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary" id="btn-add-todo">등록</button>
			</div>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.4/index.global.min.js'></script>
<script src="https://momentjs.com/downloads/moment.min.js" type="text/javascript"></script>
<script type="text/javascript">
	// 모달객체를 생성합니다.
	let todoInfoModal = new bootstrap.Modal("#modal-todo-info");
	// 모달객체가 닫히면 실행할 이벤트 핸들러 함수를 등록한다.
	// 이벤트 핸들러 함수에서는 입력필드의 값을 초기화한다.
	$("#modal-todo-info").on('hidden.bs.modal', function(event) {
		let title = $(":input[name=startDate]").val("");
		let catNo = $("select[name=catNo] option:eq(0)").prop("selected", true);
		let allDay = $(":checkbox[name=allDay]").prop("checked", false);
		let startDate = $(":input[name=startDate]").val("");
		let startTime = $(":input[name=startTime]").val("").prop("disabled", false);
		let endDate = $(":input[name=endDate]").val("");
		let endTime = $(":input[name=endTime]").val("").prop("disabled", false);
		let description = $("textarea[name=description]").val("")
	})
	
	// FullCalendar의 Calender객체를 생성한다.
	let calendar = new FullCalendar.Calendar(document.getElementById("calendar"), {
		locale: 'ko',
		initialView: 'dayGridMonth',
		dateClick: function(info) {
			let clickedDate = info.dateStr;
			let nowTime = moment().format("HH:mm");
			openTodoModal(info.dateStr, nowTime);
		}
	});
	// Calendar를 렌더링한다.
	calendar.render();

	// 일정 등록 모달을 표시한다.
	// 날짜와 시간을 전달받아서 시작일자, 시작시간, 종료일자, 종료시간을 입력한다.
	// 시작일자와 종료일자는 같은 날짜로 하고, 시작시간에 현재 시간을 입력하고, 종료시간은 현재 시간보다 1시간 후로 입력한다.
	function openTodoModal(date, time) {
		let endTime = moment().add('1', 'h').format('HH:mm');
		
		$(":input[name=startDate]").val(date);	
		$(":input[name=endDate]").val(date);
		$(":input[name=startTime]").val(time);
		$(":input[name=endTime]").val(endTime);
		
		todoInfoModal.show();
	}
	
	// 내 일정보기 링크를 클릭했을 때 실행될 이벤트핸들러 함수를 등록한다.
	$("#link-my-todos").click(function(event) {
		event.preventDefault();
	});
	
	// 부서 일정보기 링크를 클릭했을 때 실행될 이벤트핸들러 함수를 등록한다.
	$("#link-dept-todos").click(function(event) {
		event.preventDefault();
	})
	
	// 새 일정 등록 링크를 클릭했을 때 실행될 이벤트핸들러 함수를 등록한다
	// 새 일정 등록 링크를 클리했을 때는 현재 날짜와 시간을 일정 등록 모달에 입력필드에 입력한다.
	$("#link-add-todo").click(function(event) {
		event.preventDefault();
		
		let date = moment().format("YYYY-MM-DD");
		let time = moment().format("HH:mm");
		openTodoModal(date, time);
	});
	
	// 일정 검색하기 링크를 클릭했을 때 실행될 이벤트핸들러 함수를 등록한다.
	$("#link-search-todos").click(function(event) {
		event.preventDefault();
	})
	
	// 하루종일 스위치의 상태가 변경될 때 실행될 이벤트핸들러 함수를 등록한다.
	// 이벤트핸들러 함수에서는 하루종일이 활성화되면 시작시간, 종료시간 입력필드를 비활성화한다.
	$(":checkbox[name=allDay]").change(function() {
		if ($(this).prop('checked')) {
			$(":input[name=startTime]").prop("disabled", true);
			$(":input[name=endTime]").prop("disabled", true);
		} else {
			$(":input[name=startTime]").prop("disabled", false);
			$(":input[name=endTime]").prop("disabled", false);
			
		}
	});
	
	// 일정 등록 모달창의 등록버튼을 클릭했을 때 실행될 이벤트핸들러 함수를 등록한다.
	$("#btn-add-todo").click(function() {
		let title = $(":input[name=startDate]").val();
		let catNo = $("select[name=catNo]").val();
		let allDay = $(":checkbox[name=allDay]:checked").val();
		let startDate = $(":input[name=startDate]").val();
		let startTime = $(":input[name=startTime]").val();
		let endDate = $(":input[name=endDate]").val();
		let endTime = $(":input[name=endTime]").val();
		let description = $("textarea[name=description]").val()
		
		let data = {
			title: title,
			catNo: catNo,
			startDate: startDate,
			endDtate: endDate,
			description: description
		};
		if (allDay) {
			data['allDay'] = 'Y';
		} else {
			data['allDay'] = 'N';
			data['startTime'] = startTime;
			data['endTime'] = endTime;
		}
		
		$.post('/todos/add', JSON.stringify(data))
		 .done(function(todo) {
			 
		 })
		 .fail(function() {
			 
		 });
		
	});
</script>
</body>
</html>