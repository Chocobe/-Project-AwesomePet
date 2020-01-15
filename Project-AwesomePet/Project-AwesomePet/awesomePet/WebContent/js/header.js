// "가족을 찾아요" 클릭 이벤트 메서드 입니다.
function familyBoardView(context) {
	alert("가족을 찾아요 메뉴 클리!");
}


// "소통해요" 클릭 이벤트 메서드 입니다.
function communicationBoardView(context) {
	alert("소통해요 메뉴 클릭!");
}


// "궁금해요" 클릭 이벤트 메서드 입니다.
function questionBoardView(context) {
	location.href = context + "/questionBoardView.do";
}


// "로그인" 버튼 클릭 메서드 입니다.
function loginView(context) {
	location.href = context + "/loginView.do";
}


// "회원가입" 버튼 클릭 메서드 입니다.
function joinView(context) {
	location.href = context + "/joinView.do";
}


// "로그아웃" 버튼 클릭 메서드 입니다.
function memberLogout(context) {
	location.href = context + "/memberLogout.do";
}

// "마이 페이지" 버튼 클릭 메서드 입니다.
function myPageView(context) {
	location.href = context + "/myPageView.do";
}