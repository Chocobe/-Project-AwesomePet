package com.awesomePet.controllers.petReplyController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.PetReplyService;


public class PetReplyDeleteController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		int parentIDX = Integer.parseInt(request.getParameter("parentIDX"));
		String resultPagePath = "/petContentsView.do?requestBoardIDX=" + parentIDX;
		
		int requestReplyIDX = Integer.parseInt(request.getParameter("requestReplyIDX"));
		
		PetReplyService petReplyService = new PetReplyService();
		int result = petReplyService.deletePetReply(requestReplyIDX);
		
		if(result != 1) {
			System.out.println("<PetReplyDeleteController - execute() 에러> : 댓글 삭제를 실패 하였습니다");
		}
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
