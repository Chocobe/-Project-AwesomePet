package com.awesomePet.service;

import java.util.List;

import com.awesomePet.dao.QuestionBoardDAO;
import com.awesomePet.vo.QuestionContentsVO;

public class QuestionBoardService {
	private QuestionBoardDAO questionBoardDAO;
	
// 초기화
	public QuestionBoardService() {
		questionBoardDAO = new QuestionBoardDAO();
	}
	
	
// "궁금해요" 게시판의 게시글 총 개수를 구합니다.
	public int getTotalContentsCnt() {
		return questionBoardDAO.selectTotalContentsCnt();
	}
	
	
// "궁금해요" 게시판의 페이지 개수를 구합니다.
	public int getTotalPageCnt() {
		return questionBoardDAO.selectTotalPageCnt();
	}
	
	
// "궁금해요" 게시판의 게시글을 조회합니다.
	public List<QuestionContentsVO> getQuestionContentsList(int requestPage) {
		return questionBoardDAO.selectContentsList(requestPage);
	}
	
	
// "궁금해요" 특정 글을 조회합니다.
	public QuestionContentsVO getQuestionContents(int requestBoardIDX) {
		return questionBoardDAO.selectContents(requestBoardIDX);
	}
}
