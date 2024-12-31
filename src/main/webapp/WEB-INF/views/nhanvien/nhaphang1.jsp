<%-- <%@ page pageEncoding="utf-8"%>
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
<title>Quản ly nhập hàng</title>
<style>
label {
	display: block;
	font: 1rem 'Fira Sans', sans-serif;
}

input, label {
	margin: 0.4rem 0;
}
</style>
<link rel="icon" href="${root}/resources/img/logowebsite.png" type="image/png" />

<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />
<link href="${root}/resources/table/table.css" rel="stylesheet"/>
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">

	<%@ include file="/WEB-INF/common/nhanvien/header.jsp"%>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4">
				<div class="card-header">
					<label class="font-weight-bold"
						style="font-size: 20px; color: #800000">THÔNG TIN NHẬP
						HÀNG</label>
				</div>
				<div class="card-header">
					<form class="row g-3"
						action="http://localhost:8080/Highlands/import/defineId.htm"
						method="post">
						<div class="form-group last mb-3">
							<label style="font-size: 18px; color: black; font-weight: bold;">Nhân
								Viên Nhập: </label> <label
								style="font-size: 18px; color: darkred; font-weight: bold;">${nv}</label>
						</div>

						<div class="col-md-2">
							<label for="manhaphang">Mã Nhập Hàng</label> <input type="text"
								class="form-control" placeholder="mã phiếu nhập"
								name="manhaphang">
						</div>

						<div class="col-md-2">
							<label>Ngày Nhập</label> <input class="form-control" type="text"
								value="${thoigiannhaphang}" disabled>
						</div>
						<div class='parent' class="col-md-2">
							<div class='child float-left-child' style="margin-left: 2.5em">
								<button name="${btnStatus}" class="btn btn-success">Lưu</button>
								<button
									onclick="location.href='http://localhost:8080/Highlands/import/defineId.htm'"
									class="btn btn-outline-dark" type="button">Tải Lại
									Trang</button>

								<p5 class="text-success">${message1}</p5>
								<p5 class="text-danger">${message0}</p5>
							</div>
						</div>
						<div class="form-inline col-5">
							<span id="result1"></span>
						</div>
					</form>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> CHI TIẾT PHIẾU NHẬP
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>Mã Nhập Hàng</th>
									<th>Họ Tên</th>
									<th>Ngày Nhập</th>
									<th>Xóa</th>
									<!-- <th>Nguyên Liệu Nhập</th> -->
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>Mã Nhập Hàng</th>
									<th>Họ Tên</th>
									<th>Ngày Nhập</th>
									<th>Xóa</th>
								</tr>
							</tfoot>
							<tbody>
								<td>${nv}</td>
								<c:forEach var="pn" items="${dspn}">
									<tr>
										<td>${pn.MANH}</td>
										<td>${pn.nhanvien.HO} ${pn.nhanvien.TEN}</td>
										<td>${pn.NGAYNHAP}</td>
										<td><a
											href="/Highlands/import/defineId/${pn.MANH}.htm?linkDelete"
											rel="stylesheet"><img width="31" height="31"
												src="<c:url value='/resources/img/delete.png'/>" /></a></td>
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
					<div class="text-muted">Copyright &copy; Your Website 2022</div>
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
</html> --%>