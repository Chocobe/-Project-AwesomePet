<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	info="pet게시판 글 작성 페이지 입니다."
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<%
	String initialize_css = application.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	String petContentsWriterView_css = application.getRealPath("/css/petBoard/petContentsWriterView.css");
	File petContentsWriterView_css_file = new File(petContentsWriterView_css);
	Date petContentsWriterView_css_ver = new Date(petContentsWriterView_css_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="petContentsWriterView_css_ver" value="<%= petContentsWriterView_css_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>분양등록</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
        
        <link href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" rel="stylesheet" type="text/css">
        <link href="${contextPath}/css/petBoard/petContentsWriterView.css?ver=${petContentsWriterView_css_ver}" rel="stylesheet" type="text/css">
        
        <style type="text/css">
               
        </style>
    </head>
    
    <body>
    	<!-- 헤더 페이지를 포함 시킵니다. -->
    	<%@ include file="/views/header.jsp" %>
    
    
    	<!-- 대분류 페이지를 포함 시킵니다. -->
    	<%@ include file="/views/petBoard/typeWriterView.jsp" %>
    	
    
    	<!-- 소분류 페이지를 포함 시킵니다. -->
    	<%@ include file="/views/petBoard/subTypeWriterView.jsp" %>
    	
    	
    	<!-- 분양등록 -->
        <div class="petContentsWriterWrap">
            <form action="" method="POST" class="petContainer" enctype="multipart/form-data">
                <h1>분양등록</h1>
                
                <!-- 대분류 입력부 -->
                <div class="inputContainer">
                    <p>대분류</p>
                    
                    <select class="typeName">
                        <option>강아지</option>
                        <option>대형견</option>
                        <option>고양이</option>
                    </select>
                </div>
                
                <!-- 소분류 입력부 -->
                <div class="inputContainer">
                    <p>소분류</p>
                    
                    <select class="subTypeName">
                        <option>진도</option>
                        <option>허스키</option>
                        <option>푸들</option>
                    </select>
                </div>
                
                <!-- 나이 입력부 -->
                <div class="inputContainer">
                    <p>나이</p>
                    
                    <input type="text" name="age">
                </div>
                
                <!-- 성별 입력부 -->
                <div class="inputContainer">
                    <p>성별</p>
                    
                    <input type="text" name="gender">
                </div>
                
                <!-- 분양가 입력부 -->
                <div class="inputContainer">
                    <p>분양가</p>
                    
                    <input type="text" name="price">
                </div>
                
                <!-- 예방접종 입력부 -->
                <div class="inputContainer">
                    <p>예방접종</p>
                    
                    <input type="text" name="vaccination">
                </div>
                
                <!-- 중성화 입력부 -->
                <div class="inputContainer">
                    <p>중성화</p>
                    
                    <input type="neutralization">
                </div>
                
                <!-- 분양글 상태 입력부 -->
                <div class="inputContainer">
                    <p>분양글 상태</p>
                    <select name="state">
                        <option>공개</option>
                        <option>비공개</option>
                        <option>분양완료</option>
                    </select>
                </div>
                
                <!-- 이미지 입력부 -->
                <div class="imgUploaderContainer">
                    <!-- js 에서 형식 출력 -->
                    <div class="imgUploader">
                		<div class="uploaderButtonsContainer">
                			<input type="file" name="imgLocation_1">
                            <input type="button" value="❌">
                		</div>
                		
                		<img src="${contextPath}/appImages/family.jpg">
                	</div>
                	
                	<!-- js 에서 형식 출력 -->
                	<div class="imgUploader">
                		<div class="uploaderButtonsContainer">
                			<input type="file" name="imgLocation_1">
                            <input type="button" value="❌">
                		</div>
                		
                		<img src="${contextPath}/appImages/family.jpg">
                	</div>
                	
                	<!-- js 에서 형식 출력 -->
                	<div class="imgUploader">
                		<div class="uploaderButtonsContainer">
                			<input type="file" name="imgLocation_1">
                            <input type="button" value="❌">
                		</div>
                		
                		<img src="${contextPath}/appImages/family.jpg">
                	</div>
                </div>
                
                <!-- 등록버튼부 -->
                <div class="submitButtonContainer">
                    <input type="submit" value="등록">
                </div>
            </form>
        </div>
        
        
        <!-- 푸터 페이지를 포함 시킵니다. -->
        <%@ include file="/views/footer.jsp" %>
    </body>
</html>