package com.awesomePet.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.awesomePet.controllers.SubController;
import com.awesomePet.controllers.indexControllers.IndexViewController;
import com.awesomePet.controllers.memberControllers.LoginViewController;
import com.awesomePet.controllers.memberControllers.MemberLoginController;

public class ContextListener implements ServletContextListener {
	// 요청에 따른 SubController의 DI(의존성 주입)을 수행합니다.
	@Override
	public void contextInitialized(ServletContextEvent event) {
		Map<String, SubController> subControllers = new HashMap<String, SubController>();
		
		// index.do 처리를 위한 SubController 입니다.
		subControllers.put("/index.do", new IndexViewController());
		
		// 회원 서비스를 위한 SubController 입니다.
		subControllers.put("/loginView.do", new LoginViewController());
		subControllers.put("/memberLogin.do", new MemberLoginController());
		
		
		event.getServletContext().setAttribute("subControllers", subControllers);
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent event) { }
}
