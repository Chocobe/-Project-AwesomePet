package com.awesomePet.vo;

public class PetSubTypeVO {
	private String typeName;
	private String originTypeName;
	
	private String subTypeName;
	private String originSubTypeName;
	
	private String subTypeComment;
	
	
// 생성자
	public PetSubTypeVO() { }
	
	public PetSubTypeVO(String typeName,
						String subTypeName,
						String subTypeComment) {
		this(typeName,
			 null,
			 subTypeName,
			 null,
			 subTypeComment);
	}
	
	public PetSubTypeVO(String typeName,
						String originTypeName,
						String subTypeName,
						String originSubTypeName,
						String subTypeComment) {
		this.typeName = typeName;
		this.originTypeName = originTypeName;
		this.subTypeName = subTypeName;
		this.originSubTypeName = originSubTypeName;
		this.subTypeComment = subTypeComment;
	}
	
	
// typeName
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeName() {
		return typeName;
	}
	
	
// originTypeName
	public void setOriginTypeName(String originTypeName) {
		this.originTypeName = originTypeName;
	}
	public String getOriginTypeName() {
		return originTypeName;
	}
	
	
// subTypeName
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	public String getSubTypeName() {
		return subTypeName;
	}
	
	
// originSubTypeName
	public void setOriginSubTypeName(String originSubTypeName) {
		this.originSubTypeName = originSubTypeName;
	}
	public String getOriginSubTypeName() {
		return originSubTypeName;
	}

	
// subTypeComment
	public void setSubTypeComment(String subTypeComment) {
		this.subTypeComment = subTypeComment;
	}
	public String getSubTypeComment() {
		return subTypeComment;
	}
}
