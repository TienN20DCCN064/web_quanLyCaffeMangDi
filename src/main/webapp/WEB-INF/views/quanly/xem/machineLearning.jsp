<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>Quản lý sản phẩm</title>
</style>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />
<link href="${root}/resources/table/table.css" rel="stylesheet" />
<link href="${root}/resources/table/time.css" rel="stylesheet" />
<link href="${root}/resources/thongBao/thongBao.css" rel="stylesheet" />
<link href="${root}/resources/table/time.css" rel="stylesheet" />
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
						style="font-size: 20px; color: #800000">Gợi ý nhập nguyên
						liệu</label>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> Gợi ý: Nhập nguyên liệu
					</div>

					<div>
						<!-- Form 1: Nhập ngày -->
						<form action="hocMay.htm" method="get"
							style="display: flex; align-items: center; gap: 15px;">
							<!-- Ngày bắt đầu -->
							<label for="startDate">Ngày bắt đầu:</label> <input type="date"
								id="startDate" name="startDate"
								value="${startDate != null ? startDate : ''}" />

							<!-- Ngày kết thúc -->
							<label for="endDate">Ngày kết thúc:</label> <input type="date"
								id="endDate" name="endDate"
								value="${endDate != null ? endDate : ''}" />

							<!-- Nút lọc -->
							<button type="submit">Lọc</button>
						</form>
					</div>

					<!-- Form 2: Nhập số nguyên, căn phải -->
					<div
						style="display: flex; justify-content: flex-end; margin-top: 20px;">
						<form action="hocMay.htm" method="get"
							style="display: flex; align-items: center; gap: 15px;">
							<!-- Nhập số nguyên lớn hơn 0 -->
							<label for="numberInput" style="font-size: 17px;">Dự đoán
								nguyên liệu cần nhập trong :</label>

							<!--  <input type="number" id="numberInput" name="numberInput" placeholder="Nhập số" min="1" required /> -->
							<input type="number" id="numberInput" name="numberInput"
								placeholder="Nhập số" min="1" required value="${nhapSo}" />


							<!-- Nút gửi -->
							<button type="submit">Gửi</button>
						</form>
					</div>


					<div class="row">
						<!-- Cột nội dung cũ -->
						<div class="col-md-5" style="padding-right: 15px;">
							<div class="card-body special-highlight">
								<i class="highlighted">${sanPhamBanChay}</i>
							</div>
							<div class="card-body">
								<i>${soNgayDuDoan}</i>
							</div>
							<div class="card-body">
								<i>${thongbao_soNgay}</i>
							</div>
							<c:choose>
								<c:when test="${not empty list_nl_sap_het}">
									<div class="card-body">
										<i class="red-text">Danh sách sản phẩm sắp hết :
											${list_nl_sap_het}</i>
									</div>
								</c:when>
								<c:otherwise>
									<div class="card-body no-products">
										<i>Không có sản phẩm nào sắp hết trong 5 ngày !</i>
									</div>
								</c:otherwise>
							</c:choose>
						</div>


						<!-- Đường ngăn cách -->
						<div class="col-md-1"
							style="border-left: 5px solid #ccc; height: auto; padding: 20px; margin-left: 130px;"></div>

						<!-- Cột nội dung mới -->
						<div class="col-md-5" style="padding-right: 15px;">
							<div class="card-body special-highlight">
								<i class="highlighted">Dự đoán nguyên liệu cần nhập trong
									${soNgay_nhapThemNL}</i>
							</div>

							<table border="1">
								<tbody>
									<c:forEach var="entry"
										items="${mapDuDoanSoNlCanNhapTrong_soNgay}">
										<tr>
											<c:forEach var="nl" items="${listNL}">
												<c:if test="${nl.MANL == entry.key}">
													<td>${nl.TENNL}</td>
													<td>${entry.value}${nl.DONVI}</td>
												</c:if>
											</c:forEach>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
					</div>









					<div class="card-body">
						<a
							style="font-size: 20px; font-weight: bold; color: #007bff; text-decoration: none; padding: 10px 15px; border: 0px solid #007bff; display: inline-block; transition: all 0.3s ease;">
							Dự đoán ngày hết nguyên liệu </a>

						<table id="datatablesSimple">

							<thead>
								<tr>
									<th class="id-list" style="padding-left: 120px;">Tên
										nguyên liệu</th>
									<th class="id-list" style="padding-left: 130px;">Dự đoán
										ngày hết</th>
									<th class="amount-list" style="padding-left: 490px;">Tồn
										kho</th>

								</tr>
							</thead>
							<tfoot>
								<tr>
									<th class="id-list" style="padding-left: 120px;">Tên
										nguyên liệu</th>
									<th class="id-list" style="padding-left: 130px;">Dự đoán
										ngày hết</th>
									<th class="amount-list" style="padding-left: 490px;">Tồn
										kho</th>

								</tr>
							</tfoot>

							<tbody>
								<c:forEach var="entry" items="${map_nguyenLieu_ngay}">
									<tr>


										<c:forEach var="nl" items="${listNL}">
											<c:if test="${nl.MANL == entry.key}">
												<td>${nl.TENNL}</td>
											</c:if>
										</c:forEach>

										<td style="padding-left: 50px;">${entry.value} ngày</td>


										<c:forEach var="nl" items="${listNL}">
											<c:if test="${nl.MANL == entry.key}">
												<td>${nl.SLTON} ${nl.DONVI}</td>
											</c:if>
										</c:forEach>



										<!--     <td>tien</td> -->
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