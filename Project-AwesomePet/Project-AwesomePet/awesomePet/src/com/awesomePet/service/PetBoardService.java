package com.awesomePet.service;

import java.util.List;

import com.awesomePet.dao.PetBoardDAO;
import com.awesomePet.vo.PetBoardImageVO;
import com.awesomePet.vo.PetBoardVO;
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
	public int writePetBoard(PetBoardVO petBoardVO) {
		return petBoardDAO.insertPetBoard(petBoardVO);
	}
	
	
// petBoardImage 테이블에 데이터를 INSERT 합니다.
	public int writePetBoardImages(List<PetBoardImageVO> petBoardImageList) {
		return petBoardDAO.insertPetBoardImages(petBoardImageList);
	}
	
	
// petType 테이블에 데이터를 INSERT 합니다.
	public int writePetType(String typeName) {
		return petBoardDAO.insertPetType(typeName);
	}
	
	
// petType 테이블에 데이터를 UPDATE 합니다.
	public int updatePetType(String typeName, String originTypeName) {
		return petBoardDAO.updatePetType(typeName, originTypeName);
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
	
	
	
}
