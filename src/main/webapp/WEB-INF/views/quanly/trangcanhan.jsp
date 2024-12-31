<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<!-- https://bootsnipp.com/snippets/4Mm5R -->
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="icon" href="${root}/resources/img/logowebsite.png"
	type="image/png" />

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<head>
<title>Hồ sơ quản lý</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}/resources/frofile/frofile.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<div id="layoutSidenav_content"
	style="
        background: url('${root}/resources/frofile/frofile.jpg') no-repeat center center;
        background-size: cover;
        background-repeat: no-repeat;
        height: 100vh;
        width: 100vw;
        margin: 0;
        padding: 0;">

	<div class="container bootstrap snippet">
		<div class="row">
			<div class="col-sm-1">
				<div class="text-center">
					<img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png"
						class="avatar img-circle img-thumbnail" alt="avatar">
				</div>
			</div>
			<div class="col-sm-10">
				<h2>${hoten}</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-9">
				<div class="tab-content">
					<form:form class="form" modelAttribute="nv"
						action="/Highlands/home/profile/edit.htm" rel="stylesheet">
						<div class="form-group">
							<div class="col-md-6">
								<label class="font-weight-bold"><h4>Họ Và Tên</h4></label>
								<form:input path="HOTENNV" type="text" class="form-control"
									id="exampleFormControlInput1" />
								<p style="color: red; font-style: oblique">
									<form:errors path="HOTENNV" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-6">
								<label class="font-weight-bold"><h4>Địa Chỉ</h4></label>
								<form:input path="DIACHI" type="text" class="form-control"
									id="exampleFormControlInput1" />
								<p style="color: red; font-style: oblique">
									<form:errors path="DIACHI" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-6">
								<label class="font-weight-bold"><h4>Email</h4></label>
								<form:input path="EMAIL" type="text" class="form-control"
									id="exampleFormControlInput1" />
								<p style="color: red; font-style: oblique">
									<form:errors path="EMAIL" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-6">
								<label class="font-weight-bold"><h4>Số Điện Thoại</h4></label>
								<form:input path="SDT" type="text" class="form-control"
									id="exampleFormControlInput1" />
								<p style="color: red; font-style: oblique">
									<form:errors path="SDT" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-2">
								<label class="font-weight-bold"><h4>Giới Tính</h4></label><br>
								<form:radiobutton path="GT" value="Nam" label="Nam" name="GT" />
								<br>
								<form:radiobutton path="GT" value="Nữ" label="Nữ" name="GT" />
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-2">
								<label for="start"><h4>Ngày Sinh</h4></label><br>
								<form:input path="NGAYSINH" type="date" id="datePickerId"
									data-date-format="yyyy-MM-dd" name="NGAYSINH" min="1800-01-01"
									max="2024-1-11" />
							</div>
						</div>
						<div>
							<p style="font-size: 20px; margin-left: 450px; margin-top: 100px;"
								class="text-success">${message1}</p>
							<p style="font-size: 20px; margin-left: 450px; margin-top: 100px;"
								class="text-danger">${message0}</p>

						</div>

						<div class="form-group">
							<div class="col-xs-12">
								<br>
								<button name="luu" class="btn btn-lg btn-success" type="submit">
									<i class="glyphicon glyphicon-ok-sign"></i> Lưu
								</button>
								<button class="btn btn-lg" type="reset"
									onclick="location.href='http://localhost:8080/Highlands/home/profile/edit.htm'">
									<i class="glyphicon glyphicon-repeat"></i> Tải Lại Trang
								</button>

							</div>
						</div>
					</form:form>


					<div class="form-group">
						<div class="col-xs-12">
							<c:if test="${tk=='Q01'}">
								<button class="btn btn-lg btn-secondary"
									style="position: absolute; top: -45px; left: 272px;"
									onclick="location.href='http://localhost:8080/Highlands/account/admin.htm'">
									<i class="glyphicon glyphicon-remove-sign"></i> Trở Về
								</button>
							</c:if>
							<c:if test="${tk=='Q02'}">
								<button class="btn btn-lg btn-secondary"
									style="position: absolute; top: -45px; left: 272px;"
									onclick="location.href='http://localhost:8080/Highlands/home/trangchuquanly.htm'">
									<i class="glyphicon glyphicon-remove-sign"></i> Trở Về
								</button>
							</c:if>

							<c:if test="${tk=='Q03'}">
								<button class="btn btn-lg btn-secondary"
									style="position: absolute; top: -45px; left: 272px;"
									onclick="location.href='http://localhost:8080/Highlands/home/trangchunhanvien.htm'">
									<i class="glyphicon glyphicon-remove-sign"></i> Trở Về
								</button>
							</c:if>

						</div>
					</div>

					<hr>
				</div>
			</div>
		</div>
	</div>
</div>
</html>