package com.awesomePet.controllers.questionReplyControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.QuestionBoardService;
import com.awesomePet.service.QuestionReplyService;

public class QuestionReplyDeleteController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		int parentIDX = Integer.parseInt(request.getParameter("parentIDX"));
		String resultPagePath = "/questionContentsView.do?requestBoardIDX=" + parentIDX;
		
		int requestReplyIDX = Integer.parseInt(request.getParameter("requestReplyIDX"));
		
		QuestionReplyService questionReplyService = new QuestionReplyService();
		int result = questionReplyService.deleteQuestionReply(requestReplyIDX);
		
		if(result == 1) {
			QuestionBoardService questionBoardService = new QuestionBoardService();
			questionBoardService.increaseReplyCnt(parentIDX, -1);
			
		} else {
			System.out.println("<QuestionReplyDeleteController - execute() 에러> : 댓글 삭제를 실패 하였습니다");
		}
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
