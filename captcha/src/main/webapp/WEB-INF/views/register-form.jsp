<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>샘플 애플리케이션</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><a class="nav-link" href="/">홈</a></li>
		</ul>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
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
			<form class="border bg-light p-3" method="post" action="register">
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
					<label class="form-label">글자를 보이는 대로 입력하세요</label>
					<div>
						<img src="/captcha/image" class="img-thumbnail"/>
						<button class="btn btn-outline-dark py-4">재요청</button>
					</div>
					<input type="text" class="form-control" name="value" />
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
</body>
</html>