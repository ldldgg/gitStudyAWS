<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>회원정보 수정</title>

<script type="text/javascript">
	function pagePrevMoveFnc(no) {
		location.href = './one.do?no=' + no;
	}
	
	function pageMoveListFnc() {
		location.href = './list.do';
	}
	
	
	function pageMoveDeleteFnc(no){
		var url = "./deleteCtr.do?no=" + no;
		location.href = url;
	}

	
// 	window.onload = function(){
// 		var submitObj = document.getElementById('submit');
		
// 		submitObj.addEventListener('click', function(e) {
// 			var memberNameVal = document.getElementById('memberName').value;
// 			var memberEmailVal = document.getElementById('memberEmail').value;
// 			var nameVal = document.getElementById('name').value;
// 			var emailVal = document.getElementById('email').value;
			
// 			var nameCheck = memberNameVal == nameVal;
// 			var emailCheck = memberEmailVal == emailVal;
			
// 			var alertStr = '';
			
// 			if(nameCheck && emailCheck){
// 				e.preventDefault();
				
// 				alertStr = '정보를 변경해주세요';
// 			}else if(!memberNameVal) {
// 				e.preventDefault();
				
// 				alertStr = '이름이 비어있습니다';
// 			}else if(!memberEmailVal) {
// 				e.preventDefault();
				
// 				alertStr = '이메일이 비어있습니다';
// 			}
			
// 			alert(alertStr);
// 		})
		
// 	}
	
</script>

</head>
	
<body>

	<input id="name" type="hidden" value="${memberDto.name}">
	<input id="email" type="hidden" value="${memberDto.email}">

	<jsp:include page="../Header.jsp" />
	
	<h1>회원정보 수정</h1>
	<form action='./updateCtr.do' method='post' enctype="multipart/form-data">
		<input type='hidden' name='no' value='${memberDto.no}'>
		이름: <input type='text' name='name' id='memberName'
			value='${memberDto.name}'><br>
		이메일: <input type='text' name='email' id='memberEmail'
			value='${memberDto.email}'><br>
		비밀번호: <input type="password" name="password" value=""><br>
		첨부파일:
		<div id="fileContent">
			<div>
				<c:choose>
					<c:when test="${empty fileList}">
						<input type="hidden" id="fileIdx" name="fileIdx" value="">
						<input type="file" id="file0" name="file0">
						<a href="#this" id="delete0" onclick="FileFnc();">삭제</a><br>
					</c:when>
					<c:otherwise>
						<c:forEach var="row" items="${fileList}" varStatus="obj">
							<input type="hidden" id="fileIdx_${obj.index}" name="fileIdx" value="${row.IDX}">
							<img alt="image not found" src="<c:url value='/img/${row.STORED_FILE_NAME}'/>"/><br>
							${row.ORIGINAL_FILE_NAME} <input type="file" id="file_${obj.index}" name="file_${obj.index}">
							(${row.FILE_SIZE}kb) <a href="#this" id="delete_${obj.index}">삭제</a><br>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
		가입일: <fmt:formatDate value="${requestScope.memberDto.createDate}" 
			pattern="yyyy년MM월dd일 hh:mm"/><br>
		<input type="button" value="파일추가" onclick="addFileFnc();">
		<input id="submit" type='submit' value='저장'>
		<input type='button' value='삭제' 
			onclick='pageMoveDeleteFnc(${memberDto.no});'>
		<input type='button' value='뒤로' onclick='pagePrevMoveFnc(${memberDto.no});'>	
		<input type='button' value='목록' onclick='pageMoveListFnc();'>	
	</form>
	
	<jsp:include page="../Tail.jsp" />
</body>

</html>