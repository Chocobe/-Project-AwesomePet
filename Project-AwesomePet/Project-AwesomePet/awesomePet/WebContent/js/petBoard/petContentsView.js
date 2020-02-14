// 초기화 메서드 입니다.
$(function initContent() {
	printSplitContent();
});


// 글 내용을 분할하여 출력합니다.
	function printSplitContent() {
		const contentsContainer = $(".contentsContainer");
		
		const requestContent = $(".requestContent").val();
		const contentArray = requestContent.split("\n");
		
		for(let i = 0; i < contentArray.length; i++) {
			const p = $("<p class='contents'>").text(contentArray[i]);
			const br = $("<br/>");
			contentsContainer.prepend(p).append(br);
		}
		
		if(contentArray.length > 0) {
			for(let i = 0; i < 3; i++) {
				contentsContainer.prepend($("<br>"));
			}
		}
	}


// 목록으로 돌아가는 버튼 입니다. (이전페이지로 이동)
	function backToList(context, requestPage, typeName, subTypeName) {
		location.href = context + "/petBoardView.do?requestPage=" + requestPage + "&requestTypeName=" + typeName + "&requestSubTypeName=" + subTypeName;
	}


// 글 삭제 버튼 메서드 입니다.
	function deleteContents(context) {
		const height = $(window).height();
		
		// Modal 기능을 구현하기 위해 높이값들을 변경합니다. (scroll기능 off)
		$(".petContentsWrap").css({
			"height": "600",
			"overflow": "hidden"
		});
		
		$(".deleteConfirmContainer").css({
			"display": "block",
			"height": height
		});
		
		$("footer").css({
			"display": "none"
		});
		
		$(".deleteConfirmContainer").on("scroll touchmove mousewheel", function(event) {
			event.preventDefault();
			event.stopPropagation();
		});
	}


	// 글 삭제 확인 버튼 메서드 입니다.
	function deleteConfirm(context, boardIDX) {
		alert("삭제 요청 boardIDX : " + boardIDX);
		
		$.ajax({
			type: "POST",
			async: true,
			url: context + "/petContentsDelete.do",
			data: {"requestBoardIDX": boardIDX},
			dataType: "TEXT",
			success: function(result, status) {
				if(result == "true") {
					location.href = context + "/petBoardView.do";
					
				} else {
					alert("삭제 실패");
				}
			}
		});
	}


// 글 삭제 취소 버튼 메서드 입니다.
	function deleteCancel() {
		// 삭제를 취소할 경우, 화면의 높이값을 원래대로 변경합니다. (scroll기능 on) 
		$(".petContentsWrap").css({
			"height": "auto"
		});
		
		$(".deleteConfirmContainer").css({
			"display": "none"
		});
		
		$("footer").css({
			"display": "block"
		});
	}





























