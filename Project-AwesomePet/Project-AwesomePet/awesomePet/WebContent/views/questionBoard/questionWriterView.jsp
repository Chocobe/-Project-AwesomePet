<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	isELIgnored="false"
	
	info="궁금해요 글 작성 페이지 입니다."
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<%
	String initialize_css = application.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	String questionWriterView_css = application.getRealPath("css/questionBoard/questionWriterView.css");
	File questionWriterView_css_file = new File(questionWriterView_css);
	Date questionWriterView_css_ver = new Date(questionWriterView_css_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="questionWriterView_css_ver" value="<%= questionWriterView_css_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>궁금해요 글쓰기</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
        
        <link href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" rel="stylesheet" type="text/css">
        <link href="${contextPath}/css/questionBoard/questionWriterView.css?ver=${questionWriterView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<!-- 헤더 페이지를 포함시킵니다. -->
    	<%@ include file="/views/header.jsp" %>

    	
    	<div class="questionTitleContainer">
		    <h1>💡 궁금해요</h1>
		    <p>애완동물에 대한 질문 게시판 입니다.</p>
		</div>
    
        <div class="questionWriterContainer">
            <form action="${contextPath}/${action}" method="POST">
                <div class="titleContainer">
                    <input type="text" name="title" value="${questionContentsVO.title}" autocomplete="off">
                    <label>제목을 입력하세요</label>
                </div>
                
                <div class="contentsContainer">
                    <textarea name="content">${questionContentsVO.content}</textarea>
                    <label>내용을 입력하세요</label>
                </div>
                
                <div class="buttonsContainer">
                	<c:choose>
                		<c:when test='${action eq "questionContentsUpdate.do"}'>
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
    </body>
</html>