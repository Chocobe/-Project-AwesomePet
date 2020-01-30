package com.awesomePet.controllers.communicationBoardControllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.CommunicationBoardService;
import com.awesomePet.vo.CommunicationBoardVO;
import com.awesomePet.vo.CommunicationContentsVO;

public class CommunicationBoardViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/views/communicationBoard/communicationBoardView.jsp";
		CommunicationBoardService communicationBoardService = new CommunicationBoardService();
		

	// 1. 페이지 정보를 구합니다.
		// 1-1. 전체 페이지 개수를 구합니다.
		int totalPageCnt = communicationBoardService.getTotalPageCnt();
		
		// 1-2. 요청 페이지를 가져옵니다. (요청 페이지 값의 기본값 : 1 페이지)
		String requestPageString = request.getParameter("requestPage");
		int requestPage = 1;
		
		// 1-3. 요청한 페이지가 있다면, 해당값을 사용합니다.
		if(requestPageString != null && requestPageString.length() > 0) {
			requestPage = Integer.parseInt(requestPageString);
		}
		
		// 1-4. 요청 페이지가 총 페이지보다 클 경우는 마지막 페이지로 대체 합니다.
		if(requestPage > totalPageCnt) {
			requestPage = totalPageCnt;
		}
		
		// 1-5. 요청 페이지가 시작 페이지보다 작을 경우는 시작 페이지로 대체합니다.
		if(requestPage < 1) {
			requestPage = 1;
		}
		
		
	// 2. 요청 페이지에 대한 글 List
		List<CommunicationContentsVO> communicationContentsList = communicationBoardService.getCommunicationContentsList(requestPage);
		
	// 3. QuestionBoardVO 객체를 생성합니다.
		CommunicationBoardVO communicationBoardVO = new CommunicationBoardVO(communicationContentsList, totalPageCnt, requestPage);

		request.setAttribute("communicationBoardVO", communicationBoardVO);
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
