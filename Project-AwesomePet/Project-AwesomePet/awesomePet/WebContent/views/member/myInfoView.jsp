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
	String initialize_css = application.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	String myInfoView_css = application.getRealPath("/css/member/myInfoView.css");
	File myInfoView_css_file = new File(myInfoView_css);
	Date myInfoView_css_ver = new Date(myInfoView_css_file.lastModified());
	
	String myInfoView_js = application.getRealPath("/js/member/myInfoView.js");
	File myInfoView_js_file = new File(myInfoView_js);
	Date myInfoView_js_ver = new Date(myInfoView_js_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="myInfoView_css_ver" value="<%= myInfoView_css_ver %>"/>
<c:set var="myInfoView_js_ver" value="<%= myInfoView_js_ver %>"/>


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
    
    
        <div class="myInfoViewContainer">
            <form method="POST" action="..${contextPath}/updateMyInfo.do">
                <h1 class="fixTitle">회원정보 수정</h1>
                <div class="errorMessage"></div>
                
                <!-- memberID -->
                <div class="longBox">
                    <input type="text" class="fixed" maxlength="20" value="${myInfoVO.memberID}" disabled>
                    <input type="hidden" class="memberID" name="memberID" value="${myInfoVO.memberID}">
                    <label class="animation">아이디</label>
                </div>
                
                <!-- memberPW -->
                <div class="longBox">
                    <input type="password" class="fixedMemberPW" maxlength="20" placeholder="${myInfoVO.memberPWMasking}">
                    <input type="hidden" class="memberPWOrigin" name="memberPWOrigin" value="${myInfoVO.memberPW}">
                    <input type="hidden" class="memberPW" name="memberPW">
                    <label class="animation">비밀번호</label>
                </div>
                
                <div class="longBox">
                    <input type="password" class="fixedMemberPWCheck" maxlength="20" placeholder="${myInfoVO.memberPWMasking}">
                    <label class="animation">비밀번호 재확인</label>
                </div>
                
                <!-- memberName -->
                <div class="longBox">
                    <input type="text" class="fixed" maxlength="12" value="${myInfoVO.memberName}" disabled>
                    <input type="hidden" class="memberName" name="memberName" value="${myInfoVO.memberName}">
                    <label class="animation">이름</label>
                </div>
                
                <!-- memberBirthDay -->
                <div class="shortBox">
                    <label>생년월일</label>
                    
                    <div>
                        <input type="text" class="fixedMemberBirthDayYear" maxlength="4" placeholder="${myInfoVO.memberBirthDayYear}">
                        <input type="hidden" class="memberBirthDayYearOrigin" value="${myInfoVO.memberBirthDayYear}">
                        <input type="hidden" class="memberBirthDayYear" name="memberBirthDayYear">
                        
                        <span class="delim"> - </span>
                        
                        <input type="text" class="fixedMemberBirthDayMonth" maxlength="2" placeholder="${myInfoVO.memberBirthDayMonth}">
                        <input type="hidden" class="memberBirthDayMonthOrigin" value="${myInfoVO.memberBirthDayMonth}">
                        <input type="hidden" class="memberBirthDayMonth" name="memberBirthDayMonth">
                        
                        <span class="delim"> - </span> 
                        
                        <input type="text" class="fixedMemberBirthDayDate" maxlength="2" placeholder="${myInfoVO.memberBirthDayDate}">
                        <input type="hidden" class="memberBirthDayDateOrigin" value="${myInfoVO.memberBirthDayDate}">
                        <input type="hidden" class="memberBirthDayDate" name="memberBirthDayDate">
                    </div>
                </div>
                
                <!-- memberEmail -->
                <div class="longBox">
                    <input type="text" class="fixedMemberEmail" maxlength="50" placeholder="${myInfoVO.memberEmail}">
                    <input type="hidden" class="memberEmailOrigin" value="${myInfoVO.memberEmail}">
                    <input type="hidden" class="memberEmail" name="memberEmail">
                    <label>이메일</label>
                </div>
                
                <!-- memberPhone -->
                <div class="shortBox">
                    <label>휴대전화</label>
                    
                    <div>
                        <input type="text" class="fixedMemberPhone_1" maxlength="3" placeholder="${myInfoVO.memberPhone_1}">
                        <input type="hidden" class="memberPhone_1Origin" value="${myInfoVO.memberPhone_1}">
                        <input type="hidden" class="memberPhone_1" name="memberPhone_1">
                        
                        <span class="delim"> - </span> 
                        
                        <input type="text" class="fixedMemberPhone_2" maxlength="4" placeholder="${myInfoVO.memberPhone_2}">
                        <input type="hidden" class="memberPhone_2Origin" value="${myInfoVO.memberPhone_2}">
                        <input type="hidden" class="memberPhone_2" name="memberPhone_2">
                        
                        <span class="delim"> - </span> 
                        
                        <input type="text" class="fixedMemberPhone_3" maxlength="4" placeholder="${myInfoVO.memberPhone_3}">
                        <input type="hidden" class="memberPhone_3Origin" value="${myInfoVO.memberPhone_3}">
                        <input type="hidden" class="memberPhone_3" name="memberPhone_3">
                    </div>
                </div>
                
                <!-- memberAddr -->
                <div class="longBox">
                    <input type="text" class="fixedMemberAddr" maxlength="50" placeholder="${myInfoVO.memberAddr}">
                    <input type="hidden" class="memberAddrOrigin" value="${myInfoVO.memberAddr}">
                    <input type="hidden" class="memberAddr" name="memberAddr">
                    <label>주소</label>
                </div>
                
                <div class="submitBox">
                	<!-- memberGrade -->
                	<input type="hidden" name="memberGrade" value="${myInfoVO.memberGrade}">
                	
                    <input type="button" value="수정하기" onclick="updateMyInfo();">
                    <input type="reset" value="다시작성">
                </div>
            </form>    
        </div>
        
        
        <!-- 푸터 페이지를 포함시킵니다. -->
        <%@ include file="/views/footer.jsp" %>
        
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="${contextPath}/js/member/myInfoView.js?ver=${myInfoView_js_ver}" type="text/javascript"></script>
    </body>
</html>
