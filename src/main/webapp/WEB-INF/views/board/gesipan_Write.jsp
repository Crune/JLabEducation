<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function cancle() {
		location.href = "./gesipan.jlab";
	}
</script>
</head>
<body>
	<form action="./gesipan_save.jlab" method="post">
		<table border="1" align="center" width="100%">
			<tr>
				<td><label>累己磊 : 
					<c:if test="${empty sessionScope.sesion_id}">
						<input type="text" name="name">
					</c:if>
					<c:if test="${not empty sessionScope.sesion_id}">
						${sessionScope.name}
						<input type="hidden" name="name" value="${sessionScope.name}">
					</c:if>
				</label></td>
			</tr>
			<tr>
				<td width="100%"><label>力 格 : <input type="text"
						name="title" size="60"></label></td>
			</tr>
			<tr>
				<td width="100%"><textarea name="content" cols="70" rows="15"></textarea></td>
			</tr>
		</table>
		<table align="right">
			<tr>
				<td><input type="submit" value="历厘"></td>
				<td><input type="button" value="秒家" onclick="cancle()"></td>
			</tr>
		</table>
	</form>
</body>

</html>