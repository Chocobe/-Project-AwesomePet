let petTypeList = null;

const subTypeContainer = $(".subTypeContainer");
const inputContainer = subTypeContainer.children(".inputForm").children(".inputContainer");
const selectorContainer = subTypeContainer.children(".selectorContainer");
const updateContainer = subTypeContainer.children(".updateContainer");
	
let initTypeCallbackMethod;
let initPetContentsCallbackMethod;


// 소분류 초기화 메서드 입니다.
	function initSubTypeView(context, parsedJSON, initTypeViewCallback, initPetContentsWriterCallback) {
		petTypeList = parsedJSON;
		
		initTypeCallbackMethod = initTypeViewCallback;
		initPetContentsCallbackMethod = initPetContentsWriterCallback;
		
		// 1. "소분류 추가" 의 "<SELECT>" 출력부를 초기화 합니다.
		let typeNameSelector = inputContainer.children("select");
		typeNameSelector.children().remove();
		updateContainer.children().remove();
		
		// 2. "소분류 추가" 의 "<SELECT>" 출력부를 출력 합니다.
		for(let i in petTypeList) {
			const option = $("<option>").text(petTypeList[i].typeName);
			typeNameSelector.append(option);
		}
		
		// 3. "소분류 수정" 의 "대분류 <SELECT>" 출력부를 초기화 합니다.
		typeNameSelector = selectorContainer.children(".typeName");
		typeNameSelector.children().remove();
		
		
		for(let i in petTypeList) {
			const option = $("<option>").text(petTypeList[i].typeName);
			typeNameSelector.append(option);
		}
		
		$(typeNameSelector).val("");
		
		// 4. "소분류 수정" 의 이벤트 메서드를 추가 합니다.
		typeNameSelector.change(function() {
			typeSelectEvent(context, $(typeNameSelector).val());
		});
		
		
		/* "소분류 추가" 의 "<SELECT>" 형식 
		<select name="typeName">
	    	<option>강아지</option>
	        <option>대형견</option>
	        <option>고양이</option>
		</select> 
		*/
	}
	
	
	// 선택한 조건의 "소분류" 데이터를 출력합니다.
	function typeSelectEvent(context, type) {
		// typeName 조건 검사
		for(let i in petTypeList) {
			if(type == petTypeList[i].typeName) {
				const subTypeList = petTypeList[i].subTypeList;
	
				// 기존에 출력했던 요소들을 삭제 합니다.
				updateContainer.children().remove();
				
				// 해당 subType 데이터를 모두 출력합니다.
				for(let j in subTypeList) {
					const inputFormDiv = $("<div>").attr({
						"class": "inputForm"
					});
					
					const inputContainerDiv = $("<div>").attr({
						"class": "inputContainer"
					});
					
					const typeNameSelect = $("<select>").attr({
						"class": "typeName"
					});
					
					for(let k in petTypeList) {
						const option = $("<option>").text(petTypeList[k].typeName);
						typeNameSelect.append(option);
					}
					typeNameSelect.val(petTypeList[i].typeName);
					
					const originTypeName = $("<input>").attr({
						"type": "hidden",
						"class": "originTypeName",
						"value": subTypeList[j].typeName
					});
					
					const subTypeNameInput = $("<input>").attr({
						"type": "text",
						"class": "subTypeName",
						"value": subTypeList[j].subTypeName,
						"placeholder": subTypeList[j].subTypeName
					});
					
					const originSubTypeName = $("<input>").attr({
						"type": "hidden",
						"class": "originSubTypeName",
						"value": subTypeList[j].subTypeName
					});
					
					const subTypeCommentTextarea = $("<textarea>").attr({
						"class": "subTypeComment"
					}).val(subTypeList[j].subTypeComment);
					
					
					inputContainerDiv.append(typeNameSelect);
					inputContainerDiv.append(originTypeName);
					inputContainerDiv.append(subTypeNameInput);
					inputContainerDiv.append(originSubTypeName);
					inputContainerDiv.append(subTypeCommentTextarea);
					
					inputFormDiv.append(inputContainerDiv);
					
					
					const buttonsContainerDiv = $("<div>").attr({
						"class": "buttonsContainer"
					});
					
					const submitButton = $("<input>").attr({
						"type": "button",
						"class": "submitButton",
						"value": "수정",
						"onclick": "updatePetSubTypeName('" + context + "', this);"
					});
					
					const deleteButton = $("<input>").attr({
						"type": "button",
						"class": "deleteButton",
						"value": "삭제",
						"onclick": "deletePetSubTypeName('" + context + "', this);"
					});
					
					buttonsContainerDiv.append(submitButton);
					buttonsContainerDiv.append(deleteButton);
					
					inputFormDiv.append(buttonsContainerDiv);
					
					updateContainer.append(inputFormDiv);
				}
			}
		}
		
		/* 출력 형식
		<div class="inputForm">
	        <div class="inputContainer">
	            <select class="typeName">
	                <option>강아지</option>
	                <option>대형견</option>
	                <option>고양이</option>
	            </select>
	            <input type="hidden" class="originTypeName" value="기존값">
	            
	            <input type="text" class="subTypeName" value="기존값">
	            <input type="hidden" class="originSubTypeName" value="기존값">
	            
	            <textarea class="subTypeComment">기존값</textarea>
	        </div>
	        
	        <div class="buttonsContainer">
	            <input type="button" class="submitButton" value="수정" onclick="updatePetSubTypeName(context, this);">
	            <input type="button" class="deleteButton" value="삭제" onclick="">
	        </div>
	    </div> 
	    */
	}
	
	
