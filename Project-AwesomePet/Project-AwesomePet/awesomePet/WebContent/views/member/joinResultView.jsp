<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	isELIgnored="false"
	
	info="회원가입 결과 페이지 입니다"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <!-- 3초 후, index.do 페이지를 요청 합니다 -->
        <meta http-equiv="refresh" content="3;${contextPath}/index.do">
        <title>회원가입 완료</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
        
        <!-- 초기화 -->
        <link href="${contextPath}/css/initialize.css" rel="stylesheet">
        
        <!-- joinResultView.css -->
        <link href="${contextPath}/css/member/joinResultView.css" rel="stylesheet">
    </head>
    
    <body>
    	<%@ include file="/views/header.jsp" %>
    
        <div class="joinResultContainer">
            <div class="wrap">
                <div class="memberID">
                    TEST <!-- 회원가입 ID (EL) -->
                    <span class="sir">님</span>
                </div>
                
                <div class="greeting">
                    <span class="title">🐹 AwesomePet</span>의 
                    <span class="member">회원</span>이 되셨습니다.
                </div>
                
                <input class="homeButton" type="button" value="홈으로" onclick="goToHome('${contextPath}');">
            </div>
        </div>
        
        <%@ include file="/views/footer.jsp" %>
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript">
            function goToHome(context) {
				location.href = context + "/index.do";
            }
        </script>
    </body>
</html>