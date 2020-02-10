let initPetContentsWriterCallbackMethod;
let initSubTypeCallbackMethod;

// 대분류 초기화 메서드 입니다.
	function initTypeView(context, initPetContentsWriterCallback, initSubTypeCallback, boardIDX) {
		initPetContentsWriterCallbackMethod = initPetContentsWriterCallback;
		initSubTypeCallbackMethod = initSubTypeCallback;
		
		const updateContainer = $(".typeContainer .updateContainer");
		
		// 기존의 ".innerContainer"를 삭제 함으로써 출력된 데이터를 초기화 합니다.
		updateContainer.children(".innerContainer").remove();
		
		$.ajax({
			type: "POST",
			async: true,
			url: context + "/" + "petTypeView.do",
			success: function(jsonString, status) {
				const parsedJSON = JSON.parse(jsonString);
				
				// 1. type의 출력부를 초기화 합니다.
				for(let i in parsedJSON) {
					const typeDataElement = createTypeDataElement(context, parsedJSON[i].typeName);
					updateContainer.append(typeDataElement);
				}
				
				// 2. subType의 출력부를 초기화 합니다. (subType 갱신)
				initSubTypeCallback(context, parsedJSON, initTypeView, initPetContentsWriterCallbackMethod);
				
				// 3. petContents 작성부를 초기화 합니다.
				initPetContentsWriterCallback(context, parsedJSON, boardIDX);
			},
			error: function(jsonString, status) {
				alert("ajax 에러");
			}
		});
	}
	
	
	// 대분류에 출력할 데이터를 태그로 만들어 반환합니다.
	function createTypeDataElement(context, type) {
		const innerContainer = $("<div>").attr({
			"class": "innerContainer" 
		});
		
		const typeName = $("<input>").attr({
			"type": "text",
			"class": "typeName",
			"value": type,
			"placeholder": type,
			"autocomplete": "off"
		});
		
		const originTypeName = $("<input>").attr({
			"type": "hidden",
			"class": "originTypeName",
			"value": type
		});
		
		const submitButton = $("<input>").attr({
			"type": "button",
			"class": "submitButton",
			"value": "수정",
			"onclick": "updatePetTypeName(`" + context + "`, this);"
		});
		
		const deleteButton = $("<input>").attr({
			"type": "button",
			"class": "deleteButton",
			"value": "삭제",
			"onclick": "deletePetType(`" + context + "`, this);"
		});
		
		innerContainer.append(typeName);
		innerContainer.append(originTypeName);
		innerContainer.append(submitButton);
		innerContainer.append(deleteButton);
		
		return innerContainer;
		
		
		/* "대분류" 출력 형식
		<div class="innerContainer">
	        <input type="text" class="typeName" name="typeName" placeholder="기존값" autocomplete="off">
	        <input type="hidden" class="originTypeName" name="originTypeName" value="기존값">
	        
	        <input type="button" class="submitButton" value="수정" onclick="updatePetTypeName(contextPath);">
	        <input type="button" class="deleteButton" value="삭제" onclick="">
		</div>
		*/
	}
	
	
// DB에 type값을 "저장"하기 위한 메서드 입니다.
	function writePetTypeName(context) {
		const typeContainer = $(".typeContainer");
		const inputForm = $(typeContainer).children(".inputForm");
		const typeInputValue = $(inputForm).children(".typeName").val();
		
		$.ajax({
			type: "POST",
			async: true,
			url: context + "/petTypeWrite.do",
			dataType: "TEXT",
			data: {
				"typeName" : typeInputValue
			},
			success: function(result, status) {
				initTypeView(context, initPetContentsWriterCallbackMethod, initSubTypeCallbackMethod, null);
			},
			error: function(result, status) {
				alert("typeName 저장 실패...");
			},
			complete: function(result, status) {
				$(inputForm).children(".typeName").val("");
			}
		});
	}
	
	
// DB에 type값을 "변경"하기 위한 메서드 입니다.
	function updatePetTypeName(context, target) {
		const innerContainer = $(target).parent();
		
		const typeName = $(innerContainer).children(".typeName").val();
		const originTypeName = $(innerContainer).children(".originTypeName").val();
		
		$.ajax({
			type: "POST",
			async: true,
			url: context + "/petTypeUpdate.do",
			dataType: "TEXT",
			data: {
				"typeName": typeName,
				"originTypeName": originTypeName
			},
			success: function(result, status) {
				initTypeView(context, initPetContentsWriterCallbackMethod, initSubTypeCallbackMethod, null);
			},
			error: function(result, status) {
				alert("typeName 수정 실패...");
			}
		});
	}
	
	
// DB에 type값을 "삭제"하기 위한 메서드 입니다.
	function deletePetType(context, target) {
		const innerContainer = $(target).parent();
		const typeName = $(innerContainer).children(".originTypeName").val();
		
		alert("typeName : " + typeName);
		
		$.ajax({
			type: "POST",
			async: true,
			url: context + "/petTypeDelete.do",
			dataType: "TEXT",
			data: {
				"typeName": typeName
			},
			success: function(result, status) {
				initTypeView(context, initPetContentsWriterCallbackMethod, initSubTypeCallbackMethod, null);
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	