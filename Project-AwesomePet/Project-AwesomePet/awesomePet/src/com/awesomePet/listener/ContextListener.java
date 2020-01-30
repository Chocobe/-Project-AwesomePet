package com.awesomePet.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.awesomePet.controllers.SubController;
import com.awesomePet.controllers.communicationBoardControllers.CommunicationBoardViewController;
import com.awesomePet.controllers.indexControllers.IndexViewController;
import com.awesomePet.controllers.memberControllers.CheckOverlapIDController;
import com.awesomePet.controllers.memberControllers.JoinViewController;
import com.awesomePet.controllers.memberControllers.LoginViewController;
import com.awesomePet.controllers.memberControllers.MemberJoinController;
import com.awesomePet.controllers.memberControllers.MemberJoinResultViewController;
import com.awesomePet.controllers.memberControllers.MemberLoginController;
import com.awesomePet.controllers.memberControllers.MemberLogoutController;
import com.awesomePet.controllers.memberControllers.MyInfoCertificateController;
import com.awesomePet.controllers.memberControllers.MyInfoCertificateViewController;
import com.awesomePet.controllers.memberControllers.MyInfoViewController;
import com.awesomePet.controllers.memberControllers.MyPageViewController;
import com.awesomePet.controllers.memberControllers.UpdateMyInfoController;
import com.awesomePet.controllers.memberControllers.UpdateMyInfoResultViewController;
import com.awesomePet.controllers.questionBoardControllers.QuestionBoardViewController;
import com.awesomePet.controllers.questionBoardControllers.QuestionContentsDeleteController;
import com.awesomePet.controllers.questionBoardControllers.QuestionContentsUpdateController;
import com.awesomePet.controllers.questionBoardControllers.QuestionContentsViewController;
import com.awesomePet.controllers.questionBoardControllers.QuestionContentsWriteController;
import com.awesomePet.controllers.questionBoardControllers.QuestionWriterViewController;
import com.awesomePet.controllers.questionReplyControllers.QuestionReplyDeleteController;
import com.awesomePet.controllers.questionReplyControllers.QuestionReplyUpdateController;
import com.awesomePet.controllers.questionReplyControllers.QuestionReplyViewController;
import com.awesomePet.controllers.questionReplyControllers.QuestionReplyWriteController;

public class ContextListener implements ServletContextListener {
	// 요청에 따른 SubController의 DI(의존성 주입)을 수행합니다.
	@Override
	public void contextInitialized(ServletContextEvent event) {
		Map<String, SubController> subControllers = new HashMap<String, SubController>();
		
	// index.do 처리를 위한 SubController 입니다.
		subControllers.put("/index.do", new IndexViewController());
		
		
	// 회원 서비스를 위한 SubController 입니다.
		// 로그인 페이지 요청 컨트롤러
		subControllers.put("/loginView.do", new LoginViewController());
		
		// 로그인 요청 컨트롤러
		subControllers.put("/memberLogin.do", new MemberLoginController());
		
		// 로그아웃 요청 컨트롤러
		subControllers.put("/memberLogout.do", new MemberLogoutController());
		
		// 회원가입 페이지 요청 컨트롤러
		subControllers.put("/joinView.do", new JoinViewController());
		
		// 회원가입 ID중복 체크 요청 컨트롤러
		subControllers.put("/checkOverlapID.do", new CheckOverlapIDController());
		
		// 회원가입 요청 컨트롤러
		subControllers.put("/memberJoin.do", new MemberJoinController());
		
		// 회원가입 결과 페이지 요청 컨트롤러
		subControllers.put("/memberJoinResultView.do", new MemberJoinResultViewController());
		
		// 마이페이지 요청 컨트롤러
		subControllers.put("/myPageView.do", new MyPageViewController());
		
		// 내 정보 수정을 위한 인증 페이지 요청 컨트롤러
		subControllers.put("/myInfoCertificateView.do", new MyInfoCertificateViewController());
		
		// 내 정보 수정을 위한 인증 요청 컨트롤러
		subControllers.put("/myInfoCertificate.do", new MyInfoCertificateController());
		
		// 내 정보 수정 페이지 요청 컨트롤러
		subControllers.put("/myInfoView.do", new MyInfoViewController());
		
		// 내 정보 수정 요청 컨트롤러
		subControllers.put("/updateMyInfo.do", new UpdateMyInfoController());
		
		// 내 정보 수정 결과 페이지 요청 컨트롤러
		subControllers.put("/updateMyInfoResultView.do", new UpdateMyInfoResultViewController());
		
		
	// 질문 게시판(궁금해요)를 위한 SubController 입니다.
		// 질문 게시판(궁금해요) 페이지 요청 컨트롤러
		subControllers.put("/questionBoardView.do", new QuestionBoardViewController());
		
		// 질문 게시판(궁금해요) 특정 글 페이지 요청 컨트롤러
		subControllers.put("/questionContentsView.do", new QuestionContentsViewController());
		
		// 질문 게시판(궁금해요) 글 작성/수정 페이지 요청 컨트롤러
		subControllers.put("/questionWriterView.do", new QuestionWriterViewController());
		
		// 질문 게시판(궁금해요) 글 작성 요청 컨트롤러
		subControllers.put("/questionContentsWrite.do", new QuestionContentsWriteController());
		
		// 질문 게시판(궁금해요) 글 수정 요청
		subControllers.put("/questionContentsUpdate.do", new QuestionContentsUpdateController());
		
		// 질문 게시판(궁금해요) 글 삭제 요청
		subControllers.put("/questionContentsDelete.do", new QuestionContentsDeleteController());
		
		
	// 질문 게시판(궁금해요) "댓글"을 위한 SubController 입니다.
		// 질문 게시판(궁금해요)의 "댓글" 페이지 요청 컨트롤러
		subControllers.put("/questionReplyView.do", new QuestionReplyViewController());
		
		// 질문 게시판(궁금해요)의 "댓글" 작성 요청 컨트롤러
		subControllers.put("/questionReplyWrite.do", new QuestionReplyWriteController());
		
		// 질문 게시판(궁금해요)의 "댓글" 수정 요청 컨트롤러
		subControllers.put("/questionReplyUpdate.do", new QuestionReplyUpdateController());
		
		// 질문 게시판(궁금해요)의 "댓글" 삭제 요청 컨트롤러
		subControllers.put("/questionReplyDelete.do", new QuestionReplyDeleteController());
		
		
	// 자유게시판(소통해요)를 위한 SubController 입니다.
		// 자유게시판(소통해요) 페이지 요청 컨트롤러
		subControllers.put("/communicationBoardView.do", new CommunicationBoardViewController());
		
		
		event.getServletContext().setAttribute("subControllers", subControllers);
	}
	
	
// 소멸자에서 수행할 기능은 지정하지 않았습니다.
	@Override
	public void contextDestroyed(ServletContextEvent event) { }
}
