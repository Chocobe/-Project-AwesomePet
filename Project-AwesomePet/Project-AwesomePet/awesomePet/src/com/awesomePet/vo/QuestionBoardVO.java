package com.awesomePet.vo;

import java.util.List;

public class QuestionBoardVO {
	private List<QuestionContentsVO> questionContentsList;
	
	// 페이지 데이터 SETTER - setPageInfo()를 통해서 수행할 수 있습니다.
	private int totalPageCnt;	// GETTER 만 있습니다.
	private int currentPage;	// GETTER 만 있습니다.
	private int prevPage;		// GETTER 만 있습니다.
	private int nextPage;		// GETTER 만 있습니다.
	
	
// questionContentsVO
	public List<QuestionContentsVO> getQuestionContentsList() {
		return questionContentsList;
	}
	public void setQuestionContentsList(List<QuestionContentsVO> questionContentsList) {
		this.questionContentsList = questionContentsList;
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
			nextPage = currentPage;
			
		} else {
			nextPage = currentPage + 1;
		}
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
	
	
// totalPageCnt
	public int getTotalPageCnt() {
		return totalPageCnt;
	}
}
