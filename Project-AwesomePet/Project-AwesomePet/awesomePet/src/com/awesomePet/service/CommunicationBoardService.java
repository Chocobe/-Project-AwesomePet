package com.awesomePet.service;

import java.util.List;

import com.awesomePet.dao.CommunicationBoardDAO;
import com.awesomePet.vo.CommunicationContentsVO;

public class CommunicationBoardService {
	private CommunicationBoardDAO communicationBoardDAO;
	
	
// 초기화
	public CommunicationBoardService() {
		communicationBoardDAO = new CommunicationBoardDAO();
	}
	
	
// "궁금해요" 게시판의 게시글 총 개수를 구합니다.
	public int getTotalContentsCnt() {
		return communicationBoardDAO.selectTotalContentsCnt();
	}
	
	
// "궁금해요" 게시판의 페이지 개수를 구합니다.
	public int getTotalPageCnt() {
		return communicationBoardDAO.selectTotalPageCnt();
	}
	
	
// "궁금해요" 게시판의 게시글을 조회합니다.
	public List<CommunicationContentsVO> getCommunicationContentsList(int requestPage) {
		return communicationBoardDAO.selectContentsList(requestPage);
	}
	
	
// "궁금해요" 특정 글을 조회합니다.
	public CommunicationContentsVO getCommunicationContents(int requestBoardIDX) {
		return communicationBoardDAO.selectContents(requestBoardIDX);
	}
	
	public CommunicationContentsVO getCommunicationContents(String writerID, String title, String content) {
		return communicationBoardDAO.selectContents(writerID, title, content);
	}
	
	
// "궁금해요" 글을 작성합니다.
	public int writeCommunicationContents(CommunicationContentsVO communicationContentsVO) {
		return communicationBoardDAO.insertCommunicationContents(communicationContentsVO);
	}
	
	
// "궁금해요" 글을 수정합니다.
	public int updateCommunicationContents(CommunicationContentsVO communicationContentsVO) {
		return communicationBoardDAO.updateCommunicationContents(communicationContentsVO);
	}
	
	
// "궁금해요" 글을 삭제합니다.
	public int deleteCommunicationContents(int boardIDX) {
		return communicationBoardDAO.deleteCommunicationContents(boardIDX);
	}
	
	
// "궁금해요" 조회수를 증가 시킵니다.
	public int increaseWatch(int boardIDX) {
		return communicationBoardDAO.updateWatch(boardIDX);
	}
	
	
// "궁금해요" 댓글수를 증가 시킵니다.
	public void updateReplyCnt(int boardIDX, int value) {
		communicationBoardDAO.updateReplyCnt(boardIDX, value);
	}

}
