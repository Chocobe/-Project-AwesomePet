package com.awesomePet.service;

import java.util.List;

import com.awesomePet.dao.CommunicationReplyDAO;
import com.awesomePet.vo.CommunicationReplyContentsVO;


public class CommunicationReplyService {
	private CommunicationReplyDAO communicationReplyDAO;
	
// 생성자
	public CommunicationReplyService() {
		this.communicationReplyDAO = new CommunicationReplyDAO();
	}
	
	
// 해당 게시물("소통해요" 게시글) 에 대한 댓글 총 개수를 구합니다.
	public int getTotalReplyContentsCnt(int parentID) {
		return communicationReplyDAO.selectTotalReplyCnt(parentID);
	}
	
	
// 해당 게시물("소통해요" 게시글) 에 대한 댓글 페이지 수를 구합니다.
	public int getTotalPageCnt(int parentID) {
		return communicationReplyDAO.selectTotalPageCnt(parentID);
	}
	

// 해당 게시물("소통해요" 게시글) 에 대한 댓글 리스트를 조회합니다.
	public List<CommunicationReplyContentsVO> getCommunicationReplyList(int parentID, int requestPage) {
		return communicationReplyDAO.selectReplyList(parentID, requestPage);
	}
	
	
// 해당 게시물("소통해요" 게시글) 에 대한 댓글을 작성 합니다.
	public int writeCommunicationReply(CommunicationReplyContentsVO communicationReplyContentsVO) {
		return communicationReplyDAO.insertCommunicationReply(communicationReplyContentsVO);
	}
	
	
// 해당 게시물("소통해요" 게시글) 의 특정 댓글을 수정 합니다.
	public int updateCommunicationReply(CommunicationReplyContentsVO communicationReplyContentsVO) {
		return communicationReplyDAO.updateCommunicationReply(communicationReplyContentsVO);
	}
	
	
// 해당 게시물("소통해요" 게시글) 의 특정 댓글 하나를 조회 합니다.
	public CommunicationReplyContentsVO getCommunicationReply(int replyIDX) {
		return communicationReplyDAO.selectCommunicationReply(replyIDX);
	}
	
	
// 해당 게시물("소통해요" 게시글) 의 특정 댓글을 삭제 합니다.
	public int deleteCommunicationReply(int replyIDX) {
		return communicationReplyDAO.deleteCommunicationReply(replyIDX);
	}
}
