<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	isELIgnored="false"
	
	info="궁금해요 게시판 페이지 입니다."
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<%
	String initialize_css = application.getRealPath("/css/iniaitlize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	String questionBoardView_css = application.getRealPath("/css/questionBoardView.css");
	File questionBoardView_css_file = new File(questionBoardView_css);
	Date questionBoardView_css_ver = new Date(questionBoardView_css_file.lastModified());
%>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>궁금해요</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
        
        <link href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" rel="stylesheet" type="text/css">
        <link href="${contextPath}/css/questionBoard/questionBoardView.css?ver=${questionBoardView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<%@ include file="/views/header.jsp" %>
    
    
   		<div class="questionTitleContainer">
		    <h1>💡 궁금해요</h1>
		    <p>애완동물에 대한 질문 게시판 입니다.</p>
		</div>
    
        <section class="questionWrap">
            <div class="boardContainer">
                <table>
                    <tr class="title">
                        <th>글번호</th>
                        <th>작성자</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                    
                    <tr class="border">
                        <td colspan="5"></td>
                    </tr>
                    
                    <!-- 게시물을 생성합니다. -->
                    <c:forEach var="contents" items="${questionBoardVO.questionContentsList}">
                    	<tr class="content">
                    		<td>${contents.boardIDX}</td>
                    		<td>${contents.writerID}</td>
                    		
                    		<td>
                    			<a href="..${contextPath}/questionContentsView.do?requestContents=${contents.boardIDX}">${contents.title}</a>
                    		</td>
                    		
                    		<td>${contents.writeDate}</td>
                    		<td>${contents.watch}</td>
                    	</tr>
                    </c:forEach>
                </table>
            </div>
            
            <div class="pageContainer">
            	<a href="..${contextPath}/questionBoardView.do?requestPage=${1}" class="page">첫페이지</a>
                <span class="boundary">|</span>
                <a href="..${contextPath}/questionBoardView.do?requestPage=${questionBoardVO.prevPage}" class="page">이전</a>
                
                <!-- 페이지 번호를 생성합니다. -->
                <c:forEach var="pageNav" begin="${questionBoardVO.beginPage}" end="${questionBoardVO.endPage}">
                	<span class="boundary">|</span>
                	
                	<c:choose>
                		<c:when test="${questionBoardVO.currentPage eq pageNav}">
                			<span class="currentPage">${pageNav}</span>
                		</c:when>
                	
                		<c:otherwise>
                			<a href="..${contextPath}/questionBoardView.do?requestPage=${pageNav}" class="page">${pageNav}</a>
                		</c:otherwise>
                	</c:choose>
                </c:forEach>
                
                <span class="boundary">|</span>
                <a href="..${contextPath}/questionBoardView.do?requestPage=${questionBoardVO.nextPage}" class="page">다음</a>
                <span class="boundary">|</span>
                <a href="..${contextPath}/questionBoardView.do?requestPage=${questionBoardVO.totalPageCnt}" class="page">끝페이지</a>
            </div>
            
            <div class="buttonContainer">
                <input type="button" value="글쓰기">
            </div>
        </section>
        
        
        <%@ include file="/views/footer.jsp" %>
    </body>
</html>