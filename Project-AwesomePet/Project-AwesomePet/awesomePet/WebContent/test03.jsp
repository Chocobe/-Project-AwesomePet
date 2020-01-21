<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	
	<body>
		<h1>testarea 테스트 중입니다.</h1>
		<form class="myForm" action="test01" method="POST">
			<textarea class="myTest" name="myTest"></textarea>
			<br/>
			<input type="button" value="전송" onclick="readTest();">
		</form>
		
		<div class="result"></div>
		
		
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			function readTest() {
				const inputValue = $(".myTest").val();
				alert("입력값 : " + inputValue);
				
				const arr = inputValue.split("\n");
				
				for(var i = 0; i < arr.length; i++) {
					const text = arr[i];
					const div = $("<p>").text(text);
					
					$(".result").append(div);
				}
			}
		</script>
	</body>
</html>