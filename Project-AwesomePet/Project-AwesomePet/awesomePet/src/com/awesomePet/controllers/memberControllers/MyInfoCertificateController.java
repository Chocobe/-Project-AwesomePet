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

public class MyInfoCertificateController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		MemberService memberService = new MemberService();
		HttpSession session = request.getSession();
		
		String resultPagePath = "";
		
		String memberID = (String)session.getAttribute("memberLoginID");
		String memberPW = request.getParameter("memberPW");
		
		MemberVO memberVO = new MemberVO(memberID, memberPW);
		MemberVO myInfoVO = memberService.memberLogin(memberVO);
		
		if(myInfoVO == null) {
			request.setAttribute("errorMessage", "비밀번호가 일치하지 않습니다");
			resultPagePath = "/myInfoCertificateView.do";
			
		} else {
			request.setAttribute("myInfoVO", myInfoVO);
			resultPagePath = "/myInfoView.do";
		}
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
