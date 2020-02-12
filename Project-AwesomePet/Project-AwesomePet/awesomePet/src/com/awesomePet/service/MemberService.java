package com.awesomePet.service;

import com.awesomePet.dao.MemberDAO;
import com.awesomePet.vo.MemberVO;

// 회원관련 서비스 클래스 입니다.
public class MemberService {
	private MemberDAO memberDAO;
	
	
// 생성자
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	
// 로그인 서비스 메서드 입니다.
	public MemberVO memberLogin(MemberVO memberVO) {
		return memberDAO.selectMember(memberVO);
	}
	
	
// 회원가입 ID의 중복여부 검사 메서드 입니다.
	// true : 중복
	// false : 사용가능
	public boolean checkOverlapID(String id) {
		return memberDAO.selectID(id);
	}
	
	
// 회원가입 서비스 메서드 입니다.
	public boolean memberJoin(MemberVO memberVO) {
		return memberDAO.insertMember(memberVO);
	}
	
	
// 회원정보 수정 메서드 입니다.
	public boolean updateMyInfo(MemberVO memberVO, String memberPWOrigin) {
		return memberDAO.updateMember(memberVO, memberPWOrigin);
	}
	
	
// 현재 회원의 등급을 가져옵니다.
	public int getGrade(String memberLoginID) {
		return memberDAO.selectGrade(memberLoginID);
	}
}
