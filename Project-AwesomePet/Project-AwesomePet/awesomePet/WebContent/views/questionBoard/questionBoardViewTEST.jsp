<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	session="true"
	buffer="8kb"
	autoFlush="true"
	
	isELIgnored="false"
	
	info="질문 게시판 입니다"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>질문 게시판 입니다.</title>
		
		<style type="text/css">
					
		/* 초기화 */
			* {
			    margin: 0;
			    padding: 0;
			    box-sizing: border-box;
			    
			    font-family: 'Jua', sans-serif;
			}
			
			body {
			    width: 1024px;
			    margin: 0 auto;
			}
			
			a, a:hover, 
			a:visited {
			    text-decoration: none;
			    color: black;
			}
			
			
		/* 테이블 */
			.questionBoardContainer {
				width: 1024px;
				
				padding: 30px;
				
				position: relative;
				
				display: flex;
				flex-flow: row wrap;
				justify-content: center;
			}
			
			.questionBoardContainer h1 {
				width: 100%;
				
				margin-left: 50px;
			}
			
			.questionBoardContainer p {
				width: 100%;
				
				margin: 20px 50px;
			}
		</style>
	</head>
	
	<body>
		<div class="questionBoardContainer">
			<h1>궁금해요</h1>
			<p>애완동물에 대한 질문 게시판 입니다.</p>
		
			<div class="questionTable">
				<table border="1">
					<tr> 
						<td>글번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
					</tr>
				
				<c:forEach var="contents" items="${questionBoardVO.questionContentsList}">
					<tr>
						<td>${contents.boardIDX}</td>
						<td>${contents.writerID}</td>
						<td>${contents.title}</td>
						<td>${contents.content}</td>
						<td>${contents.watch}</td>
					</tr>
				</c:forEach>
				</table>
				
				<div class="pageTest">
					<a href="..${contextPath}/questionBoardView.do?requestPage=${1}">첫페이지</a>
					
					<a href="..${contextPath}/questionBoardView.do?requestPage=${questionBoardVO.prevPage}">이전페이지</a>
					
					<c:forEach var="page" begin="1" end="${questionBoardVO.totalPageCnt}">
						<c:choose>
							<c:when test="${page eq questionBoardVO.currentPage}">
								<a href="..${contextPath}/questionBoardView.do?requestPage=${page}" style="color: #03a9f4;">${page}</a>
							</c:when>

							<c:otherwise>				
								<a href="..${contextPath}/questionBoardView.do?requestPage=${page}">${page}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<a href="..${contextPath}/questionBoardView.do?requestPage=${questionBoardVO.nextPage}">다음페이지</a>
					
					<a href="..${contextPath}/questionBoardView.do?requestPage=${questionBoardVO.totalPageCnt}">끝페이지</a>
				</div>
				
				<div class="questionPage">
					<p>궁금해요 총 페이지 개수 : ${questionBoardVO.totalPageCnt}</p>
					<p>요청한 페이지 번호 : ${questionBoardVO.currentPage}</p>
					<p>이전 페이지 번호 : ${questionBoardVO.prevPage}</p>
					<p>다음 페이지 번호 : ${questionBoardVO.nextPage}</p>
				</div>
			</div>
		</div>
	</body>
</html>