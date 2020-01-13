package com.awesomePet.controllers.memberControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;

public class MyInfoCertificateViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = request.getContextPath() + "/views/member/myInfoCertificateView.jsp";
		ControllerUtil.redirect(response, resultPagePath);
	}
}
