<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function m_delete(){
		location.href = "./member_delete.jlab";
	}
</script>
</head>
<body>
<form name="update" action="./member_info.jlab" method="post">

	<table border="1" width="300" align="center">
		<c:forEach items="${list}" var="list">
		<tr>
			<td>���̵�</td>		<td><input type="text" name="member_id" value="${list.member_id}" readonly></td>
		</tr>
		
		<tr>
			<td>��й�ȣ</td>		<td><input type="text" name="member_pw" value="${list.member_pw}"></td>
		</tr>
		
		<tr>
			<td>�̸�</td>		<td><input type="text" name="member_name" value="${list.member_name}" readonly></td>
		</tr>
		
		<tr>
			<td align="center" colspan="2" height="60">
				<input type="submit" value="����">
				<input type="reset"" value="���">
				<input type="button" value="ȸ��Ż��" onclick="m_delete()">
				<input type="button" value="�ڷΰ���" onclick="javascript:history.back(1)"></td>
		</tr>
		</c:forEach>
		
	</table>

	
</form>
</body>
</html>