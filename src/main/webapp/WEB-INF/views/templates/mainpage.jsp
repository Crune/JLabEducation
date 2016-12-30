<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<head>
	<title><tiles:getAsString name="title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
	<script language='javascript' src="/js/default.js"  charset="utf-8" ></script>
</head>
<body>
<center>
<table border="1" height="500">zzz

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
