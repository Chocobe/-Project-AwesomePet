// 이미지 업로드에 대한 초기화 메서드 입니다.
function initImgUploader() {
	$(".imgUploader input").change(function() {
		const imgUploader = $(this).parent().parent();
		const uploaderButtonsContainer = $(this).parent();
		
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
				const imgCancelButton = $("<input>").attr({"type": "button", 
														   "class": "cancelButton", 
														   "value": "❌", 
														   "onclick": "cancelImgUpload(this);"}
				);
				
				// 취소버튼 출력
				uploaderButtonsContainer.append(imgCancelButton);
			}
			
			// 파일 읽기
			fileReader.readAsDataURL(this.files[0]);
			
		} else {
			cancelImgUpload(this);
		}
	});
}
initImgUploader();
	
	
// 이미지 선택 "취소" 버튼 메서드 입니다.
function cancelImgUpload(target) {
	const imgUploader = $(target).parent().parent();
	imgUploader.children("img").remove();
	
	const uploaderButtonsContainer = $(target).parent();
	uploaderButtonsContainer.children("input[type=file]").val("");
	uploaderButtonsContainer.children(".cancelButton").remove();
}