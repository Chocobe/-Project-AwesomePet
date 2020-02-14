package com.awesomePet.service;

import java.util.List;

import com.awesomePet.dao.PetBoardDAO;
import com.awesomePet.vo.PetBoardVO;
import com.awesomePet.vo.PetContentsImageVO;
import com.awesomePet.vo.PetContentsVO;
import com.awesomePet.vo.PetSubTypeVO;
import com.awesomePet.vo.PetTypeVO;
import com.awesomePet.vo.PetVO;

public class PetBoardService {
	private PetBoardDAO petBoardDAO;
	
	
// 생성자
	public PetBoardService() {
		petBoardDAO = new PetBoardDAO();
	}
	
	
// petType 의 전체 데이터를 조회 합니다.
	public List<PetTypeVO> getPetTypeList() {
		return petBoardDAO.selectTotalPetType();
	}
	
	
// pet 테이블에 데이터를 INSERT 합니다.
	public int writePet(PetVO petVO) {
		return petBoardDAO.insertPet(petVO);
	}
	
	
// pet 테이블에서 petID를 SELECT 합니다.
	public int getPetID(PetVO petVO) {
		return petBoardDAO.selectPetID(petVO);
	}
	
	
// petBoard 테이블에 데이터를 INSERT 합니다.
	public int writePetBoard(PetContentsVO petBoardVO) {
		return petBoardDAO.insertPetBoard(petBoardVO);
	}
	
	
// petBoardImage 테이블에 데이터를 INSERT 합니다.
	public int writePetContentsImages(List<PetContentsImageVO> petBoardImageList) {
		return petBoardDAO.insertPetContentsImages(petBoardImageList);
	}
	
	
// petType 테이블에 데이터를 INSERT 합니다.
	public int writePetType(String typeName) {
		return petBoardDAO.insertPetType(typeName);
	}
	
	
// petType 테이블에 데이터를 UPDATE 합니다.
	public int updatePetType(String typeName, String originTypeName) {
		return petBoardDAO.updatePetType(typeName, originTypeName);
	}
	
	
// petType 테이블에 데이터를 DELETE 합니다.
	public int deletePetType(String typeName) {
		return petBoardDAO.deletePetType(typeName);
	}
	
	
// petSubType 테이블에 데이터를 INSERT 합니다.
	public int writePetSubType(PetSubTypeVO petSubTypeVO) {
		return petBoardDAO.insertPetSubType(petSubTypeVO);
	}
	
	
// petSubType 테이블에 데이터를 UPDATE 합니다.
	public int updatePetSubType(PetSubTypeVO petSubTypeVO) {
		return petBoardDAO.updatePetSubType(petSubTypeVO);
	}

	
// petSubType 테이블에 데이터를 DELETE 합니다.
	public int deletePetSubType(PetSubTypeVO petSubTypeVO) {
		return petBoardDAO.deletePetSubType(petSubTypeVO);
	}
	
	
// "공개"인 petBoard의 글 개수를 구합니다.
	public int getTotalPublicPageCnt() {
		return petBoardDAO.selectTotalPublicPageCnt();
	}
	
	public int getTotalPublicPageCnt(String requestTypeName) {
		return petBoardDAO.selectTotalPublicPageCnt(requestTypeName);
	}
	
	public int getTotalPublicPageCnt(String requestTypeName, String requestSubTypeName) {
		return petBoardDAO.selectTotalPublicPageCnt(requestTypeName, requestSubTypeName);
	}
	
	
// "공개"인 petBoardView를 출력하기 위한 데이터를 조회 합니다. (pet, petContentsImage 테이블 데이터)
	public PetBoardVO getPublicPetBoard(int requestPage) {
		return petBoardDAO.selectPublicPetBoard(requestPage);
	}
	
	public PetBoardVO getPublicPetBoard(int requestPage, String requestTypeName) {
		return petBoardDAO.selectPublicPetBoard(requestPage, requestTypeName);
	}
	
	public PetBoardVO getPublicPetBoard(int requestPage, String requestTypeName, String requestSubTypeName) {
		return petBoardDAO.selectPublicPetBoard(requestPage, requestTypeName, requestSubTypeName);
	}
	
	
// "공개", "비공개", "입양완료"인 petBoard의 글 개수를 구합니다.
	public int getTotalPageCnt() {
		return petBoardDAO.selectTotalPageCnt();
	}
	
	public int getTotalPageCnt(String requestTypeName) {
		return petBoardDAO.selectTotalPageCnt(requestTypeName);
	}
	
	public int getTotalPageCnt(String requestTypeName, String requestSubTypeName) {
		return petBoardDAO.selectTotalPageCnt(requestTypeName, requestSubTypeName);
	}
	
	
// "공개", "비공개", "입양완료"인 petBoardView를 출력하기 위한 데이터를 조회 합니다. (pet, petContentsImage 테이블 데이터)
	public PetBoardVO getPetBoard(int requestPage) {
		return petBoardDAO.selectPetBoard(requestPage);
	}
	
	public PetBoardVO getPetBoard(int requestPage, String requestTypeName) {
		return petBoardDAO.selectPetBoard(requestPage, requestTypeName);
	}
	
	public PetBoardVO getPetBoard(int requestPage, String requestTypeName, String requestSubTypeName) {
		return petBoardDAO.selectPetBoard(requestPage, requestTypeName, requestSubTypeName);
	}
	
	
// "가족을 찾아요" "특정 글" 페이지를 요청합니다.
	public PetBoardVO getPetContents(int requestBoardIDX) {
		return petBoardDAO.selectPetContents(requestBoardIDX);
	}
	
	
// "가족을 찾아요" "특정 글" 이미지 리스트를 요청합니다.
	public List<PetContentsImageVO> getImageList(int requestBoardIDX) {
		return petBoardDAO.selectPetContentsImageList(requestBoardIDX);
	}
	
	
// "가족을 찾아요" "특정 글"의 삭제를 요청합니다.
	public int deletePetContents(int requestBoardIDX) {
		return petBoardDAO.deletePetContents(requestBoardIDX);
	}
	
	
// "가족을 찾아요" 조회수를 증가 시킵니다.
	public int increaseWatch(int requestBoardIDX) {
		return petBoardDAO.updateWatch(requestBoardIDX);
	}
}
