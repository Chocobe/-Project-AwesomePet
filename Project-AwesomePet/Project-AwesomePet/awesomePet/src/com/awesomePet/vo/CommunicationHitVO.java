package com.awesomePet.vo;

public class CommunicationHitVO {
	private int boardIDX;
	private String hitMemberID;
	
	
// 생성자
	public CommunicationHitVO() { } 
	
	public CommunicationHitVO(int boardIDX,
							  String hitMemberID) {
		this.boardIDX = boardIDX;
		this.hitMemberID = hitMemberID;
	}
	
	
// boardIDX
	public void setBoardIDX(int boardIDX) {
		this.boardIDX = boardIDX;
	}
	public int getBoardIDX() {
		return boardIDX;
	}
	
	
// hitMemberID
	public void setHitMemberID(String hitMemberID) {
		this.hitMemberID = hitMemberID;
	}
	public String getHitMemberID() {
		return hitMemberID;
	}
}
