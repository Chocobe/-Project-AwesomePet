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


<%
	String header_css = application.getRealPath("/css/header.css");
	File header_css_file = new File(header_css);
	Date header_css_ver = new Date(header_css_file.lastModified());
	
	String header_js = application.getRealPath("/js/header.js");
	File header_js_file = new File(header_js);
	Date header_js_ver = new Date(header_js_file.lastModified());
%>


<c:set var="header_css_ver" value="<%= header_css_ver %>"/>
<c:set var="header_js_ver" value="<%= header_js_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title></title>
		
		<link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
		
		<link rel="stylesheet" href="${contextPath}/css/header.css?ver=${header_css_ver}" type="text/css"/>
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
                    <a href="#" onclick="familyBoardView('${contextPath}')">
						가족을 찾아요
                    </a>
                </li>
                
                <li>
                    <a href="#" onclick="communicationBoardView('${contextPath}')">
						소통해요
                    </a>
                </li>
                
                <li>
                    <a href="#" onclick="questionBoardView('${contextPath}')">
						궁금해요
                    </a>
                </li>
                
                <li>
                    <a href="https://github.com/Chocobe/-Project-AwesomePet/tree/master/Project-AwesomePet/Project-AwesomePet/awesomePet" target="_blank">
						Github(소스코드)
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