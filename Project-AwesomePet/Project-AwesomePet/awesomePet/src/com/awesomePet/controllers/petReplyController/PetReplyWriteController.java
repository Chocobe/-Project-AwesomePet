package com.awesomePet.controllers.petReplyController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.PetReplyService;
import com.awesomePet.vo.PetReplyContentsVO;


public class PetReplyWriteController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		int parentIDX = Integer.parseInt(request.getParameter("parentIDX"));
		
		HttpSession session = request.getSession();
		String writerID = (String)session.getAttribute("memberLoginID");
		
		String inputValue = request.getParameter("inputValue");
		
		PetReplyContentsVO petReplyContentsVO = 
						new PetReplyContentsVO(parentIDX, 
											   writerID, 
											   inputValue);
		
		PetReplyService petReplyService = new PetReplyService();
		int result = petReplyService.writePetReply(petReplyContentsVO);
		
		PrintWriter out = response.getWriter();
		if(result == 1) {
			out.print("true");
			
		} else {
			System.out.println("<CommunicationReplyWriteController 에러> : 댓글 작성을 실패 하였습니다");
			out.print("false");
		}
		
		out.close();
	}
}
