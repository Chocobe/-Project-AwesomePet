package com.awesomePet.controllers.questionReplyControllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.QuestionReplyService;
import com.awesomePet.vo.QuestionReplyContentsVO;
import com.awesomePet.vo.QuestionReplyVO;
import com.google.gson.Gson;

public class QuestionReplyViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		QuestionReplyService questionReplyService = new QuestionReplyService();
		
	// 1. 페이지 정보를 구합니다.
		// 1-1. 궁금해요 원본글의 인덱스값을 가져옵니다.
		String parentIDString = request.getParameter("parentIDX");
		int parentID = 0;
		if(parentIDString != null && parentIDString.length() > 0) {
			parentID = Integer.parseInt(parentIDString);
		}
		
		// 1-2. 요청한 댓글의 페이지 번호를 가져옵니다.
		String requestReplyPageString = request.getParameter("requestReplyPage");
		int requestReplyPage = 1;
		if(requestReplyPageString != null && requestReplyPageString.length() > 0) {
			requestReplyPage = Integer.parseInt(requestReplyPageString);
		}
		
		// 1-3. 전체 페이지 개수를 구합니다.
		int totalPageCnt = questionReplyService.getTotalPageCnt(parentID);
		
		// 1-4. 요청 페이지번호의 유효성을 검사합니다.
		if(requestReplyPage > totalPageCnt) {
			requestReplyPage = totalPageCnt;
		}
		
		if(requestReplyPage < 1) {
			requestReplyPage = 1;
		}
		
	// 2. 요청 페이지의 댓글 데이터를 List로 가져옵니다.
		List<QuestionReplyContentsVO> questionReplyList = questionReplyService.getQuestionReplyList(parentID, requestReplyPage);
		
	// 3. QuestionReplyVO 객체를 생성합니다.
		QuestionReplyVO questionReplyVO = new QuestionReplyVO(questionReplyList,
															  totalPageCnt,
															  requestReplyPage);
		
	// 4. JSON 형식으로 변환합니다.
		Gson gson = new Gson();
		String json = gson.toJson(questionReplyVO);
		
	// 5. JSON을 반환합니다.
		PrintWriter out = response.getWriter();
		out.print(json);
		
		out.close();
	}
}
