<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="ptithcm.entity.ChiTietHoaDon"%>
<%@ page import="ptithcm.entity.SanPham"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<link href="${root}/resources/thongke/css/styles.css" rel="stylesheet" />
<link href="${root}/resources/table/table.css" rel="stylesheet" />
<link href="${root}/resources/table/time.css" rel="stylesheet" />
<link rel="icon" href="${root}/resources/img/logowebsite.png" type="image/png" />

<head>
<meta charset="ISO-8859-1">


<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>

<title>Thống kê</title>

</head>
<body>
	<%@ include file="/WEB-INF/common/quanly/header.jsp"%>

	<div class="container-fluid px-4">


		<div>
			<div class="left">
				<div>

					<div>
						<form action="thongke.htm" method="get">
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

					<br>


					<table>
						<thead>
							<tr>
                                <th style="text-align: center;">ID</th>
                                <th style="text-align: center;">Ngày Lập</th>
                                <th style="text-align: center;">Tổng Tiền</th>
								<!--  <th class="id-list" style="text-align: right;">ID</th> -->
							
							</tr>
						</thead>
						<tfoot>
								<tr>
                                <th style="text-align: center;">ID</th>
                                <th style="text-align: center;">Ngày Lập</th>
                                <th style="text-align: center;">Tổng Tiền</th>
								<!--  <th class="id-list" style="text-align: right;">ID</th> -->
							
							</tr>
						</tfoot>
						<tbody>
							<c:forEach var="hd" items="${listHD}">
								<tr>
									<td class="id-list">${hd.ID}</td>
									<td class="date-list">${hd.NGAYLAP}</td>
									<%-- <td class="amount-list">${hd.TONGTIEN} đồng</td> --%>
									<td><fmt:formatNumber value="${hd.TONGTIEN}"
											pattern="#,###" /> đồng</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>



					<div class="right">
						<div class="total">
							<span class="total-label">Doanh thu:</span> <span
								class="total-amount" id="total-amount"><fmt:formatNumber
									value="${doanhThu}" pattern="#,###" /> đồng</span>

						</div>

						<div class="top5" style="margin-top: 100px; margin-left: 40px;">Top 5 sản phẩm bán chạy</div>


						<table class="product-table">
							<thead>
							
								<tr>
									<th>Tên sản phẩm</th>
									<th>Size</th>
									<th>Số lượng bán ra</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="sp" items="${topSanPham}" varStatus="status">
									<c:if test="${status.index < 5}">
										<c:set var="productKey" value="${sp.key}" />
										<c:set var="masp"
											value="${fn:substringBefore(productKey, '-')}" />
										<c:set var="masize"
											value="${fn:substringAfter(productKey, '-')}" />

										<c:set var="found" value="false" />

										<c:forEach var="ctsp" items="${listCTHD}">
											<c:if
												test="${ctsp.pk.SANPHAM.MASP == masp && ctsp.pk.SIZE.MASIZE == masize && found == 'false'}">
												<tr>
													<td>${ctsp.pk.SANPHAM.TENSP}</td>
													<td>${ctsp.pk.SIZE.TENSIZE}</td>
													<td>${sp.value}</td>
												</tr>
												<c:set var="found" value="true" />
											</c:if>
										</c:forEach>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>





					<c:if test="${found == 'false'}">
						<td>Không tìm thấy sản phẩm</td>
					</c:if>
					<br>
					<div class="product-container"></div>
				</div>
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
</html>