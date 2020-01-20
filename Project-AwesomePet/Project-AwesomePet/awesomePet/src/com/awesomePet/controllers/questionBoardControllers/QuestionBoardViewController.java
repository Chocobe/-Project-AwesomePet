package com.awesomePet.controllers.questionBoardControllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.QuestionBoardService;
import com.awesomePet.vo.QuestionBoardVO;
import com.awesomePet.vo.QuestionContentsVO;

public class QuestionBoardViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/views/questionBoard/questionBoardView.jsp";
		QuestionBoardService questionBoardService = new QuestionBoardService();
		
		QuestionBoardVO questionBoardVO = new QuestionBoardVO();
		

	// 페이지 정보를 구합니다.
		// 1. 전체 페이지 개수를 구합니다.
		int totalPageCnt = questionBoardService.getTotalPageCnt();
		
		// 2. 요청 페이지를 가져옵니다. (요청 페이지 값의 기본값 : 1 페이지)
		String requestPageString = request.getParameter("requestPage");
		int requestPage = 1;
		
		// 3. 요청한 페이지가 있다면, 해당값을 사용합니다.
		if(requestPageString != null) {
			requestPage = Integer.parseInt(requestPageString);
		}
		
		// 4. 요청 페이지가 총 페이지보다 클 경우는 마지막 페이지로 대체 합니다.
		if(requestPage > totalPageCnt) {
			requestPage = totalPageCnt;
		}
		
		// 5. 요청 페이지가 시작 페이지보다 작을 경우는 시작 페이지로 대체합니다.
		if(requestPage < 1) {
			requestPage = 1;
		}
		
		questionBoardVO.setPageInfo(totalPageCnt, requestPage);
		
		// 2. 요청 페이지에 대한 글 List
		List<QuestionContentsVO> questionContentsList = questionBoardService.getQuestionContentsList(requestPage);
		
		questionBoardVO.setQuestionContentsList(questionContentsList);

		request.setAttribute("questionBoardVO", questionBoardVO);
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
