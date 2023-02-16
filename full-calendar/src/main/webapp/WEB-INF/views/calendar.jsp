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
				<a href="" class="list-group-item">내 일정 보기</a>
				<a href="" class="list-group-item">부서일정 보기</a>
				<a href="" class="list-group-item">새 일정등록</a>
				<a href="" class="list-group-item">일정 검색하기</a>
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
							<div class="col-sm-12">
								<label class="form-label">제목</label>
								<input type="text" class="form-control form-control-sm" name="title">
							</div>
							<div class="col-sm-12">
								<label class="form-label">구분</label>
								<input type="text" class="form-control form-control-sm" name="title">
							</div>
							<div class="col-sm-12">
								<label class="form-label">하루종일</label>
								<div class="form-check form-switch d-inline float-end">
									<input class="form-check-input" type="checkbox" role="switch">
								</div>
							</div>
							<div class="col-sm-6 mb-2">
								<label class="form-label">시작일자</label>
								<input type="date" class="form-control form-control-sm" name="title">
							</div>
							<div class="col-sm-6 mb-2">
								<label class="form-label">시작시간</label>
								<input type="date" class="form-control form-control-sm" name="title">
							</div>
							<div class="col-sm-6 mb-2">
								<label class="form-label">종료일자</label>
								<input type="date" class="form-control form-control-sm" name="title">
							</div>
							<div class="col-sm-6 mb-2">
								<label class="form-label">종료시간</label>
								<input type="date" class="form-control form-control-sm" name="title">
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary">등록</button>
			</div>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.4/index.global.min.js'></script>
<script type="text/javascript">
	let todoInfoModal = new bootstrap.Modal("#modal-todo-info");
	
	let calendar = new FullCalendar.Calendar(document.getElementById("calendar"), {
		locale: 'ko',
		initialView: 'dayGridMonth',
		dateClick: function(info) {
			todoInfoModal.show();
		}
	});
	calendar.render();
</script>
</body>
</html>