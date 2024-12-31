<%-- <%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Spring MVC</title>
<base href ="${pageContext.servletContext.contextPath }/">
<style>
	table{
		border-collapse:collapse;
		width:100%;
	}
	th,td{
		line-height: 25px;
		border:1px solid black;
		padding:5px;
	}
	th{
		background-color:gray;
	}
</style>
</head>
<body>
	<h1>EL & JSTL</h1>
	<table>
	<tr>
		<th>Mã sản phẩm</th>
		<th>Tên sản phẩm</th>
		<th>Đơn vị</th>
		<th>Số lượng tồn</th>
	</tr>
	<c:forEach var="nl" items="${dsnguyenlieu}">
	<tr>
		<td>${nl.MANL }</td>
		<td>${nl.TEN }</td>
		<td>${nl.DONVI } </td>
		<td>${nl.SLTON }</td>
	</tr>
	</c:forEach>
	</table>
	
</body>
</html> --%>