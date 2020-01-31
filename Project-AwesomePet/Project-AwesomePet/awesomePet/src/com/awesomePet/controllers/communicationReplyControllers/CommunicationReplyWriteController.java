package com.awesomePet.controllers.communicationReplyControllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.CommunicationBoardService;
import com.awesomePet.service.CommunicationReplyService;
import com.awesomePet.vo.CommunicationReplyContentsVO;


public class CommunicationReplyWriteController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		int parentIDX = Integer.parseInt(request.getParameter("parentIDX"));
		
		HttpSession session = request.getSession();
		String writerID = (String)session.getAttribute("memberLoginID");
		
		String inputValue = request.getParameter("inputValue");
		
		CommunicationReplyContentsVO communicationReplyContentsVO = 
						new CommunicationReplyContentsVO(parentIDX, 
														 writerID, 
														 inputValue);
		CommunicationReplyService communicationReplyService = new CommunicationReplyService();
		int result = communicationReplyService.writeCommunicationReply(communicationReplyContentsVO);
		
		PrintWriter out = response.getWriter();
		if(result == 1) {
			CommunicationBoardService communicationBoardService = new CommunicationBoardService();
			communicationBoardService.updateReplyCnt(parentIDX, result);
			out.print("true");
			
		} else {
			System.out.println("<CommunicationReplyWriteController 에러> : 댓글 작성을 실패 하였습니다");
			out.print("false");
		}
		
		out.close();
	}
}
