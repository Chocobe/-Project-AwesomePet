package com.awesomePet.controllers.communicationBoardControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.CommunicationBoardService;
import com.awesomePet.vo.CommunicationContentsVO;

public class CommunicationContentsViewController implements SubController {
	private static final int MAX_COOKIE_AGE;
	
	static {
		MAX_COOKIE_AGE = 60;
	}
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/views/communicationBoard/communicationContentsView.jsp";
		
		CommunicationBoardService communicationBoardService = new CommunicationBoardService();
		int requestBoardIDX = Integer.parseInt(request.getParameter("requestBoardIDX"));
		
		// 조회수(watch)를 +1 합니다.
		HttpSession session = request.getSession();
		String memberLoginID = (String)session.getAttribute("memberLoginID");
		if(memberLoginID == null) {
			memberLoginID = "client";
		}
		
		Cookie[] cookies = request.getCookies();
		boolean isWatched = false;
		String cookieName = "communicationContentsWatched-" + requestBoardIDX;
		// 이전 페이지로 돌아가기 위한 페이지값을 파라메터로 넘겨줍니다.
		String requestPage = request.getParameter("requestPage");
		
		System.out.println("리퀘스트 페이지값 : " + requestPage);
		
		// 봤던 게시물인지 검사합니다.
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals(cookieName)) {
				if(cookies[i].getValue().equals(memberLoginID)) {
					isWatched = true;
					break;
				}
			}
		}
		
		// 봤던 게시물이 아닐 경우 (또는 cookie의 maxAge값이 초과하여 cookie가 삭제된 경우)
		if(isWatched == false) {
			Cookie watchedCookie = new Cookie(cookieName, memberLoginID);
			watchedCookie.setMaxAge(MAX_COOKIE_AGE); // 60초(1분)
			watchedCookie.setPath("/");
			response.addCookie(watchedCookie);
			
			communicationBoardService.increaseWatch(requestBoardIDX);
		}
		
		
		CommunicationContentsVO communicationContentsVO = communicationBoardService.getCommunicationContents(requestBoardIDX);
		request.setAttribute("communicationContentsVO", communicationContentsVO);
		request.setAttribute("requestPage", requestPage);
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
