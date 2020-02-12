<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	info="가족을 찾아요 페이지 입니다."
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>

<%
	String initialize_css = application.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());

	String petBoardView_css = application.getRealPath("/css/petBoard/petBoardView.css");
	File petBoardView_css_file = new File(petBoardView_css);
	Date petBoardView_css_ver = new Date(petBoardView_css_file.lastModified());
	
	String petBoardView_js = application.getRealPath("/js/petBoard/petBoardView.js");
	File petBoardView_js_file = new File(petBoardView_js);
	Date petBoardView_js_ver = new Date(petBoardView_js_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="petBoardView_css_ver" value="<%= petBoardView_css_ver %>"/>
<c:set var="petBoardView_js_ver" value="<%= petBoardView_js_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>가족을 찾아요</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
        
        <link href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" rel="stylesheet" type="text/css">
        <link href="${contextPath}/css/petBoard/petBoardView.css?ver=${petBoardView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<!-- 헤더 페이지를 첨부 합니다. -->
    	<%@ include file="/views/header.jsp" %>
    	
    	
        <div class="petBoardTitleContainer">
		    <h1>🏡 가족을 찾아요</h1>
		    <p>반려동물 분양 게시판 입니다.</p>
		</div>
        
        <div class="typeOuterContainer">
            <div class="typeMenuContainer">
            	<p class="menuTitle">카테고리</p>
            
                <div class="absoluteContainer">
                    <div class="typeName">전체</div>
                    
                    <ul class="typeList">
                        <li>전체</li>
                    </ul>
                </div>
            </div>
            
            
            <div class="subTypeMenuContainer">
                <!-- typeName과 subTypeName의 인덱스 번호를 맞추기 위한 요소 입니다. -->
                <div class="subTypeContainer">
                    <ul class="subTypeList"></ul>
                </div>
                
                <!-- 반복문 출력 -->
            </div>
        </div>
        
        <div class="boardContainer">
            <!-- 이미지 최적 사이즈 : "200:150" -->
            <c:if test="${empty petBoardVO}">
            	<h1>petBoardVO가 null 입니다.</h1>
            </c:if>
            
            <c:forEach var="petVO" items="${petBoardVO.petList}">
            	<div class="contentsContainer">
            		<c:if test="${not empty petVO.imageList[0]}">
            			<img src="${petVO.imageList[0].imgLocation}">
            		</c:if>
            		
            		<p class="subTypeName">${petVO.subTypeName}</p>
            		<p class="age">${petVO.age}살</p>
            	</div>
            </c:forEach>
        </div>
        
        
        <div class="pageContainer">
            <a href="..${contextPath}/communicationBoardView.do?requestPage=${1}" class="page">첫페이지</a>
            
            <span class="boundary">|</span>
            <a href="..${contextPath}/communicationBoardView.do?requestPage=${communicationBoardVO.prevPage}" class="page">이전</a>
            
            
            
            <!-- 페이지 번호를 생성합니다. (반복문) -->
            <span class="boundary">|</span>
            <a href="..${contextPath}/communicationBoardView.do?requestPage=${pageNav}" class="page">1</a>
                
            <span class="boundary">|</span>
            <a href="..${contextPath}/communicationBoardView.do?requestPage=${pageNav}" class="page">2</a>
               
            <span class="boundary">|</span>
            <a href="..${contextPath}/communicationBoardView.do?requestPage=${pageNav}" class="page">3</a>
               
            <span class="boundary">|</span>
            <a href="..${contextPath}/communicationBoardView.do?requestPage=${pageNav}" class="page">4</a>
               
            <span class="boundary">|</span>
            <a href="..${contextPath}/communicationBoardView.do?requestPage=${pageNav}" class="page">5</a>
            <!-- 페이지 번호를 생성합니다. (반복문) -->
               
                
                
            <span class="boundary">|</span>
            <a href="..${contextPath}/communicationBoardView.do?requestPage=${communicationBoardVO.nextPage}" class="page">다음</a>
            
            <span class="boundary">|</span>
            <a href="..${contextPath}/communicationBoardView.do?requestPage=${communicationBoardVO.totalPageCnt}" class="page">끝페이지</a>
        </div>
        
        <div class="hiddenValue">
        	<input type="hidden" class="requestTypeName" value="${petBoardVO.currentTypeName}">
        	<input type="hidden" class="requestSubTypeName" value="${petBoardVO.currentSubTypeName}">
        </div>
        
        
        <!-- 푸터 페이지를 첨부 합니다. -->
        <%@ include file="/views/footer.jsp" %>
        
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="${contextPath}/js/petBoard/petBoardView.js?ver=${petBoardView_js_ver}" type="text/javascript"></script>
        
        <script type="text/javascript">
        	initTypeMenu(`${contextPath}`, `${petBoardVO.currentTypeName}`, `${petBoardVO.currentSubTypeName}`);
        </script>
    </body>
</html>
















