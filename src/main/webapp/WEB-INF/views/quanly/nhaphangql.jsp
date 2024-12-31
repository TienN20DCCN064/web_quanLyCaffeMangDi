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
<title>Nhập hàng</title>
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

<link href="${root}/resources/table/table.css" rel="stylesheet" />
<link rel="icon" href="${root}/resources/img/logowebsite.png"
	type="image/png" />

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
						style="font-size: 20px; color: #800000">Chi Tiết _ Thông
						Tin Nhập Hàng</label>
				</div>
				<div class="card-header">
					<form class="row g-3"
						action="http://localhost:8080/Highlands/import/list.htm"
						method="post">
						<div class="form-group last mb-3">
							<label style="font-size: 18px; color: black; font-weight: bold;">Nhân
								Viên Nhập: </label> <label
								style="font-size: 18px; color: darkred; font-weight: bold;">${nv}</label>
						</div>

						<div class="col-md-2">
							<label for="manhaphang">Mã Nhập Hàng</label> <input
								class="form-control" type="text" value="${manhaphang}" disabled>
						</div>

						<div class="col-md-2">
							<label>Ngày Nhập</label> <input class="form-control" type="text"
								value="${thoigiannhaphang}" disabled>
						</div>

						<div class="col-md-3">
							<label>Nguyên Liệu</label><br> <select id="mySelect"
								class="form-control" name="chonnguyenlieu">
								<c:forEach items="${dsnl}" var="item">
									<option value="${item.MANL}">${item.TENNL}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2">
							<label for="soluong">Số Lượng</label> <input type="number"
								min="0" values="0" class="form-control"
								placeholder="${item.DONVI}" name="soluong">
						</div>

						<div class="col-md-2">
							<label for="dongia">Đơn Giá</label> <input type="number" min="0"
								class="form-control" values="0" placeholder="VNĐ" name="dongia">
						</div>


						<div class='parent' class="col-md-2">
							<div class='child float-left-child' style="margin-left: 2.5em">
								<button name="${btnStatus}" class="btn btn-success">Nhập
									mặt hàng</button>
								<button
									onclick="location.href='http://localhost:8080/Highlands/import/list.htm'"
									class="btn btn-outline-dark" type="button">Tải Lại
									Trang</button>
								<button
									onclick="location.href='http://localhost:8080/Highlands/import/defineId.htm'"
									class="btn btn-warning" type="button">Hủy Nhập Hàng</button>
								<p5 class="text-success">${message1}</p5>
								<p5 class="text-danger">${message0}</p5>
							</div>
						</div>
						<div>
							<label for="btnSave">Lưu thông tin phiếu nhập: </label>
							<button class="btn btn-danger" name="btnSave">
								<i class="glyphicon glyphicon-ok-sign"></i> LƯU
							</button>
						</div>
						<div class="form-inline col-5">
							<span id="result1"></span>
						</div>
					</form>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> DANH SÁCH NGUYÊN LIỆU NHẬP ql
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th style="text-align: center;">Tên Hàng Hóa</th>
									<th style="text-align: center;">Đơn Vị</th>
									<th style="text-align: center;">Số Lượng Tồn</th>
									<th style="text-align: center;">Số Lượng Nhập</th>
									<th style="text-align: center;">Đơn Giá</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th style="text-align: center;">Tên Hàng Hóa</th>
									<th style="text-align: center;">Đơn Vị</th>
									<th style="text-align: center;">Số Lượng Tồn</th>
									<th style="text-align: center;">Số Lượng Nhập</th>
									<th style="text-align: center;">Đơn Giá</th>
								</tr>
							</tfoot>

							<tbody>
								<c:forEach items="${nguyenlieu}" var="item" varStatus="loop">
									<tr>
										<td>${item.TENNLDTO}</td>
										<td>${item.DONVI}</td>
										<td>${item.SLTON}</td>
										<td>${item.SOLUONG}</td>
										<td>${item.DONGIA}</td>
										<%-- <c:if test="${item.MANL eq nl.MANL}">
											<td>${sl}</td>
											<td>${dg}</td>
										</c:if>
										<c:if test="${item.MANL ne nl.MANL}">
											<td>0</td>
											<td>0</td>
										</c:if> --%>
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
		$(document).ready(function() {
			// Initialize the DataTable
			var dataTable = $('datatablesSimple').DataTable();

			// Catch the change event of the select element
			$('mySelect').on('change', function() {
				var selectedValue = $(this).val(); // Get the selected value

				// Set the search input value and trigger the search
				dataTable.search(selectedValue).draw();
			});
		});
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