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
		<h1>궁금해요 댓글 테스트 중입니다.</h1>
		<input type="button" value="댓글보기" onclick="location.href='${contextPath}/questionReplyView.do?boardIDX=45'">
		<input type="button" value="댓글(AJAX)" onclick="questionReplyView(`${contextPath}`)">
		
		<div class="result"></div>
		
		
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			function questionReplyView(context) {
				const result = $(".result");
				
				$.ajax({
					type: "POST",
					async: true,
					url: context + "/questionReplyView.do?boardIDX=45",
					success: function(jsonData, status) {
						const parsedJSON = JSON.parse(jsonData);
						const replyContentsList = parsedJSON.questionReplyContentsList;
						
						const totalPageCnt = $("<h1>").text(parsedJSON.totalPageCnt);
						const currentPage = $("<p>").text(parsedJSON.currentPage);
						const prevPage = $("<p>").text(parsedJSON.prevPage);
						const nextPage = $("<p>").text(parsedJSON.nextPage);
						const beginPage = $("<p>").text(parsedJSON.beginPage);
						const endPage = $("<p>").text(parsedJSON.endPage);
						
						result.append(totalPageCnt);
						result.append(currentPage);
						result.append(prevPage);
						result.append(nextPage);
						result.append(beginPage);
						result.append(endPage);
						
						for(let reply in replyContentsList) {
							const value = $("<p>").text(replyContentsList[reply].content);
							result.append(value);
						}
					}
				});
			}
		</script>
	</body>
</html>