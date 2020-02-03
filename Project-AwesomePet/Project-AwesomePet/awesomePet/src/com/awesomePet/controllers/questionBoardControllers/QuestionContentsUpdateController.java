package com.awesomePet.controllers.questionBoardControllers;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.QuestionBoardService;
import com.awesomePet.vo.QuestionContentsVO;

public class QuestionContentsUpdateController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/questionContentsView.do";
		QuestionBoardService questionBoardService = new QuestionBoardService();
		
		int boardIDX = Integer.parseInt(request.getParameter("requestBoardIDX"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		QuestionContentsVO questionContentsVO = new QuestionContentsVO(boardIDX,
																	   title,
																	   content);
		int result = questionBoardService.updateQuestionContents(questionContentsVO);
		
		if(result == 1) {
			resultPagePath += "?requestBoardIDX=" + boardIDX;
		}
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
