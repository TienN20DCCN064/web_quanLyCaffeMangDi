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
<title>Quản lý nhân viên</title>
<style>
label {
	display: block;
	font: 1rem 'Fira Sans', sans-serif;
}

input, label {
	margin: 0.4rem 0;
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />
<link href="${root}/resources/table/table.css" rel="stylesheet"/>
<link href="${root}/resources/table/time.css" rel="stylesheet"/>
<link rel="icon" href="${root}/resources/img/logowebsite.png" type="image/png" />

<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">

	<%@ include file="/WEB-INF/common/quanly/header.jsp"%>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4">
				<div class="card-header">
					<label class="font-weight-bold"
						style="font-size: 20px; color: #800000">QUẢN LÝ NHÂN VIÊN</label>
				</div>
				<div class="card-header">
					<form:form class="row mb-3" modelAttribute="nv"
						action="/Highlands/employee/list.htm" rel="stylesheet">
						<c:if test="${btnStatus=='btnAdd'}">
							<div class="col-md-2">
								<label class="font-weight-bold">Mã Nhân Viên</label>
								<form:input path="MANV" type="text" class="form-control"
									placeholder="Trường bắt buộc" id="manv" />
								<p style="color: red; font-style: oblique">
									<form:errors path="MANV" />
								</p>
							</div>
						</c:if>
						<c:if test="${btnStatus=='btnEdit'}">
							<div class="col-md-2" hidden="true">
								<label class="font-weight-bold">Mã Nhân Viên</label>
								<form:input path="MANV" type="text" class="form-control"
									placeholder="Trường bắt buộc" id="manv" />
								<p style="color: red; font-style: oblique">
									<form:errors path="MANV" />
								</p>
							</div>
							<div class="col-md-2">
								<label class="font-weight-bold">Mã Nhân Viên</label>
								<form:input path="MANV" type="text" class="form-control"
									placeholder="Trường bắt buộc" disabled="true" id="manv" />
								<p style="color: red; font-style: oblique">
									<form:errors path="MANV" />
								</p>
							</div>
						</c:if>
						<div class="col-md-2">
							<label class="font-weight-bold">Họ Và Tên </label>
							<form:input path="HOTENNV" type="text" class="form-control"
								placeholder="Trường bắt buộc" id="exampleFormControlInput1" />
							<p style="color: red; font-style: oblique">
								<form:errors path="HOTENNV" />
							</p>
						</div>
						
						<div class="col-md-2">
							<label class="font-weight-bold">Giới Tính</label><br>
							<form:radiobutton path="GT" value="Nam" label="Nam" name="GT" />
							<br>
							<form:radiobutton path="GT" value="Nữ" label="Nữ" name="GT" />
						</div>
						<div class="col-md-2">
							<label for="start">Ngày Sinh</label>
							<form:input path="NGAYSINH" type="date" id="datePickerId"
								data-date-format="yyyy-MM-dd" name="NGAYSINH" min="1800-01-01"
								value="2000-01-01" max="2020-12-31" />
						</div>
						<div class="col-md-4">
							<label class="font-weight-bold">Địa Chỉ</label>
							<form:input path="DIACHI" type="text" class="form-control"
								placeholder="Trường bắt buộc" id="exampleFormControlInput1" />
							<p style="color: red; font-style: oblique">
								<form:errors path="DIACHI" />
							</p>
						</div>
						<div class="col-md-4">
							<label class="font-weight-bold">Email </label>
							<form:input path="EMAIL" type="text" class="form-control"
								placeholder="Trường bắt buộc" id="exampleFormControlInput1" />
							<p style="color: red; font-style: oblique">
								<form:errors path="EMAIL" />
							</p>
						</div>
						
						
						<div class="col-md-2">
							<label class="font-weight-bold">Số Điện Thoại </label>
							<form:input path="SDT" type="text" class="form-control"
								placeholder="Trường bắt buộc" id="exampleFormControlInput1" />
							<p style="color: red; font-style: oblique">
								<form:errors path="SDT" />
							</p>
						</div>
						<div class='parent' class="col-md-2">
							<div class='child float-left-child' style="margin-left: 2.5em">
								<button name="${btnStatus}" class="btn btn-success">Lưu</button>
								<button
									onclick="location.href='http://localhost:8080/Highlands/employee/list.htm'"
									class="btn btn-outline-dark" type="button">Tải Lại
									Trang</button>

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
						<i class="fas fa-table me-1"></i> DANH SÁCH NHÂN VIÊN
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
						<thead>
						    <tr>
						        <th style="text-align: center;">Mã Nhân Viên</th>
						        <th style="text-align: center;">Họ Tên</th>
						        <th style="text-align: center;">Giới Tính</th>
						        <th style="text-align: center;">Ngày Sinh</th>
						        <th style="text-align: center;">Địa Chỉ</th>
						        <th style="text-align: center;">Email</th>
						        <th style="text-align: center;">Số Điện Thoại</th>
						        <th style="text-align: center;">Sửa</th>
						        <th style="text-align: center;">Xóa</th>
						    </tr>
						</thead>

							<tfoot>
							    <tr>
							        <th style="text-align: center;">Mã Nhân Viên</th>
							        <th style="text-align: center;">Họ Tên</th>
							        <th style="text-align: center;">Giới Tính</th>
							        <th style="text-align: center;">Ngày Sinh</th>
							        <th style="text-align: center;">Địa Chỉ</th>
							        <th style="text-align: center;">Email</th>
							        <th style="text-align: center;">Số Điện Thoại</th>
							        <th style="text-align: center;">Sửa</th>
							        <th style="text-align: center;">Xóa</th>
							    </tr>
							</tfoot>
							<tbody>
								<c:forEach var="nv" items="${dsnhanvien}">
									<tr>
										<td>${nv.MANV}</td>
										<td>${nv.HOTENNV}</td>
										<td>${nv.GT}</td>
										<td>${nv.NGAYSINH}</td>
										<td>${nv.DIACHI}</td>
										<td>${nv.EMAIL}</td>
										<td>${nv.SDT}</td>
										<td><a
											href="/Highlands/employee/list/${nv.MANV}.htm?linkEdit"
											rel="stylesheet"><img width="31" height="31"
												src="<c:url value='/resources/img/edit.png'/>" /></a></td>
										<td><a name="btnDelete" onclick="confirmDelete()"
											href="/Highlands/employee/list/${nv.MANV}.htm?linkDelete"
											rel="stylesheet" role="button"><img width="31"
												height="31" src="<c:url value='/resources/img/delete.png'/>" /></a>
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
	<script>
		function confirmDelete() {
			if (window.confirm("Nhân viên hiện đang được cấp 1 tài khoản, xóa nhân viên sẽ dẫn đến xóa tài khoản. Bạn có muốn xóa?")) {
				// perform the delete action
			}
		}
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/js/scripts.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/js/datatables-simple-demo.js"></script>
</body>
</html>