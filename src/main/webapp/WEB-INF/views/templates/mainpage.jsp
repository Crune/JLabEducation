<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<head>
	<title><tiles:getAsString name="title" /></title>
</head>
<body>
<center>
<table border="1" height="500">

	<tr>
		<td colspan="2"><tiles:insertAttribute name="top" /></td>	
	</tr>
	
	<tr>
		<td>
			<c:if test="${not empty sessionScope.sesion_id}">
				<tiles:insertAttribute name="login_true" />
			</c:if>
			<c:if test="${empty sessionScope.sesion_id}">
				<tiles:insertAttribute name="login_false" />
			</c:if>
		</td>	
	
		<td width="500" height="300">
			<tiles:insertAttribute name="body" />
		</td>	
	</tr>
	
	<tr>
		<td colspan="2"><tiles:insertAttribute name="bottom" /></td>	
	</tr>
	
</table>
</center>
</body>
</html>
