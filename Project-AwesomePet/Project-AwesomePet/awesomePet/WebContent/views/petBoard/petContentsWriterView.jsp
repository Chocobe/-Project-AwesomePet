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
	
	String petContentsWriterView_js = application.getRealPath("/js/petBoard/petContentsWriterView.js");
	File petContentsWriterView_js_file = new File(petContentsWriterView_js);
	Date petContentsWriterView_js_ver = new Date(petContentsWriterView_js_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="petContentsWriterView_css_ver" value="<%= petContentsWriterView_css_ver %>"/>
<c:set var="petContentsWriterView_js_ver" value="<%= petContentsWriterView_js_ver %>"/>


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
            <form action="${contextPath}/petContentsWrite.do" method="POST" class="petContainer" enctype="multipart/form-data">
                <h1>분양등록</h1>
                
                <!-- 대분류 입력부 -->
                <div class="inputContainer typeNameDiv">
                    <p>대분류</p>
                    
                    <!-- js에 의해 목록을 생성합니다. -->
                    <select class="typeName"></select>
                </div>
                
                <!-- 소분류 입력부 -->
                <div class="inputContainer subTypeNameDiv">
                    <p>소분류</p>
                    
                    <!-- js에 의해 목록을 생성합니다. -->
                    <select class="subTypeName" name="subTypeName"></select>
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
                    
                    <input type="text" name="neutralization">
                </div>
                
                <!-- 분양글 상태 입력부 -->
                <div class="inputContainer">
                    <p>분양글 상태</p>
                    <select name="boardState">
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
                            <input type="button" value="❌" class="cancelButton" onclick="cancelImgUpload(this)">
                		</div>
                		
                		<input type="hidden" name="action" class="action" value="">
                		<!-- js에 의해 "<img>" 태그가 추가 됩니다. (업로드 미리보기) -->
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
        
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="${contextPath}/js/petBoard/petContentsWriterView.js?ver=${petContentsWriterView_js_ver}"></script>
        
        <script type="text/javascript">
        	// 대분류 설정, 소분류 설정, 분양등록 초기화
        	// ("대분류 설정 - typeWriterView.js"에서 정의)
        	initTypeView(`${contextPath}`, 
        				 initPetContentsWriterView, 
        				 initSubTypeView,
        				 `${noValue}`);
        </script>
    </body>
</html>