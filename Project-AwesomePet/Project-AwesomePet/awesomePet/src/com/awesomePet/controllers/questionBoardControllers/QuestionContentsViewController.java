package com.awesomePet.controllers.questionBoardControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.QuestionBoardService;
import com.awesomePet.vo.QuestionContentsVO;

public class QuestionContentsViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/views/questionBoard/questionContentsView.jsp";
		
		QuestionBoardService questionBoardService = new QuestionBoardService();
		int requestBoardIDX = Integer.parseInt(request.getParameter("requestBoardIDX"));
		
		QuestionContentsVO questionContentsVO = questionBoardService.getQuestionContents(requestBoardIDX);
		request.setAttribute("questionContentsVO", questionContentsVO);
		request.setAttribute("action", request.getParameter("action"));
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
