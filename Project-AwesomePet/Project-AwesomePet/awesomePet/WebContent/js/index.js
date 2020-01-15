// "가족을 찾아요" 클릭 이벤트 메서드 입니다.
function familyBoardView(context) {
	alert("가족을 찾아요!");
}


// "소통해요" 클릭 이벤트 메서드 입니다.
function communicationBoardView(context) {
	alert("소통해요~~~");
}


// "궁금해요" 클릭 이벤트 메서드 입니다.
function questionBoardView(context) {
	alert(contextPath + "/questionBoardView.do");
	location.href = context + "/login.do";
}