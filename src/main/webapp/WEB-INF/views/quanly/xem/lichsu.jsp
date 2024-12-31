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
<title>Lịch sử thay đổi giá</title>

<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />
<link rel="icon" href="${root}/resources/img/logowebsite.png"
	type="image/png" />

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
						style="font-size: 20px; color: #800000">LỊCH SỬ THAY ĐỔI GIÁ</label>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> LỊCH SỬ THAY ĐỔI GIÁ
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th style="text-align: center;">Thời gian</th>
									<th style="text-align: center;">Tên sản phẩm</th>
									<th style="text-align: center;">Size</th>

									<th style="text-align: center;">Giá cũ</th>
									<th style="text-align: center;">Giá thay đổi</th>
									<th style="text-align: center;">Giá hiện tại</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th style="text-align: center;">Thời gian</th>
									<th style="text-align: center;">Tên sản phẩm</th>
									<th style="text-align: center;">Size</th>

									<th style="text-align: center;">Giá cũ</th>
									<th style="text-align: center;">Giá thay đổi</th>
									<th style="text-align: center;">Giá hiện tại</th>

								</tr>
							</tfoot>

							<tbody>
								<c:forEach var="ls" items="${dslichsu}">
									<tr>
										<td>${ls.THOIGIAN}</td>
										<td>${ls.CT_SANPHAM.SANPHAM.TENSP}</td>
										<td>${ls.CT_SANPHAM.SIZE.TENSIZE}</td>
										<td><fmt:formatNumber value="${ls.GIACU}" pattern="#,###" />
											đồng</td>
										<td><fmt:formatNumber value="${ls.GIATHAYDOI}"
												pattern="#,###" /> đồng</td>
										<c:forEach var="ctsp" items="${dsCTSP}">
											<c:if test="${ctsp == ls.CT_SANPHAM}">
												
												<td><fmt:formatNumber value="${ctsp.GIAHIENTHOI}" pattern="#,###" /> đồng	</td>
											</c:if>

										</c:forEach>
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