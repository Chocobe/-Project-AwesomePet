const errorMessage = $(".errorMessage");

const PW_MIN_LENGTH = 2;
const ADDR_MIN_LENGTH = 5;


//--------------------------------------------------------------------------------
// 회원정보 수정 메서드 입니다.
function updateMyInfo() {
	// 1. 비밀번호 유효검사 메서드를 호출합니다.
	const isValidPW = checkPW();
	if(isValidPW === false) {
		return;
	}
	
	// 2. 생년월일 유효검사 메서드를 호출합니다.
	const isValidBirthDay = checkBirthDay();
	if(isValidBirthDay === false) {
		return;
	}
	
	// 3. Email 유효검사 메서드를 호출합니다.
	const isValidEmail = checkEmail();
	if(isValidEmail === false) {
		return;
	}
	
	// 4. Phone 유효검사 메서드를 호출합니다.
	const isValidPhone = checkPhone();
	if(isValidPhone === false) {
		return;
	}
	
	// 5. Addr 유효검사 메서드를 호출합니다.
	const isValidAddr = checkAddr();
	if(isValidAddr === false) {
		return;
	}
	
	$(".myInfoViewContainer form").submit();
}
//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
// 비밀번호 유효검사 메서드 입니다.
function checkPW() {
	const fixedMemberPW = $(".fixedMemberPW");
	const fixedMemberPWCheck = $(".fixedMemberPWCheck");
	const memberPWOrigin = $(".memberPWOrigin");
	const memberPW = $(".memberPW");
	
	// 1. 비밀번호의 수정여부 검사
	if(fixedMemberPW.val() == "") {
		memberPW.val(memberPWOrigin.val());
		errorMessage.text("");
		
		return true;
	}
	
	// 2. 비밀번호 공백문자 검사
	if(fixedMemberPW.val().indexOf(" ") != -1) {
		errorMessage.text("비밀번호에 공백문자를 사용할 수 없습니다");
		fixedMemberPW.val("");
		fixedMemberPWCheck.val("");
		
		return false;
	}
	
	// 3. 비밀번호 길이 검사
	if(fixedMemberPW.val().length < PW_MIN_LENGTH) {
		errorMessage.text("비밀번호는 " + PW_MIN_LENGTH + "글자 이상 입력해 주세요");
		fixedMemberPW.val("");
		fixedMemberPWCheck.val("");
		
		return false;
	}
	
	// 4. 두 개의 비밀번호 입력이 동일한지 검사
	if(fixedMemberPW.val() != fixedMemberPWCheck.val()) {
		errorMessage.text("비밀번호가 동일하지 않습니다");
		fixedMemberPW.val("");
		fixedMemberPWCheck.val("");
		
		return false;
	}
	
	memberPW.val(fixedMemberPW.val());
	errorMessage.text("");
	
	return true;
}
//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
// 생년월일 유효검사 메서드 입니다.
function checkBirthDay() {
	// 년도
	const fixedBirthDayYear = $(".fixedMemberBirthDayYear");
	const birthDayYearOrigin = $(".memberBirthDayYearOrigin");
	const memberBirthDayYear = $(".memberBirthDayYear");
	
	// 월
	const fixedBirthDayMonth = $(".fixedMemberBirthDayMonth");
	const birthDayMonthOrigin = $(".memberBirthDayMonthOrigin");
	const memberBirthDayMonth = $(".memberBirthDayMonth");
	
	// 일
	const fixedBirthDayDate = $(".fixedMemberBirthDayDate");
	const birthDayDateOrigin = $(".memberBirthDayDateOrigin");
	const memberBirthDayDate = $(".memberBirthDayDate");

	// 1940이전 생년월일은 사용할 수 없습니다. 또한 각 항목별 값의 범위를 지정합니다.
	const birthDayCondition = /^(19[4-9][0-9]|2[0-9]{3})-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])$/;
	
	// 년도 수정 처리
	if(fixedBirthDayYear.val() == "") {
		memberBirthDayYear.val(birthDayYearOrigin.val());
		
	} else {
		memberBirthDayYear.val(fixedBirthDayYear.val());
	}
	
	// 월 수정 처리
	if(fixedBirthDayMonth.val() == "") {
		memberBirthDayMonth.val(birthDayMonthOrigin.val());
		
	} else {
		memberBirthDayMonth.val(fixedBirthDayMonth.val());
	}
	
	// 일 수정 처리
	if(fixedBirthDayDate.val() == "") {
		memberBirthDayDate.val(birthDayDateOrigin.val());
		
	} else {
		memberBirthDayDate.val(fixedBirthDayDate.val());
	}
	
	// 수정된 생년월일 조합
	const inputBirthDayString = memberBirthDayYear.val() + "-" +
								memberBirthDayMonth.val() + "-" +
								memberBirthDayDate.val();
	const inputBirthDay = new Date(inputBirthDayString);
	const today = new Date();
	
	
	// 1. 생년월일 유효검사
	if(!birthDayCondition.test(inputBirthDayString)) {
		errorMessage.text("생년월일을 정확히 입력해 주세요");
		fixedBirthDayYear.val("");
		fixedBirthDayMonth.val("");
		fixedBirthDayDate.val("");
		
		return false;
	}
	
	// 2. 미래 생년월일인지 검사
	if(inputBirthDay >= today) {
		errorMessage.text("존재하지 않는 생년월일 입니다");
		fixedBirthDayYear.val("");
		fixedBirthDayMonth.val("");
		fixedBirthDayDate.val("");
		
		return false;
	}
	
	errorMessage.text("");
	return true;
}
//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
// Email 유효검사 메서드 입니다.
function checkEmail() {
	const fixedMemberEmail = $(".fixedMemberEmail");
	const memberEmailOrigin = $(".memberEmailOrigin");
	const memberEmail = $(".memberEmail");
	
	// Email형식 조건을 지정합니다.
	const emailCondition = /^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/;
	
	// 이메일 수정 처리
	if(fixedMemberEmail.val() == "") {
		memberEmail.val(memberEmailOrigin.val());
		
	} else {
		memberEmail.val(fixedMemberEmail.val());
	}
	
	// 이메일 유효검사
	if(!emailCondition.test(memberEmail.val())) {
		errorMessage.text("Email형식이 아닙니다");
		fixedMemberEmail.val("");
		
		return false;
	}
	
	errorMessage.text("");
	return true;
}
//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
// 휴대전화 유효검사 메서드 입니다.
function checkPhone() {
	const fixedMemberPhone_1 = $(".fixedMemberPhone_1");
	const memberPhone_1Origin = $(".memberPhone_1Origin");
	const memberPhone_1 = $(".memberPhone_1");
	
	const fixedMemberPhone_2 = $(".fixedMemberPhone_2");
	const memberPhone_2Origin = $(".memberPhone_2Origin");
	const memberPhone_2 = $(".memberPhone_2");
	
	const fixedMemberPhone_3 = $(".fixedMemberPhone_3");
	const memberPhone_3Origin = $(".memberPhone_3Origin");
	const memberPhone_3 = $(".memberPhone_3");
	
	// 휴대전화의 자리수에 대한 조건을 지정 합니다.
	const phoneCondition = /^\d{2,3}-\d{3,4}-\d{4}$/;
	
	// 휴대전화 수정 처리
	if(fixedMemberPhone_1.val() == "") {
		memberPhone_1.val(memberPhone_1Origin.val());
		
	} else {
		memberPhone_1.val(fixedMemberPhone_1.val());
	}
	
	if(fixedMemberPhone_2.val() == "") {
		memberPhone_2.val(memberPhone_2Origin.val());
		
	} else {
		memberPhone_2.val(fixedMemberPhone_2.val());
	}
	
	if(fixedMemberPhone_3.val() == "") {
		memberPhone_3.val(memberPhone_3Origin.val());
		
	} else {
		memberPhone_3.val(fixedMemberPhone_3.val());
	}
	
	const fixedMemberPhone = memberPhone_1.val() + "-" + memberPhone_2.val() + "-" + memberPhone_3.val();
	
	// 휴대번호 유효검사
	if(!phoneCondition.test(fixedMemberPhone)) {
		errorMessage.text("휴대전화를 정확히 입력해 주세요");
		fixedMemberPhone_1.val("");
		fixedMemberPhone_2.val("");
		fixedMemberPhone_3.val("");
		
		return false;
	}
	
	errorMessage.text("");
	return true;
}
//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
// 주소 유효성 검사 메서드 입니다.
function checkAddr() {
	const fixedMemberAddr = $(".fixedMemberAddr");
	const memberAddrOrigin = $(".memberAddrOrigin");
	const memberAddr = $(".memberAddr");
	
	// 주소 수정 처리
	if(fixedMemberAddr.val() == "") {
		memberAddr.val(memberAddrOrigin.val());
		
	} else {
		memberAddr.val(fixedMemberAddr.val());
	}
	
	// 주소 유효검사
	if(memberAddr.val().trim().length < ADDR_MIN_LENGTH) {
		errorMessage.text("주소를 다시 입력해 주세요");
		fixedMemberAddr.val("");
		
		return false;
	}
	
	errorMessage.text("");
	return true;
}
//--------------------------------------------------------------------------------