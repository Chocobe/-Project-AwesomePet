package com.awesomePet.service;

import java.util.List;

import com.awesomePet.dao.QuestionReplyDAO;
import com.awesomePet.vo.QuestionReplyContentsVO;

public class QuestionReplyService {
	QuestionReplyDAO questionReplyDAO;
	
// 생성자
	public QuestionReplyService() {
		this.questionReplyDAO = new QuestionReplyDAO();
	}
	
	
// 해당 게시물("궁금해요" 게시글) 에 대한 댓글 총 개수를 구합니다.
	public int getTotalReplyContentsCnt(int parentID) {
		return questionReplyDAO.selectTotalReplyCnt(parentID);
	}
	
	
// 해당 게시물("궁금해요" 게시글) 에 대한 댓글 페이지 수를 구합니다.
	public int getTotalPageCnt(int parentID) {
		return questionReplyDAO.selectTotalPageCnt(parentID);
	}
	

// 해당 게시물("궁금해요" 게시글) 에 대한 댓글 리스트를 조회합니다.
	public List<QuestionReplyContentsVO> getQuestionReplyList(int parentID, int requestPage) {
		return questionReplyDAO.selectReplyList(parentID, requestPage);
	}
	
	
// 해당 게시물("궁금해요" 게시글) 에 대한 댓글을 작성 합니다.
	public int writeQuestionReply(QuestionReplyContentsVO questionReplyContentsVO) {
		return questionReplyDAO.insertReply(questionReplyContentsVO);
	}
}
