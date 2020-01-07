<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	session="true"
	buffer="8kb"
	autoFlush="true"
	
	isELIgnored="false"
	
	info="로그인 페이지 입니다."
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>로그인</title>
        
        <!-- CSS초기화 파일을 링크 합니다. -->
        <link rel="stylesheet" href="${contextPath}/css/initialize.css"/>
        
        <!-- 현재 페이지(login.jsp)의 CSS파일을 링크 합니다. -->
        <link rel="stylesheet" href="${contextPath}/css/login.css"/>
    </head>
    
    <body>
        <!-- 헤더 페이지를 포함시킵니다. -->
        <%@ include file="/views/header.jsp" %>
        
        
        <section>
            <div class="loginBox">
                <h2>로그인</h2>
                
                <form method="POST" action="..${contextPath}/memberLogin.do">
                    <div class="inputBox">
                        <input type="text" name="memberID" required autocomplete="off">
                        <label>User ID</label>
                    </div>
                    
                    <div class="inputBox">
                        <input type="password" name="memberPW" required>
                        <label>Password</label>
                    </div>
                    
                    <div class="submitBox">
                        <input type="submit" value="로그인">
                        <input type="button" value="회원가입" onclick="#">
                    </div>
                    
                    <div class="findBox">
                        <a href="#">아이디 찾기</a>
                        <div class="separator">|</div>
                        <a href="#">비밀번호 찾기</a>
                    </div>
                </form>
            </div>
        </section>
        
        
        <!-- 푸터 페이지를 포함시킵니다. -->
        <%@ include file="/views/footer.jsp" %>
    </body>
</html>
