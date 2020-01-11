package com.awesomePet.controllers.memberControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.MemberService;
import com.awesomePet.vo.MemberVO;

public class MemberLoginController implements SubController {
// Login 서비스를 호출합니다.
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		MemberService memberService = new MemberService();
		System.out.println("MemberLoginController의 execute() 메서드가 호출되었습니다");
		
		String resultPagePath = "";
		
		String memberID = request.getParameter("memberID");
		String memberPW = request.getParameter("memberPW");
		
		MemberVO memberVO = new MemberVO(memberID, memberPW);
		MemberVO resultVO = memberService.memberLogin(memberVO);
		
		if(resultVO == null) {
			request.setAttribute("memberLoginError", "ID 또는 PW가 일치하지 않습니다.");
			resultPagePath = "/loginView.do";
			
			System.out.println("******************* 로그인 실패 테스트 입니다");
			
			ControllerUtil.forward(request, response, resultPagePath);

		} else {
			HttpSession session = request.getSession();
			session.setAttribute("memberLoginID", resultVO.getMemberID());
			resultPagePath = request.getContextPath() + "/index.do";
			
			response.sendRedirect(resultPagePath);
		}
	}
}
