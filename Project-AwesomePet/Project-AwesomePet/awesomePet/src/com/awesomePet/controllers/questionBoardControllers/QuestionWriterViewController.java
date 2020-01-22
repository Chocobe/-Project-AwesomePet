package com.awesomePet.controllers.questionBoardControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;

public class QuestionWriterViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "";
		
		HttpSession session = request.getSession();
		String memberLoginID = (String)session.getAttribute("memberLoginID");
		
		if(memberLoginID == null) {
			resultPagePath = request.getContextPath() + "/loginView.do";
			ControllerUtil.redirect(response, resultPagePath);
			
		} else {
			resultPagePath = "/views/questionBoard/questionWriterView.jsp";
			ControllerUtil.forward(request, response, resultPagePath);
		}
	}
}
