// 이벤트 초기화 메서드 입니다.
$(function() {
    initFamilyEvent();
    initCommunicationEvent();
    initQuestionEvent();
});


// "가족을 찾아요" 클릭 이벤트 메서드 입니다.
function initFamilyEvent() {
    $(".family").click(function(event) {
        location.href = "cardTest.html";
    });
}


// "소통해요" 클릭 이벤트 메서드 입니다.
function initCommunicationEvent() {
    $(".communication").click(function(event) {
        location.href = "cardTest1.html";
    });
}


// "궁금해요" 클릭 이벤트 메서드 입니다.
function initQuestionEvent() {
    $(".question").click(function(event) {
        location.href = "login.html";
    });
}
