package com.awesomePet.controllers.questionReplyControllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.QuestionReplyService;
import com.awesomePet.vo.QuestionReplyContentsVO;

public class QuestionReplyWriteController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		int parentIDX = Integer.parseInt(request.getParameter("parentIDX"));
		
		HttpSession session = request.getSession();
		String writerID = (String)session.getAttribute("memberLoginID");
		
		String inputValue = request.getParameter("inputValue");
		
		QuestionReplyContentsVO questionReplyContentsVO = new QuestionReplyContentsVO(parentIDX, 
																					  writerID, 
																					  inputValue);
		QuestionReplyService questionReplyService = new QuestionReplyService();
		int result = questionReplyService.writeQuestionReply(questionReplyContentsVO);
		
		PrintWriter out = response.getWriter();
		if(result == 1) {
			out.print("true");
			
		} else {
			out.print("false");
		}
		
		out.close();
	}
}