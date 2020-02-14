// 초기화 메서드 입니다.
function initContent(context, boardIDX) {
	printSplitContent();
	initHitButton(context, boardIDX);
}


// 글 내용을 분할하여 출력합니다.
	function printSplitContent() {
		const contentsContainer = $(".contentsContainer");
		
		const requestContent = $(".requestContent").val();
		const contentArray = requestContent.split("\n");
		
		for(let i = 0; i < contentArray.length; i++) {
			const p = $("<p class='contents'>").text(contentArray[i]);
			const br = $("<br/>");
			contentsContainer.append(p).append(br);
		}
	}


// 목록으로 돌아가는 버튼 입니다. (이전페이지로 이동)
	function backToList(context, requestPage) {
		location.href = context + "/communicationBoardView.do?requestPage=" + requestPage;
	}


// 글 삭제 버튼 메서드 입니다.
	function deleteContents(context) {
		const height = $(window).height();
		
		// Modal 기능을 구현하기 위해 높이값들을 변경합니다. (scroll기능 off)
		$(".communicationContentsWrap").css({"height": "600",
											 "overflow": "hidden"});
		
		$(".deleteConfirmContainer").css({"display": "block",
										  "height": height});
		
		$("footer").css({"display": "none"});
		
		$(".deleteConfirmContainer").on("scroll touchmove mousewheel", function(event) {
			event.preventDefault();
			event.stopPropagation();
		});
	}


	// 글 삭제 확인 버튼 메서드 입니다.
	function deleteConfirm(context, boardIDX) {
		$.ajax({
			type: "POST",
			async: true,
			url: context + "/communicationContentsDelete.do",
			data: {"requestBoardIDX": boardIDX},
			dataType: "text",
			success: function(result, status) {
				if(result == 1) {
					location.href = context + "/communicationBoardView.do";
					
				} else {
					alert("삭제 실패");
				}
			}
		});
	}


// 글 삭제 취소 버튼 메서드 입니다.
	function deleteCancel() {
		// 삭제를 취소할 경우, 화면의 높이값을 원래대로 변경합니다. (scroll기능 on) 
		$(".communicationContentsWrap").css({"height": "auto"});
		$(".deleteConfirmContainer").css({"display": "none"});
		$("footer").css({"display": "block"});
	}


// "좋아요" 버튼의 초기화 메서드 입니다. ("좋아요" 여부에 따른 출력)
	function initHitButton(context, boardIDX) {
		const hitButton = $(".hitButton");
		
		$.ajax({
			type: "POST",
			async: true,
			url: context + "/communicationContentsHitChecker.do",
			data: {
				"boardIDX" : boardIDX
			},
			datatype: "TEXT",
			success: function(isHitted, status) {
				if(isHitted == "true") {
					hitButton.css({"background": "#ff0058"});
					
				} else {
					hitButton.css({"background": "#aaa"});
				}
			}
		});
	}


	// "좋아요" 버튼 메서드 입니다.
	function hit(context, boardIDX) {
		$.ajax({
			type: "POST",
			async: false,
			url: context + "/communicationContentsHit.do",
			data: {
				"boardIDX" : boardIDX
			},
			datatype: "TEXT",
			complete: function(actionValue, status) {
				if(actionValue == "insertHit") {
					console.log("좋아요");
					
				} else {
					console.log("좋아요 취소");
				}
			}
		});
		
		location.reload();
	}


























