

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
<title>Chi tiết hóa đơn</title>
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
						style="font-size: 20px; color: #800000">Chi tiết hóa dơn</label>
				</div>

				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> DANH SÁCH CHI TIẾT HÓA ĐƠN
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th style="text-align: center;">IDHD</th>
									<th style="text-align: center;">MÃ sp</th>
									<th style="text-align: center;">MÃ size</th>
									<th style="text-align: center;">Đơn giá</th>
									<th style="text-align: center;">Số lượng</th>
									<th style="text-align: center;">Mã km</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th style="text-align: center;">IDHD</th>
									<th style="text-align: center;">MÃ sp</th>
									<th style="text-align: center;">MÃ size</th>
									<th style="text-align: center;">Đơn giá</th>
									<th style="text-align: center;">Số lượng</th>
									<th style="text-align: center;">Mã km</th>
								</tr>
							</tfoot>

							<tbody>
								<c:forEach var="hoadon" items="${listCTHD}">
									<tr class="each-bill">
										<td>${hoadon.pk.HOADON.ID}</td>
										<!-- Lấy IDHD từ đối tượng HoaDon -->
										<td>${hoadon.pk.SANPHAM.MASP}</td>
										<!-- Lấy MASP từ đối tượng SanPham -->
										<td>${hoadon.pk.SIZE.MASIZE}</td>
										<!-- Lấy MASIZE từ đối tượng Size -->
										<c:forEach var="ctSP" items="${listCTSanPham}">
											<c:if
												test="${ctSP.SANPHAM.MASP == hoadon.pk.SANPHAM.MASP && ctSP.SIZE.MASIZE == hoadon.pk.SIZE.MASIZE}">
												<!-- Hiển thị giá nếu điều kiện đúng -->
												<td><fmt:formatNumber value="${ctSP.GIAHIENTHOI}"
														pattern="#,###" /> đồng</td>
											</c:if>
										</c:forEach>

										<td>${hoadon.SOLUONG}</td>
										<!-- Lấy SOLUONG -->
										<c:if
											test="${hoadon != null && hoadon.pk != null && hoadon.pk.MAKM != null}">
											<td>${hoadon.pk.MAKM.MAKM}</td>
											<!-- Lấy MAKM từ đối tượng KhuyenMai -->
										</c:if>

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