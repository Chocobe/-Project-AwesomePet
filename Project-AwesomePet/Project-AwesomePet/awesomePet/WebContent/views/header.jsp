<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
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
		
		<link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
		
		<link rel="stylesheet" href="${contextPath}/css/initialize.css" type="text/css"/>
		<link rel="stylesheet" href="${contextPath}/css/header.css" type="text/css"/>
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
            	<c:choose>
            		<c:when test="${empty memberLoginID}">
						<input type="button" value="로그인" class="firstInput" onclick="loginView('${contextPath}');">
						<input type="button" value="회원가입" class="secondInput" onclick="joinView('${contextPath}');">
					</c:when>
					
					<c:otherwise>
						<p>안녕하세요 <span style="color: peru;">🐹 ${memberLoginID}</span> 님</p>
						<input type="button" value="마이 페이지" class="firstInput" onclick="myPageView('${contextPath}')">
						<input type="button" value="로그아웃" class="secondInput" onclick="memberLogout('${contextPath}')">
					</c:otherwise>
				</c:choose>
            </div>
        </nav>
        
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript" src="${contextPath}/js/header.js?ver=${header_js_ver}"></script>
	</body>
</html>