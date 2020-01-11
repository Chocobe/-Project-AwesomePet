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
        <title>AwesomePet 회원가입</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
        
        <!-- 초기화 -->
        <link href="${contextPath}/css/initialize.css?ver=1" rel="stylesheet" type="text/css">
		<!-- joinView.css -->
        <link href="${contextPath}/css/member/joinView.css?ver=1" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<%@ include file="/views/header.jsp" %>
    
        <div class="joinContainer">
            <form method="POST" action="#">
                <h1 class="joinTitle">회원가입</h1>
                
                <div class="animationBox">
                    <input type="text" class="memberID" maxlength="20" required>
                    <label class="animation">아이디</label>
                </div>
                
                <div class="animationBox">
                    <input type="password" class="memberPW" maxlength="20" required>
                    <label class="animation">비밀번호</label>
                </div>
                
                <div class="animationBox">
                    <input type="password" class="memberPWCheck" maxlength="20" required>
                    <label class="animation">비밀번호 재확인</label>
                </div>
                
                <div class="animationBox">
                    <input type="text" class="memberName" maxlength="12" required>
                    <label class="animation">이름</label>
                </div>
                
                <div class="fixedBox">
                    <label>생년월일</label>
                    
                    <div>
                        <input type="text" class="memberBirthDayYear" maxlength="4" required>
                        <span class="delim"> - </span> 
                        <input type="text" class="memberBirthDayMonth" maxlength="2" required>
                        <span class="delim"> - </span> 
                        <input type="text" class="memberBirthDayDay" maxlength="2" required>
                    </div>
                </div>
                
                <div class="animationBox">
                    <input type="text" class="memberEmail" maxlength="50" required>
                    <label>이메일</label>
                </div>
                
                <div class="fixedBox">
                    <label>휴대전화</label>
                    
                    <div>
                        <input type="text" class="memberPhone_1" maxlength="3" required>
                        <span class="delim"> - </span> 
                        <input type="text" class="memberPhone_2" maxlength="4" required>
                        <span class="delim"> - </span> 
                        <input type="text" class="memberPhone_3" maxlength="4" required>
                    </div>
                </div>
                
                <div class="animationBox">
                    <input type="text" class="memberAddr" maxlength="50" required>
                    <label>주소</label>
                </div>
                
                <div class="submitBox">
                    <input type="submit" value="회원가입">
                    <input type="reset" value="다시작성">
                </div>
            </form>    
        </div>
        
        <%@ include file="/views/footer.jsp" %>
    </body>
</html>