package com.awesomePet.controllers.questionBoardControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.QuestionBoardService;
import com.awesomePet.vo.QuestionContentsVO;

public class QuestionContentsWriteController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/questionContentsView.do";
		
		QuestionBoardService questionBoardService = new QuestionBoardService();
		HttpSession session = request.getSession();
		
		String writerID = (String)session.getAttribute("memberLoginID");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		QuestionContentsVO questionContentsVO = new QuestionContentsVO(writerID,
																	   title,
																	   content);
		
		int result = questionBoardService.writeQuestionContents(questionContentsVO);
		
		if(result == 1) {
			int boardIDX = questionBoardService.getQuestionContents(writerID, title, content).getBoardIDX();
			resultPagePath += "?requestBoardIDX=" + boardIDX/* + "&action=fixed" */;
		}
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
