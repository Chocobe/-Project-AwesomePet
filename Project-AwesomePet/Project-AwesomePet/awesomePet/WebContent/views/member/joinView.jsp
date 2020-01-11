<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= request.getContextPath() %>"/>

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>AwesomePet 회원가입</title>
        
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
        
        <!-- 초기화 -->
        <link href="${contextPath}/css/initialize.css?ver=1" rel="stylesheet" type="text/css">
		<!-- joinView.css -->
        <link href="${contextPath}/css/member/joinView.css?ver=1" rel="stylesheet" type="text/css">
    </head>
    
    <body>
    	<%@ include file="/views/header.jsp" %>
    
        <div class="joinContainer">
            <form method="POST" action="../${contextPath}/memberJoin.do">
            	<div class="titleWrap">
                	<h1 class=joinTitle>회원가입</h1>
                	<div class="invalidIDMessage"></div>
               	</div>
                
                <div class="animationBox">
                    <input type="text" class="memberID" maxlength="20" required>
                    <label class="animation">아이디</label>
                    
                    <input type="button" class="checkIDButton" value="ID검사" onclick="checkOverlapID()">
                </div>
                
                <div class="animationBox">
                    <input type="password" class="memberPW" maxlength="20" required>
                    <label class="animation">비밀번호</label>
                </div>
                
                <div class="animationBox">
                    <input type="password" class="memberPWCheck" maxlength="20" required>
                    <label class="animation">비밀번호 재확인</label>
                </div>
                
                <div class="animationBox">
                    <input type="text" class="memberName" maxlength="12" required>
                    <label class="animation">이름</label>
                </div>
                
                <div class="fixedBox">
                    <label>생년월일</label>
                    
                    <div>
                        <input type="text" class="memberBirthDayYear" maxlength="4" required>
                        <span class="delim"> - </span> 
                        <input type="text" class="memberBirthDayMonth" maxlength="2" required>
                        <span class="delim"> - </span> 
                        <input type="text" class="memberBirthDayDay" maxlength="2" required>
                    </div>
                </div>
                
                <div class="animationBox">
                    <input type="text" class="memberEmail" maxlength="50" required>
                    <label>이메일</label>
                </div>
                
                <div class="fixedBox">
                    <label>휴대전화</label>
                    
                    <div>
                        <input type="text" class="memberPhone_1" maxlength="3" required>
                        <span class="delim"> - </span> 
                        <input type="text" class="memberPhone_2" maxlength="4" required>
                        <span class="delim"> - </span> 
                        <input type="text" class="memberPhone_3" maxlength="4" required>
                    </div>
                </div>
                
                <div class="animationBox">
                    <input type="text" class="memberAddr" maxlength="50" required>
                    <label>주소</label>
                </div>
                
                <div class="submitBox">
                    <input type="submit" value="회원가입" disabled>
                    <input type="reset" value="다시작성">
                </div>
            </form>    
        </div>
        
        <%@ include file="/views/footer.jsp" %>
        
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript">        	
        	// submit버튼 입니다.
        	const submitButton = $(".joinContainer form .submitBox input[type='submit']");
        	const idInputBox = $(".joinContainer form .animationBox .memberID");
        	const errorMessage = $(".joinContainer form .titleWrap .invalidIDMessage");    
        	const checkIDButton = $(".joinContainer form .animationBox .checkIDButton");
        
        	
        	// 초기화
        	$(function() {
        		submitButton.attr("disabled", true);
        		
        		idInputBox.change(function(event) {
        			event.preventDefault();
        			
        			// 공백이 있을 경우 : 0 ~ 양수 값을 반환 합니다.
        			if(idInputBox.val().indexOf(" ") != -1) {
        				errorMessage.text("ID에는 공백문자를 사용할 수 없습니다");
        				checkIDButton.attr("disabled", true);
        				
        			} else {
        				errorMessage.text("ID 중복검사를 다시 눌러주세요");
        				checkIDButton.attr("disabled", false);
        			}
        			
        			submitButton.attr("disabled", true);
        		});
        	});
        	
        	
        	// ID 중복검사 메서드 입니다. (ajax를 이용한 비동기 요청)
        	function checkOverlapID() {
        		const inputID = $(".memberID").val();
        		
        		$.ajax({
        			type: "POST",
        			url: "http://localhost:8096/awesomePet/checkOverlapID.do",
        			async: true,
        			dataType: "TEXT",
        			data: {id : inputID},
        			success: function(data, textStatus) {
        				const isOverlapID = JSON.parse(data);
        				
        				if(isOverlapID) {
        					invalidFunc(inputID);
        					
        				} else {
        					validIDFunc(inputID);
        				}
        			},
        			error: function(data, textStatus) {
        				// ID 중복검사 실패
        				
        				alert("ajax 실패");
        			}
        		});
        	}
        	
        	
        	// ID를 사용할 수 있는 경우, 호출되는 메서드 입니다.
        	function invalidFunc(id) {
        		// ID 중복 시, 호출 메서드
        		errorMessage.text(id + " 는 사용할 수 없습니다");
        		
        		isInvalidID = true;
        		submitButton.attr("disabled", true);
        		
        		checkIDButton.attr("disabled", false);
        	}
        	
        	
        	// ID가 중복될 경우, 호출되는 메서드 입니다.
        	function validIDFunc(id) {
        		// ID 사용가능 시, 호출 메서드
        		errorMessage.text("");
        		
        		isInvalidID = false;
        		submitButton.attr("disabled", false);
				
        		checkIDButton.attr("disabled", true);
        	}
        </script>
    </body>
</html>