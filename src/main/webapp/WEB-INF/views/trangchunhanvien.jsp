<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<head>
<meta charset="utf-8">
<title>TRANG CHỦ NHÂN VIÊN</title>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>NHÂN VIÊN</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${root}/resources/quanly/css/styles.css" rel="stylesheet" />
<link rel="icon" href="${root}/resources/img/logowebsite.png" type="image/png" />

<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<%@ include file="/WEB-INF/common/nhanvien/header.jsp"%>

	
<div id="layoutSidenav_content"
     style="
        background: url('${root}/resources/account/images/tt.jpg') no-repeat center center;
        background-size: cover;
        background-repeat: no-repeat;
        height: 100vh;
        width: 100vw;
        margin: 0;
        padding: 0;">
    <main>
        <div class="container-fluid px-4">
            <!-- Thêm phần chữ Trang Chủ Quản Lý -->
            <div class="header-text">
               Trang Chủ Nhân Viên
            </div>
            <!-- Nội dung khác -->
        </div>
    </main>
</div>

<style>
    .header-text {
        font-size: 5.5rem; /* Kích thước chữ */
        color: white; /* Màu chữ */
        font-weight: bold; /* Đậm */
        font-style: italic; /* In nghiêng */
        position: absolute; /* Vị trí tuyệt đối */
        top: 300px; /* Cách trên 10px */
        left: 350px; /* Cách lề trái 10px */
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7); /* Hiệu ứng bóng cho chữ */
    }
</style>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/assets/demo/chart-area-demo.js"></script>
	<script src="${root}/resources/quanly/assets/demo/chart-bar-demo.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="${root}/resources/quanly/js/datatables-simple-demo.js"></script>
</body>
</html>
