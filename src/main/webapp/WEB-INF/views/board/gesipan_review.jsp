<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
				<form action="./gesipan_update.jlab" method="post" name="gasi">
					<table width="100%">
						<tr>
							<td>
								<table border="1" width="100%" align="center">
									<tr>
										<td>��ȣ : <input type="text" name="num" id="num" value="${member.num}" readonly="readonly" /></td>
									</tr>
									<tr>
										<td>���� : <input type="text" name="title" value="${member.title}">
										</td>
									</tr>
									<tr>
										<td>�̸� : <input type="text" name="name" value="${member.name}" readonly="readonly">
										</td>
									</tr>
									<tr align="center">
										<td width="100%"><textarea name="content" cols="70" rows="20">${member.content}</textarea></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table border="1" align="right">
									<tr>
										<td><input type="button" value="���" onclick="location.href='./gesipan.jlab'" /></td>
										<td><input type="submit" value="����"></td>
										<td><input type="button" value="����" onclick="location.href='./gesipan_delete.jlab?num=${member.num}'" /></td>
										<td><input type="button" value="���" onclick="location.href='./gesipan.jlab'" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</form>
	<form action="./gesipan_reple.jlab" method="post">
		<table>
			<tr>
				<td>
					<!-- ��� �ۼ� table -->
					<table>
						<Tr>
							<td>�̸� 
								<c:if test="${empty sessionScope.sesion_id}">
									<input type="text" name="name">
								</c:if>
								<c:if test="${not empty sessionScope.sesion_id}">
									${sessionScope.name}
									<input type="hidden" name="name" value="${sessionScope.name}">
								</c:if>
							</td>
							<td><textarea id="content" name="content" cols="30" rows="3"></textarea></td>
							<td><input type="submit" onclick="gasi_board()" value="���"></td>
						</Tr>
					</table> <input type="hidden" id="gasinumber" name="gasinumber" value="${member.num}" />
				</td>
				<!-- ��� ���� �κ� -->
			</tr>
			<c:if test="${list!= null }">
				<!--  ����� null�� �ƴҰ�� -->
				<tr>
					<td>
						<table border=1>
							<!--  ��� List �ҷ���. -->
							<c:forEach items="${list}" var="list">
								<tr>
									<td>�ۼ���</td>
									<td>${list.name}</td>
									<td>���� :</td>
									<td>${list.content}</td>
									<td>�ۼ���</td>
									<td>${list.regdate}</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</c:if>
		</table>
	</form>
</body>

</html>