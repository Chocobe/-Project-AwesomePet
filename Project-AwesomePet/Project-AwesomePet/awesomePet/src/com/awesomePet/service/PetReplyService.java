package com.awesomePet.service;

import java.util.List;

import com.awesomePet.dao.PetReplyDAO;
import com.awesomePet.vo.PetReplyContentsVO;


public class PetReplyService {
	private PetReplyDAO petReplyDAO;
	
// 생성자
	public PetReplyService() {
		this.petReplyDAO = new PetReplyDAO();
	}
	
	
// 해당 게시물("가족을 찾아요" 게시글) 에 대한 댓글 총 개수를 구합니다.
	public int getTotalReplyContentsCnt(int parentID) {
		return petReplyDAO.selectTotalReplyCnt(parentID);
	}
	
	
// 해당 게시물("가족을 찾아요" 게시글) 에 대한 댓글 페이지 수를 구합니다.
	public int getTotalPageCnt(int parentID) {
		return petReplyDAO.selectTotalPageCnt(parentID);
	}
	

// 해당 게시물("가족을 찾아요" 게시글) 에 대한 댓글 리스트를 조회합니다.
	public List<PetReplyContentsVO> getPetReplyList(int parentID, int requestPage) {
		return petReplyDAO.selectReplyList(parentID, requestPage);
	}
	
	
// 해당 게시물("가족을 찾아요" 게시글) 에 대한 댓글을 작성 합니다.
	public int writePetReply(PetReplyContentsVO petReplyContentsVO) {
		return petReplyDAO.insertPetReply(petReplyContentsVO);
	}
	
	
// 해당 게시물("가족을 찾아요" 게시글) 의 특정 댓글을 수정 합니다.
	public int updatePetReply(PetReplyContentsVO petReplyContentsVO) {
		return petReplyDAO.updatePetReply(petReplyContentsVO);
	}
	
	
// 해당 게시물("가족을 찾아요" 게시글) 의 특정 댓글 하나를 조회 합니다.
	public PetReplyContentsVO getPetReply(int replyIDX) {
		return petReplyDAO.selectPetReply(replyIDX);
	}
	
	
// 해당 게시물("가족을 찾아요" 게시글) 의 특정 댓글을 삭제 합니다.
	public int deletePetReply(int replyIDX) {
		return petReplyDAO.deletePetReply(replyIDX);
	}
}
