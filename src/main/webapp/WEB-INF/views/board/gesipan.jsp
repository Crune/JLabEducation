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
				<td>번호</td>
				<td>제목</td>
				<td>글쓴이</td>
				<td>시간</td>
			</tr>
			<!-- glist의 길이 많큼 for문 실행 -->
			<c:forEach  items="${glist}"  var="glist">
				<tr  align="center"  height="15px">
					<td>${glist.num }</td>
					<!-- 게시물 번호 -->
					<td><a  href="./gesipan_rview.jlab?num=${glist.num }">${glist.title }</a></td>
					<td>${glist.name }</td> 
					<!-- 게시물  작성자-->
					<td>${glist.regdate }</td> 
					<!--  게시물 작성일자 -->
				</tr>
			</c:forEach>
		</table>
		<table  align="right">
			<tr>
				<td><input  type="submit"  value="글 쓰기" ></td>
			</tr>
		</table>
		 
	</form>
	 
</body>

</html>