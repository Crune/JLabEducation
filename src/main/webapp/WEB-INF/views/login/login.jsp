<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<script type="text/javascript">
function join(){
	location.href="./join.jlab";
}
function logout(){
	location.href = "./logout.jlab";
}
function member_info(){
	location.href = "./member_info.jlab";
}
</script>

</head>
<body>
<form action="./login.jlab" method="post" name="login">


	
		<table border="0" align="center" width="250" height="100">
		
			<tr>
				<td>���̵�</td>  <td><input type="text" name="member_id" value=""></td>
			</tr>
			
			<tr>
				<td>�н�����</td>  <td><input type="password" name="member_pw" value=""></td>
			</tr>
			
			<tr align="center">
				<td colspan="2"><input type="submit"value="login">&nbsp;&nbsp;&nbsp;
								<input type="button"value="ȸ������" onclick="join()"></td>
			</tr>
			
		</table>

</form>
</body>
</html>