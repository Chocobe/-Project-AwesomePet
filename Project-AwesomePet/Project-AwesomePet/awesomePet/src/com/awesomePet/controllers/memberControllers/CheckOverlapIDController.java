package com.awesomePet.controllers.memberControllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.MemberService;


public class CheckOverlapIDController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		MemberService memberService = new MemberService();
		
		PrintWriter out = response.getWriter();		
		String jsonData = request.getParameter("id");
		
		boolean isOverlapID = memberService.checkOverlapID(jsonData);
		out.print(isOverlapID);	
	}
}
