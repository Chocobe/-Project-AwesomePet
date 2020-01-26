// 페이지 호출 시, 초기작업을 수행합니다. (초기화)
function init() {
	initContent();
}
init();


// 글 내용을 분할하여 출력합니다.
function initContent() {
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
function backToList() {
	history.go(-1);
}


// 목록으로 돌아가는 버튼 입니다. (글 작성/수정했을 경우)
function goToList(context) {
	location.href = context + "/questionBoardView.do";
}


// 글 삭제 버튼 메서드 입니다.
function deleteContents(context) {
	const height = $(window).height();
	
	// Modal 기능을 구현하기 위해 높이값들을 변경합니다. (scroll기능 off)
	$(".questionContentsWrap").css({"height": "600",
									"overflow": "hidden"});
	
	$(".deleteConfirmContainer").css({"display": "block",
									  "height": height});
	
	$("footer").css({"display": "none"});
	
	$(".deleteConfirmContainer").on("scroll touchmove mousewheel", function(event) {
		event.preventDefault();
		event.stopPropagation();
	});
	
	
}

function deleteConfirm() {
	// 삭제를 취소할 경우, 화면의 높이값을 원래대로 변경합니다. (scroll기능 on) 
	$(".questionContentsWrap").css({"height": "auto"});
	$(".deleteConfirmContainer").css({"display": "none"});
	$("footer").css({"display": "block"});
}