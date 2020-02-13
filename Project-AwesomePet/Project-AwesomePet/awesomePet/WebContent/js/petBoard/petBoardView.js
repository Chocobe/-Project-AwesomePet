const typeMenuContainer = $(".typeMenuContainer");
const absoluteContainer = typeMenuContainer.children(".absoluteContainer");
const typeList = $(absoluteContainer).children(".typeList");
const subTypeMenuContainer = $(".subTypeMenuContainer");

// 초기화
	function initTypeMenu(context, requestTypeName, requestSubTypeName) {
		initTypeMenuData(context, requestTypeName, requestSubTypeName);
	    initTypeMenuAction();
	};
	
	
// 분류 메뉴의 데이터를 초기화 합니다.
	function initTypeMenuData(context, requestTypeName, requestSubTypeName) {
		$.ajax({
			type: "GET",
			async: false,
			url: context + "/petTypeView.do",
			success: function(jsonString, status) {
				const parsedJSON = JSON.parse(jsonString);
				
				for(let i in parsedJSON) {
					const typeName_li = $("<li>").text(parsedJSON[i].typeName);
					typeList.append(typeName_li);
					
					const subTypeJSON = parsedJSON[i].subTypeList;
					let subTypeContainer = null;
					let subType_ul = null;
					
					// subTypeContainer 출력형식 생성
					if(subTypeJSON.length > 0) {
						subTypeContainer = $("<div>").attr({
							"class": "subTypeContainer"
						});
						
						subType_ul = $("<ul>").attr({
							"class": "subTypeList"
						});
						
						const subType_li = $("<li>").text("전체");
						
						subType_ul.append(subType_li);
						subTypeContainer.append(subType_ul);
					}
					
					// subType 각 항목 생성
					for(let j in subTypeJSON) {
						const subType_li = $("<li>").text(subTypeJSON[j].subTypeName);
						subType_ul.append(subType_li);
					}
					
					// 생성된 subType이 유효하다면, 출력 합니다.
					if(subTypeContainer != null) {
						subTypeMenuContainer.append(subTypeContainer);
					}
					
					/* subType 출력 형식
			        <div class="subTypeContainer">
			            <ul class="subTypeList">
			                <li>전체</li>
			                <li>푸들푸들</li>
			            </ul>
			        </div> 
					*/
				}
			}
		});
	}
	
	
// 분류 메뉴의 동작을 초기화 합니다.
	function initTypeMenuAction() {
	    // typeName 메뉴를 엽니다.
	    typeMenuContainer.mouseenter(function() {
	        $(typeList).stop().slideDown(200);
	    });
	    
	    // typeName 메뉴를 닫습니다.
	    $(absoluteContainer).mouseleave(function() {
	        $(typeList).stop().slideUp(300);
	    });
	    
	    // 타입 선택 동작을 합니다.
	    $(typeList).children("li").click(function() {
	        // 이전 subTypeName 메뉴를 닫습니다.
	        
	        // subTypeName 메뉴를 엽니다.
	        $(absoluteContainer).children(".typeName").text($(this).text());
	        $(".requestTypeName").text($(this).text());
	        $(typeList).stop().slideUp(300);
	        
	        const typeNameIDX = $(this).index();
	        subTypeMenuContainer.children(".subTypeContainer").css({
	            "display": "none"
	        });
	        
	        subTypeMenuContainer.children(".subTypeContainer").eq(typeNameIDX).slideDown(300);
	    });
	    
	    subTypeMenuContainer.children(".subTypeContainer").children(".subTypeList").children("li").click(function() {
	    	alert($(this).text());
	    });
	}
	
	
// 게시물의 동작 메서드 입니다. (글 보기 동작)
	function petContentsView(context, target) {
		alert("contextPath : " + context + ", boardIDX : " + $(target).children(".boardIDX").val());
	}
	
