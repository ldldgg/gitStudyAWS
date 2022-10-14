<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보조용 경로 Hello Spring</title>

<script src="/springHome/resources/js/jquery-3.6.1.js"></script>
<script type="text/javascript">
	function loginMoveFnc() {
		$('#simpleForm').submit();
	}
</script>

</head>
<body>
	Hello Spring Projects..
	
	<form action="auth/login.do" id="simpleForm" method="get">
		<input type="button" onclick="loginMoveFnc();" value="로그인화면">
	</form>
</body>
</html>