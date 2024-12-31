<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<link rel="icon" href="${root}/resources/img/logowebsite.png"
	type="image/png" />

<link rel="stylesheet"
	href="${root}/resources/account/fonts/icomoon/style.css">

<link rel="stylesheet"
	href="${root}/resources/account/css/owl.carousel.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="${root}/resources/account/css/bootstrap.min.css">

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
							<div class="text-center mb-5">
								<h3>
									Đổi mật khẩu tài khoản <strong>Highlands</strong>
								</h3>
							</div>
							<form:form modelAttribute="tk"
								action="/Highlands/home/password.htm" rel="stylesheet">
								<div class="form-group last mb-3">
									<p5 class="text-success">${message1}</p5>
									<p5 class="text-danger">${message0}</p5>
								</div>
								<div class="form-group first">
									<a class="small"
										href="http://localhost:8080/Highlands/home/index.htm">Quay
										Lại Trang Đăng Nhập</a>
								</div>
							</form:form>
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