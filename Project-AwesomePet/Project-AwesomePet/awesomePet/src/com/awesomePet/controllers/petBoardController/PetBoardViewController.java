package com.awesomePet.controllers.petBoardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.MemberService;
import com.awesomePet.service.PetBoardService;
import com.awesomePet.vo.PetBoardVO;

public class PetBoardViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/views/petBoard/petBoardView.jsp";
		PetBoardService petBoardService = new PetBoardService();
		MemberService memberService = new MemberService();
		
	// 1. 사용자의 등급을 가져옵니다.
		HttpSession session = request.getSession();
		String memberLoginID = (String)session.getAttribute("memberLoginID");
		
		int grade = 0;
		if(memberLoginID != null && memberLoginID.length() > 0) {
			grade = memberService.getGrade(memberLoginID);
		}
		request.setAttribute("grade", grade);
		
	// 2. 대분류와 소분류 요청값을 가져옵니다.
		// 2-1. 대분류
		String requestTypeName = request.getParameter("requestTypeName");
		
		if(requestTypeName == null || requestTypeName.length() < 1) {
			requestTypeName = "전체";
		}
		
		// 2-2. 소분류
		String requestSubTypeName = request.getParameter("requestSubTypeName");
		if(requestSubTypeName == null || requestSubTypeName.length() < 1) {
			requestSubTypeName = "전체";
		}
		
		// 2-3. 사용자 등급, 대분류, 소분류 조건에 따라 페이지 처리 동작을 합니다.
		int totalPageCnt = 0;
		int requestPage = 0;
		PetBoardVO petBoardVO = null;
		
		if(grade == 0) {
			// 일반 사용자일 경우, ("비공개", "분양완료" 글은 제외 됩니다)
			// 선택 옵션에 따라 총 페이지 개수 구하기
			if(requestTypeName.equals("전체")) {
				// 조건의 페이지 개수 도출
				totalPageCnt = petBoardService.getTotalPublicPageCnt();
				
				// 페이지값 보정
				requestPage = correctRequestPage(request, totalPageCnt);
				
				// DB로 부터 데이터를 조회 합니다.
				petBoardVO = petBoardService.getPublicPetBoard(requestPage);
				
			} else if(requestSubTypeName.equals("전체")) {
				// 조건의 페이지 개수 도출
				totalPageCnt = petBoardService.getTotalPublicPageCnt(requestTypeName);
				
				// 페이지값 보정
				requestPage = correctRequestPage(request, totalPageCnt);
				
				// DB로 부터 데이터를 조회 합니다.
				petBoardVO = petBoardService.getPublicPetBoard(requestPage, requestTypeName);
				
			} else {
				// 조건의 페이지 개수 도출
				totalPageCnt = petBoardService.getTotalPublicPageCnt(requestTypeName, requestSubTypeName);
				
				// 페이지값 보정
				requestPage = correctRequestPage(request, totalPageCnt);
				
				// DB로 부터 데이터를 조회 합니다.
				petBoardVO = petBoardService.getPublicPetBoard(requestPage, requestTypeName, requestSubTypeName);
			}
			
		} else {
			// 관리자 일 경우,
			// 선택 옵션에 따라 총 페이지 개수 구하기
			if(requestTypeName.equals("전체")) {
				// 조건의 페이지 개수 도출
				totalPageCnt = petBoardService.getTotalPageCnt();
				
				// 페이지값 보정
				requestPage = correctRequestPage(request, totalPageCnt);
				
				// DB로 부터 데이터를 조회 합니다.
				petBoardVO = petBoardService.getPetBoard(requestPage);
				
			} else if(requestSubTypeName.equals("전체")) {
				// 조건의 페이지 개수 도출
				totalPageCnt = petBoardService.getTotalPageCnt(requestTypeName);
				
				// 페이지값 보정
				requestPage = correctRequestPage(request, totalPageCnt);
				
				// DB로 부터 데이터를 조회 합니다.
				petBoardVO = petBoardService.getPetBoard(requestPage, requestTypeName);
				
			} else {
				// 조건의 페이지 개수 도출
				totalPageCnt = petBoardService.getTotalPageCnt(requestTypeName, requestSubTypeName);
				
				// 페이지값 보정
				requestPage = correctRequestPage(request, totalPageCnt);
				
				// DB로 부터 데이터를 조회 합니다.
				petBoardVO = petBoardService.getPetBoard(requestPage, requestTypeName, requestSubTypeName);
			}
		}
		
		if(petBoardVO != null) {
			petBoardVO.setPageInfo(totalPageCnt, requestPage);
			
			petBoardVO.setCurrentTypeName(requestTypeName);
			petBoardVO.setCurrentSubTypeName(requestSubTypeName);
			
			request.setAttribute("petBoardVO", petBoardVO);
		}
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
	
	
// 요청 페이지값을 보정 합니다. (유효한 값으로 보정)
	private int correctRequestPage(HttpServletRequest request, int totalPageCnt) {
		String requestPageString = request.getParameter("requestPage");
		int requestPage = 0;
		
		if(requestPageString != null && requestPageString.length() > 0) {
			requestPage = Integer.parseInt(requestPageString);
		}
		
		if(requestPage > totalPageCnt) {
			requestPage = totalPageCnt;
		}
		
		if(requestPage < 1) {
			requestPage = 1;
		}
		
		return requestPage;
	}
}
