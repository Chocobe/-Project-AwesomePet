package com.awesomePet.controllers.communicationReplyControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.CommunicationBoardService;
import com.awesomePet.service.CommunicationReplyService;


public class CommunicationReplyDeleteController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		int parentIDX = Integer.parseInt(request.getParameter("parentIDX"));
		String resultPagePath = "/communicationContentsView.do?requestBoardIDX=" + parentIDX;
		
		int requestReplyIDX = Integer.parseInt(request.getParameter("requestReplyIDX"));
		
		CommunicationReplyService communicationReplyService = new CommunicationReplyService();
		int result = communicationReplyService.deleteCommunicationReply(requestReplyIDX);
		
		if(result == 1) {
			CommunicationBoardService communicationBoardService = new CommunicationBoardService();
			communicationBoardService.updateReplyCnt(parentIDX, -1);
			
		} else {
			System.out.println("<CommunicationReplyDeleteController - execute() 에러> : 댓글 삭제를 실패 하였습니다");
		}
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
