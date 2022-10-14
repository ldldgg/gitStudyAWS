<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>회원정보 수정</title>

<script type="text/javascript">
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
	<form action='./updateCtr.do' method='post'>
		번호: <input type='text' name='no' 
			value='${memberDto.no}' readonly><br>
		이름: <input type='text' name='name' id='memberName'
			value='${memberDto.name}'><br>
		이메일: <input type='text' name='email' id='memberEmail'
			value='${memberDto.email}'><br>
		가입일: <fmt:formatDate value="${requestScope.memberDto.createDate}" 
			pattern="yyyy-MM-dd hh:mm"/><br>
		<input id="submit" type='submit' value='저장'>
		<input type='button' value='삭제' 
			onclick='pageMoveDeleteFnc(${memberDto.no});'>
		<input type='button' value='취소' onClick='pageMoveListFnc();'>	
	</form>
	
	<jsp:include page="../Tail.jsp" />
</body>

</html>