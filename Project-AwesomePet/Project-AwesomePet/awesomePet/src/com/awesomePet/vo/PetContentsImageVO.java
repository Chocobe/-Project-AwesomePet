package com.awesomePet.vo;

public class PetContentsImageVO {
	private int boardIDX;
	private int orderNumber;
	private String imgLocation;
	private String imgOriginLocation;
	
	
	
	
// 생성자
	public PetContentsImageVO() { }
	
	public PetContentsImageVO(int boardIDX,
							int orderNumber,
							String imgLocation,
							String imgOriginLocation) {
		this.boardIDX = boardIDX;
		this.orderNumber = orderNumber;
		this.imgLocation = imgLocation;
		this.imgOriginLocation = imgOriginLocation;
	}

	
// boardIDX
	public int getBoardIDX() {
		return boardIDX;
	}
	public void setBoardIDX(int boardIDX) {
		this.boardIDX = boardIDX;
	}

	
// orderNumber
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	
// imgLocation
	public String getImgLocation() {
		return imgLocation;
	}
	public void setImgLocation(String imgLocation) {
		this.imgLocation = imgLocation;
	}

	
// imgOriginLocation
	public String getImgOriginLocation() {
		return imgOriginLocation;
	}
	public void setImgOriginLocation(String imgOriginLocation) {
		this.imgOriginLocation = imgOriginLocation;
	}
}
