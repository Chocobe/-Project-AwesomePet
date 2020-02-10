<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	
	<body>
		<div>
			<input type="button" value="요소 추가" class="myButton">
		</div>
		
		
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$("input[type=button]").click(function() {
					const input = $("<input>").attr({
						"type": "button",
						"value": "요소 추가",
						"class": "myButton"
					});
					
					$("div").append(input);
					
					alert("Hello");
				});
			});			
		</script>
	</body>
</html>