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
	<form  action="./gesipan_Write.jlab"  method="post">
		<table  border="1"  align="center"  width="100%">
			<tr  height="10px"  align="center">
				<td>��ȣ</td>
				<td>����</td>
				<td>�۾���</td>
				<td>�ð�</td>
			</tr>
			<!-- glist�� ���� ��ŭ for�� ���� -->
			<c:forEach  items="${glist}"  var="glist">
				<tr  align="center"  height="15px">
					<td>${glist.num }</td>
					<!-- �Խù� ��ȣ -->
					<td><a  href="./gesipan_rview.jlab?num=${glist.num }">${glist.title }</a></td>
					<td>${glist.name }</td> 
					<!-- �Խù�  �ۼ���-->
					<td>${glist.regdate }</td> 
					<!--  �Խù� �ۼ����� -->
				</tr>
			</c:forEach>
		</table>
		<table  align="right">
			<tr>
				<td><input  type="submit"  value="�� ����" ></td>
			</tr>
		</table>
		 
	</form>
	 
</body>

</html>