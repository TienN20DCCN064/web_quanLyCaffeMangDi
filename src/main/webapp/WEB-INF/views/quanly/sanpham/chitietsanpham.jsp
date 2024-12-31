<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<title>Chi tiết sản phẩm</title>
</style>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />
<link rel="icon" href="${root}/resources/img/logowebsite.png" type="image/png" />

<link href="${root}/resources/table/table.css" rel="stylesheet" />

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
						style="font-size: 20px; color: #800000">QUẢN LÝ CHI TIẾT
						SẢN PHẨM</label>
				</div>
				<div class="card-header">
					<form:form class="row g-3" modelAttribute="chitiet"
						action="/Highlands/product/detail/list.htm" rel="stylesheet">
						<c:if test="${btnStatus=='btnAdd'}">
							<div class="col-md-4">
								<label class="font-weight-bold">Sản phẩm</label>
								<form:select path="pk.SANPHAM.MASP" items="${cacsanpham}"
									itemValue="MASP" itemLabel="TENSP" class="form-control"
									id="sanpham" />
							</div>
						</c:if>
						<c:if test="${btnStatus=='btnEdit'}">
							<div class="col-md-4" hidden="true">
								<label class="font-weight-bold">Sản phẩm</label>
								<form:select path="pk.SANPHAM.MASP" items="${cacsanpham}"
									itemValue="MASP" itemLabel="TENSP" class="form-control"
									id="sanpham" />
							</div>
							<div class="col-md-4">
								<label class="font-weight-bold">Sản phẩm</label>
								<form:select path="pk.SANPHAM.MASP" disabled="true"
									items="${cacsanpham}" itemValue="MASP" itemLabel="TENSP"
									class="form-control" id="sanpham" />
							</div>
						</c:if>
						<c:if test="${btnStatus=='btnAdd'}">
							<div class="col-md-4">
								<label class="font-weight-bold">Size</label>
								<form:select path="pk.SIZE.MASIZE" items="${cacsize}"
									itemValue="MASIZE" itemLabel="TENSIZE" class="form-control"
									id="size" />
							</div>
						</c:if>
						<c:if test="${btnStatus=='btnEdit'}">
							<div class="col-md-4" hidden="true">
								<label class="font-weight-bold">Size</label>
								<form:select path="pk.SIZE.MASIZE" items="${cacsize}"
									itemValue="MASIZE" itemLabel="TENSIZE" class="form-control"
									id="size" />
							</div>
							<div class="col-md-4">
								<label class="font-weight-bold">Size</label>
								<form:select path="pk.SIZE.MASIZE" disabled="true"
									items="${cacsize}" itemValue="MASIZE" itemLabel="TENSIZE"
									class="form-control" id="size" />
							</div>
						</c:if>
						<div class="col-md-4">
							<label class="font-weight-bold">Công thức</label><br>
							<form:select path="CONGTHUC.MACT" class="form-control">
								id="exampleFormControlInput1">
								    <form:option value="" label="NULL" />
								<c:forEach var="congthuc" items="${caccongthuc}">
									<form:option value="${congthuc.MACT}">${congthuc.MACT}
										${congthuc.CONGTHUC}</form:option>
								</c:forEach>
							</form:select>
						</div>
						<div class="col-md-4">
							<label class="font-weight-bold">Giá ( đồng )</label>
							<form:input path="GIAHIENTHOI" type="number" step='100'
								placeholder='VNĐ' class="form-control"
								id="exampleFormControlInput1" />
							<p style="color: red; font-style: oblique">
								<form:errors path="GIAHIENTHOI" />
							</p>
						</div>
						<div class='parent' class="col-md-2">
							<hr>
							<div class='child float-left-child' style="margin-left: 2.5em">
								<button name="${btnStatus}" class="btn btn-success">Lưu</button>
								<button
									onclick="location.href='http://localhost:8080/Highlands/product/detail/list.htm'"
									class="btn btn-outline-dark" type="button">Tải Lại
									Trang</button>
								<p5 class="text-success">${message1}</p5>
								<p5 class="text-danger">${message0}</p5>
								<p5 class="text-danger">${message}</p5>
							</div>
						</div>
						<div class="form-inline col-5">
							<hr>
							<span id="result1"></span>
						</div>
					</form:form>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> Bảng chi tiết các sản phẩm
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th style="text-align: center;">Sản phẩm</th>
									<th style="text-align: center;">Size</th>
									<th style="text-align: center;">Công thức</th>
									<th style="text-align: center;">Giá</th>
									<th style="text-align: center;">Chi tiết nguyên liệu</th>
									<th style="text-align: center;">Sửa</th>
									<th style="text-align: center;">Xóa</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th style="text-align: center;">Sản phẩm</th>
									<th style="text-align: center;">Size</th>
									<th style="text-align: center;">Công thức</th>
									<th style="text-align: center;">Giá</th>
									<th style="text-align: center;">Chi tiết nguyên liệu</th>
									<th style="text-align: center;">Sửa</th>
									<th style="text-align: center;">Xóa</th>
								</tr>
							</tfoot>

							<tbody>
								<c:forEach var="ct" items="${dschitiet}">
									<tr>
										<td>${ct.SANPHAM.TENSP}</td>
										<td>${ct.SIZE.TENSIZE}</td>
										<td>${ct.CONGTHUC.MACT} - ${ct.CONGTHUC.CONGTHUC}</td>

										<td><fmt:formatNumber value="${ct.GIAHIENTHOI}"
												pattern="#,###" /> đồng</td>

										<td><a
											href="/Highlands/product/detailMaterial/${ct.pk.SANPHAM.MASP}/${ct.pk.SIZE.MASIZE }/list.htm"
											rel="stylesheet"><img width="31" height="31"
												src="<c:url value='/resources/img/edit.png'/>" /></a></td>
										<td><a
											href="/Highlands/product/detail/list/${ct.pk.SANPHAM.MASP}/${ct.pk.SIZE.MASIZE }.htm?linkEdit"
											rel="stylesheet"><img width="31" height="31"
												src="<c:url value='/resources/img/edit.png'/>" /></a></td>
										<td><a name="btnDelete"
											href="/Highlands/product/detail/list/${ct.pk.SANPHAM.MASP}/${ct.pk.SIZE.MASIZE }.htm?linkDelete"
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/js/scripts.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/js/datatables-simple-demo.js"></script>
</body>
</html>