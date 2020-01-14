package com.awesomePet.controllers.memberControllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.MemberService;
import com.awesomePet.vo.MemberVO;

public class MemberJoinController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		MemberService memberService = new MemberService();
		String resultPagePath = "";
		
		String memberID = request.getParameter("memberID");
		String memberPW = request.getParameter("memberPW");
		String memberName = request.getParameter("memberName");
		
		String memberBirthDayYear = request.getParameter("memberBirthDayYear");
		String memberBirthDayMonth = request.getParameter("memberBirthDayMonth");
		String memberBirthDayDate = request.getParameter("memberBirthDayDate");
		String memberBirthDayString = memberBirthDayYear + "-" + memberBirthDayMonth + "-" + memberBirthDayDate;
		LocalDate memberBirthDay = Date.valueOf(memberBirthDayString).toLocalDate();
		
		String memberEmail = request.getParameter("memberEmail");
		
		String memberPhone_1 = request.getParameter("memberPhone_1");
		String memberPhone_2 = request.getParameter("memberPhone_2");
		String memberPhone_3 = request.getParameter("memberPhone_3");
		String memberPhone = memberPhone_1 + "-" + memberPhone_2 + "-" + memberPhone_3;
		
		String memberAddr = request.getParameter("memberAddr");
		
		MemberVO memberVO = new MemberVO(memberID,
										 memberPW,
										 memberName,
										 memberBirthDay,
										 memberEmail,
										 memberPhone,
										 memberAddr,
										 0);
		
		boolean memberJoinResult = memberService.memberJoin(memberVO);
		
		if(memberJoinResult) {
			MemberVO memberLoginVO = memberService.memberLogin(memberVO);
			
			HttpSession session = request.getSession();
			session.setAttribute("memberLoginID", memberLoginVO.getMemberID());
			
			resultPagePath = "/memberJoinResultView.do";
			
			ControllerUtil.forward(request, response, resultPagePath);
			
		} else {
			request.setAttribute("memberJoinVO", memberVO);
			resultPagePath = "/memberJoinView.do";
			
			ControllerUtil.forward(request, response, resultPagePath);
		}
	}
}
