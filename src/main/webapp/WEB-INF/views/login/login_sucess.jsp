<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">

function logout(){
	location.href = "./logout.jlab";
}
function member_info(){
	location.href = "./member_info.jlab";
}
</script>
</head>
<body>
<form>

	<table width="250" align="center" border="0">
	
		<tr>
				<td colspan="2" align="center">${name} 님 안녕하세요!!</td>
			</tr>
			
			<tr>
				<td align="center"><input type="button" value="로그아웃" onclick="logout()"></td>
				<td align="center"><input type="button" value="회원정보" onclick="member_info()"></td>
			</tr>
		
	</table>

</form>
</body>
</html>