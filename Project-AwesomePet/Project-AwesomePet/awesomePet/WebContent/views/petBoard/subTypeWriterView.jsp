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
	String subTypeWriterView_css = application.getRealPath("/css/petBoard/subTypeWriterView.css");
	File subTypeWriterView_css_file = new File(subTypeWriterView_css);
	Date subTypeWriterView_css_ver = new Date(subTypeWriterView_css_file.lastModified());
	
	String subTypeWriterView_js = application.getRealPath("/js/petBoard/subTypeWriterView.js");
	File subTypeWriterView_js_file = new File(subTypeWriterView_js);
	Date subTypeWriterView_js_ver = new Date(subTypeWriterView_js_file.lastModified());
%>

<c:set var="subTypeWriterView_css_ver" value="<%= subTypeWriterView_css_ver %>"/>
<c:set var="subTypeWriterView_js_ver" value="<%= subTypeWriterView_js_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title></title>
        
        <link href="${contextPath}/css/petBoard/subTypeWriterView.css?ver=${subTypeWriterView_css_ver}" rel="stylesheet" type="text/css">
        
        <style type="text/css">
        
        </style>
    </head>
    
    <body>
    <!-- 소분류 설정 -->
        <div class="petContentsWriterWrap">
            <div class="subTypeContainer">
                <h1>소분류 설정</h1>
                
                <!-- 소분류 생성부 -->
                <div class="inputForm">
                    <p>소분류 추가</p>
                    
                    <div class="inputContainer">
                    	<!-- 대분류 선택부 -->
                        <select class="typeName"></select>
                        
                        <!-- 소분류 입력부 -->
                        <input type="text" class="subTypeName">
                        
                        <!-- 소분류 설명 입력부 -->
                        <textarea class="subTypeComment"></textarea>
                    </div>
                    
                    <div class="buttonsContainer">
                        <input type="button" value="추가" onclick="petSubTypeWrite(`${contextPath}`);">
                    </div>
                </div>
                
                <hr/>
                
                <!-- 소분류 조회부 -->
                <div class="selectorContainer">
                    <p>소분류 수정</p>
                    
                    <!-- 값 변경 시, js에서 다시 출력 -->
                    <select class="typeName" name="typeName"></select>
                </div>
                
                <!-- 소분류 수정부 (반복문 출력) -->
                <div class="updateContainer"></div>
            </div>
        </div>
        
        
        <script src="${contextPath}/js/petBoard/subTypeWriterView.js?ver=${subTypeWriterView_js_ver}"></script>
    </body>
</html>