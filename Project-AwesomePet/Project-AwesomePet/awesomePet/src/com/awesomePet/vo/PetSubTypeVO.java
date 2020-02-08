package com.awesomePet.vo;

public class PetSubTypeVO {
	private String typeName;
	private String subTypeName;
	private String subTypeComment;
	
	
// 생성자
	public PetSubTypeVO() { }
	
	public PetSubTypeVO(String typeName,
						String subTypeName,
						String subTypeComment) {
		this.typeName = typeName;
		this.subTypeName = subTypeName;
		this.subTypeComment = subTypeComment;
	}
	
	
// typeName
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeName() {
		return typeName;
	}
	
	
// subTypeName
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	public String getSubTypeName() {
		return subTypeName;
	}
	
	
// subTypeComment
	public void setSubTypeComment(String subTypeComment) {
		this.subTypeComment = subTypeComment;
	}
	public String getSubTypeComment() {
		return subTypeComment;
	}
}
