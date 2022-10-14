<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style type="text/css">


nav>ul {
	list-style-type: none;
	padding: 0px;
	overflow: hidden;
	background-color: #333333;
	display: table;
	margin-left: auto;
	margin-right: auto;
}

nav>ul>li {
	float: left;
	width: 40px;
}

nav>ul>li>a {
	display: block;
	color: white;
	text-align: center;
	padding: 16px;
	text-decoration: none;
}

nav>ul>li>a:hover {
	color: #ffd9ec;
	background-color: #5d5d5d;
	font-weight: bold;
}

.active{
	color: #ffd9ec;
	background-color: #5d5d5d;
	font-weight: bold;
}
</style>

<script src="/springHome/resources/js/jquery-3.6.1.js"></script>

<script type="text/javascript">
	function goPageFnc(pageNum){
// 		var curPageObj = document.getElementById('curPage');
// 		curPageObj.value = pageNum;
		
// 		var hiddenFormObj = document.getElementById('pagingForm');
// 		hiddenFormObj.submit();

		$("#curPage").val(pageNum);
		$("#pagingForm").submit();
	}
	
	$(document).ready(function() {
		var idStr = '#pageButton' + $('#curPage').val();
		
		$(idStr).addClass('active');
	});
</script>

<nav>
	<ul>
	<c:if test="${pagingMap.memberPaging.prevBlock ne 1}">
		<li>
			<a href="#" onclick="goPageFnc(${pagingMap.memberPaging.prevBlock});"><span>&laquo;</span></a>
		</li>
	</c:if>

	<c:forEach var="num" 
		begin="${pagingMap.memberPaging.blockBegin}" 
		end="${pagingMap.memberPaging.blockEnd}">
		
		<li id='pageButton${num}'>
			<a href="#" onclick="goPageFnc(${num});"><c:out value="${num}"/></a>
		</li>
	
	</c:forEach>
	
	<c:if test="${pagingMap.memberPaging.curBlock < pagingMap.memberPaging.totBlock}">
		<li>
			<a href="#" onclick="goPageFnc(${pagingMap.memberPaging.nextBlock});"><span>&raquo;</span></a>
		</li>
	</c:if>
	</ul>
</nav>