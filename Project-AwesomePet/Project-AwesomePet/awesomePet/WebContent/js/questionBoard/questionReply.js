const replyContainer = $(".questionContentsWrap .replyContainer");
const replyPageContainer = $(".questionContentsWrap .replyPageContainer");

let contextPath = null;
let parentIDX = null;
let requestReplyPage = null;


// 댓글을 조회합니다.
function loadReply(context, parent, replyPage) {
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
			// 1. 기존에 출력한 댓글과 댓글 페이지를 지웁니다. (초기화)
			initReply(context, parent, replyPage);
			
			// 2. 결과 문자열을 JSON형으로 변환합니다.
			const parsedJSON = JSON.parse(resultData);
			
			// 3. 조회한 댓글을 출력합니다.
			printReply(parsedJSON);
			
			// 4. 댓글 페이지를 출력합니다.
			printReplyPage(parsedJSON);
		}
	});
}


// 댓글과 댓글 페이지를 초기화 합니다.
function initReply(context, parent, replyPage) {
	contextPath = context;
	parentIDX = parent;
	requestReplyPage = replyPage;
	
	replyContainer.empty();
	replyPageContainer.empty();
}


// 조회한 댓글을 출력합니다.
function printReply(parsedJSON) {
	const questionReplyContentsList = parsedJSON.questionReplyContentsList;
	
	// 출력하기 위한 태그요소들을 생성합니다.
	for(let i in questionReplyContentsList) {
		// 1. 각각의 댓글이 들어가는 <div class="reply">
		const reply = $("<div>").attr({"class": "reply"});
		
		// 2. 이전 댓글과 구분하기 위한 <hr/>
		const hr = $("<hr>");
		
		// 3. 댓글 헤드부(작성자, 작성일, 수정버튼, 삭제버튼) <div class=".replyTitleContainer">
		const replyTitleContainer = $("<div>").attr({"class": "replyTitleContainer"});
		
		// 4. 작성자
		const writerID = $("<p>");
		writerID.text(questionReplyContentsList[i].writerID);
		
		// 5. 작성일
		const writeDate = $("<p>");
		const currentDate = questionReplyContentsList[i].writeDate;
		writeDate.text("(" + currentDate.year + "-" + currentDate.month + "-" + currentDate.day + ")");
		
		// 6. 수정버튼
		const fixButton = $("<input>").attr({"type": "button", "value": "🔧", "onclick": "what1();"});
		
		// 7. 삭제버튼
		const deleteButton = $("<input>").attr({"type": "button", "value": "❌", "onclick": "what2();"});

		reply.append(hr);
		reply.append(replyTitleContainer);
		
		replyTitleContainer.append(writerID);
		replyTitleContainer.append(writeDate);
		replyTitleContainer.append(fixButton);
		replyTitleContainer.append(deleteButton);
		
		// 8. 개행문자 단위로 분할하여 출력합니다.
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


// 조회한 댓글의 페이지를 출력합니다.
function printReplyPage(parsedJSON) {
	let boundary = null;
	
	// 1. 댓글 "시작" 링크
	const firstPage = $("<a>").attr({"onclick": "loadReply(`${contextPath}`, `${parentIDX}`, `1`);"});
	firstPage.text("시작");
	boundary = $("<p>").text("|");
	
	replyPageContainer.append(firstPage);
	replyPageContainer.append(boundary);
	
	// 2. 댓글 "이전" 링크
	const beginPage = $("<a>").attr({"onclick": "loadReply(`${contextPath}`, `${parentIDX}`, " + parsedJSON.prevPage + ");"});
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
			currentPage = $("<a>").attr({"onclick": "loadReply(`${contextPath}`, `${parentIDX}`, " + i + ");"});
			currentPage.text(i);
		}
		
		boundary = $("<p>").text("|");
		
		replyPageContainer.append(currentPage);
		replyPageContainer.append(boundary);
	}
	
	// 4. 댓글 "다음" 링크	
	const nextPage = $("<a>").attr({"onclick": "loadReply(`${contextPath}`, `${parentIDX}`, " + parsedJSON.nextPage + ");"});
	nextPage.text("다음");
	boundary = $("<p>").text("|");
	
	replyPageContainer.append(nextPage);
	replyPageContainer.append(boundary);
	
	// 5. 댓글 "끝" 링크
	const lastPage = $("<a>").attr({"onclick": "loadReply(`${contextPath}`, `${parentIDX}`, " + parsedJSON.totalPageCnt + ");"});
	lastPage.text("끝");
	
	replyPageContainer.append(lastPage);
}


function what1() {
	alert("what_1");
}


function what2() {
	alert("what_2");
}