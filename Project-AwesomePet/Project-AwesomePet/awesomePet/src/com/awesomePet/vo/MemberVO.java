package com.awesomePet.vo;

import java.sql.Date;

public class MemberVO {
	private String memberID;
	private String memberPW;
	private String memberEmail;
	private String memberPhone;
	private String memberAddr;
	private int memberGrade;
	private Date memberJoinDate;
	
	
// 생성자
	public MemberVO() { }
	
	public MemberVO(String memberID,
					String memberPW) {
		this(memberID,
			 memberPW,
			 null, null, null, 0, null);
	}

	public MemberVO(String memberID,
					String memberPW,
					String memberEmail,
					String memberPhone,
					String memberAddr,
					int memberGrade,
					Date memberJoinDate) {
		this.memberID = memberID;
		this.memberPW = memberPW;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberAddr = memberAddr;
		this.memberGrade = memberGrade;
		this.memberJoinDate = memberJoinDate;
	}

	
// memberID	
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	
// memberPW
	public String getMemberPW() {
		return memberPW;
	}
	public void setMemberPW(String memberPW) {
		this.memberPW = memberPW;
	}

	
// memberEmail
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	

// memberPhone
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	
// memberAddr
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}

	
// memberGrade
	public int getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(int memberGrade) {
		this.memberGrade = memberGrade;
	}

	
// memberJoinDate
	public Date getMemberJoinDate() {
		return memberJoinDate;
	}
	public void setMemberJoinDate(Date memberJoinDate) {
		this.memberJoinDate = memberJoinDate;
	}
}
