<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form name="input" action="./join.jlab" method="post">
	<table border="1" width="300" align="center">				
		<tr>
			<td>���̵�</td>		
			<td colspan="2">	<input type="text"name="member_id"value="">	</td>				
		</tr>
						
		<tr>
			<td>��й�ȣ</td>		
			<td><input type="password"name="member_pw"value=""></td>
		</tr>
			
		<tr>
			<td>����</td>		
			<td><input type="text"name="member_name"value=""></td>
		</tr>
		
		<tr align="center">
			<td colspan="2">
				<input type="submit" value="�����ϱ�"/><input type="button" value="���" onclick="javascript:history.go(-1)">
			</td>
		</tr>
	</table>
</form>
</body>
</html>