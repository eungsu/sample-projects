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
				<input type="hidden" name="captchaKey" />
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
						<div class="card-header">보안요구 사항 <span class="float-end">(재시도 횟수가 <span class="fw-bold text-danger" id="captcha-countdown">3</span>회 남았습니다.)</span></div>
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
									<input type="text" class="form-control d-inline-block w-50" name="captchaValue" />	
									<button type="button" class="btn btn-primary btn-sm" id="btn-confirm-captcha">확인</button>								
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="text-end">
					<button type="submit" class="btn btn-primary btn-sm">회원가입</button>
				</div>
			</form>			
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	
	const $captchaImageBox = $("#captcha-image-box");
	let captchaCountdown = 3;
	
	$("#btn-reload-captcha").click(function() {
		if (captchaCountdown === 0) {
			alert('보안요구사항 입력 횟수를 초과하였습니다');	
			location.href="/"
			return false;
		}
		captchaCountdown--;
		setCaptchaImage();	
		$("#captcha-countdown").text(captchaCountdown);
	})
	
	$("#btn-confirm-captcha").click(function() {
		
	})
	
	function setCaptchaImage() {
		$.get("/captcha/key").done(function(key) {
			$("#form-register :input[name=captchaKey]").val(key);
	
			let img = `<img src="/captcha/image?key=\${key}" />`;
			$captchaImageBox.empty().append(img);
		})
	}
	setCaptchaImage();
	
})
</script>
</body>
</html>