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
	String typeWriterView_css = application.getRealPath("/css/petBoard/typeWriterView.css");
	File typeWriterView_css_file = new File(typeWriterView_css);
	Date typeWriterView_css_ver = new Date(typeWriterView_css_file.lastModified());
%>

<c:set var="typeWriterView_css_ver" value="<%= typeWriterView_css_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title></title>
        
        <link href="${contextPath}/css/petBoard/typeWriterView.css?ver=${typeWriterView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
        <!-- 대분류 설정 -->
        <div class="petContentsWriterWrap">
            <div class="typeContainer">
                <h1>대분류 설정</h1>
                
                <!-- 대분류 입력부 -->
                <form class="inputForm" action="" method="POST">
                    <p>대분류 추가</p>
                    <input type="text" class="typeName" name="typeName" autocomplete="off">
                    <input type="submit" value="추가">
                </form>
                
                <!-- 기존 대분류 데이터 수정부 (반복문 출력) - ajax사용 -->
                <div class="updateContainer">
                    <p>대분류 수정</p>
                    
                    <form action="" method="POST" class="innerContainer">
                        <input type="text" class="typeName" name="typeName" placeholder="기존값" autocomplete="off">
                        <input type="hidden" name="originTypeName" value="기존값(EL)">
                        
                        <input type="submit" class="submitButton" value="수정">
                        <input type="button" class="deleteButton" value="삭제" onclick="">
                    </form>
                    
                    <!-- 기존 대분류 데이터 수정부 -->
                    <form action="" method="POST" class="innerContainer">
                        <input type="text" class="typeName" name="typeName" placeholder="기존값" autocomplete="off">
                        <input type="hidden" name="originTypeName" value="기존값(EL)">
                        
                        <input type="submit" class="submitButton" value="수정">
                        <input type="button" class="deleteButton" value="삭제" onclick="">
                    </form>
                    
                    <!-- 기존 대분류 데이터 수정부 -->
                    <form class="innerContainer">
                        <input type="text" class="typeName" name="typeName" placeholder="기존값" autocomplete="off">
                        <input type="hidden" name="originTypeName" value="기존값(EL)">
                        
                        <input type="submit" class="submitButton" value="수정">
                        <input type="button" class="deleteButton" value="삭제" onclick="">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>