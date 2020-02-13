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
        
        <!-- 카테고리 출력부 입니다. -->
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
        
        <!-- 게시물 출력부 입니다. -->
        <div class="boardContainer">
            <!-- 이미지 최적 사이즈 : "200:150" -->
            <c:if test="${empty petBoardVO}">
            	<h1>petBoardVO가 null 입니다.</h1>
            </c:if>
            
            <c:forEach var="petVO" items="${petBoardVO.petList}">
            	<div class="contentsContainer" onclick="petContentsView(`${contextPath}`, this);">
            		<c:if test="${not empty petVO.imageList[0]}">
            			<img src="${petVO.imageList[0].imgLocation}">
            		</c:if>
            		
            		<p class="subTypeName">${petVO.subTypeName}</p>
            		<p class="age">${petVO.age}살</p>
            		
            		<input type="hidden" class="boardIDX" value="${petVO.petID}">
            	</div>
            </c:forEach>
        </div>
        
        <!-- 페이지 출력부 입니다. -->
        <div class="pageContainer">
       		<a href="${contextPath}/petBoardView.do?requestPage=${1}" class="page">첫페이지</a>
			<span class="boundary">|</span>
			<a href="${contextPath}/petBoardView.do?requestPage=${petBoardVO.prevPage}" class="page">이전</a>
			
			<!-- 페이지 번호를 생성합니다. -->
			<c:forEach var="pageNav" begin="${petBoardVO.beginPage}" end="${petBoardVO.endPage}">
				<span class="boundary">|</span>
				
				<c:choose>
					<c:when test="${petBoardVO.currentPage eq pageNav}">
						<span class="currentPage">${pageNav}</span>
					</c:when>
				
					<c:otherwise>
						<a href="${contextPath}/petBoardView.do?requestPage=${pageNav}" class="page">${pageNav}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<span class="boundary">|</span>
			<a href="${contextPath}/petBoardView.do?requestPage=${petBoardVO.nextPage}" class="page">다음</a>
			<span class="boundary">|</span>
			<a href="${contextPath}/petBoardView.do?requestPage=${petBoardVO.totalPageCnt}" class="page">끝페이지</a>
		</div>

		<!-- 현재 페이지의 카테고리값을 보관 합니다. -->        
        <div class="hiddenValue">
        	<input type="hidden" class="requestTypeName" value="${petBoardVO.currentTypeName}">
        	<input type="hidden" class="requestSubTypeName" value="${petBoardVO.currentSubTypeName}">
        </div>
        
        <!-- 글 작성 페이지 버튼 입니다. -->
        <!-- (관리자만 보이게 수정 하기) -->
        <div class="buttonContainer">
        	<input type="button" value="글쓰기" onclick="petContentsWriterView(`${contextPath}`);">
        	<input type="hidden" class="action" value="petContentsWrite.do">
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
