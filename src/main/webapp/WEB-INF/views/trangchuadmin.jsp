<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Quản lý tài khoản</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />

<link href="${root}/resources/table/table.css" rel="stylesheet" />
<link rel="icon" href="${root}/resources/img/logowebsite.png" type="image/png" />

<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<a class="navbar-brand ps-3">QUẢN LÝ TÀI KHOẢN</a>
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
				role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
					class="fas fa-user fa-fw"></i></a>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item"
						href="http://localhost:8080/Highlands/home/profile.htm">Hồ sơ
							của bạn</a></li>
					<li><a class="dropdown-item"
						href="http://localhost:8080/Highlands/home/changepassword.htm">Đổi
							Mật Khẩu</a></li>
					<li><hr class="dropdown-divider" /></li>
					<li><a class="dropdown-item"
						href="http://localhost:8080/Highlands/login/logout.htm">Đăng
							Xuất</a></li>
				</ul></li>
		</ul>
	</nav>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4">
				<div class="card-header">
					<label class="font-weight-bold"
						style="font-size: 20px; color: #800000">QUẢN LÝ TÀI KHOẢN
						ad</label>
				</div>

				<div class="card-header">
					<form:form class="row g-3" modelAttribute="tk"
						action="/Highlands/account/list.htm" rel="stylesheet">
						<hr>
						<c:if test="${btnStatus!='btnEdit'}">
							<div class="col-md-2">
								<label class="font-weight-bold">Tên Đăng Nhập</label>
								<form:input path="TENDANGNHAP" type="text" class="form-control"
									id="exampleFormControlInput1" />
								<p style="color: red; font-style: oblique">
									<form:errors path="TENDANGNHAP" />
								</p>
							</div>
							<!-- <div class="col-md-2">
								<label class="font-weight-bold">Mật Khẩu</label> <input
									type="password" name="password" placeholder="Nhập mật khẩu">
							</div> -->
							<div class="col-md-2">
								<label class="font-weight-bold">Quyền Hạn</label><br>
								<form:select path="QUYEN.MAQUYEN" items="${aus}" item=""
									itemValue="MAQUYEN" itemLabel="TENCV" class="form-control"
									aria-label=".form-select-lg example">
								</form:select>
							</div>
							<div class="col-md-2">
								<label class="font-weight-bold">Trạng Thái</label><br>
								<form:radiobutton path="TRANGTHAI" value="False"
									label="Tài Khoản Tạm Ngưng" />
								<br>
								<form:radiobutton path="TRANGTHAI" value="True"
									label="Tài Khoản Hoạt Động" />
							</div>
						</c:if>
						<c:if test="${btnStatus=='btnEdit'}">
							<div class="col-md-2">
								<label class="font-weight-bold">Tên Đăng Nhập</label>
								<form:input path="TENDANGNHAP" type="text" readonly="true"
									class="form-control" id="exampleFormControlInput1" />
								<p style="color: red; font-style: oblique">
									<form:errors path="TENDANGNHAP" />
								</p>
							</div>
							<div class="col-md-2">
								<label class="font-weight-bold">Quyền Hạn</label><br>
								<form:select disabled="true" path="QUYEN.MAQUYEN" items="${aus}"
									item="" itemValue="MAQUYEN" readonly="true" itemLabel="TENCV"
									class="form-control" aria-label=".form-select-lg example">
								</form:select>
							</div>
							<div class="col-md-2">
								<label class="font-weight-bold">Trạng Thái</label><br>
								<form:radiobutton path="TRANGTHAI" value="False"
									label="Tài Khoản Tạm Ngưng" />
								<br>
								<form:radiobutton path="TRANGTHAI" value="True"
									label="Tài Khoản Hoạt Động" />
							</div>
						</c:if>
						<div class='parent' class="col-md-2">
							<div class='child float-left-child' style="margin-left: 2.5em">
								<button name="${btnStatus}" class="btn btn-success">Lưu</button>
								<button
									onclick="location.href='http://localhost:8080/Highlands/account/admin.htm'"
									class="btn btn-light" type="button">Tải Lại Trang</button>

								<p5 class="text-success">${message1}</p5>
								<p5 class="text-danger">${message0}</p5>
							</div>
						</div>
						<div class="form-inline col-5">
							<span id="result1"></span>
						</div>
					</form:form>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> DANH SÁCH TÀI KHOẢN
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th style="text-align: center;">Tên Đăng Nhập</th>
									<th style="text-align: center;">Quyền</th>
									<th style="text-align: center;">Trạng Thái</th>
									<th style="text-align: center;">Sửa</th>
									<th style="text-align: center;">Reset Mật Khẩu</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th style="text-align: center;">Tên Đăng Nhập</th>
									<th style="text-align: center;">Quyền</th>
									<th style="text-align: center;">Trạng Thái</th>
									<th style="text-align: center;">Sửa</th>
									<th style="text-align: center;">Reset Mật Khẩu</th>
								</tr>
							</tfoot>

							<tbody>
								<c:forEach var="tk" items="${dstaikhoan}">
									<tr>
										<td>${tk.TENDANGNHAP}</td>
										<td>${tk.getQUYEN().getTENCV()}</td>
										<c:if test="${tk.TRANGTHAI eq true}">
											<td>Tài Khoản Hoạt Động</td>
										</c:if>
										<c:if test="${tk.TRANGTHAI eq false}">
											<td>Tài Khoản Tạm Ngưng</td>
										</c:if>
										<!-- NÚt Sửa -->

										<td><a
											href="/Highlands/account/list/${tk.TENDANGNHAP}.htm?linkEdit"
											rel="stylesheet"><img width="31" height="31"
												src="<c:url value='/resources/img/edit.png'/>" /></a></td>

										<!-- NÚt Reset Mật Khẩu -->
										<td><a
											href="/Highlands/account/list/${tk.TENDANGNHAP}.htm?linkReset"
											rel="stylesheet" role="button"><img width="31"
												height="31" src="<c:url value='/resources/img/edit.png'/>" /></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
		<footer class="py-4 bg-light mt-auto">
			<div class="container-fluid px-4">
				<div class="d-flex align-items-center justify-content-between small">
					<div class="text-muted">Copyright &copy; Your Website 2024</div>
					<div>
						<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
							&amp; Conditions</a>
					</div>
				</div>
			</div>
		</footer>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/js/scripts.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/js/datatables-simple-demo.js"></script>
</body>
</html>