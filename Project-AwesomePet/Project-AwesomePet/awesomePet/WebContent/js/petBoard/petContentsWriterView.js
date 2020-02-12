// 이미지 업로더의 임시 번호 입니다. (저장/출력 순서를 위한 임시 번호)
let fileInputTempNumber = 1;

// "분양등록" 초기화 메서드 입니다.
	function initPetContentsWriterView(context, parsedJSON, boardIDX) {
		const petContainer = $(".petContainer");
		
		const typeNameDiv = petContainer.children(".typeNameDiv");
		const subTypeNameDiv = petContainer.children(".subTypeNameDiv");
		
		const typeNameSelector = typeNameDiv.children(".typeName");
		const subTypeNameSelector = subTypeNameDiv.children(".subTypeName");
		
		// 1. "분양등록"의 "대분류"와 "소분류"에 출력한 기존 데이터를 삭제 합니다.
		typeNameSelector.children().remove();
		subTypeNameSelector.children().remove();
		
		// 2. "분양등록"의 "대분류" 를 초기화 합니다.
		for(let i in parsedJSON) {
			const option = $("<option>").text(parsedJSON[i].typeName);
			typeNameSelector.append(option);
		}
		
		typeNameSelector.val("");
		subTypeNameSelector.val("");
		
		// 3. "분양등록"의 "대분류"에 이벤트를 설정합니다.
		typeNameSelector.change(function() {
			subTypeNameSelector.children().remove();
			
			for(let i in parsedJSON) {
				if($(typeNameSelector).val() == parsedJSON[i].typeName) {
					const subTypeList = parsedJSON[i].subTypeList;
					
					for(let j in subTypeList) {
						const option = $("<option>").text(subTypeList[j].subTypeName);
						subTypeNameSelector.append(option);
					}
				}
			}
		});
		
		// 4. "분양등록"의 이미지 업로드를 초기화 합니다.
		if(boardIDX != null && boardIDX.length > 0) {
			initImgUpdater(boardIDX);
			
		} else {
			const fileInput = $(".imgUploaderContainer .imgUploader .uploaderButtonsContainer input[type=file]");
			initImgUploader();
		}
	}
	
	
// "분양등록"의 글 "작성" 시, 이미지 업로드 초기화 메서드 입니다.
	function initImgUploader(targetFileInput) {
		if(targetFileInput != null) {
			initFileInput(targetFileInput);
			
		} else {
			const imgUploaderContainer = $(".imgUploaderContainer");
			const imgUploader = imgUploaderContainer.children(".imgUploader");
			const uploaderButtonsContainer = $(imgUploader).children(".uploaderButtonsContainer");
			const fileInput = $(uploaderButtonsContainer).children("input[type=file]");
			
			initFileInput(fileInput);
		}
	}


	// <input type="file">의 초기화 메서드 입니다.
	function initFileInput(fileInput) {
		$(fileInput).change(function() {
			const imgUploaderContainer = $(".imgUploaderContainer");
			const imgUploader = $(fileInput).parent().parent();
			const uploaderButtonsContainer = $(imgUploader).children(".uploaderButtonsContainer");
			
			if(this.files != null && this.files[0] != null) {
				const fileReader = new FileReader();
				
				// 파일 업로드 완료 이벤트를 설정 합니다.
				fileReader.onload = function(event) {
					const src = event.target.result;
					
					if($(imgUploader).children(".action").val() == "fixed") {
						$(imgUploader).children("img").attr({
							"src": src
						});
						
					} else {
						const img = $("<img>").attr({
							"src": src
						});
						
						imgUploader.append(img);
						
						// 새로운 이미지 업로드 형식을 추가 합니다.
						const newImgUploader = createImgUploader();
						imgUploaderContainer.append(newImgUploader);
					}
					
					$(imgUploader).children(".action").val("fixed");
				}
				
				fileReader.readAsDataURL(this.files[0]);
				
			} else {
				$(imgUploader).children(".action").val("");
			}
		});
	}

	
	// 새로운 이미지 업로드 형식을 추가하는 메서드 입니다.
	function createImgUploader() {
		fileInputTempNumber++;
		
		const imgUploaderDiv = $("<div>").attr({
			"class": "imgUploader"
		});
		
		const uploaderButtonsContainerDiv = $("<div>").attr({
			"class": "uploaderButtonsContainer"
		});
		
		const fileInput = $("<input>").attr({
			"type": "file",
			"name": "imgLocation_" + fileInputTempNumber
		});
		initFileInput(fileInput);
		
		const cancelButton = $("<input>").attr({
			"type": "button",
			"value": "❌",
			"class": "cancelButton",
			"onclick": "cancelImgUpload(this)"
		});
		
		uploaderButtonsContainerDiv.append(fileInput);
		uploaderButtonsContainerDiv.append(cancelButton);
		
		const actionInput = $("<input>").attr({
			"type": "hidden",
			"name": "action",
			"class": "action",
			"value": ""
		});
		
		imgUploaderDiv.append(uploaderButtonsContainerDiv);
		imgUploaderDiv.append(actionInput);
		
		return imgUploaderDiv;
		
		/* ".imgUploader" 형식
		<div class="imgUploader">
			<div class="uploaderButtonsContainer">
				<input type="file" name="imgLocation">
	            <input type="button" value="❌" class="cancelButton" onclick="cancelImgUpload(this)">
			</div>
			
			<input type="hidden" name="action" class="action" value="">
			<!-- js에 의해 "<img>" 태그가 추가 됩니다. (업로드 미리보기) -->
		</div>
		*/
	}
	
	
	// "분양등록"의 이미지 업로드 취소 버튼 메서드 입니다.
	function cancelImgUpload(target) {
		const imgUploaderContainer = $(".imgUploaderContainer");
		const imgUploader = $(target).parent().parent();
		
		if(($(imgUploaderContainer).children(".imgUploader")).size() > 1) {
			if(imgUploader.children(".action").val() == "fixed") {
				imgUploader.remove();
			}
			
		} else {
			$(imgUploader).children("img").remove();
			$(imgUploader).children("input[type=file]").val("");
		}
	}
	
	
	
	
	
	
// "분양등록"의 글 "수정" 시, 이미지 업로드 초기화 메서드 입니다.
	function initImgUpdater(boardIDX) {
		const imgUploaderContainer = $(".imgUploaderContainer");
		
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	