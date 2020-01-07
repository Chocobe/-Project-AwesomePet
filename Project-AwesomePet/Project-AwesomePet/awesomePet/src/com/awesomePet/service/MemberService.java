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
}
