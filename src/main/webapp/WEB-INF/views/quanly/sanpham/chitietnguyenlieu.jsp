<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>Chi tiết nguyên liệu của sản phẩm</title>
</style>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />

<link href="${root}/resources/table/table.css" rel="stylesheet"/>
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
						style="font-size: 20px; color: #800000">QUẢN LÝ CHI TIẾT
						NGUYÊN LIỆU CỦA SẢN PHẨM</label>
				</div>
				<div class="card-header">
					<form:form class="row g-3" modelAttribute="chitiet"
						action="/Highlands/product/detailMaterial/list.htm"
						rel="stylesheet">
						
						<c:if test="${btnStatus=='btnAdd'}">
							<div class="col-md-4">
							<label class="font-weight-bold">Sản phẩm Size</label><br> 
							<select name="ct_sanpham" class="form-control">
								<c:forEach var="ct" items="${cacchitietsanpham}">
									<option value="${ct.pk}">${ct.pk.SANPHAM.TENSP}
										${ct.pk.SIZE.TENSIZE}</option>
								</c:forEach>
							</select><br>
							
						</div>
						</c:if>
						<c:if test="${btnStatus=='btnEdit'}">
							<div class="col-md-4" hidden="true">
							<label class="font-weight-bold">Sản phẩm Size</label><br> 
							<select name="ct_sanpham" class="form-control">
								<c:forEach var="ct" items="${cacchitietsanpham}">
									<option value="${ct.pk}">${ct.pk.SANPHAM.TENSP}
										${ct.pk.SIZE.TENSIZE}</option>
								</c:forEach>
							</select><br>
							
						</div>
							<div class="col-md-4">
								<label class="font-weight-bold">Sản phẩm Size</label>
								<p class="form-control">"${chitiet.pk.CT_SANPHAM.pk.SANPHAM.TENSP}"
								"${chitiet.pk.CT_SANPHAM.pk.SIZE.TENSIZE}"</p>
								
							</div>
						</c:if>
						<form:hidden path="pk.CT_SANPHAM.pk.SANPHAM.MASP" />
						<form:hidden path="pk.CT_SANPHAM.pk.SIZE.MASIZE" />
						<div class="col-md-4">
							<label class="font-weight-bold">Nguyên liệu</label><br>
							<form:select path="pk.NGUYENLIEU.MANL" items="${cacnguyenlieu}"
								itemValue="MANL" itemLabel="TENNL" class="form-control"
								id="exampleFormControlInput1" />
						</div>
						<div class="col-md-4">
							<label class="font-weight-bold">Số lượng</label>
							<form:input path="SOLUONG" type="number" step='0.01'
								placeholder='0.00' class="form-control"
								id="exampleFormControlInput1" />
							<p style="color: red; font-style: oblique">
								<form:errors path="SOLUONG" />
							</p>
						</div>
						<div class='parent' class="col-md-2">
							<hr>
							<div class='child float-left-child' style="margin-left: 2.5em">
								<button name="${btnStatus}" class="btn btn-success">Lưu</button>
								<button
									onclick="location.href='http://localhost:8080/Highlands/product/detailMaterial/list.htm'"
									class="btn btn-outline-dark" type="button">Tải Lại
									Trang</button>
								<p5 class="text-success">${message1}</p5>
								<p5 class="text-danger">${message0}</p5>
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
						<i class="fas fa-table me-1"></i> Bảng chi tiết nguyên liệu của
						các sản phẩm
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
    <tr>
        <th style="text-align: center;">Sản phẩm</th>
        <th style="text-align: center;">Size</th>
        <th style="text-align: center;">Nguyên liệu</th>
        <th style="text-align: center;">Số lượng</th>
        <th style="text-align: center;">Sửa</th>
        <th style="text-align: center;">Xóa</th>
    </tr>
</thead>
<tfoot>
    <tr>
        <th style="text-align: center;">Sản phẩm</th>
        <th style="text-align: center;">Size</th>
        <th style="text-align: center;">Nguyên liệu</th>
        <th style="text-align: center;">Số lượng</th>
        <th style="text-align: center;">Sửa</th>
        <th style="text-align: center;">Xóa</th>
    </tr>
</tfoot>

							<tbody>
								<c:forEach var="chitiet" items="${dschitiet}">
									<tr>
										<td>${chitiet.CT_SANPHAM.SANPHAM.TENSP}</td>
										<td>${chitiet.CT_SANPHAM.SIZE.TENSIZE}</td>
										<td>${chitiet.NGUYENLIEU.TENNL}</td>
										<td><fmt:formatNumber value="${chitiet.SOLUONG}" pattern="#,###" /> ${chitiet.NGUYENLIEU.DONVI}</td>
									
										<td><a
											href="/Highlands/product/detailMaterial/list/${chitiet.CT_SANPHAM.SANPHAM.MASP}/${chitiet.CT_SANPHAM.SIZE.MASIZE}/${ chitiet.NGUYENLIEU.MANL }.htm?linkEdit"
											rel="stylesheet"><img width="31" height="31"
												src="<c:url value='/resources/img/edit.png'/>" /></a></td>
										<td><a name="btnDelete"
											href="/Highlands/product/detailMaterial/list/${chitiet.CT_SANPHAM.SANPHAM.MASP}/${chitiet.CT_SANPHAM.SIZE.MASIZE}/${ chitiet.NGUYENLIEU.MANL }.htm?linkDelete"
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
</html>