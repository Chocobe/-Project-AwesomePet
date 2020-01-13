package com.awesomePet.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.awesomePet.controllers.SubController;
import com.awesomePet.controllers.indexControllers.IndexViewController;
import com.awesomePet.controllers.memberControllers.CheckOverlapIDController;
import com.awesomePet.controllers.memberControllers.JoinViewController;
import com.awesomePet.controllers.memberControllers.LoginViewController;
import com.awesomePet.controllers.memberControllers.MemberJoinController;
import com.awesomePet.controllers.memberControllers.MemberJoinResultViewController;
import com.awesomePet.controllers.memberControllers.MemberLoginController;
import com.awesomePet.controllers.memberControllers.MemberLogoutController;
import com.awesomePet.controllers.memberControllers.MyInfoCertificateViewController;
import com.awesomePet.controllers.memberControllers.MyPageViewController;

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
		
		
		event.getServletContext().setAttribute("subControllers", subControllers);
	}
	
	
// 소멸자에서 수행할 기능은 지정하지 않았습니다.
	@Override
	public void contextDestroyed(ServletContextEvent event) { }
}
