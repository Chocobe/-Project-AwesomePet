package com.awesomePet.controllers.questionBoardControllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.QuestionBoardService;
import com.awesomePet.vo.QuestionContentsVO;

public class QuestionContentsViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/views/questionBoard/questionContentsView.jsp";
		
		QuestionBoardService questionBoardService = new QuestionBoardService();
		int requestBoardIDX = Integer.parseInt(request.getParameter("requestBoardIDX"));
		
		// 조회수(watch)를 +1 합니다.
		HttpSession session = request.getSession();
		String memberLoginID = (String)session.getAttribute("memberLoginID");
		if(memberLoginID == null) {
			memberLoginID = "client";
		}
		
		Cookie[] cookies = request.getCookies();
		boolean isWatched = false;
		String cookieName = "questionContentsWatched-" + requestBoardIDX;
		// 이전 페이지로 돌아가기 위한 페이지값을 파라메터로 넘겨줍니다.
		String requestPage = request.getParameter("requestPage");
		
		System.out.println("리퀘스트 페이지값 : " + requestPage);
		
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals(cookieName)) {
				if(cookies[i].getValue().equals(memberLoginID)) {
					isWatched = true;
					break;
				}
			}
		}
		
		if(isWatched == false) {
			Cookie watchedCookie = new Cookie(cookieName, memberLoginID);
			watchedCookie.setMaxAge(60); // 60초(1분)
			watchedCookie.setPath("/");
			response.addCookie(watchedCookie);
			
			questionBoardService.increaseWatch(requestBoardIDX);
		}
		
		
		QuestionContentsVO questionContentsVO = questionBoardService.getQuestionContents(requestBoardIDX);
		request.setAttribute("questionContentsVO", questionContentsVO);
		request.setAttribute("requestPage", requestPage);
		request.setAttribute("action", request.getParameter("action"));
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
