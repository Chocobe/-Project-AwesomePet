package com.awesomePet.vo;

import java.util.List;

public class PetReplyVO {
	private List<PetReplyContentsVO> petReplyContentsList;
	
	private int totalPageCnt;
	private int currentPage;
	private int prevPage;
	private int nextPage;
	private int beginPage;
	private int endPage;
	
	
// 생성자
	public PetReplyVO() { }
	
	public PetReplyVO(List<PetReplyContentsVO> petReplyContentsList,
			   		  int totalPageCnt,
			   		  int currentPage) {
		this.petReplyContentsList = petReplyContentsList;
		setPageInfo(totalPageCnt, currentPage);
	}
	
	
	
// 페이지 정보 SET 메서드입니다.
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
		
		// 5. 시작 페이지를 보정합니다.
		if(totalPageCnt - currentPage < 2) {
			beginPage = totalPageCnt - 4;
		}
		
		if(beginPage < 1) {
			beginPage = 1;
		}
	}
	
	
// petReplyContentsList
	public List<PetReplyContentsVO> getPetReplyContentsVO() {
		return petReplyContentsList;
	}
	public void setPetReplyVO(List<PetReplyContentsVO> petReplyContentsList) {
		this.petReplyContentsList = petReplyContentsList;
	}
	
	
// totalPageCnt
	public int getTotalPageCnt() {
		return totalPageCnt;
	}
	public void setTotalPageCnt(int totalPageCnt) {
		this.totalPageCnt = totalPageCnt;
	}
	
	
// currentPage
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
// prevPage
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	
	
// nextPage
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	
// beginPage
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	
	
// endPage
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
