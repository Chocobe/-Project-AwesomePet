package com.awesomePet.controllers.memberControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;

public class MemberLogoutController implements SubController {
	
// 로그아웃 기능을 수행 합니다.
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = request.getContextPath() + "/index.do";
		
		// 현재 session을 삭제합니다.
		request.getSession().invalidate();
		
		// 홈으로 돌아갑니다.
		response.sendRedirect(resultPagePath);
	}
}
