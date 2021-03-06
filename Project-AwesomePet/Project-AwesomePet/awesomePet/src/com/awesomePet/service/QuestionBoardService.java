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
	
	public QuestionContentsVO getQuestionContents(String writerID, String title, String content) {
		return questionBoardDAO.selectContents(writerID, title, content);
	}
	
	
// "궁금해요" 글을 작성합니다.
	public int writeQuestionContents(QuestionContentsVO questionContentsVO) {
		return questionBoardDAO.insertQuestionContents(questionContentsVO);
	}
	
	
// "궁금해요" 글을 수정합니다.
	public int updateQuestionContents(QuestionContentsVO questionContentsVO) {
		return questionBoardDAO.updateQuestionContents(questionContentsVO);
	}
	
	
// "궁금해요" 글을 삭제합니다.
	public int deleteQuestionContents(int boardIDX) {
		return questionBoardDAO.deleteQuestionContents(boardIDX);
	}
	
	
// "궁금해요" 조회수를 증가 시킵니다.
	public int increaseWatch(int boardIDX) {
		return questionBoardDAO.updateWatch(boardIDX);
	}
	
	
// "궁금해요" 댓글수를 증가 시킵니다.
	public void updateReplyCnt(int boardIDX, int value) {
		questionBoardDAO.updateReplyCnt(boardIDX, value);
	}
}
