package com.awesomePet.controllers.communicationBoardControllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.CommunicationBoardService;
import com.awesomePet.vo.CommunicationHitVO;

public class CommunicationContentsHitCheckerController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberLoginID = (String)session.getAttribute("memberLoginID");
		
		String boardIDX = request.getParameter("boardIDX");
		if(boardIDX == null || boardIDX.length() == 0) {
			boardIDX = "0";
		}
		
		CommunicationHitVO communicationHitVO = new CommunicationHitVO(Integer.parseInt(boardIDX), memberLoginID);
		
		CommunicationBoardService communicationBoardService = new CommunicationBoardService();
		int result = communicationBoardService.isExistsHitter(communicationHitVO);
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.print("true");
			
		} else {
			out.print("false");
		}
		
		out.close();
	}
}
