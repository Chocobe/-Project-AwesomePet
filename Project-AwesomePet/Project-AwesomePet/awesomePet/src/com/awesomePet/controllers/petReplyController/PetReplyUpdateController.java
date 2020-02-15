package com.awesomePet.controllers.petReplyController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.PetReplyService;
import com.awesomePet.vo.PetReplyContentsVO;


public class PetReplyUpdateController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		int replyIDX = Integer.parseInt(request.getParameter("replyIDX"));
		String content = request.getParameter("content");
		
		PetReplyContentsVO petReplyContentsVO = new PetReplyContentsVO(replyIDX, content);
		
		PetReplyService petReplyService = new PetReplyService();
		int result = petReplyService.updatePetReply(petReplyContentsVO);
		
		String resultContents = null;
		
		PrintWriter out = response.getWriter();
		if(result == 1) {
			PetReplyContentsVO resultVO = petReplyService.getPetReply(replyIDX);
			resultContents = resultVO.getContent();
		}
		
		out.print(resultContents);
		
		out.close();
	}
}
