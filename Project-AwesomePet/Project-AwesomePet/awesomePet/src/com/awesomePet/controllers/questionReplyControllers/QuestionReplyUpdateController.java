package com.awesomePet.controllers.questionReplyControllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.QuestionReplyService;
import com.awesomePet.vo.QuestionReplyContentsVO;


public class QuestionReplyUpdateController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		int replyIDX = Integer.parseInt(request.getParameter("replyIDX"));
		String content = request.getParameter("content");
		
		QuestionReplyContentsVO questionReplyContentsVO = new QuestionReplyContentsVO(replyIDX, content);
		
		QuestionReplyService questionReplyService = new QuestionReplyService();
		int result = questionReplyService.updateQuestionReply(questionReplyContentsVO);
		
		String resultContents = null;
		
		PrintWriter out = response.getWriter();
		if(result == 1) {
			QuestionReplyContentsVO resultVO = questionReplyService.getQuestionReply(replyIDX);
			resultContents = resultVO.getContent();
		}
		
		out.print(resultContents);
		
		out.close();
	}
}
