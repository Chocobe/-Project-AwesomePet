package com.awesomePet.service;

import java.util.List;

import com.awesomePet.dao.CommunicationBoardDAO;
import com.awesomePet.dao.CommunicationHitDAO;
import com.awesomePet.vo.CommunicationContentsVO;
import com.awesomePet.vo.CommunicationHitVO;

public class CommunicationBoardService {
	private CommunicationBoardDAO communicationBoardDAO;
	private CommunicationHitDAO communicationHitDAO;
	
	
// 초기화
	public CommunicationBoardService() {
		communicationBoardDAO = new CommunicationBoardDAO();
		communicationHitDAO = new CommunicationHitDAO();
	}
	
	
// "소통해요" 게시판의 게시글 총 개수를 구합니다.
	public int getTotalContentsCnt() {
		return communicationBoardDAO.selectTotalContentsCnt();
	}
	
	
// "소통해요" 게시판의 페이지 개수를 구합니다.
	public int getTotalPageCnt() {
		return communicationBoardDAO.selectTotalPageCnt();
	}
	
	
// "소통해요" 게시판의 게시글을 조회합니다.
	public List<CommunicationContentsVO> getCommunicationContentsList(int requestPage) {
		return communicationBoardDAO.selectContentsList(requestPage);
	}
	
	
// "소통해요" 특정 글을 조회합니다.
	public CommunicationContentsVO getCommunicationContents(int requestBoardIDX) {
		return communicationBoardDAO.selectContents(requestBoardIDX);
	}
	
	public CommunicationContentsVO getCommunicationContents(String writerID, String title, String content) {
		return communicationBoardDAO.selectContents(writerID, title, content);
	}
	
	
// "소통해요" 글을 작성합니다.
	public int writeCommunicationContents(CommunicationContentsVO communicationContentsVO) {
		return communicationBoardDAO.insertCommunicationContents(communicationContentsVO);
	}
	
	
// "소통해요" 글을 수정합니다.
	public int updateCommunicationContents(CommunicationContentsVO communicationContentsVO) {
		return communicationBoardDAO.updateCommunicationContents(communicationContentsVO);
	}
	
	
// "소통해요" 특정 글의 "좋아요" 값이 있는지 조회 합니다.
	public int isExistsHitter(CommunicationHitVO communicationHitVO) {
		return communicationHitDAO.selectCount(communicationHitVO);
	}
	
	
// "소통해요" 특정 글의 "좋아요" 를 삭제 합니다.
	public int deleteHit(CommunicationHitVO communicationHitVO) {
		return communicationHitDAO.deleteHit(communicationHitVO);
	}
	

// "소통해요" 특정 글의 "좋아요" 를 추가 합니다.
	public int insertHit(CommunicationHitVO communicationHitVO) {
		return communicationHitDAO.insertHit(communicationHitVO);
	}
	
	
// "소통해요" 글을 삭제합니다.
	public int deleteCommunicationContents(int boardIDX) {
		return communicationBoardDAO.deleteCommunicationContents(boardIDX);
	}
	
	
// "소통해요" 조회수를 증가 시킵니다.
	public int increaseWatch(int boardIDX) {
		return communicationBoardDAO.updateWatch(boardIDX);
	}
	
	
// "소통해요" 댓글수를 증가 시킵니다.
	public void updateReplyCnt(int boardIDX, int value) {
		communicationBoardDAO.updateReplyCnt(boardIDX, value);
	}

}
