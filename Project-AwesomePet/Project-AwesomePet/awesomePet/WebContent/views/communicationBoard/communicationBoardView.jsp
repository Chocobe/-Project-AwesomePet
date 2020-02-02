<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	isELIgnored="false"
	
	info="소통해요 게시판 페이지 입니다."
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<%
	String initialize_css = application.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	String communicationBoardView_css = application.getRealPath("/css/communicationBoard/communicationBoardView.css");
	File communicationBoardView_css_file = new File(communicationBoardView_css);
	Date communicationBoardView_css_ver = new Date(communicationBoardView_css_file.lastModified());
	
	String communicationBoardView_js = application.getRealPath("/js/communicationBoard/communicationBoardView.js");
	File communicationBoardView_js_file = new File(communicationBoardView_js);
	Date communicationBoardView_js_ver = new Date(communicationBoardView_js_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="communicationBoardView_css_ver" value="<%= communicationBoardView_css_ver %>"/>
<c:set var="communicationBoardView_js_ver" value="<%= communicationBoardView_js_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>소통해요</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
        
        <link href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" rel="stylesheet" type="text/css">
        <link href="${contextPath}/css/communicationBoard/communicationBoardView.css?ver=${communicationBoardView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<!-- 헤더 페이지를 포함시킵니다. -->
    	<%@ include file="/views/header.jsp" %>
    
    
   		<div class="communicationTitleContainer">
		    <h1>📸 소통해요</h1>
		    <p>어떤 글이든 좋아요!</p>
		</div>
    
        <section class="communicationWrap">
            <div class="boardContainer">
                <table>
                    <tr class="title">
                        <th>글번호</th>
                        <th>작성자</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>조회수(👍)</th>
                    </tr>
                    
                    <tr class="border">
                        <td colspan="5"></td>
                    </tr>
                    
                    <!-- 게시물을 생성합니다. -->
                    <c:forEach var="contents" items="${communicationBoardVO.communicationContentsList}">
                    	<tr class="content">
                    		<td>${contents.boardIDX}</td>
                    		<td>${contents.writerID}</td>
                    		
                    		<td>
                    			<a href="..${contextPath}/communicationContentsView.do?requestBoardIDX=${contents.boardIDX}&requestPage=${communicationBoardVO.currentPage}">
                    				${contents.title} 
                    				<c:if test="${contents.replyCnt > 0}">
                    					(<span class="replyCnt">${contents.replyCnt}</span>)
                    				</c:if>
                    			</a>
                    		</td>
                    		
                    		<td>${contents.writeDate}</td>
                    		<td>
                    			${contents.watch}
                    			<c:if test="${contents.hitCnt > 0}">
                    				(<span>${contents.hitCnt}</span>)
                    			</c:if>
                    		</td>
                    	</tr>
                    </c:forEach>
                </table>
            </div>
            
            <div class="pageContainer">
            	<a href="..${contextPath}/communicationBoardView.do?requestPage=${1}" class="page">첫페이지</a>
                <span class="boundary">|</span>
                <a href="..${contextPath}/communicationBoardView.do?requestPage=${communicationBoardVO.prevPage}" class="page">이전</a>
                
                <!-- 페이지 번호를 생성합니다. -->
                <c:forEach var="pageNav" begin="${communicationBoardVO.beginPage}" end="${communicationBoardVO.endPage}">
                	<span class="boundary">|</span>
                	
                	<c:choose>
                		<c:when test="${communicationBoardVO.currentPage eq pageNav}">
                			<span class="currentPage">${pageNav}</span>
                		</c:when>
                	
                		<c:otherwise>
                			<a href="..${contextPath}/communicationBoardView.do?requestPage=${pageNav}" class="page">${pageNav}</a>
                		</c:otherwise>
                	</c:choose>
                </c:forEach>
                
                <span class="boundary">|</span>
                <a href="..${contextPath}/communicationBoardView.do?requestPage=${communicationBoardVO.nextPage}" class="page">다음</a>
                <span class="boundary">|</span>
                <a href="..${contextPath}/communicationBoardView.do?requestPage=${communicationBoardVO.totalPageCnt}" class="page">끝페이지</a>
            </div>
            
            <div class="buttonContainer">
            	<form action="${contextPath}/communicationWriterView.do" method="GET">
            		<input type="hidden" name="action" value="communicationContentsWrite.do">
                	<input type="submit" value="글쓰기">
               	</form>
            </div>
        </section>
        
        
        <!-- 푸터 페이지를 포함시킵니다. -->
        <%@ include file="/views/footer.jsp" %>
        
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
      	<script src="${contextPath}/js/communicationBoard/communicationBoardView.js?ver=${communicationBoardView_js_ver}" type="text/javascript"></script>
    </body>
</html>