package com.awesomePet.service;

import java.util.List;

import com.awesomePet.dao.PetBoardDAO;
import com.awesomePet.vo.PetTypeVO;

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
}
