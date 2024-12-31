<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SP</title>
<style type="text/css">
	table {
	  border-collapse: collapse;
	  width: 100%;
	}
	
	th, td {
	  text-align: left;
	  padding: 8px;
	  border: 1px solid #ddd;
	}
	
	tr:nth-child(even) {
	  background-color: #f2f2f2;
	}
	
	th {
	  background-color: #4CAF50;
	  color: white;
	}
</style>
</head>
<body>
	<h1>PRODUCT</h1>
	<table>
		<thead>
			<tr>
				<th>MASP</th>
				<th>TEN</th>
				<th>HINHANH</th>
				<th>LOAISP</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listSP}" var="sanpham">
			    <tr>
			        <td>${sanpham.MASP}</td>
			        <td>${sanpham.TEN}</td>
			        <td>${sanpham.HINHANH}</td>
			        <td>${sanpham.loaiSP.MALOAI}</td>
			    </tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>