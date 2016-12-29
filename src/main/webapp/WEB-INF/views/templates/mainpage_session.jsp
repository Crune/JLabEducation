<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
	<title><tiles:getAsString name="title" /></title>
</head>
<body>
<center>
<table border="1" height="500" align="center" >

	<tr>
		<td colspan="2"><tiles:insertAttribute name="top" /></td>	
	</tr>
	
	<tr>	
	
		<td width="500" height="300" align="center">
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
