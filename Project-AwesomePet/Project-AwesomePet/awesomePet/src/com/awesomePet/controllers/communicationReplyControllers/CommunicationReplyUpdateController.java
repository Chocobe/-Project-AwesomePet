package com.awesomePet.controllers.communicationReplyControllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.CommunicationReplyService;
import com.awesomePet.vo.CommunicationReplyContentsVO;


public class CommunicationReplyUpdateController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		int replyIDX = Integer.parseInt(request.getParameter("replyIDX"));
		String content = request.getParameter("content");
		
		CommunicationReplyContentsVO communicationReplyContentsVO = 
						new CommunicationReplyContentsVO(replyIDX, content);
		
		CommunicationReplyService communicationReplyService = new CommunicationReplyService();
		int result = communicationReplyService.updateCommunicationReply(communicationReplyContentsVO);
		
		String resultContents = null;
		
		PrintWriter out = response.getWriter();
		if(result == 1) {
			CommunicationReplyContentsVO resultVO = communicationReplyService.getCommunicationReply(replyIDX);
			resultContents = resultVO.getContent();
		}
		
		out.print(resultContents);
		
		out.close();
	}
}
