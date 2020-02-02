<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	isELIgnored="false"
	
	info="소통해요 글 작성 페이지 입니다."
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<%
	String initialize_css = application.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	String communicationWriterView_css = application.getRealPath("/css/communicationBoard/communicationWriterView.css");
	File communicationWriterView_css_file = new File(communicationWriterView_css);
	Date communicationWriterView_css_ver = new Date(communicationWriterView_css_file.lastModified());
	
	String communicationWriterView_js = application.getRealPath("/js/communicationBoard/communicationWriterView.js");
	File communicationWriterView_js_file = new File(communicationWriterView_js);
	Date communicationWriterView_js_ver = new Date(communicationWriterView_js_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="communicationWriterView_css_ver" value="<%= communicationWriterView_css_ver %>"/>
<c:set var="communicationWriterView_js_ver" value="<%= communicationWriterView_js_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>소통해요 글쓰기</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
        
        <link href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" rel="stylesheet" type="text/css">
        <link href="${contextPath}/css/communicationBoard/communicationWriterView.css?ver=${communicationWriterView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<!-- 헤더 페이지를 포함시킵니다. -->
    	<%@ include file="/views/header.jsp" %>

    	
    	<div class="communicationTitleContainer">
		    <h1>📸 소통해요</h1>
		    <p>반려동물과 함께 여러분의 행복을 자랑해요</p>
		</div>
    
        <div class="communicationWriterContainer">
        	<!-- action값이 communicationContentsUpdate.do일 경우, 글수정을 수행합니다. -->
        	<!-- action값이 null일 경우, 글작성을 수행합니다. -->
            <form action="${contextPath}/${action}" method="POST" enctype="multipart/form-data">
                <div class="titleContainer">
                    <input type="text" name="title" value="${communicationContentsVO.title}" autocomplete="off">
                    <label>제목을 입력하세요</label>
                </div>
                
                <div class="contentsContainer">
                    <textarea name="content">${communicationContentsVO.content}</textarea>
                    <label>내용을 입력하세요</label>
                </div>
                
                <!-- 이미지 업로드 컨테이너 -->
                <div class="imgUploaderContainer">
                	<div class="imgUploader">
                		<div class="uploaderButtonsContainer">
                			<input type="file" name="imgLocation_1">
                		</div>
                	</div>
                	
                	<div class="imgUploader">
                		<div class="uploaderButtonsContainer">
                			<input type="file" name="imgLocation_2">
                		</div>
                	</div>
                	
                	<div class="imgUploader">
                		<div class="uploaderButtonsContainer">
                			<input type="file" name="imgLocation_3">
                		</div>
                	</div>
                </div>
                
                <div class="buttonsContainer">
                	<c:choose>
                		<c:when test='${action eq "communicationContentsUpdate.do"}'>
                			<input type="hidden" name="requestBoardIDX" value="${communicationContentsVO.boardIDX}">
                   			<input type="submit" value="수정하기">
                    	</c:when>
                    	
                    	<c:otherwise>
                    		<input type="submit" value="글쓰기">
                    	</c:otherwise>
                    </c:choose>
                </div>
            </form>
        </div>
        
        
        <!-- 푸터 페이지를 포함시킵니다. -->
        <%@ include file="/views/footer.jsp" %>
        
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="${contextPath}/js/communicationBoard/communicationWriterView.js?ver=${communicationWriterView_js_ver}" type="text/javascript"></script>
    </body>
</html>