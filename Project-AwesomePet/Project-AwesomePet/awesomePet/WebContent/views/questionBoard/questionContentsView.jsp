<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	isELIgnored="false"
	
	info="궁금해요 글 페이지 입니다."
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<%
	String initialize_css = application.getRealPath("/css/initialize.css");
	File initialize_css_file = new File(initialize_css);
	Date initialize_css_ver = new Date(initialize_css_file.lastModified());
	
	String questionContentsView_css = application.getRealPath("/css/questionBoard/questionContentsView.css");
	File questionContentsView_css_file = new File(questionContentsView_css);
	Date questionContentsView_css_ver = new Date(questionContentsView_css_file.lastModified());
	
	String questionContentsView_js = application.getRealPath("/js/questionBoard/questionContentsView.js");
	File questionContentsView_js_file = new File(questionContentsView_js);
	Date questionContentsView_js_ver = new Date(questionContentsView_js_file.lastModified());
%>

<c:set var="initialize_css_ver" value="<%= initialize_css_ver %>"/>
<c:set var="questionContentsView_css_ver" value="<%= questionContentsView_css_ver %>"/>
<c:set var="questionContentsView_js_ver" value="<%= questionContentsView_js_ver %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>글 제목으로 변경할것</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
        
		<link rel="stylesheet" href="${contextPath}/css/initialize.css?ver=${initialize_css_ver}" type="text/css">        
		<link href="${contextPath}/css/questionBoard/questionContentsView.css?ver=${questionContentsView_css_ver}" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<!-- 헤더 페이지를 포함시킵니다. -->
    	<%@ include file="/views/header.jsp" %>

    	
    	<div class="questionTitleContainer">
		    <h1>💡 궁금해요</h1>
		    <p>애완동물에 대한 질문 게시판 입니다.</p>
		</div>
    
        <div class="questionContentsWrap">
       		<!-- 글 제목부 입니다. (제목, 작성자, 작성일, 조회수) -->
            <div class="titleContainer">
                <h1>${questionContentsVO.title}</h1>
                
                <table class="infoTable">
                    <tr>
                        <th>작성자</th>
                        <td>${questionContentsVO.writerID}</td>
                    </tr>
                    
                    <tr>
                        <th>작성일</th>
                        <td>${questionContentsVO.writeDate}</td>
                    </tr>
                    
                    <tr>
                        <th>조회수</th>
                        <td>${questionContentsVO.watch} 명</td>
                    </tr>
                </table>
            </div>
            
            
			<!-- 본문 입니다. -->
            <div class="contentsContainer">
            	<!-- javascript에서 hidden값을 분할하여 출력합니다. -->
            	<input type="hidden" class="requestContent" value="${questionContentsVO.content}">
            </div>
            
            
            <!-- 글 처리 버튼입니다. (목록으로 이동, 수정, 삭제) -->
            <div class="optionButtonsContainer">
				<input type="button" value="목록으로" onclick="backToList('${contextPath}', `${requestPage}`);">
                
                <!-- 이 글의 작성자일 경우, 수정/삭제 버튼을 출력합니다. -->
                <c:if test="${memberLoginID eq questionContentsVO.writerID}">
	                <form action="${contextPath}/questionWriterView.do" method="POST">
	                	<input type="hidden" name="action" value="questionContentsUpdate.do">
	                	<input type="hidden" name="requestBoardIDX" value="${questionContentsVO.boardIDX}">
	                	<input type="submit" value="글수정">
	                </form>
	                
	                <input type="button" value="글삭제" onclick="deleteContents(`${contextPath}`)">
				</c:if>
            </div>
            
            
            <!-- 댓글 작성부 입니다. -->
            <div class="replyWriteContainer">
                <textarea class="replyTextarea" placeholder="아름다운 말은 모두를 행복하게 해요 💕"></textarea>
                    
                <div class="replyButtonContainer">
                    <input type="button" class="replyButton" value="댓글달기" onclick="">
                </div>
            </div>
               
               
            <!-- 불러온 댓글 출력부 입니다. -->
            <div class="replyContainer"></div>            
            
            
            <!-- 댓글 페이지부 입니다. -->
            <div class="replyPageContainer">
                <a href="#">시작</a>
                <p>|</p>
                <a href="#">이전</a>
                <p>|</p>
                <a href="#">1</a>
                <p>|</p>
                <a href="#">2</a>
                <p>|</p>
                <a href="#">3</a>
                <p>|</p>
                <a href="#">4</a>
                <p>|</p>
                <a href="#">5</a>
                <p>|</p>
                <a href="#">다음</a>
                <p>|</p>
                <a href="#">끝</a>
            </div>
        </div>
        
        
        <!-- 푸터 페이지를 포함시킵니다. -->
        <%@ include file="/views/footer.jsp" %>
        
        
        <!-- 삭제 확인창 입니다. -->
    	<div class="deleteConfirmContainer">
    		<div class="innerContainer">
	    		<h2>정말로 삭제하시겠습니까?</h2>
	    		<p>(삭제한 글은 복구할 수 없습니다)</p>
	    		<input type="button" value="삭제" onclick="deleteConfirm(`${contextPath}`, `${questionContentsVO.boardIDX}`)">
	    		<input type="button" value="취소" onclick="deleteCancel();">
	    	</div>
    	</div>
    	
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="${contextPath}/js/questionBoard/questionContentsView.js?ver=${questionContentsView_js_ver}" type="text/javascript"></script>
        
        <script type="text/javascript">
        	const replyContainer = $(".questionContentsWrap .replyContainer");
        
        	function myTest(contextPath, parentIDX, requestReplyPage) {
        		$.ajax({
        			type: "GET",
        			async: true,
        			url: contextPath + "/questionReplyView.do",
        			datatype: "TEXT",
        			data: {
        				"parentIDX": parentIDX,
        				"requestReplyPage": requestReplyPage
        			},
        			success: function(resultData, status) {
        			// 1. 조회 결과를 분할합니다.
        				// 1-1. 결과 문자열을 JSON형으로 변환합니다.
        				const parsedJSON = JSON.parse(resultData);
        				
        				// 1-2. 페이지 관련 데이터를 추출합니다.
        				const totalPageCnt = parsedJSON.totalPageCnt;
        				let testString = "totalPageCnt : " + totalPageCnt + "\n";
        				testString += ("currentPage : " + parsedJSON.currentPage + "\n");
        				testString += ("prevPage : " + parsedJSON.prevPage + "\n");
        				testString += ("nextPage : " + parsedJSON.nextPage + "\n");
        				testString += ("beginPage : " + parsedJSON.beginPage + "\n");
        				testString += ("endPage : " + parsedJSON.endPage);
        					
        				alert(testString);
        				
        				// 1-3. 리플을 출력합니다.
        				const questionReplyContentsList = parsedJSON.questionReplyContentsList;
        				for(let i in questionReplyContentsList) {
        			
        					// 출력하기 위한 태그요소들을 생성합니다.
            				// 각각의 댓글이 들어가는 <div class="reply">
            				const reply = $("<div>").attr({"class": "reply"});
            				
            				// 이전 댓글과 구분하기 위한 <hr/>
            				const hr = $("<hr>");
            				
            				// 댓글 헤드부(작성자, 작성일, 수정버튼, 삭제버튼) <div class=".replyTitleContainer">
            				const replyTitleContainer = $("<div>").attr({"class": "replyTitleContainer"});
            				// 작성자
            				const writerID = $("<p>");
            				writerID.text(questionReplyContentsList[i].writerID);
            				
            				// 작성일
            				const writeDate = $("<p>");
            				const currentDate = questionReplyContentsList[i].writeDate;
            				writeDate.text("(" + currentDate.year + "-" + currentDate.month + "-" + currentDate.day + ")");
            				
            				// 수정버튼
            				const fixButton = $("<input>").attr({"type": "button", "value": "🔧", "onclick": "what1();"});
            				
            				// 삭제버튼
            				const deleteButton = $("<input>").attr({"type": "button", "value": "❌", "onclick": "what2();"});

            				reply.append(hr);
            				reply.append(replyTitleContainer);
            				
            				replyTitleContainer.append(writerID);
            				replyTitleContainer.append(writeDate);
            				replyTitleContainer.append(fixButton);
            				replyTitleContainer.append(deleteButton);
            				
            				// 개행문자 단위로 분할하여 출력합니다.
            				const splitedContents = questionReplyContentsList[i].content.split("\n");
            				for(let i in splitedContents) {
            					const currentContent = $("<p>").text(splitedContents[i]);
            					reply.append(currentContent);
            				}
            				
            				/* 생성할 <div class="reply"> 형식 입니다.
            		            <div class="reply">
            		                <hr/>
            		                
            		                <div class="replyTitleContainer">
            		                    <p class="replyWriterID">작성자ID</p>
            		                    <p class="writeDate">(2020-01-21)</p>
            		                    <input type="button" value="🔧" onclick="">
            		                    <input type="button" value="❌" onclick="">
            		                </div>
            		                
            		                <p>안녕하세요</p>
            		                <p>리플 테스트 중입니다.</p>
            		                <p>리플 테스트 중입니다.</p>
            		            </div>
            	        	*/
        					
            				replyContainer.append(reply);
        				}
        			}
        		});
        	}
        	myTest(`${contextPath}`, `${questionContentsVO.boardIDX}`,`${requestReplyPage}`);
        	
        	
        	function what1() {
        		alert("what_1");
        	}
        	
        	
        	function what2() {
        		alert("what_2");
        	}
        </script>
    </body>
    

</html>