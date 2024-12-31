<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
<title>Bán Hàng</title>

<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />

<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />
<link rel="icon" href="${root}/resources/img/logowebsite.png" type="image/png" />

<link href="${root}/resources/sanpham/css/styles.css" rel="stylesheet" />
<link href="${root}/resources/thongke/spBanHang/thanhtimkiem.css"
	rel="stylesheet" />
<link href="${root}/resources/table/table.css" rel="stylesheet" />
<!-- Thêm file sanpham.js -->
<script src="${root}/resources/thongke/spBanHang/thanhtimkiem.js"></script>

</head>



<body class="sb-nav-fixed" style="padding-top: 50px;">



	<form action="${root}/sell/home.htm" method="get"
		style="padding-left: 50px;">
		<input type="hidden" name="backHome" value="true" />
		<button type="submit" class="navbar-brand ps-3">Trang chủ</button>

	</form>

	<!-- <div id="layoutSidenav_content"> -->

	<!-- Left Column: Danh mục sản phẩm -->
	<div class="left-column">
		<div class="categories">
			<a href="${root}/sell/viewSP/All.htm?linkSearch=true"> <span
				class="category" style="font-size: 20px; font-weight: bold;">
					ALL</span>
			</a>
			<c:forEach var="loaiSP" items="${listLSP}">
				<a href="${root}/sell/viewSP/${loaiSP.MALOAI}.htm?linkSearch=true">
					<span class="category" style="font-size: 20px; font-weight: bold;">
						${loaiSP.TENLOAI} </span>
				</a>
			</c:forEach>
		</div>


		<div id="search-container" style="margin-top: 10px;">
			<input type="text" id="searchInput" onkeyup="updateSearchQuery()"
				placeholder="Tìm kiếm sản phẩm..."
				value="${searchQuery != null ? searchQuery : ''}" />
		</div>

		<a> ${searchQuery}</a>


		<div id="product-container">
			<c:forEach var="sanpham" items="${listSP}">
			
				<!-- 		<div class="product"> -->
				<div class="product" data-masp="${sanpham.TENSP}">
					<img src="${root}/resources/img/sanpham/${sanpham.HINHANH}"
						alt="${sanpham.TENSP}" />


					<h3>${sanpham.TENSP}</h3>
					<div>

						<!-- chỉ lấy những sản phẩm có chi tiết sản phẩm -->
						<c:forEach var="ctsp" items="${listCTSP}">
							<c:if test="${sanpham.MASP == ctsp.getSANPHAM().getMASP()}">
								<a
									href="${root}/sell/viewSP/${sanpham.MASP}/${ctsp.getSIZE().getMASIZE()}.htm?clickSIZE=true">
									<button class="size">${ctsp.getSIZE().getMASIZE()}</button>
								</a>


							</c:if>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- Right Column: Chi tiết sản phẩm -->
	<div class="right-column">
		<div class="product-details">
			<h3>Chi tiết sản phẩm</h3>
			<!-- 	<table  border="1"
				style="width: 100%; text-align: left; border-collapse: collapse;"> -->
			<table>
				<thead>
					<tr>
						<th>Tên</th>
						<th>Giá</th>
						<th>Giảm giá</th>
						<th>Số lượng</th>
						<th>Tổng</th>
						<th>Hành động</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${product_choise_List}"
						varStatus="status">
						<tr>
							<!-- Tên sản phẩm -->
							<td><c:forEach var="sp" items="${dssanpham}">
									<c:if test="${sp.MASP == item.name}">
										<c:forEach var="size" items="${listSize_choise}">
											<c:if test="${size.MASIZE == item.size}">
												<span>${sp.TENSP} - ${size.TENSIZE}</span>
											</c:if>
										</c:forEach>
									</c:if>
								</c:forEach></td>

							<!-- Giá -->
							<td><fmt:formatNumber value="${item.gia}"
									maxFractionDigits="2" minFractionDigits="0" /> Đồng</td>

							<!-- Giảm giá -->
							<!--  sửa phần này nếu trường hợp ko có mã khuyễn mãi nào  -->
							<td><c:choose>
									<c:when test="${not empty item.maKhuyenMai}">
										<c:forEach var="ct_km" items="${listCT_KM_today}">
											
											<c:if test="${item.maKhuyenMai == ct_km.KHUYENMAI.MAKM}">

												<c:if test="${item.size == ct_km.SIZE.MASIZE}">
													<c:if test="${item.name == ct_km.SANPHAM.MASP}">
														<span>${ct_km.PHANTRAM} %</span>
													</c:if>
												</c:if>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<span>0 %</span>
									</c:otherwise>
								</c:choose></td>

							<!-- Số lượng -->
							<td>${item.soLuong}</td>

							<!-- Tổng -->
							<!-- Tổng -->
							<td><c:choose>
									<c:when test="${not empty item.maKhuyenMai}">
										<c:forEach var="ct_km" items="${listCT_KM_today}">
											<c:if test="${item.maKhuyenMai == ct_km.KHUYENMAI.MAKM}">
												<c:if test="${item.size == ct_km.SIZE.MASIZE}">
													<c:if test="${item.name == ct_km.SANPHAM.MASP}">
														<fmt:formatNumber
															value="${item.tongTien * (1 - ct_km.PHANTRAM / 100)}"
															maxFractionDigits="2" minFractionDigits="0" />
                            đồng
                       </c:if>
												</c:if>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<fmt:formatNumber value="${item.tongTien}"
											maxFractionDigits="0" />
            đồng
        </c:otherwise>
								</c:choose></td>


							<!-- Hành động -->
							<td>
								<form
									action="${root}/sell/viewSP/billAction/${status.index}.htm"
									method="get">
									<input type="hidden" name="deleteBill" value="true" />
									<button type="submit" id="confirm-btn">Xóa</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
		<div class="total-cost-box">
			<p>
				Tổng cộng:
				<fmt:formatNumber value="${tongTien_choise_List}"
					maxFractionDigits="2" minFractionDigits="0" />
				đồng
			</p>
		</div>
		<div>
			<div style="display: flex; gap: 300px; align-items: center;">
				<form action="${root}/sell/viewSP/billAction/All.htm" method="get"
					style="margin: 0;">
					<input type="hidden" name="deleteBill" value="true" />
					<button type="submit" id="confirm-btn" style="padding: 10px 20px;">Hủy
						thanh toán</button>
				</form>

				<%--  <a href="${root}/sell/viewSP/billAction.htm?addBill=true" style="margin: 0;">
            <button id="confirm-btn" style="padding: 10px 20px;">Xác nhận</button>
        </a> --%>

				<form action="${root}/sell/viewSP/billAction/All.htm" method="get"
					style="margin: 0;">
					<input type="hidden" name="addBill" value="true" />
					<button type="submit" id="confirm-btn" style="padding: 10px 20px;">Xác
						nhận</button>
				</form>


			</div>
		</div>
	<p class="text-danger" style="font-size: 2rem;">${thongBao}</p>

		<!-- Hiển thị thông báo -->
	</div>
	<!-- 		</div> -->

</body>


<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<script src="${root}/resources/quanly/js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
	crossorigin="anonymous"></script>
<script src="${root}/resources/quanly/js/datatables-simple-demo.js"></script>

</html>