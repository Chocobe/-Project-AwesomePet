package com.awesomePet.controllers.memberControllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.MemberService;
import com.awesomePet.vo.MemberVO;

public class UpdateMyInfoController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		MemberService memberService = new MemberService();
		String resultPagePath = "/updateMyInfoResultView.do";
		
		String memberID = request.getParameter("memberID");
		String memberPW = request.getParameter("memberPW");
		String memberPWOrigin = request.getParameter("memberPWOrigin");
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
		int memberGrade = Integer.parseInt(request.getParameter("memberGrade"));
		
		MemberVO memberVO = new MemberVO(memberID,
										 memberPW,
										 memberName,
										 memberBirthDay,
										 memberEmail,
										 memberPhone,
										 memberAddr,
										 memberGrade);
		
		boolean updateMyInfoResult = memberService.updateMyInfo(memberVO, memberPWOrigin);
		
		if(!updateMyInfoResult) {
			request.setAttribute("resultMessage", "회원정보 수정 실패");
		}
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
