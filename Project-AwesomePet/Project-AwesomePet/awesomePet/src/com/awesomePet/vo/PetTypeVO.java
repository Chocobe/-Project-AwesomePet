package com.awesomePet.vo;

import java.util.List;

public class PetTypeVO {
	private String typeName;
	private List<PetSubTypeVO> subTypeList;
	
	
// 생성자
	public PetTypeVO() { }
	
	public PetTypeVO(String typeName,
					 List<PetSubTypeVO> subTypeList) {
		this.typeName = typeName;
		this.subTypeList = subTypeList;
	}
	
	
// typeName
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeName() {
		return typeName;
	}
	
	
// petSubTypeList
	public void setPetSubTypeList(List<PetSubTypeVO> subTypeList) {
		this.subTypeList = subTypeList;
	}
	public List<PetSubTypeVO> getSubTypeList() {
		return subTypeList;
	}
}
