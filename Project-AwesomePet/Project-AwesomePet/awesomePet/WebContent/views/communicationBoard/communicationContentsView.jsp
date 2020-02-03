<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	isELIgnored="false"
	
	info="소통해요 글 페이지 입니다."
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<%
	String initialize_css = application.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	String communicationContentsView_css = application.getRealPath("/css/communicationBoard/communicationContentsView.css");
	File communicationContentsView_css_file = new File(communicationContentsView_css);
	Date communicationContentsView_css_ver = new Date(communicationContentsView_css_file.lastModified());
	
	String communicationContentsView_js = application.getRealPath("/js/communicationBoard/communicationContentsView.js");
	File communicationContentsView_js_file = new File(communicationContentsView_js);
	Date communicationContentsView_js_ver = new Date(communicationContentsView_js_file.lastModified());
	
	String communicationReply_js = application.getRealPath("/js/communicationBoard/communicationReply.js");
	File communicationReply_js_file = new File(communicationReply_js);
	Date communicationReply_js_ver = new Date(communicationReply_js_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="communicationContentsView_css_ver" value="<%= communicationContentsView_css_ver %>"/>
<c:set var="communicationContentsView_js_ver" value="<%= communicationContentsView_js_ver %>"/>
<c:set var="communicationReply_js_ver" value="<%= communicationReply_js_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>글 제목으로 변경할것</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
        
		<link rel="stylesheet" href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" type="text/css">        
		<link href="${contextPath}/css/communicationBoard/communicationContentsView.css?ver=${communicationContentsView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<!-- 헤더 페이지를 포함시킵니다. -->
    	<%@ include file="/views/header.jsp" %>

    	
    	<div class="communicationTitleContainer">
		    <h1>📸 소통해요</h1>
		    <p>어떤 글이든 좋아요!</p>
		</div>
    
        <div class="communicationContentsWrap">
       		<!-- 글 제목부 입니다. (제목, 작성자, 작성일, 조회수) -->
            <div class="titleContainer">
                <h1>${communicationContentsVO.title}</h1>
                
                <table class="infoTable">
                    <tr>
                        <th>작성자</th>
                        <td>${communicationContentsVO.writerID}</td>
                    </tr>
                    
                    <tr>
                        <th>작성일</th>
                        <td>${communicationContentsVO.writeDate}</td>
                    </tr>
                    
                    <tr>
                        <th>조회수 (👍)</th>
                        <td>
                        	${communicationContentsVO.watch} 
                        	(<span>${communicationContentsVO.hitCnt}</span>)
                        </td>
                    </tr>
                </table>
            </div>
            
            
			<!-- 본문 입니다. -->
            <div class="contentsContainer">
            	<!-- javascript에서 hidden값을 분할하여 출력합니다. -->
            	<input type="hidden" class="requestContent" value="${communicationContentsVO.content}">
            	
            	<!-- 이미지 출력부 -->
            	<c:if test="${not empty communicationContentsVO.imgLocation_1}">
            		<img src="${communicationContentsVO.imgLocation_1}">
            	</c:if>
            	
            	<c:if test="${not empty communicationContentsVO.imgLocation_2}">
            		<img src="${communicationContentsVO.imgLocation_2}">
            	</c:if>
            	
            	<c:if test="${not empty communicationContentsVO.imgLocation_3}">
            		<img src="${communicationContentsVO.imgLocation_3}">
            		<h1>${communicationContentsVO.imgLocation_3}</h1>
            	</c:if>
            	
            	<!-- 본문 출력부 : initContent() 에서 첨부 합니다. -->
            </div>
            
            
            <!-- 글 처리 버튼입니다. (목록으로 이동, 수정, 삭제) -->
            <div class="optionButtonsContainer">
            	<!-- 로그인 상태일 경우, "좋아요" 버튼을 출력합니다. -->
            	<c:if test="${not empty memberLoginID}">
            		<input type="button" value="좋아요" class="hitButton" onclick="hit(`${contextPath}`, `${communicationContentsVO.boardIDX}`);">
            	</c:if>
            	
				<input type="button" value="목록으로" onclick="backToList('${contextPath}', `${requestPage}`);">
                
                <!-- 이 글의 작성자일 경우, 수정/삭제 버튼을 출력합니다. -->
                <c:if test="${memberLoginID eq communicationContentsVO.writerID}">
	                <form action="${contextPath}/communicationWriterView.do" method="POST">
	                	<input type="hidden" name="action" value="communicationContentsUpdate.do">
	                	<input type="hidden" name="requestBoardIDX" value="${communicationContentsVO.boardIDX}">
	                	<input type="submit" value="글수정">
	                </form>
	                
	                <input type="button" value="글삭제" onclick="deleteContents(`${contextPath}`)">
				</c:if>
            </div>
            
            
            <!-- 댓글 작성부 입니다. -->
            <div class="replyWriteContainer">
           		<c:if test="${not empty memberLoginID}">
	                <textarea class="replyTextarea" placeholder="아름다운 말은 모두를 행복하게 해요 💕"></textarea>
	                    
	                <div class="replyButtonContainer">
	                    <input type="button" class="replyButton" value="댓글달기" onclick="communicationReplyWrite();">
	                </div>
               </c:if>
            </div>
               
               
            <!-- 불러온 댓글 출력부 입니다. -->
            <div class="replyContainer"></div>            
            
            
            <!-- 댓글 페이지부 입니다. -->
            <div class="replyPageContainer"></div>
        </div>
        
        
        <!-- 푸터 페이지를 포함시킵니다. -->
        <%@ include file="/views/footer.jsp" %>
        
        
        <!-- 개시글 삭제 확인창 입니다. (팝업형식) -->
    	<div class="deleteConfirmContainer">
    		<div class="innerContainer">
	    		<h2>정말로 삭제하시겠습니까?</h2>
	    		<p>(삭제한 글은 복구할 수 없습니다)</p>
	    		<input type="button" value="삭제" onclick="deleteConfirm(`${communicationContentsVO.boardIDX}`)">
	    		<input type="button" value="취소" onclick="deleteCancel();">
	    	</div>
    	</div>
    	
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="${contextPath}/js/communicationBoard/communicationContentsView.js?ver=${communicationContentsView_js_ver}" type="text/javascript"></script>
        <script src="${contextPath}/js/communicationBoard/communicationReply.js?ver=${communicationReply_js_ver}" type="text/javascript"></script>
        
        <script type="text/javascript">
        	// 댓글 조회/출력 (communicationReplyView.js)
        	$(window).onload = loadReply(`${contextPath}`, `${communicationContentsVO.boardIDX}`, `${requestReplyPage}`, `${memberLoginID}`);
        	
			// 본문을 개행문자("\n") 단위로 분할하여 출력 합니다.        	
        	$(window).onload += initContent(`${contextPath}`, `${communicationContentsVO.boardIDX}`);
        </script>
    </body>
</html>