<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
	
	info="마이페이지 입니다"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>


<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>마이 페이지</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap?ver=1" rel="stylesheet">
        
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
            
            
        /* section */
            section {
                width: 100%;
                padding: 30px;
                
                border-radius: 20px;
                
                background: url(/awesomePet/appImages/loginBackground.jpg);
                background-size: cover;
                background-position: center;
                
                display: flex;
                flex-flow: row wrap;
                justify-content: center;
            }
            
            
        /* .buttonsWrap */
            .buttonsWrap {                
                width: 100%;
                
                border-radius: 10px;
                
                padding: 30px;
                
                display: flex;
                flex-flow: row wrap;
                justify-content: space-around;
                
                background: rgba(0, 0, 0, 0.7);
            }
            
            .buttonsWrap div {
                width: 200px;
                height: 100px;
                
                border-radius: 10px;
                
                position: relative;
                
                display: flex;
                flex-flow: row wrap;
                justify-content: center;
                align-items: center;
                
                background: peru;
            }
            
            .buttonsWrap div a {
                display: inline-block;
                width: 100%;
                
                text-align: center;
            }
            
            
        /* .adoptWrap */
            .adoptWrap {
                margin-top: 30px;
                padding: 30px;
                
                width: 600px;
                
                display: flex;
                flex-flow: row wrap;
                justify-content: center;
                
                background: rgba(0, 0, 0, 0.7);
            }
            
            .adoptWrap table {
                border-collapse: collapse;
                
                background: rgba(255, 255, 255, 0.5);
            }
            
            .adoptWrap table tr {
                height: 60px;
            }
            
            .adoptWrap table tr .picture {
                text-align: center;
            }
            
            .adoptWrap table tr .itemName {
                text-align: right;
            }
            
            .adoptWrap table tr .itemContent {
                text-align: center;
            }
            
            
        /* .fixButtonWrap */
            .fixButtonWrap {
                width: 100%;
                
                margin-top: 30px;
                
                display: flex;
                justify-content: center;
            }
            
            .fixButtonWrap a {
                width: 200px;
                height: 50px;
                
                color: #fff;
                font-size: 18px;
                text-align: center;
                line-height: 50px;
                
                background: #03a9f4;
                
                border-radius: 10px;
                
                transition: 0.5s;
            }
            
            .fixButtonWrap a:hover {
                background: #ff0058;
            }
        </style>
    </head>
    
    <body>
    	<!-- 헤더 페이지를 포함시킵니다. -->
    	<%@ include file="/views/header.jsp" %>
    
    
        <section>
            <div class="buttonsWrap">
                <div class="findFamily">
                    <a href="#">
                        가족을 찾아요 찜<br/>
                        (반사 에니메이션)
                    </a>
                </div>
                
                <div class="communication">
                    <a href="#">
                        소통해요 작성 글<br/>
                        (반사 에니메이션)
                    </a>
                </div>
                
                <div class="question">
                    <a href="#">
                        궁금해요 작성 글<br/>
                        (반사 에니메이션)
                    </a>
                </div>
            </div>
            
            <div class="adoptWrap">
                <table>
                    <colgroup>
                        <col width="150px">
                        <col width="100px">
                        <col width="300px">
                    </colgroup>
                    
                    <tr>
                        <td rowspan="3" class="picture">사진</td>
                        <td class="itemName">품종 :&nbsp;</td>
                        <td class="itemContent">진도</td>
                    </tr>
                    
                    <tr>
                        <td class="itemName">이름 :&nbsp;</td>
                        <td class="itemContent">진돌이</td>
                    </tr>
                    
                    <tr>
                        <td class="itemName">입양날짜 :&nbsp;</td>
                        <td class="itemContent">2020년 1월 13일</td>
                    </tr>
                </table>
            </div>
            
            <div class="fixButtonWrap">
                <a href="${contextPath}/myInfoCertificateView.do">내정보 수정</a>
            </div>
        </section>
        
        
        <!-- 푸터 페이지를 포함시킵니다. -->
        <%@ include file="/views/footer.jsp" %>
    </body>
</html>