// DB에 subType 데이터를 작성하기 위한 메서드 입니다.
	function petSubTypeWrite(context) {
		const inputContainer = $(subTypeContainer).children(".inputForm").children(".inputContainer");
		
		const typeName = $(inputContainer).children(".typeName").val();
		const subTypeName = $(inputContainer).children(".subTypeName").val();
		const subTypeComment = $(inputContainer).children(".subTypeComment").val();
		
		$.ajax({
			type: "POST",
			async: true,
			url: context + "/petSubTypeWrite.do",
			dataType: "TEXT",
			data: {
				"typeName": typeName,
				"subTypeName": subTypeName,
				"subTypeComment": subTypeComment
			},
			success: function(result, status) {
				initTypeCallbackMethod(context, initPetContentsCallbackMethod, initSubTypeView, null);
			},
			error: function(result, status) {
				alert("subTypeName 저장 실패...");
			},
			complete: function(result, status) {
				$(inputContainer).children(".typeName").val("");
				$(inputContainer).children(".subTypeName").val("");
				$(inputContainer).children(".subTypeComment").val("");
			}
		});
	}
	
	
// DB에 petSubType 데이터를 "수정"하기 위한 메서드 입니다.
	function updatePetSubTypeName(context, target) {
		const inputForm = $(target).parent().parent();
		const inputContainer = $(inputForm).children(".inputContainer");
		
		const typeName = $(inputContainer).children(".typeName").val();
		const originTypeName = $(inputContainer).children(".originTypeName").val();
		
		const subTypeName = $(inputContainer).children(".subTypeName").val();
		const originSubTypeName = $(inputContainer).children(".originSubTypeName").val();
		
		const subTypeComment = $(inputContainer).children(".subTypeComment").val();
		
		$.ajax({
			type: "POST",
			async: true,
			url: context + "/petSubTypeUpdate.do",
			dataType: "TEXT",
			data: {
				"typeName" : typeName,
				"originTypeName": originTypeName,
				"subTypeName": subTypeName,
				"originSubTypeName": originSubTypeName,
				"subTypeComment": subTypeComment
			},
			success: function(result, status) {
				initTypeCallbackMethod(context, initPetContentsCallbackMethod, initSubTypeView, null);
				
			},
			error: function(result, status) {
				alert("subType 수정 실패...");
			}
		});
	}

	
// DB에 petSubType 데이터를 "삭제"하기 위한 메서드 입니다.
	function deletePetSubTypeName(context, target) {
		const inputForm = $(target).parent().parent();
		const inputContainer = $(inputForm).children(".inputContainer");
		
		const originTypeName = $(inputContainer).children(".originTypeName").val();
		const originSubTypeName = $(inputContainer).children(".originSubTypeName").val();
		
		$.ajax({
			type: "POST",
			async: true,
			url: context + "/petSubTypeDelete.do",
			dataType: "TEXT",
			data: {
				"typeName": originTypeName,
				"subTypeName": originSubTypeName
			},
			success: function(result, status) {
				initTypeCallbackMethod(context, initPetContentsCallbackMethod, initSubTypeView, null);
			},
			error: function(result, status) {
				alert("sutType 삭제 실패...");
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	