package com.awesomePet.vo;

import java.util.List;

public class PetBoardVO {
	private List<PetVO> petList;
	private List<PetContentsVO> petContentsList;
	
	private String currentTypeName;
	private String currentSubTypeName;
	
	// 페이지 데이터 SETTER - setPageInfo()를 통해서 수행할 수 있습니다.
	private int totalPageCnt;	// GETTER 만 있습니다.
	private int currentPage;	// GETTER 만 있습니다.
	private int prevPage;		// GETTER 만 있습니다.
	private int nextPage;		// GETTER 만 있습니다.
	private int beginPage;		// GETTER 만 있습니다.
	private int endPage;		// GETTER 만 있습니다.
	
	
// 생성자
	public PetBoardVO() { }
	
	public PetBoardVO(List<PetVO> petList, 
					  List<PetContentsVO> petContentsList) {
		this(petList,
			 petContentsList,
			 0,
			 0);
	}
	
	public PetBoardVO(List<PetVO> petList,
					  List<PetContentsVO> petContentsList,
					  int totalPageCnt,
					  int currentPage) {
		setPetList(petList);
		setPetContentsList(petContentsList);
		setPageInfo(totalPageCnt, currentPage);
	}
	
	
// questionContentsList
	public List<PetContentsVO> getPetContentsList() {
		return petContentsList;
	}
	public void setPetContentsList(List<PetContentsVO> petContentsList) {
		this.petContentsList = petContentsList;
	}
	
	
// petList
	public List<PetVO> getPetList() {
		return petList;
	}
	public void setPetList(List<PetVO> petList) {
		this.petList = petList;
	}
	
	
// currentTypeName
	public String getCurrentTypeName() {
		return currentTypeName;
	}
	public void setCurrentTypeName(String currentTypeName) {
		this.currentTypeName = currentTypeName;
	}
	
	
// currentSubTypeName
	public String getCurrentSubTypeName() {
		return currentSubTypeName;
	}
	public void setCurrentSubTypeName(String currentSubTypeName) {
		this.currentSubTypeName = currentSubTypeName;
	}
	
	
// 페이지 정보 SETTER 메서드 
	public void setPageInfo(int totalPageCnt, int currentPage) {
		this.totalPageCnt = totalPageCnt;
		this.currentPage = currentPage;
		
		// 1. 이전 페이지를 설정합니다.
		if(currentPage < 2) {
			prevPage = 1;
			
		} else {
			prevPage = currentPage - 1;
		}
		
		// 2. 다음 페이지를 설정합니다.
		if(currentPage >= totalPageCnt) {
			nextPage = totalPageCnt;
			
		} else {
			nextPage = currentPage + 1;
		}
		
		// 3. 출력용 시작 페이지를 설정합니다.
		if(currentPage - 2 < 1) {
			beginPage = 1;
			
		} else {
			beginPage = currentPage - 2;
		}
		
		// 4. 출력용 끝 페이지를 설정합니다.
		if(beginPage + 4 < totalPageCnt) {
			endPage = beginPage + 4;
			
		} else {
			endPage = totalPageCnt;
		}
		
		// 5. 전체 페이지와 끝 페이지에 대한 시작 페이지를 보정합니다.
		if(totalPageCnt - currentPage < 2) {
			beginPage = totalPageCnt - 4;
		}
		
		if(beginPage < 1) {
			beginPage = 1;
		}
	}

	
// totalPageCnt
	public int getTotalPageCnt() {
		return totalPageCnt;
	}
	
// currentPage
	public int getCurrentPage() {
		return currentPage;
	}
	
	
// prevPage
	public int getPrevPage() {
		return prevPage;
	}
	
	
// nextPage
	public int getNextPage() {
		return nextPage;
	}
	
	
// beginPage
	public int getBeginPage() {
		return beginPage;
	}
	
	
// endPage
	public int getEndPage() {
		return endPage;
	}
}
