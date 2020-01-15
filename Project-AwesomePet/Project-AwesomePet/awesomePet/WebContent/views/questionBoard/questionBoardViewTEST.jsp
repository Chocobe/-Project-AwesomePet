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
					
					<tr>
						<td>데이터</td>
						<td>데이터</td>
						<td>데이터</td>
						<td>데이터</td>
						<td>데이터</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>