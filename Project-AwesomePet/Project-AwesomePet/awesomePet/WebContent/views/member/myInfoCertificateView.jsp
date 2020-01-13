<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	info="마이 페이지 인증 페이지 입니다"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<%
	String initialize_css = application.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	String myInfoCertificateView_css = application.getRealPath("/css/member/myInfoCertificateView.css");
	File myInfoCertificateView_css_file = new File(myInfoCertificateView_css);
	Date myInfoCertificateView_css_ver = new Date(myInfoCertificateView_css_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="myInfoCertificateView_css_ver" value="<%= myInfoCertificateView_css_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>마이페이지 인증 페이지</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
        
        <link href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" rel="stylesheet" type="text/css">
        <link href="${contextPath}/css/member/myInfoCertificateView.css?ver=${myInfoCertificateView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<!-- 헤더 페이지를 포함시킵니다. -->
    	<%@ include file="/views/header.jsp" %>
    
    
        <section>
            <div class="certificateWrap">
                <h1>마이 페이지 인증</h1>
                <div class="errorMessage">Error</div>
                    
                <form action="..${contextPath}/myPageCertificate.do" method="POST">
                    <div class="inputBox">
                        <input type="password" name="memberPW" required>
                        <label>Password</label>
                    </div>
                    
                    <div class="submitBox">
                        <input type="submit" value="인증하기">
                    </div>
                </form>
            </div>
        </section>
        
        
        <!-- 푸터 페이지를 포함시킵니다. -->
        <%@ include file="/views/footer.jsp" %>
    </body>
</html>
