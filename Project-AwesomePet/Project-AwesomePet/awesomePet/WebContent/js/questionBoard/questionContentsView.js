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