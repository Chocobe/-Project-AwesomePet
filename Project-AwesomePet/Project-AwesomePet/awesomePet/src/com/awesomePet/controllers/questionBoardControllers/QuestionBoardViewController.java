package com.awesomePet.controllers.questionBoardControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;

public class QuestionBoardViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/views/questionBoard/questionBoardViewTEST.jsp";
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
