<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	isELIgnored="false"
	
	info="회원정보 수정 결과 페이지 입니다"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<%
	String initialize_css = application.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	String updateMyInfoResultView_css = application.getRealPath("/css/member/updateMyInfoResultView.css");
	File updateMyInfoResultView_css_file = new File(updateMyInfoResultView_css);
	Date updateMyInfoResultView_css_ver = new Date(updateMyInfoResultView_css_file.lastModified());
	
	String updateMyInfoResultView_js = application.getRealPath("/js/member/updateMyInfoResultView.js");
	File updateMyInfoResultView_js_file = new File(updateMyInfoResultView_js);
	Date updateMyInfoResultView_js_ver = new Date(updateMyInfoResultView_js_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="updateMyInfoResultView_css_ver" value="<%= updateMyInfoResultView_css_ver %>"/>
<c:set var="updateMyInfoResultView_js_ver" value="<%= updateMyInfoResultView_js_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>회원정보 수정 결과</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
        
        <link href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" rel="stylesheet" type="text/css">
        <link href="${contextPath}/css/member/updateMyInfoResultView.css?ver=${updateMyInfoResultView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    <!-- 헤더 페이지를 포함 시킵니다. -->
    	<%@ include file="/views/header.jsp" %>
    	
    
        <div class="updateMyInfoContainer">
            <div class="wrap">
                <c:choose>
                	<c:when test="${empty errorMessage}">
		                <h1>회원정보가 수정되었습니다.</h1>
		                <div class="buttonBox">
		                    <input type="button" value="홈으로" onclick="goToHome('/awesomePet');">
		                </div>
					</c:when>
	                
	                <c:otherwise>
		                <h1>회원정보 수정이 실패하였습니다.</h1>
		                <div class="buttonBox">
		                    <input type="button" value="홈으로" onclick="goToHome('/awesomePet')">
		                    <input type="button" value="마이 페이지" onclick="goToMyInfoView('/PET')">
		                </div>
					</c:otherwise>
				</c:choose>
            </div>
        </div>
        
        
        <!-- 푸터 페이지를 포함 시킵니다. -->
        <%@ include file="/views/footer.jsp" %>
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="${contextPath}/js/member/updateMyInfoResultView.js?ver=${updateMyInfoResultView_js_ver}" type="text/javascript"></script>
    </body>
</html>