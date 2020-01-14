<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	info="회원정보 수정 페이지 입니다"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= application.getContextPath() %>"/>


<%
	String initialize_css = request.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	String myInfoView_css = request.getRealPath("/css/member/myInfoView.css");
	File myInfoView_css_file = new File(myInfoView_css);
	Date myInfoView_css_ver = new Date(myInfoView_css_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="myInfoView_css_ver" value="<%= myInfoView_css_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>회원정보 수정 페이지</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
        
        <link href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" rel="stylesheet" type="text/css">
        <link href="${contextPath}/css/member/myInfoView.css?ver=${myInfoView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<!-- 헤더 페이지를 포함시킵니다. -->
    	<%@ include file="/views/header.jsp" %>
    
    
        <div class="joinContainer">
            <form method="POST" action="#">
                <h1 class="fixTitle">회원정보 수정</h1>
                <div class="errorMessage">Error</div>
                
                <div class="animationBox">
                    <input type="text" class="fixed" maxlength="20" value="${myInfoVO.memberID}" disabled>
                    <input type="hidden" class="memberID" value="${myInfoVO.memberID}">
                    <label class="animation">아이디</label>
                </div>
                
                <div class="animationBox">
                    <input type="password" class="memberPW" maxlength="20" placeholder="${myInfoVO.memberPWMasking}">
                    <input type="hidden" class="memberPWOrigin" value="${myInfoVO.memberPW}">
                    <label class="animation">비밀번호</label>
                </div>
                
                <div class="animationBox">
                    <input type="password" class="memberPWCheck" maxlength="20" placeholder="${myInfoVO.memberPWMasking}">
                    <label class="animation">비밀번호 재확인</label>
                </div>
                
                <div class="animationBox">
                    <input type="text" class="fixed" maxlength="12" value="${myInfoVO.memberName}" disabled>
                    <input type="hidden" class="memberName" value="${myInfoVO.memberName}">
                    <label class="animation">이름</label>
                </div>
                
                <div class="fixedBox">
                    <label>생년월일</label>
                    
                    <div>
                        <input type="text" class="memberBirthDayYear" maxlength="4" placeholder="${myInfoVO.memberBirthDayYear}">
                        <input type="hidden" class="memberBirthDayYearOrigin" value="0000">
                        
                        <span class="delim"> - </span> 
                        
                        <input type="text" class="memberBirthDayMonth" maxlength="2" placeholder="${myInfoVO.memberBirthDayMonth}">
                        <input type="hidden" class="memberBirthDayMonthOrigin" value="${myInfoVO.memberBirthDayMonth}">
                        
                        <span class="delim"> - </span> 
                        
                        <input type="text" class="memberBirthDayDate" maxlength="2" placeholder="${myInfoVO.memberBirthDayDate}">
                        <input type="hidden" class="memberBirthDayDateOrigin" value="${myInfoVO.memberBirthDayDate}">
                    </div>
                </div>
                
                <div class="animationBox">
                    <input type="text" class="memberEmail" maxlength="50" placeholder="${myInfoVO.memberEmail}">
                    <input type="hidden" class="memberEmailOrigin" value="${myInfoVO.memberEmail}">
                    <label>이메일</label>
                </div>
                
                <div class="fixedBox">
                    <label>휴대전화</label>
                    
                    <div>
                        <input type="text" class="memberPhone_1" maxlength="3" placeholder="${myInfoVO.memberPhone_1}">
                        <input type="hidden" class="memberPhone_1Origin" value="${myInfoVO.memberPhone_1}">
                        
                        <span class="delim"> - </span> 
                        
                        <input type="text" class="memberPhone_2" maxlength="4" placeholder="${myInfoVO.memberPhone_2}">
                        <input type="hidden" class="memberPhone_2Origin" value="${myInfoVO.memberPhone_2}">
                        
                        <span class="delim"> - </span> 
                        
                        <input type="text" class="memberPhone_3" maxlength="4" placeholder="${myInfoVO.memberPhone_3}">
                        <input type="hidden" class="memberPhone_3Origin" value="${myInfoVO.memberPhone_3}">
                    </div>
                </div>
                
                <div class="animationBox">
                    <input type="text" class="memberAddr" maxlength="50" placeholder="${myInfoVO.memberAddr}">
                    <input type="hidden" class="memberAddrOrigin" value="${myInfoVO.memberAddr}">
                    <label>주소</label>
                </div>
                
                <div class="submitBox">
                    <input type="submit" value="수정하기">
                    <input type="reset" value="다시작성">
                </div>
            </form>    
        </div>
        
        
        <!-- 푸터 페이지를 포함시킵니다. -->
        <%@ include file="/views/footer.jsp" %>
    </body>
</html>