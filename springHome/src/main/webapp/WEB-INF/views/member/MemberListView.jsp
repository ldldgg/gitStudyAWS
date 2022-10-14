<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>회원목록</title>

<style type="text/css">
table, tr, td, th {
	border: 1px solid black;
}

table {
	border-collapse: collapse;
}

</style>
</head>

<body>
	<form action="./list.do" method="get" id="pagingForm">
		<input type="hidden" name="curPage" id="curPage" value="${pagingMap.memberPaging.curPage}">
	</form>

	<jsp:include page="/WEB-INF/views/Header.jsp" />

	<h1>회원목록</h1>

	<p>
		<a href="./add.do">신규 회원 추가 / 해킹 당했쥬?</a>
	</p>

	<div>
		
		<table>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>이메일</th>
				<th>가입일</th>
				<th></th>
			</tr>
			<c:forEach var="memberDto" items="${memberList}">
				<tr>
					<td>${memberDto.no}</td>
					<td><a href='./update.do?no=${memberDto.no}'>${memberDto.name}</a>
					</td>
					<td>${memberDto.email}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm"
							value="${memberDto.createDate}" /></td>
					<td><a href='./deleteCtr.do?no=${memberDto.no}'>[삭제]</a></td>
				</tr>
			</c:forEach>
		</table>
		
		<jsp:include page="/WEB-INF/views/common/Paging.jsp"/>

	</div>

	<jsp:include page="/WEB-INF/views/Tail.jsp" />

</body>

</html>