<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<title>샘플 애플리케이션</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><a class="nav-link" href="/">홈</a></li>
		</ul>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link active" href="/register">회원가입</a></li>
		</ul>
	</div>
</nav>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1>회원가입</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-6">
			<p>회원정보를 입력하세요</p>
			<form id="form-register" class="border bg-light p-3" method="post" action="register">
				<input type="hidden" id="captcha-key" />
				<div class="mb-2">
					<label class="form-label">이메일</label>
					<input type="text" class="form-control" name="email" />
				</div>
				<div class="mb-2">
					<label class="form-label">비밀번호</label>
					<input type="password" class="form-control" name="password" />
				</div>
				<div class="mb-2">
					<label class="form-label">이름</label>
					<input type="text" class="form-control" name="name" />
				</div>
				<div class="mb-2">
					<div class="card">
						<div class="card-header">보안요구 사항 <span id="captcha-message" class="float-end">(재시도 횟수가 <span class="fw-bold text-danger" id="captcha-countdown">3</span>회 남았습니다.)</span></div>
						<div class="card-body">
							<div class="row">
								<div class="col-5">
									<span id="captcha-image-box" class="border-1 p-1"></span>						
								</div>
								<div class="col-6">
									<p class="small">
										<span>글자를 보이는 대로 입력하세요</span>
										<button type="button" class="btn btn-success btn-sm float-end" id="btn-reload-captcha"><i class="bi bi-arrow-clockwise"></i></button>
									</p>
									<input type="text" class="form-control form-control-sm d-inline-block w-50" id="captcha-value" />	
									<button type="button" class="btn btn-outline-success btn-sm" id="btn-confirm-captcha">확인</button>								
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="text-end">
					<button type="submit" class="btn btn-primary">회원가입</button>
				</div>
			</form>			
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript">
$(function() {

	// 캡차이미지가 표시되는 span 엘리먼트
	const $captchaImageBox = $("#captcha-image-box");
	// 캡차이미지 재시도 횟수다.
	let captchaCountdown = 3;
	// 캡차 입력값 검증여부다.
	let captchaConfirmed = false;
	
	// 회원가입 폼에서 submit 이벤트가 발생하면 실행되는 이벤트 핸들러 함수를 등록한다.
	$("#form-register").submit(function() {
		let email = $("#form-register :input[name=email]").val();
		let password = $("#form-register :input[name=password]").val();
		let name = $("#form-register :input[name=name]").val();
		
		if (email === '') {
			alert("이메일은 필수입력값입니다.");
			return false;
		}
		if (password === '') {
			alert('비밀번호는 필수입력값입니다.');
			return false;
		}
		if (name === '') {
			alert('이름은 필수입력값입니다.');
			return false;
		}
		// 캡차 이미지 입력값 검증여부를 확인한다.
		if (!captchaConfirmed) {
			alert("보안요구사항 인증이 완료되지 않았습니다");
			return false;
		}
		return true;
	});
	
	// 캡차 이미지 리로딩 버튼에서 클릭 이벤트가 발생했을 때 실행되는 이벤트 핸들러 함수를 등록한다.
	$("#btn-reload-captcha").click(function() {
		// 캡차 이미지를 리로딩하는 함수를 호출한다.
		reloadCaptchaImage();
	})
	
	// 캡차 입력값 확인 버튼에서 클릭 이벤트가 발생했을 때 실행되는 이벤트 핸들러 함수를 등록한다.
	$("#btn-confirm-captcha").click(function() {
		// 캡차 이미지 키값을 읽어온다.
		let captchaKey = $("#captcha-key").val();
		// 사용자가 입력한 캡차 이미지 입력값을 읽어온다.
		let captchaValue = $("#captcha-value").val();
		// 사용자가 입력한 캡차 이미지 입력값을 비어 있으면 경고창을 표시한다.
		if ($.trim(captchaValue) === '') {
			alert("입력된 값이 없습니다.");
			return false;
		}
		
		// 캡차 이미지 키값과 입력값을 서버로 보내서 검증한다.
		// response -> {result:불린값, response:소요시간}
		// response.result는 검증통과여부를 boolean 값으로 제공한다.
		$.post("/captcha/confirm", {key:captchaKey, value:captchaValue})
		.done(function(response) {
			if (!response.result) {
				// 검증을 통과하지 못한 경우 경고창을 표시하고, 캡차이미지를 리로딩시킨다.
				alert("값이 일치하지 않습니다.");
				reloadCaptchaImage();
			} else {
				// 검증을 통과한 경우
				// 캡차 입력값 검증여부를 true로 설정한다.
				captchaConfirmed = true;

				// 인증완료 메세지를 표시하고, 캡차이미지 리로딩버튼과 확인 버튼을 비활성화 시킨다.
				$("#captcha-message").text("인증완료").addClass("text-success fw-bold");
				$("#btn-confirm-captcha").addClass("disabled");
				$("#btn-reload-captcha").addClass("disabled");
				// 인증완료 경고창을 표시한다.
				alert("인증되었습니다.");
			}
		})
	});
	
	// 캡차 이미지를 리로딩하는 함수다.
	function reloadCaptchaImage() {
		// 캡차 이미지 재로딩 횟수를 체크하고, 횟수가 초과된 경우 경고창을 출력하고, 홈 페이지로 이동시킨다.
		if (captchaCountdown === 0) {
			alert('보안요구사항 입력 횟수를 초과하였습니다');	
			location.href="/"
			return false;
		}
		// 갭차 이미지 재로딩 횟수가 남아 있는 경우
		// 캡차 이미지 재로딩 횟수를 1감소시킨다.
		captchaCountdown--;
		$("#captcha-countdown").text(captchaCountdown);
		// 갭차 이미지를 표시하는 함수를 호출한다.
		displayCaptchaImage();
		// 사용자 입력값을 초기화한다.
		$("#captcha-value").val("");
	}
	
	// 캡차 이미지를 표시하는 함수다.
	function displayCaptchaImage() {
		// 서버로부터 갭차 이미지 키정보을 받아온다.
		$.get("/captcha/key").done(function(response) {
			// response -> {key:xxxxxxx}
			// 발급받은 캡차 이미지 키값을 히든 필드에 입력한다.
			$("#captcha-key").val(response.key);
			// 발급받은 캡차 이미지 키값으로 이미지를 표시하는 img 태그를 생성한다.
			// "/captcha/image?key=발급받은 캡차이미지 키값"는 캡차이미지를 요청하는 URL이다.
			let img = `<img src="/captcha/image?key=\${response.key}" />`;
			// img 태그를 화면에 추가한다.
			$captchaImageBox.empty().append(img);
		})
	}
	// 회원가입 화면 로딩시 캡차 이미지를 표시하는 함수를 호출한다.
	displayCaptchaImage();	
})
</script>
</body>
</html>