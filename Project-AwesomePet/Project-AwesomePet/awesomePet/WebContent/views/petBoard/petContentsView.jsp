<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	isELIgnored="false"
	
	info="가족을 찾아요 글 페이지 입니다."
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<%
	String initialize_css = application.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	String petContentsView_css = application.getRealPath("/css/petBoard/petContentsView.css");
	File petContentsView_css_file = new File(petContentsView_css);
	Date petContentsView_css_ver = new Date(petContentsView_css_file.lastModified());
	
	String petContentsView_js = application.getRealPath("/js/petBoard/petContentsView.js");
	File petContentsView_js_file = new File(petContentsView_js);
	Date petContentsView_js_ver = new Date(petContentsView_js_file.lastModified());
	
	String petReply_js = application.getRealPath("/js/petBoard/petReply.js");
	File petReply_js_file = new File(petReply_js);
	Date petReply_js_ver = new Date(petReply_js_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="petContentsView_css_ver" value="<%= petContentsView_css_ver %>"/>
<c:set var="petContentsView_js_ver" value="<%= petContentsView_js_ver %>"/>
<c:set var="petReply_js_ver" value="<%= petReply_js_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>글 제목으로 변경할것</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
        
		<link rel="stylesheet" href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" type="text/css">        
		<link href="${contextPath}/css/petBoard/petContentsView.css?ver=${petContentsView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<!-- 헤더 페이지를 포함시킵니다. -->
    	<%@ include file="/views/header.jsp" %>

    	
    	<div class="petTitleContainer">
		    <h1>🏡 가족을 찾아요</h1>
		    <p>반려동물 분양 게시판 입니다.</p>
		</div>
    
        <div class="petContentsWrap">
       		<!-- 글 제목부 입니다. (제목, 작성자, 작성일, 조회수) -->
            <div class="titleContainer">
                <h1>(${petBoardVO.currentTypeName}) ${petBoardVO.currentSubTypeName}</h1>
                
                <table class="infoTable">
                    <tr>
                        <th>작성일</th>
                        <td>${petBoardVO.petContentsList[0].writeDate}</td>
                    </tr>
                    
                    <tr>
                        <th>조회수</th>
                        <td>${petBoardVO.petContentsList[0].watch}</td>
                    </tr>
                </table>
            </div>
            
            
			<!-- 본문 입니다. -->
            <div class="contentsContainer">
            	<!-- javascript에서 hidden값을 분할하여 출력합니다. -->
				<!-- 코멘트 출력부 : initComment() 에서 첨부 합니다. -->
            
            	<br/><br/>
            
            	<p class="contents">나이 : ${petBoardVO.petList[0].age}살</p>
            	<p class="contents">성별 : ${petBoardVO.petList[0].gender}</p>
            	<p class="contents">예방접종 : ${petBoardVO.petList[0].vaccination}</p>
            	<p class="contents">중성화 : ${petBoardVO.petList[0].neutralization}</p>
            	<p class="contents">책임비 : ${petBoardVO.petList[0].price}원</p>
            
            	<br/><br/><br/><br/>
            	
            	<!-- 이미지 출력부 -->
            	<c:forEach var="petImageVO" items="${petBoardVO.petList[0].imageList}">
            		<img src="${petImageVO.imgLocation}">
            	</c:forEach>
				
            	<input type="hidden" class="requestContent" value="${petBoardVO.currentSubTypeComment}">
            </div>
            
            
            <!-- 글 처리 버튼입니다. (목록으로 이동, 수정, 삭제) -->
            <div class="optionButtonsContainer">
				<input type="button" value="목록으로" onclick="backToList('${contextPath}', `${requestPage}`, `${requestTypeName}`, `${requestSubTypeName}`);">
				
                <!-- 이 글의 작성자일 경우, 수정/삭제 버튼을 출력합니다. -->
                <c:if test="${memberLoginID eq petBoardVO.petContentsList[0].writerID}">
	                <input type="button" value="글삭제" onclick="deleteContents(`${contextPath}`)">
				</c:if>
            </div>
            
            
            <!-- 댓글 작성부 입니다. -->
            <div class="replyWriteContainer">
           		<c:if test="${not empty memberLoginID}">
	                <textarea class="replyTextarea" placeholder="아름다운 말은 모두를 행복하게 해요 💕"></textarea>
	                    
	                <div class="replyButtonContainer">
	                    <input type="button" class="replyButton" value="댓글달기" onclick="petReplyWrite();">
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
	    		<input type="button" value="삭제" onclick="deleteConfirm(`${contextPath}`,`${petBoardVO.petContentsList[0].boardIDX}`);">
	    		<input type="button" value="취소" onclick="deleteCancel();">
	    	</div>
    	</div>
    	
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="${contextPath}/js/petBoard/petContentsView.js?ver=${petContentsView_js_ver}" type="text/javascript"></script>
        <script src="${contextPath}/js/petBoard/petReply.js?ver=${petReply_js_ver}" type="text/javascript"></script>
        
        <script type="text/javascript">
        	// 댓글 조회/출력 (petReplyView.js)
        	$(window).onload = loadReply(`${contextPath}`, `${petBoardVO.petContentsList[0].boardIDX}`, `${requestReplyPage}`, `${memberLoginID}`);
        </script>
    </body>
</html>