package com.awesomePet.controllers.questionBoardControllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.QuestionBoardService;

public class QuestionContentsDeleteController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		QuestionBoardService questionBoardService = new QuestionBoardService();
		
		int requestBoardIDX = Integer.parseInt(request.getParameter("requestBoardIDX"));
		
		int result = questionBoardService.deleteQuestionContents(requestBoardIDX);
		
		PrintWriter out = response.getWriter();
		out.print(result);
		
		out.close();
	}
}
