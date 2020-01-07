package com.awesomePet.controllers.memberControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;

public class MemberLoginController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String memberID = request.getParameter("memberID");
		String memberPW = request.getParameter("memberPW");
		
		
	}
}
