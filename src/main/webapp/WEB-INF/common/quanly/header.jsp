<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 	
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark"> -->
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark"
	style="height: 100px;">

	<!-- Navbar Brand-->
	<a class="navbar-brand ps-3"
		href="http://localhost:8080/Highlands/home/trangchuquanly.htm">Quản
		Lý</a>
	<!-- Sidebar Toggle-->
	<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
		id="sidebarToggle" href="#!">
		<i class="fas fa-bars"></i>
	</button>
	<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
			aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
			<ul class="dropdown-menu dropdown-menu-end"
				aria-labelledby="navbarDropdown">
				<li><a class="dropdown-item"
					href="http://localhost:8080/Highlands/home/profile.htm">Hồ sơ
						của bạn</a></li>
				<li><a class="dropdown-item"
					href="http://localhost:8080/Highlands/home/changepassword.htm">Đổi
						Mật Khẩu</a></li>
				<li><hr class="dropdown-divider" /></li>
				<li><a class="dropdown-item"
					href="http://localhost:8080/Highlands/login/logout.htm">Đăng
						Xuất</a></li>
			</ul></li>
	</ul>
</nav>
<div id="layoutSidenav">
	<div id="layoutSidenav_nav">
		<nav class="sb-sidenav accordion sb-sidenav-dark"
			id="sidenavAccordion">
			<div class="sb-sidenav-menu">
				<div class="nav">
					<div class="sb-sidenav-menu-heading">Bán hàng</div>
					<!-- <a class="nav-link" href="http://localhost:8080/Highlands/product/viewSP.htm">Bán hàng</a> -->
					<a class="nav-link"
						href="http://localhost:8080/Highlands/sell/viewSP.htm">Bán
						hàng</a>

					<div class="sb-sidenav-menu-heading">Quản lý</div>
					<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
						data-bs-target="#collapseLayouts" aria-expanded="false"
						aria-controls="collapseLayouts">
						<div class="sb-nav-link-icon">
							<i class="fas fa-columns"></i>
						</div> Quản Lý
						<div class="sb-sidenav-collapse-arrow">
							<i class="fas fa-angle-down"></i>
						</div>
					</a>
					<div class="collapse" id="collapseLayouts"
						aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
						<nav class="sb-sidenav-menu-nested nav">
							<a class="nav-link"
								href="http://localhost:8080/Highlands/employee/list.htm">Nhân
								viên</a> <a class="nav-link"
								href="http://localhost:8080/Highlands/product/list.htm">Sản
								phẩm</a> <a class="nav-link"
								href="http://localhost:8080/Highlands/product/detail/list.htm">Chi
								tiết _ sản phẩm</a> <a class="nav-link"
								href="http://localhost:8080/Highlands/product/type/list.htm">Loại
								sản phẩm</a> <a class="nav-link"
								href="http://localhost:8080/Highlands/product/size/list.htm">Size
								sản phẩm</a> <a class="nav-link"
								href="http://localhost:8080/Highlands/product/recipe/list.htm">Công
								thức</a> <a class="nav-link"
								href="http://localhost:8080/Highlands/material/list.htm">Vật
								liệu - Loại</a> <a class="nav-link"
								href="http://localhost:8080/Highlands/import/defineId.htm">Nguyên
								liệu- SL</a> <a class="nav-link"
								href="http://localhost:8080/Highlands/product/detailMaterial/list.htm">Chi
								tiết _ nguyên liệu</a> <a class="nav-link"
								href="http://localhost:8080/Highlands/bill/list.htm">Hóa đơn</a>
							<a class="nav-link"
								href="http://localhost:8080/Highlands/bill/detail/list.htm">Chi
								Tiết _ Hóa Đơn</a> <a class="nav-link"
								href="http://localhost:8080/Highlands/promotion/list.htm">Khuyến
								mãi</a> <a class="nav-link"
								href="http://localhost:8080/Highlands/thongke.htm">Thống Kê
								Doanh Thu</a>

						</nav>
					</div>
					<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
						data-bs-target="#collapsePages" aria-expanded="false"
						aria-controls="collapsePages">
						<div class="sb-nav-link-icon">
							<i class="fas fa-book-open"></i>
						</div> Xem
						<div class="sb-sidenav-collapse-arrow">
							<i class="fas fa-angle-down"></i>
						</div>
					</a>
					<div class="collapse" id="collapsePages"
						aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
						<nav class="sb-sidenav-menu-nested nav accordion"
							id="sidenavAccordionPages">
							<a class="nav-link"
								href="http://localhost:8080/Highlands/history/list.htm">Lịch
								sử thay đổi giá</a> <a class="nav-link"
								href="http://localhost:8080/Highlands/machineLearning/hocMay.htm">
								Gợi ý: Nhập nguyên liệu</a>
						</nav>
					</div>
				</div>
			</div>
			<div class="sb-sidenav-footer">
				<div class="small">Logged in as:</div>
				Quản lý
			</div>
		</nav>
	</div>