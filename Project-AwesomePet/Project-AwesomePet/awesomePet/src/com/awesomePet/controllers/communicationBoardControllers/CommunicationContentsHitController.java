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

public class CommunicationContentsHitController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberLoginID = (String)session.getAttribute("memberLoginID");
		int boardIDX = Integer.parseInt(request.getParameter("boardIDX"));
		
		CommunicationHitVO communicationHitVO = new CommunicationHitVO(boardIDX, memberLoginID);
		
		CommunicationBoardService communicationBoardService = new CommunicationBoardService();
		String action = "";
		
		// 이전에 좋아요를 눌렀었는지 여부에 따라 토글 동작을 합니다.
		int isExistsHitter = communicationBoardService.isExistsHitter(communicationHitVO);
		
		if(isExistsHitter > 0) {
			// 좋아요 취소 동작 (이전에 좋아요를 눌렀던 상태)
			communicationBoardService.deleteHit(communicationHitVO);
			action = "deleteHit";
			
		} else {
			// 좋아요 동작
			communicationBoardService.insertHit(communicationHitVO);
			action = "insertHit";
		}
		
		PrintWriter out = response.getWriter();
		out.print(action);
		
		out.close();
	}
}
