package com.awesomePet.controllers.questionBoardControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.QuestionBoardService;
import com.awesomePet.vo.QuestionContentsVO;

public class QuestionWriterViewController implements SubController {
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
			resultPagePath = "/views/questionBoard/questionWriterView.jsp";
			
			// GET : 글쓰기, POST : 수정하기
			if(request.getMethod().equals("POST")) {
				doPost(request, response);
			}
			
			// action값에 따라 수정/작성 요청값이 정해 집니다. (<form>태그의 action값으로 사용)
			request.setAttribute("action", request.getParameter("action"));
			
			ControllerUtil.forward(request, response, resultPagePath);
		}
	}
	
	
	// "궁금해요" 글 수정 페이지 요청 메서드 입니다.
	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		QuestionBoardService questionBoardService = new QuestionBoardService();
		int requestBoardIDX = Integer.parseInt(request.getParameter("requestBoardIDX"));
		
		QuestionContentsVO questionContentsVO = questionBoardService.getQuestionContents(requestBoardIDX);
		
		request.setAttribute("questionContentsVO", questionContentsVO);
	}
}
