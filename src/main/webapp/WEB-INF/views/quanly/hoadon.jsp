<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
<title>Quản lý hóa đơn</title>

<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />
<link href="${root}/resources/table/table.css" rel="stylesheet" />
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
						style="font-size: 20px; color: #800000">QUẢN LÝ HÓA ĐƠN</label>
				</div>


				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> DANH SÁCH HÓA ĐƠN
					</div>
					<div>
						<form action="list.htm" method="get">
							<label for="startDate">Ngày bắt đầu:</label> <input type="date"
								id="startDate" name="startDate"
								value="${startDate != null ? startDate : ''}" /> <label
								for="endDate">Ngày kết thúc:</label> <input type="date"
								id="endDate" name="endDate"
								value="${endDate != null ? endDate : ''}" />

							<button type="submit">Lọc</button>
						</form>




					</div>

					<div class="card-body">
						<i>${thongBao}</i>
					</div>




					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th style="text-align: center;">ID</th>
									<th style="text-align: center;">Ngày lập</th>
									<th style="text-align: center;">Tổng tiền</th>
									<th style="text-align: center;">Nhân viên lập</th>
									<th style="text-align: center;">Trạng thái</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="hoadon" items="${listHD}">
									<tr class="each-bill">
										<td>${hoadon.ID}</td>
										<td>${hoadon.NGAYLAP}</td>
										<td><fmt:formatNumber value="${hoadon.TONGTIEN}"
												pattern="#,###" /> đồng</td>
										
										
										<c:forEach var="nv" items="${listNV}">
											<c:if test="${nv.MANV == hoadon.MANV.getMANV()}">
												<td>${hoadon.MANV.getMANV()} -  ${nv.HOTENNV}     </td> 
											</c:if>
										</c:forEach>

										<td><a
											href="${root}/bill/list/${hoadon.ID}.htm?linkChangeStatus">
												<button type="button" class="toggle-button"
													style="background-color:${hoadon.PHANLOAI == '1' ? 'green' : 'red'};
										               font-size: 1rem; /* Điều chỉnh kích thước chữ */
										               padding: 20px 40px; /* Điều chỉnh khoảng cách bên trong nút */
										               border-radius: 10px; /* Tạo bo tròn cho nút */
										               width: auto; /* Tự động điều chỉnh chiều rộng */
										               height: auto;">
													${hoadon.PHANLOAI == '1' ? '' : ''}</button>

										</a></td>

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






















<%-- 

	<div class="container-fluid px-4">
		<div class="show">
			<br>
			<div class="table-container">
				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>Ngày lập</th>
							<th>Tổng tiền</th>
							<th>Mã nhân viên</th>

							<th>Trạng thái</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="hoadon" items="${listHD}">
							<tr class="each-bill">
								<td>${hoadon.ID}</td>
								<td>${hoadon.NGAYLAP}</td>

								<td><fmt:formatNumber value="${hoadon.TONGTIEN}"
										pattern="#,###" /> đồng</td>
								<td>${hoadon.MANV.getMANV()}</td>

								<td><a
									href="${root}/bill/list/${hoadon.ID}.htm?linkChangeStatus">
										<button type="button" class="toggle-button"
											style="background-color:${hoadon.PHANLOAI == '1' ? 'green' : 'red'};">
											${hoadon.PHANLOAI == '1' ? '' : ''}</button>
								</a></td>

							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
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
</html> --%>