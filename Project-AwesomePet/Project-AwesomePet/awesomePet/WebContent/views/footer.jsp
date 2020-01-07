<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title></title>
		
		<link rel="stylesheet" href="${contextPath}/css/footer.css"/>
	</head>
	
	<body>
		<footer>
            <div>Copyright @ 김영우</div>
            <div>Tel : 010-9551-3439</div>
            <div>Email : kyw05171@gmail.com</div>
            <div>Github : http://github.com/Chocobe</div>
        </footer>
	</body>
</html>