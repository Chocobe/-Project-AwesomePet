const replyContainer = $(".questionContentsWrap .replyContainer");
const replyPageContainer = $(".questionContentsWrap .replyPageContainer");
const replyTextarea = $(".replyWriteContainer .replyTextarea");

let contextPath = null;
let parentIDX = null;
let requestReplyPage = null;
let memberLoginID = null;

let originReplyContentsArr = null;
	
	
// 댓글을 조회합니다.
	function loadReply(context, parent, replyPage, loginID) {
		$.ajax({
			type: "GET",
			async: true,
			url: context + "/questionReplyView.do",
			datatype: "TEXT",
			data: {
				"parentIDX": parent,
				"requestReplyPage": replyPage
			},
			success: function(resultData, status) {
				// 1. 기존에 전역변수를 갱신하고, 출력한 댓글과 댓글 페이지를 지웁니다. (초기화)
				initReply(context, parent, replyPage, loginID);
				
				// 2. 결과 문자열을 JSON형으로 변환합니다.
				const parsedJSON = JSON.parse(resultData);
				
				// 3. 조회한 댓글을 출력합니다.
				printReply(parsedJSON);
				
				// 4. 댓글 페이지를 출력합니다.
				printReplyPage(parsedJSON);
			}
		});
	}
	
	
	// 1. 기존에 전역변수를 갱신하고, 출력한 댓글과 댓글 페이지를 지웁니다. (초기화)
	function initReply(context, parent, replyPage, loginID) {
		contextPath = context;
		parentIDX = parent;
		requestReplyPage = replyPage;
		memberLoginID = loginID;
		
		replyContainer.empty();
		replyPageContainer.empty();
	}
	
	
	// 3. 조회한 댓글을 출력합니다.
	function printReply(parsedJSON) {
		const questionReplyContentsList = parsedJSON.questionReplyContentsList;
		
		// 출력하기 위한 태그요소들을 생성합니다.
		for(let i in questionReplyContentsList) {
			// 1. 각각의 댓글이 들어가는 <div class="reply">
			const reply = $("<div>").attr({"class": "reply"});
			
			// 2. 이전 댓글과 구분하기 위한 <hr/>
			const hr = $("<hr>");
			
			// 3. 댓글의 ID값을 hidden으로 생성
			const replyIDX = $("<input>").attr({"type": "hidden", "class": "replyIDX" ,"value": questionReplyContentsList[i].replyIDX});
			
			// 4. 댓글 헤드부(작성자, 작성일, 수정버튼, 삭제버튼) <div class=".replyTitleContainer">
			const replyTitleContainer = $("<div>").attr({"class": "replyTitleContainer"});
			
			// 5. 작성자
			const writerID = $("<p>");
			writerID.text(questionReplyContentsList[i].writerID);
			
			// 6. 작성일
			const writeDate = $("<p>");
			const currentDate = questionReplyContentsList[i].writeDate;
			writeDate.text("(" + currentDate.year + "-" + currentDate.month + "-" + currentDate.day + ")");
			
			// 7. 수정버튼
			const fixButton = $("<input>").attr({"type": "button", "value": "🔧", "onclick": "changeFormUpdateReply(this);"});
			
			// 8. 삭제버튼
			const deleteButton = $("<input>").attr({"type": "button", "value": "❌", "onclick": "questionReplyDelete(this);"});
			
			// 9. 생성한 요소들을 조합 합니다.
			reply.append(hr);
			reply.append(replyIDX);
			reply.append(replyTitleContainer);
			
			replyTitleContainer.append(writerID);
			replyTitleContainer.append(writeDate);
			
			if(memberLoginID == questionReplyContentsList[i].writerID) {
				replyTitleContainer.append(fixButton);
				replyTitleContainer.append(deleteButton);
			}
			
			// 10. 개행문자 단위로 분할하여 출력합니다.
			const splitedContents = questionReplyContentsList[i].content.trim().split("\n");
			for(let i in splitedContents) {
				const currentContent = $("<p>").text(splitedContents[i]);
				reply.append(currentContent);
			}
			
			replyContainer.append(reply);
			
			/* 생성할 <div class="reply"> 형식 입니다.
	            <div class="reply">
	                <hr/>
	                
	                <div class="replyTitleContainer">
	                	<input type="hidden" class=".replyIDX" value="">
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
		}
	}
	
	
	// 4. 댓글 페이지를 출력합니다.
	function printReplyPage(parsedJSON) {
		let boundary = null;
		
		// 1. 댓글 "시작" 링크
		const firstPage = $("<a>").attr({"onclick": "loadReply(`${contextPath}`, `${parentIDX}`, `1`, `${memberLoginID}`);"});
		firstPage.text("시작");
		boundary = $("<p>").text("|");
		
		replyPageContainer.append(firstPage);
		replyPageContainer.append(boundary);
		
		// 2. 댓글 "이전" 링크
		const beginPage = $("<a>").attr({"onclick": "loadReply(`${contextPath}`, `${parentIDX}`, " + parsedJSON.prevPage + ", `${memberLoginID}`);"});
		beginPage.text("이전");
		boundary = $("<p>").text("|");
		
		replyPageContainer.append(beginPage);
		replyPageContainer.append(boundary);
		
		// 3. 댓글 "페이지" 링크
		for(let i = parsedJSON.beginPage; i <= parsedJSON.endPage; i++) {
			let currentPage = null;
			
			// 현재 페이지는 <p>로 출력합니다.
			if(i == parsedJSON.currentPage) {
				currentPage = $("<p>").css({"color": "red"});
				currentPage.text(i);
				
			} else {
				currentPage = $("<a>").attr({"onclick": "loadReply(`${contextPath}`, `${parentIDX}`, " + i + ", `${memberLoginID}`);"});
				currentPage.text(i);
			}
			
			boundary = $("<p>").text("|");
			
			replyPageContainer.append(currentPage);
			replyPageContainer.append(boundary);
		}
		
		// 4. 댓글 "다음" 링크	
		const nextPage = $("<a>").attr({"onclick": "loadReply(`${contextPath}`, `${parentIDX}`, " + parsedJSON.nextPage + ", `${memberLoginID}`);"});
		nextPage.text("다음");
		boundary = $("<p>").text("|");
		
		replyPageContainer.append(nextPage);
		replyPageContainer.append(boundary);
		
		// 5. 댓글 "끝" 링크
		const lastPage = $("<a>").attr({"onclick": "loadReply(`${contextPath}`, `${parentIDX}`, " + parsedJSON.totalPageCnt + ", `${memberLoginID}`);"});
		lastPage.text("끝");
		
		replyPageContainer.append(lastPage);
	}
	
	
// 댓글을 작성합니다.
	function questionReplyWrite() {
		const inputValue = replyTextarea.val();
		
		if(inputValue.trim() == "") {
			replyTextarea.val("");
			
		} else {
			$.ajax({
				type: "POST",
				async: true,
				url: contextPath + "/questionReplyWrite.do",
				data: {
					"parentIDX": parentIDX,
					"inputValue": inputValue
				},
				datatype: "TEXT",
				success: function(resultData, status) {
					loadReply(contextPath, parentIDX, 1);
				},
				complete: function(resultData, status) {
					replyTextarea.val("");
				}
			});
		}
	}
	
	
// 댓글을 수정합니다.
	// 대상 댓글을 수정상태로 변경합니다.
	function changeFormUpdateReply(target) {
		// 현재 댓글의 부모를 선택합니다.
		const thisReplyContainer = $(target).parent().parent();
		
		// 현재 댓글의 내용부분을 배열로 가져옵니다.
		const contentsArr = thisReplyContainer.children("p");
		
		// 원본 댓글을 임시 보존합니다.
		originReplyContentsArr = contentsArr;
		
		// 원본 댓글의 출력형식을 만듭니다.
		let originValue = "";
		for(let i = 0; i < contentsArr.length; i++) {
			originValue += contentsArr[i].innerText + "\n";
			$(contentsArr[i]).remove();
		}
		
		// 댓글 수정 형식에 맞도록 요소들을 생성합니다.
		const replyWriteContainer = $("<div>").attr({"class": "replyWriteContainer"});
		
		const replyTextarea = $("<textarea>").attr({"class": "replyTextarea"});
		const replyButtonContainer = $("<div>").attr({"class": "replyButtonContainer"});
		const updateButton = $("<input>").attr({"type": "button", "class": "replyButton", "value": "수정하기", "onclick": "questionReplyUpdate(this);"});
		
		replyTextarea.val(originValue);
		replyWriteContainer.append(replyTextarea);
		
		replyButtonContainer.append(updateButton);
		replyWriteContainer.append(replyButtonContainer);
		
		thisReplyContainer.append(replyWriteContainer);
		
		// 현재 댓글 내용을 <textarea>로 출력합니다.
		/*
			<div class="replyWriteContainer">
	            <textarea class="replyTextarea" placeholder="아름다운 말은 모두를 행복하게 해요 💕"></textarea>
	                
	            <div class="replyButtonContainer">
	                <input type="button" class="replyButton" value="댓글달기" onclick="questionReplyWrite();">
	            </div>
		    </div>
		*/
	}
	
	
	// 수정한 댓글을 갱신합니다.
	function questionReplyUpdate(target) {
		const thisTextareaContainer = $(target).parent().parent();
		const thisReplyContainer = $(thisTextareaContainer).parent();
		
		// 수정한 댓글내용을 가져옵니다.
		const inputValue = thisTextareaContainer.children("textarea")[0].value;
		alert(inputValue);
		
		if(inputValue.length == 0) {
			thisTextareaContainer.remove();
			
			for(let i = 0; i < originReplyContentsArr.length; i++) {
				thisReplyContainer.append(originReplyContentsArr[i]);
			}
			
			return;
		}
		
		// 현재 댓글의 replyIDX값을 가져옵니다.
		const replyIDX = $(thisReplyContainer).children(".replyIDX")[0].value;
		alert(replyIDX);
		
		// 댓글을 갱신합니다.
		$.ajax({
			type: "POST",
			async: true,
			url: contextPath + "/questionReplyUpdate.do",
			datatype: "TEXT",
			data: {
				"replyIDX": replyIDX,
				"content": inputValue
			},
			success: function(resultContents, status) {
				alert("댓글 수정 success() 메서드");
				
				alert("수정한 댓글 :\n" + resultContents);
				
				thisTextareaContainer.remove();
				
				const resultContentsArr = resultContents.trim().split("\n");
				for(let i in resultContentsArr) {
					const currentContent = $("<p>").text(resultContentsArr[i]);
					thisReplyContainer.append(currentContent);
				}
			}
		});
	}
	
	
// 댓글을 삭제 합니다.
	function questionReplyDelete(target) {
		const replyIDX = $(target).parent().parent().children(".replyIDX")[0].value;
		
		location.href = contextPath + "/questionReplyDelete.do?parentIDX=" + parentIDX + "&requestReplyIDX=" + replyIDX;
	}