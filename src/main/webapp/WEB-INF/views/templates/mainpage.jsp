<%@ page contentType="text/html; charset=euc-kr" %>
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
			<tiles:insertAttribute name="left" />
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
