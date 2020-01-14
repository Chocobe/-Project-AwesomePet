package com.awesomePet.vo;

import java.time.LocalDate;

public class MemberVO {
	private String memberID;
	
	private String memberPW;
	private String memberPWMasking;		// GETTER만 존재합니다.
	
	private String memberName;
	
	private LocalDate memberBirthDay;
	private String memberBirthDayYear;	// GETTER만 존재합니다.
	private String memberBirthDayMonth;	// GETTER만 존재합니다.
	private String memberBirthDayDate;	// GETTER만 존재합니다.
	
	private String memberEmail;
	
	private String memberPhone;
	private String memberPhone_1;		// GETTER만 존재합니다.
	private String memberPhone_2;		// GETTER만 존재합니다.
	private String memberPhone_3;		// GETTER만 존재합니다.
	
	private String memberAddr;
	private int memberGrade;
	
	private LocalDate memberJoinDay;
	private String memberJoinDayYear;	// GETTER만 존재합니다.
	private String memberJoinDayMonth;	// GETTER만 존재합니다.
	private String memberJoinDayDate;	// GETTER만 존재합니다.
	
	
// 생성자
	public MemberVO() { }
	
	public MemberVO(String memberID,
					String memberPW) {
		this(memberID,
			 memberPW,
			 null, null, null, null, null, 0, null);
	}
	
	public MemberVO(String memberID,
					String memberPW,
					String memberName,
					LocalDate memberBirthDay,
					String memberEmail,
					String memberPhone,
					String memberAddr) {
		this(memberID,
			 memberPW,
			 memberName,
			 memberBirthDay,
			 memberEmail,
			 memberPhone,
			 memberAddr,
			 0, null);
	}

	public MemberVO(String memberID,
					String memberPW,
					String memberName,
					LocalDate memberBirthDay,
					String memberEmail,
					String memberPhone,
					String memberAddr,
					int memberGrade,
					LocalDate memberJoinDay) {
		this.memberID = memberID;
		
		// 비밀번호 마스킹값을 만듭니다.
		this.memberPW = memberPW;
		if(memberPW != null) {
			memberPWMasking = "";
			for(int i = 0; i < memberPW.length(); i++) {
				memberPWMasking += "*";
			}
		}
		
		this.memberName = memberName;
		
		// 생일을 분할합니다. (년, 월, 일)
		this.memberBirthDay = memberBirthDay;
		if(memberBirthDay != null) {
			this.memberBirthDayYear = String.valueOf(memberBirthDay.getYear());
			this.memberBirthDayMonth = String.valueOf(memberBirthDay.getMonthValue());
			this.memberBirthDayDate = String.valueOf(memberBirthDay.getDayOfMonth());
		}
		
		this.memberEmail = memberEmail;

		// 휴대전화를 "-"기준으로 분할합니다.
		this.memberPhone = memberPhone;
		if(memberPhone != null) {
			String[] memberPhoneParts = memberPhone.split("-");
			this.memberPhone_1 = memberPhoneParts[0];
			this.memberPhone_2 = memberPhoneParts[1];
			this.memberPhone_3 = memberPhoneParts[2];
		}
		
		this.memberAddr = memberAddr;
		this.memberGrade = memberGrade;
		
		// 회원가입한 날을 분할합니다. (년, 월, 일)
		this.memberJoinDay = memberJoinDay;
		if(memberJoinDay != null) {
			this.memberJoinDayYear = String.valueOf(memberJoinDay.getYear());
			this.memberJoinDayMonth = String.valueOf(memberJoinDay.getMonthValue());
			this.memberJoinDayDate = String.valueOf(memberJoinDay.getDayOfMonth());
		}
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
	
	// memberPWMasking (GETTER)
	public String getMemberPWMasking() {
		return memberPWMasking;
	}
	
	
// memberName
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	
// memberBirthDay
	public LocalDate getMemberBirthDay() {
		return memberBirthDay;
	}
	public void setMemberBirthDay(LocalDate memberBirthDay) {
		this.memberBirthDay = memberBirthDay;
	}
	
	// memberBirthDayYear (GETTER)
	public String getMemberBirthDayYear() {
		return memberBirthDayYear;
	}
	
	// memberBirthDayMonth (GETTER)
	public String getMemberBirthDayMonth() {
		return memberBirthDayMonth;
	}
	
	// memberBirthDayDate (GETTER)
	public String getMemberBirthDayDate() {
		return memberBirthDayDate;
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
	
	// memberPhone_1 (GETTER)
	public String getMemberPhone_1() {
		return memberPhone_1;
	}
	
	// memberPhone_2 (GETTER)
	public String getMemberPhone_2() {
		return memberPhone_2;
	}
	
	// memberPhone_3 (GETTER)
	public String getMemberPhone_3() {
		return memberPhone_3;
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

	
// memberJoinDay
	public LocalDate getMemberJoinDay() {
		return memberJoinDay;
	}
	public void setMemberJoinDay(LocalDate memberJoinDay) {
		this.memberJoinDay = memberJoinDay;
	}
	
	// memberJoinDayYear (GETTER)
	public String getMemberJoinDayYear() {
		return memberJoinDayYear;
	}
	
	// memberJoinDayMonth (GETTER)
	public String getMemberJoinDayMonth() {
		return memberJoinDayMonth;
	}
	
	// memberJoinDayDate (GETTER)
	public String getMemberJoinDayDate() {
		return memberJoinDayDate;
	}
}
