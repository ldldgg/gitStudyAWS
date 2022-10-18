<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	alert('성공적으로 처리했습니다');
	
	window.onload = function(){
		var no = document.getElementById('no').value;
		
		location.href = '/springHome/member/one.do?no=' + no;
	}
	
</script>
</head>
<body>
	<input id="no" type="hidden" value="${memberDto.no}">
</body>
</html>