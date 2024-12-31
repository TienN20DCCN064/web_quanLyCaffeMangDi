<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<head>
<meta charset="ISO-8859-1">
<title>Quên Mật Khẩu</title>
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
<link rel="icon" href="${root}/resources/img/logowebsite.png" type="image/png" />

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="${root}/resources/account/css/bootstrap.min.css">

<!-- Style -->
<link rel="stylesheet" href="${root}/resources/account/css/style.css">

<title>Quên Mật Khẩu</title>
</head>
<body>
	<div class="half">
			
		<div class="contents order-2 order-md-1" style="background-image: url('${root}/resources/account/images/bg_1.jpg');">
			<div class="container"  style="margin-left: 800px;width: calc(100% + 100px);">
				<div class="row align-items-center justify-content-center">
					<div class="col-md-6">
						<div class="form-block">
							<div class="text-center mb-5">
								<h3>
									<strong>QUÊN MẬT KHẨU</strong>
								</h3>
							</div>
							<form action="http://localhost:8080/Highlands/login/reset.htm"
								method="post">
								<div class="form-group first">
									<label for="tendangnhap">Tên Đăng Nhập</label> <input type="text"
										class="form-control" placeholder="nhập tên đăng nhập của bạn"
										name="tendangnhap">
								</div>
								<div class="form-group first">
									<label for="email">Email</label> <input type="text"
										class="form-control" placeholder="email_của_bạn@gmail.com"
										name="email">
								</div>
								<div
									class="d-flex align-items-center justify-content-between mt-4 mb-0">
									<a class="small"
										href="http://localhost:8080/Highlands/home/index.htm">Quay
										Lại Trang Đăng Nhập</a>
									<input type="submit" value="GỬI EMAIL CHO TÔI"
										class="btn btn-block btn-primary">
								</div>
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