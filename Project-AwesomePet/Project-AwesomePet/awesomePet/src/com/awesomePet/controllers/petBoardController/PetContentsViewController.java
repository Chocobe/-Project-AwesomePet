package com.awesomePet.controllers.petBoardController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.PetBoardService;
import com.awesomePet.vo.PetBoardVO;
import com.awesomePet.vo.PetContentsImageVO;

public class PetContentsViewController implements SubController {
	private static final int MAX_COOKIE_AGE;
	
	static {
		MAX_COOKIE_AGE = 60;
	}
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/views/petBoard/petContentsView.jsp";
		PetBoardService petBoardService = new PetBoardService();
		
		int requestBoardIDX = Integer.parseInt(request.getParameter("requestBoardIDX"));
		String requestPage = request.getParameter("requestPage");
		String requestTypeName = request.getParameter("requestTypeName");
		String requestSubTypeName = request.getParameter("requestSubTypeName");
		
		request.setAttribute("requestPage", requestPage);
		request.setAttribute("requestTypeName", requestTypeName);
		request.setAttribute("requestSubTypeName", requestSubTypeName);
		
		// 조회수(watch)를 +1 합니다.
		HttpSession session = request.getSession();
		String memberLoginID = (String)session.getAttribute("memberLoginID");
		if(memberLoginID == null) {
			memberLoginID = "client";
		}
		
		Cookie[] cookies = request.getCookies();
		boolean isWatched = false;
		String cookieName = "petContentsWatched-" + requestBoardIDX;
		
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
			
			petBoardService.increaseWatch(requestBoardIDX);
		}
		
		PetBoardVO petBoardVO = petBoardService.getPetContents(requestBoardIDX);
		List<PetContentsImageVO> imageList = petBoardService.getImageList(requestBoardIDX);
		petBoardVO.getPetList().get(0).setImageList(imageList);
		
		request.setAttribute("petBoardVO", petBoardVO);
		
		System.out.println("--- 테스트! : " + petBoardVO.getCurrentSubTypeComment());
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
