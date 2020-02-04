let contextPath = null;
let boardIDX = null;

// 글수정 일 때, 기존 이미지 경로값 입니다. (initImgUpdater()에서 초기화)
let imgLocation_1 = null;
let imgLocation_2 = null;
let imgLocation_3 = null;

// 전체 초기화 메서드 입니다.
function init(context, requestBoardIDX) {
	contextPath = context;
	boardIDX = requestBoardIDX;
	
	if(boardIDX != null && boardIDX.length > 0) {
		initImgUpdater();
	}
	
	initImgUploader();
}


// 이미지 업로드에 대한 초기화 메서드 입니다.
function initImgUploader() {
	$(".imgUploader input").change(function() {
		const imgUploader = $(this).parent().parent();
		const uploaderButtonsContainer = $(this).parent();
		
		// 이전 이미지를 삭제 합니다.
		imgUploader.children("img").remove();
		uploaderButtonsContainer.children(".cancelButton").remove();
		
		if(this.files != null && this.files[0] != null) {
			const fileReader = new FileReader();
			
			// FileReader 객체가 파일 읽기를 완료한 후, 미리보기(출력) 동작을 수행합니다.
			fileReader.onload = function(event) {
				const src = event.target.result;
				
				// <img> 태그 생성
				const imgTag = $("<img>").attr({"src": src});
				
				// 이미지 출력
				imgUploader.append(imgTag);
				
				
				// 취소버튼 생성
				// boardIDX가 null일 경우 - 글쓰기
				// boardIDX가 null이 아닐 경우 - 글수정
				let imgCancelButton = null;
				
				if(boardIDX != null && boardIDX.length > 0) {
					// 글수정 시, 취소 버튼
					imgCancelButton = $("<input>").attr({
						"type" : "button",
						"class" : "cancelButton",
						"value" : "❌",
						"onclick" : "rollbackImg(this);"
					});
					
				} else {
					// 글작성 시, 취소 버튼
					imgCancelButton = $("<input>").attr({
						 "type": "button", 
						 "class": "cancelButton", 
						 "value": "❌", 
						 "onclick": "cancelImgUpload(this);"}
					);
				}
				
				// 취소버튼 출력
				uploaderButtonsContainer.append(imgCancelButton);
			}
			
			// 파일 읽기
			fileReader.readAsDataURL(this.files[0]);
			
		} else {
			cancelImgUpload(this);
		}
		
		imgUploader.children(".action").val("fixed");
	});
}
	
	
// 이미지 선택 "취소" 버튼 메서드 입니다.
function cancelImgUpload(target) {
	const imgUploader = $(target).parent().parent();
	imgUploader.children("img").remove();
	
	const uploaderButtonsContainer = $(target).parent();
	uploaderButtonsContainer.children("input[type=file]").val("");
	uploaderButtonsContainer.children(".cancelButton").remove();
}


// 글 수정 시, 원본 이미지 경로값을 가져옵니다.
function initImgUpdater() {
	$.ajax({
		type: "POST",
		async: false,
		url: contextPath + "/communicationContentsAjaxView.do",
		dataType: "TEXT",
		data: {
			"requestBoardIDX" : boardIDX
		},
		success: function(resultVO, status) {
			const jsonData = JSON.parse(resultVO);
			
			// 원래 이미지 경로값을 초기화 합니다.
			imgLocation_1 = jsonData.imgLocation_1;
			imgLocation_2 = jsonData.imgLocation_2;
			imgLocation_3 = jsonData.imgLocation_3;
			
			const uploaderButtonsContainer = $(".uploaderButtonsContainer");
			
			const imgCancelButton = $("<input>").attr({
				"type" : "button",
				"class" : "cancelButton",
				"value" : "❌",
				"onclick" : "rollbackImg(this);"
			});
			
			uploaderButtonsContainer.append(imgCancelButton);
			
			const imgUploaderContainer = $(".imgUploaderContainer");
			
			// 첫번째 원래 이미지 출력
			if(imgLocation_1 != null && imgLocation_1.length > 0) {
				const img_1 = $("<img>").attr({
					"src": imgLocation_1
				});
				imgUploaderContainer.children(".imgUploader:nth-child(1)").append(img_1);
			}
			
			// 두번째 원래 이미지 출력
			if(imgLocation_2 != null && imgLocation_2.length > 0) {
				const img_2 = $("<img>").attr({
					"src": imgLocation_2
				});
				imgUploaderContainer.children(".imgUploader:nth-child(2)").append(img_2);
			}
			
			// 세번째 원래 이미지 출력
			if(imgLocation_3 != null && imgLocation_3.length > 0) {
				const img_3 = $("<img>").attr({
					"src": imgLocation_3
				});
				imgUploaderContainer.children(".imgUploader:nth-child(3)").append(img_3);
			}
		}
	});
}


// 글수정 시, 취소 버튼 메서드 입니다.
function rollbackImg(target) {
	const imgUploader = $(target).parent().parent();
	const action = imgUploader.children(".action").val();
	
	const uploaderButtonsContainer = imgUploader.children(".uploaderButtonsContainer");
	const imgLocationName = uploaderButtonsContainer.children("input[type=file]").attr("name");
	
	// 수정 전, 원래 상태값으로 변경합니다.
	
	imgUploader.children("img").remove();
	
	// 수정 상태값에 따라 분기 합니다.
	// action == "fixed"
	//		: 수정 전 이미지로 변경 합니다.
	// action == ""
	//		: 기존 업로드 했던 이미지를 지웁니다.
	if(action == "fixed") {
		// 수정 전, 원래 이미지를 사용합니다.
		let src = "";
		
		if(imgLocationName == "imgLocation_1") {
			src = imgLocation_1;
			
		} else if(imgLocationName == "imgLocation_2") {
			src = imgLocation_2;
			
		} else if(imgLocationName == "imgLocation_3") {
			src = imgLocation_3;
		}
		
		if(src != null) {
			const img = $("<img>").attr({
				"src": src
			});
			
			imgUploader.append(img);
		}
		
		imgUploader.children(".action").val("");
		
	} else {
		// 기존 업로드 했던 이미지를 지웁니다.
		imgUploader.children("img").remove();
		imgUploader.children(".action").val("fixed");
	}
	
	uploaderButtonsContainer.children("input[type=file]").val("");
	console.log("action값 : " + imgUploader.children(".action").val());
}
