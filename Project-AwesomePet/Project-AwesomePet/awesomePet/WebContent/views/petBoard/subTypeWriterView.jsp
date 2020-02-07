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
%>

<c:set var="subTypeWriterView_css_ver" value="<%= subTypeWriterView_css_ver %>"/>


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
                <form class="inputForm">
                    <p>소분류 추가</p>
                    
                    <div class="inputContainer">
                        <select name="typeName">
                            <option>강아지</option>
                            <option>대형견</option>
                            <option>고양이</option>
                        </select>
                        
                        <input type="text" name="subTypeName">
                        
                        <textarea class="typeComment"></textarea>
                    </div>
                    
                    <div class="buttonsContainer">
                        <input type="submit" value="추가">
                    </div>
                </form>
                
                <hr/>
                
                <!-- 소분류 조회부 -->
                <div class="selectorContainer">
                    <p>소분류 수정</p>
                    
                    <!-- 값 변경 시, ajax로 subType 조회 -->
                    <select name="typeSelector">
                        <option>강아지</option>
                        <option>대형견</option>
                        <option>고양이</option>
                    </select>
                    
                    <!-- 값 변경 시, ajax로 해당 튜플 조회 -->
                    <select name="subTypeSelector">
                        <option>진도</option>
                        <option>허스키</option>
                        <option>푸들</option>
                    </select>
                </div>
                
                <!-- 소분류 수정부 (반복문 출력) -->
                <div class="updateContainer">
                    <form action="" method="POST" class="inputForm">
                        <div class="inputContainer">
                            <select name="typeName">
                                <option>강아지</option>
                                <option>대형견</option>
                                <option>고양이</option>
                            </select>
                            
                            <input type="text" name="subTypeName" value="기존값">
                            
                            <textarea class="typeComment">기존값</textarea>
                        </div>
                        
                        <div class="buttonsContainer">
                            <input type="submit" class="submitButton" value="수정">
                            <input type="button" class="deleteButton" value="삭제" onclick="">
                        </div>
                    </form>
                    
                    <form action="" method="POST" class="inputForm">
                        <div class="inputContainer">
                            <select name="typeName">
                                <option>강아지</option>
                                <option>대형견</option>
                                <option>고양이</option>
                            </select>
                            
                            <input type="text" name="subTypeName" value="기존값">
                            
                            <textarea class="typeComment">기존값</textarea>
                        </div>
                        
                        <div class="buttonsContainer">
                            <input type="submit" class="submitButton" value="수정">
                            <input type="button" class="deleteButton" value="삭제" onclick="">
                        </div>
                    </form>
                    
                    <form action="" method="POST" class="inputForm">
                        <div class="inputContainer">
                            <select name="typeName">
                                <option>강아지</option>
                                <option>대형견</option>
                                <option>고양이</option>
                            </select>
                            
                            <input type="text" name="subTypeName" value="기존값">
                            
                            <textarea class="typeComment">기존값</textarea>
                        </div>
                        
                        <div class="buttonsContainer">
                            <input type="submit" class="submitButton" value="수정">
                            <input type="button" class="deleteButton" value="삭제" onclick="">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>