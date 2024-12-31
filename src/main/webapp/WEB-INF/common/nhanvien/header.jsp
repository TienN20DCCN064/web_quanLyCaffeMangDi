<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark" style="height: 100px;">
	<!-- Navbar Brand-->
	<a class="navbar-brand ps-3"
		href="http://localhost:8080/Highlands/home/trangchunhanvien.htm">Nhân
		Viên</a>
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
					<!-- 					<a class="nav-link"
						href="http://localhost:8080/Highlands/product/viewSP.htm">Bán
						hàng</a> -->
					<a class="nav-link"
						href="http://localhost:8080/Highlands/sell/viewSP.htm">Bán
						hàng</a>

					<div class="sb-sidenav-menu-heading">Nhập nguyên liệu</div>

					<a class="nav-link"
						href="http://localhost:8080/Highlands/import/defineId.htm">Nhập
						nguyên liệu</a>
				</div>
			</div>
			<div class="sb-sidenav-footer">
				<div class="small">Logged in as:</div>
				Nhân viên
			</div>
		</nav>
	</div>

