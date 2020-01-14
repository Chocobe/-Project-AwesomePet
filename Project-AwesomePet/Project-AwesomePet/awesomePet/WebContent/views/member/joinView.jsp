<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>

<%
	// css, js 파일의 케싱에 의한 변경 미적용을 막기 위한 ver값을 생성합니다.
	
	// initizlize.css의 ver값 생성합니다.
	String initialize_css = application.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	// joinView.css의 ver값을 생성합니다.
	String joinView_css = application.getRealPath("/css/member/joinView.css");
	File joinView_css_file = new File(joinView_css);
	Date joinView_css_ver = new Date(joinView_css_file.lastModified());
	
	// joinView.js의 ver값을 생성합니다.
	String joinView_js = application.getRealPath("/js/member/joinView.js");
	File joinView_js_file = new File(joinView_js);
	Date joinView_js_ver = new Date(joinView_js_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="joinView_css_ver" value="<%= joinView_css_ver %>"/>
<c:set var="joinView_js_ver" value="<%= joinView_js_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>AwesomePet 회원가입</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
        
        <!-- 초기화 -->
        <link href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" rel="stylesheet" type="text/css">
		<!-- joinView.css -->
        <link href="${contextPath}/css/member/joinView.css?ver=${joinView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<!-- 헤더 페이지를 포함시킵니다. -->
    	<%@ include file="/views/header.jsp" %>
    
    
        <div class="joinContainer">
            <form method="POST" action="..${contextPath}/memberJoin.do">
            	<div class="titleWrap">
                	<h1 class=joinTitle>회원가입</h1>
                	<div class="errorMessage"></div>
               	</div>
                
                <div class="animationBox">
                    <input type="text" class="memberID" name="memberID" maxlength="20" autocomplete="off" required>
                    <label class="animation">아이디</label>
                    
                    <input type="button" class="checkIDButton" value="ID검사" onclick="checkOverlapID();">
                </div>
                
                <div class="animationBox">
                    <input type="password" class="memberPW" name="memberPW" maxlength="20" required>
                    <label class="animation">비밀번호</label>
                </div>
                
                <div class="animationBox">
                    <input type="password" class="memberPWCheck" maxlength="20" required>
                    <label class="animation">비밀번호 재확인</label>
                </div>
                
                <div class="animationBox">
                    <input type="text" class="memberName" name="memberName" maxlength="12" autocomplete="off" required>
                    <label class="animation">이름</label>
                </div>
                
                <div class="fixedBox">
                    <label>생년월일</label>
                    
                    <div>
                        <input type="text" class="memberBirthDayYear" name="memberBirthDayYear" maxlength="4" autocomplete="off">
                        <span class="delim"> - </span> 
                        <input type="text" class="memberBirthDayMonth" name="memberBirthDayMonth" maxlength="2" autocomplete="off">
                        <span class="delim"> - </span> 
                        <input type="text" class="memberBirthDayDate" name="memberBirthDayDate" maxlength="2" autocomplete="off">
                    </div>
                </div>
                
                <div class="animationBox">
                    <input type="text" class="memberEmail" name="memberEmail" maxlength="50" autocomplete="off" required>
                    <label>이메일</label>
                </div>
                
                <div class="fixedBox">
                    <label>휴대전화</label>
                    
                    <div>
                        <input type="text" class="memberPhone_1" name="memberPhone_1" maxlength="3" autocomplete="off">
                        <span class="delim"> - </span> 
                        <input type="text" class="memberPhone_2" name="memberPhone_2" maxlength="4" autocomplete="off">
                        <span class="delim"> - </span> 
                        <input type="text" class="memberPhone_3" name="memberPhone_3" maxlength="4" autocomplete="off">
                    </div>
                </div>
                
                <div class="animationBox">
                    <input type="text" class="memberAddr" name="memberAddr" maxlength="50" autocomplete="off" required>
                    <label>주소</label>
                </div>
                
                <div class="submitBox">
                    <input type="button" value="회원가입" class="submitButton" onclick="memberJoin();" disabled>
                    <input type="reset" value="다시작성">
                </div>
            </form>    
        </div>
        
        
        <!-- 푸터 페이지를 포함시킵니다. -->
        <%@ include file="/views/footer.jsp" %>

        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <!-- joinView.js -->
        <script src="${contextPath}/js/member/joinView.js?ver=${joinView_js_ver}" type="text/javascript"></script>
    </body>
</html>
