package com.awesomePet.controllers.memberControllers;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;

public class MemberJoinResultViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/views/member/joinResultView.jsp";
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
