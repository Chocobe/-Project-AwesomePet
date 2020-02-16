// submit버튼 입니다.
const submitButton = $(".joinContainer form .submitBox .submitButton");
const idInputBox = $(".joinContainer form .animationBox .memberID");
const errorMessage = $(".joinContainer form .titleWrap .errorMessage");    
const checkIDButton = $(".joinContainer form .animationBox .checkIDButton");

const ID_MIN_LENGTH = 2;
const NAME_MIN_LENGTH = 2;
const PW_MIN_LENGTH = 2;
const ADDR_MIN_LENGTH = 5;


// --------------------------------------------------------------------------------
// 초기화 입니다.
$(function() {
	submitButton.attr("disabled", true);
	
	idInputBox.focusin(function(event) {
		event.preventDefault();
		errorMessage.text("ID검사를 눌러주세요");
		checkIDButton.attr("disabled", false);
		submitButton.attr("disabled", true);
	});
});




// --------------------------------------------------------------------------------
// ID 유효검사 메서드 입니다. (ajax를 이용한 비동기 요청)
function checkOverlapID(context) {
	const inputID = $(".memberID").val();
	
	// 1. ID 공백문자 검사 입니다.
	if(idInputBox.val().indexOf(" ") != -1) {
		errorMessage.text("ID에는 공백문자를 사용할 수 없습니다");
		submitButton.attr("disabled", true);
		
		checkIDButton.val("ID검사?");
		
		return;
	}
	
	// 2. ID 길이 검사 입니다.
	if(idInputBox.val().length < ID_MIN_LENGTH) {
		errorMessage.text("ID는 " + ID_MIN_LENGTH + "글자 이상으로 만들어 주세요");
		return;
	}
	
	// 3. ID 중복여부 검사 입니다.
	$.ajax({
		type: "POST",
		url: context + "/checkOverlapID.do",
		async: true,
		dataType: "TEXT",
		data: {id : inputID},
		success: function(data, textStatus) {
			const isOverlapID = JSON.parse(data);
			
			if(isOverlapID) {
				invalidFunc();
				
			} else {
				validIDFunc();
			}
		},
		error: function(data, textStatus) {
			// ID 중복검사 실패
			
			alert("ajax 실패");
		}
	});
}


//ID를 사용할 수 있는 경우, 호출되는 메서드 입니다.
function invalidFunc() {
	errorMessage.text("이미 사용중인 ID입니다");
	
	submitButton.attr("disabled", true);
	checkIDButton.attr("disabled", false);
}


// ID가 중복될 경우, 호출되는 메서드 입니다.
function validIDFunc() {
	errorMessage.text("");
	
	submitButton.attr("disabled", false);
	checkIDButton.attr("disabled", true);
}
//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
// 회원가입 요청 메서드 입니다.
function memberJoin() {
	// 1. 비밀번호 유효검사 메서드를 호출합니다.
	const isValidPW = checkPW();
	if(isValidPW === false) {
		return;
	}
	
	// 2. 이름 유효검사 메서드를 호출합니다.
	const isValidName = checkName();
	if(isValidName === false) {
		return;
	}
	
	// 3. 생년월일 유효검사 메서드를 호출합니다.
	const isValidBirthDay = checkBirthDay();
	if(isValidBirthDay === false) {
		return;
	}
	
	// 4. Email 유효검사 메서드를 호출합니다.
	const isValidEmail = checkEmail();
	if(isValidEmail === false) {
		return;
	}
	
	// 5. Phone 유효검사 메서드를 호출합니다.
	const isValidPhone = checkPhone();
	if(isValidPhone === false) {
		return;
	}
	
	// 6. Addr 유효검사 메서드를 호출합니다.
	const isValidAddr = checkAddr();
	if(isValidAddr === false) {
		return;
	}
	
	$(".joinContainer form").submit();
}
// --------------------------------------------------------------------------------


// --------------------------------------------------------------------------------
// 비밀번호 유효검사 메서드 입니다.
function checkPW() {
	const pwInputBox = $(".joinContainer form .animationBox .memberPW");
	const pwCheckInputBox = $(".joinContainer form .animationBox .memberPWCheck");
	
	// 비밀번호의 공백문자 검사
	if(pwInputBox.val().indexOf(" ") != -1) {
		errorMessage.text("비밀번호에 공백문자를 사용할 수 없습니다");
		pwInputBox.val("");
		pwCheckInputBox.val("");
		
		return false;
	}
	
	// 비밀번호 길이 검사
	if(pwInputBox.val().length < PW_MIN_LENGTH) {
		errorMessage.text("비밀번호는 " + PW_MIN_LENGTH + "글자 이상 입력해 주세요");
		pwInputBox.val("");
		pwCheckInputBox.val("");
		
		return false;
	}
	
	// 두 개의 비밀번호 입력이 동일한지 검사
	if(pwInputBox.val() != pwCheckInputBox.val()) {
		errorMessage.text("비밀번호가 동일하지 않습니다");
		pwInputBox.val("");
		pwCheckInputBox.val("");
		
		return false;
	}
	
	errorMessage.text("");
	return true;
}
//--------------------------------------------------------------------------------


