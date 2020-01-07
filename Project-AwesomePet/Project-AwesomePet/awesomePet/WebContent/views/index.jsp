<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	session="true"
	buffer="8kb"
	autoFlush="true"
	
	isELIgnored="false"
	
	info="메인 페이지 입니다."
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>환영합니다 AwesomePet입니다</title>
        
        <!-- CSS초기화 파일을 링크 합니다. -->
        <link rel="stylesheet" href="${contextPath}/css/initialize.css"/>
        
        <!-- 현재 페이지(index.jsp)의 CSS파일을 링크 합니다. -->
        <link rel="stylesheet" href="${contextPath}/css/index.css"/>
    </head>
    
    <body>
    	<!-- 헤더 페이지를 포함시킵니다. -->
        <%@ include file="/views/header.jsp" %>
        
        
        <section>
            <div class="family">
                <div class="gradation">
                    <div class="card">
                        <div class="face face1">
                            <h1>🏡 가족을 찾아요</h1>
                        </div>
                        
                        <div class="face face2">
                            <h1>반려동물 분양 게시판 입니다</h1>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="communication">
                <div class="gradation">
                    <div class="card">
                        <div class="face face1">
                            <h1>📸 소통해요</h1>
                        </div>
                        
                        <div class="face face2">
                            <h1>반려동물과 함께 여러분의 행복을 자랑해요</h1>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="question">
                <div class="gradation">
                    <div class="card">
                        <div class="face face1">
                            <h1>💡 궁금해요</h1>
                        </div>
                        
                        <div class="face face2">
                            <h1>입양 & 반려동물에 대한 궁금증을 해결해요</h1>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        
        <!-- 푸터 페이지를 포함시킵니다. -->
        <%@ include file="/views/footer.jsp" %>
        
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript" src="${contextPath}/js/index.js"></script>
    </body>
</html>
