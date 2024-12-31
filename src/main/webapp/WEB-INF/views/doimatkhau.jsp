<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<head>
<meta charset="ISO-8859-1">
<title>ĐỔI MẬT KHẨU</title>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="${root}/resources/account/fonts/icomoon/style.css">

<link rel="stylesheet"
	href="${root}/resources/account/css/owl.carousel.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="${root}/resources/account/css/bootstrap.min.css">
<link rel="icon" href="${root}/resources/img/logowebsite.png"
	type="image/png" />

<!-- Style -->
<link rel="stylesheet" href="${root}/resources/account/css/style.css">

<title>Đổi Mật Khẩu</title>
</head>
<body>
	<div class="half">


		<div class="contents order-2 order-md-1"
			style="background-image: url('${root}/resources/account/images/bg_1.jpg');">
			<div class="container">
				<div class="row align-items-center justify-content-center">
					<div class="col-md-6">
						<div class="form-block">
							<div class="text-center mb-5" style="font-style: italic;" >
								<h3>Đổi Mật Khẩu</h3>
							</div>
							<form action="http://localhost:8080/Highlands/home/password.htm"
								method="post">
								<div class="form-group last mb-3">
									<label for="oldpassword">Nhập Mật Khẩu Cũ</label> <input
										type="password" class="form-control"
										placeholder="mật khẩu cũ của bạn" name="oldpassword">
								</div>
								<div class="form-group last mb-3">
									<label for="newpassword">Nhập Mật Khẩu Mới</label> <input
										type="password" class="form-control"
										placeholder="mật khẩu mới của bạn" name="newpassword1">
								</div>
								<div class="form-group last mb-3">
									<label for="newpassword">Nhập Lại Mật Khẩu</label> <input
										type="password" class="form-control"
										placeholder=" Nhập lại mật khẩu mới của bạn"
										name="newpassword2">
								</div>

								<input type="submit" value="XÁC NHẬN"
									class="btn btn-block btn-primary">

								<div class="form-group last mb-3">
									<p5 class="text-success">${message1}</p5>
									<p5 class="text-danger">${message0}</p5>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${root}/resources/account/js/jquery-3.3.1.min.js"></script>
	<script src="${root}/resources/account/js/popper.min.js"></script>
	<script src="${root}/resources/account/js/bootstrap.min.js"></script>
	<script src="${root}/resources/account/js/main.js"></script>
</body>
</html>