// --------------------------------------------------------------------------------
// 이름 유효검사 메서드 입니다.
function checkName() {
	const memberName = $(".joinContainer form .animationBox .memberName");
	const nameCondition = /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
	
	// 이름 길이 검사
	if(memberName.val().length < NAME_MIN_LENGTH) {
		errorMessage.text("이름을 정확히 입력해 주세요");
		memberName.val("");
		
		return false;
	}
	
	// 이름의 한글여부 검사
	if(nameCondition.test(memberName.val())) {
		errorMessage.text("이름은 한글로 입력해 주세요");
		memberName.val("");
		
		return false;
	}
	
	errorMessage.text("");
	return true;
}
// --------------------------------------------------------------------------------


// --------------------------------------------------------------------------------
// 생년월일 유효검사 메서드 입니다.
function checkBirthDay() {
	const birthDayYear = $(".joinContainer form .fixedBox .memberBirthDayYear");
	const birthDayMonth = $(".joinContainer form .fixedBox .memberBirthDayMonth");
	const birthDayDate = $(".joinContainer form .fixedBox .memberBirthDayDate");
	
	// 1940이전 생년월일은 사용할 수 없습니다. 또한 각 항목별 값의 범위를 지정합니다.
	const birthDayCondition = /^(19[4-9][0-9]|2[0-9]{3})-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])$/;
	
	const inputBirthDayString = birthDayYear.val() + "-" +
						  birthDayMonth.val() + "-" +
						  birthDayDate.val();
	const inputBirthDay = new Date(inputBirthDayString);
	const today = new Date();
	
	// 생년월일의 유효성 검사
	if(!birthDayCondition.test(inputBirthDayString)) {
		errorMessage.text("생년월일을 정확히 입력해 주세요");
		birthDayYear.val("");
		birthDayMonth.val("");
		birthDayDate.val("");
		
		return false;
	}
	
	// 미래 생년월일인지 검사
	if(inputBirthDay >= today) {
		errorMessage.text("존재하지 않는 생년월일 입니다");
		birthDayYear.val("");
		birthDayMonth.val("");
		birthDayDate.val("");
		
		return false;
	}
	
	errorMessage.text("");
	return true;
}
// --------------------------------------------------------------------------------


// --------------------------------------------------------------------------------
// Email 유효검사 메서드 입니다.
function checkEmail() {
	const memberEmail = $(".joinContainer form .animationBox .memberEmail");
	
	// Email형식 조건을 지정합니다.
	const emailCondition = /^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/;
	
	if(!emailCondition.test(memberEmail.val())) {
		errorMessage.text("Email형식이 아닙니다");
		memberEmail.val("");
		
		return false;
	}
	
	errorMessage.text("");
	return true;
}
// --------------------------------------------------------------------------------


// --------------------------------------------------------------------------------
// 휴대전화 유효검사 메서드 입니다.
function checkPhone() {
	// 휴대전화의 자리수에 대한 조건을 지정 합니다.
	const phoneCondition = /^\d{2,3}-\d{3,4}-\d{4}$/;
	
	const memberPhone_1 = $(".joinContainer form .fixedBox .memberPhone_1");
	const memberPhone_2 = $(".joinContainer form .fixedBox .memberPhone_2");
	const memberPhone_3 = $(".joinContainer form .fixedBox .memberPhone_3");
	
	const memberPhone = memberPhone_1.val() + "-" + memberPhone_2.val() + "-" + memberPhone_3.val();
	
	if(!phoneCondition.test(memberPhone)) {
		errorMessage.text("휴대전화를 정확히 입력해 주세요");
		memberPhone_1.val("");
		memberPhone_2.val("");
		memberPhone_3.val("");
		
		return false;
	}
	
	errorMessage.text("");
	return true;
}
// --------------------------------------------------------------------------------


// --------------------------------------------------------------------------------
// 주소 유효성 검사 메서드 입니다.
function checkAddr() {
	const memberAddr = $(".joinContainer form .animationBox .memberAddr");
	
	if(memberAddr.val().trim().length < ADDR_MIN_LENGTH) {
		errorMessage.text("주소를 다시 입력해 주세요");
		memberAddr.val("");
		
		return false;
	}
	
	errorMessage.text("");
	return true;
}
// --------------------------------------------------------------------------------