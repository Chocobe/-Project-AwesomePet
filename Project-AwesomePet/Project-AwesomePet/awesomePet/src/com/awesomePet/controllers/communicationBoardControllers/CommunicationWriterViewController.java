package com.awesomePet.controllers.communicationBoardControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.CommunicationBoardService;
import com.awesomePet.vo.CommunicationContentsVO;


public class CommunicationWriterViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "";
		
		HttpSession session = request.getSession();
		String memberLoginID = (String)session.getAttribute("memberLoginID");
		
		if(memberLoginID == null) {
			resultPagePath = request.getContextPath() + "/loginView.do";
			ControllerUtil.redirect(response, resultPagePath);
			
		} else {
			resultPagePath = "/views/communicationBoard/communicationWriterView.jsp";
			
			// GET : 글쓰기, POST : 수정하기
			if(request.getMethod().equals("POST")) {
				doPost(request, response);
			}
			
			// action값에 따라 수정/작성 요청값이 정해 집니다. (<form>태그의 action값으로 사용)
			request.setAttribute("action", request.getParameter("action"));
			
			ControllerUtil.forward(request, response, resultPagePath);
		}
	}
	
	
	// "소통해요" 글 수정 페이지 요청 메서드 입니다.
	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		CommunicationBoardService communicationBoardService = new CommunicationBoardService();
		int requestBoardIDX = Integer.parseInt(request.getParameter("requestBoardIDX"));
		
		CommunicationContentsVO communicationContentsVO = communicationBoardService.getCommunicationContents(requestBoardIDX);
		
		request.setAttribute("communicationContentsVO", communicationContentsVO);
	}
}
