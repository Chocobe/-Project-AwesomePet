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
		
		<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet"/>
		
		<link rel="stylesheet" href="${contextPath}/css/initialize.css"/>
		<link rel="stylesheet" href="${contextPath}/css/header.css"/>
	</head>
	
	<body>
		<header class="logoFont">
            <div class="logo">
                <a href="${contextPath}/index.do">
                    Awesome
                    <img class="logo1" src="${contextPath}/appImages/logoImage1.jpg" height="50">
                    Pet
                </a>                
            </div>
        </header>
        
        <nav>
            <ul class="mainMenu">
                <li>
                    <a href="#">
						저희 매장은요
                    </a>
                </li>
                <li>
                    <a href="#">
						가족을 찾아요
                    </a>
                </li>
                <li>
                    <a href="#">
						소통해요
                    </a>                    
                </li>
                <li>
                    <a href="#">
						궁금해요
                    </a>
                </li>
            </ul>
            
            <div class="memberMenu">
				<input type="button" value="로그인" onclick="loginView('${contextPath}');">
				<input type="button" value="회원가입" onclick="join('${contextPath}');">
            </div>
        </nav>
        
        
        <script src="http://code.jquery.com/jquery-min-js"></script>
        <script type="text/javascript" src="${contextPath}/js/header.js"></script>
	</body>
</html>