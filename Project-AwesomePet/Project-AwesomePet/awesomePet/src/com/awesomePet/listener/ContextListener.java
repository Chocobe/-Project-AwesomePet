package com.awesomePet.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.awesomePet.controllers.SubController;
import com.awesomePet.controllers.indexControllers.IndexController;

public class ContextListener implements ServletContextListener {
	// 요청에 따른 SubController의 DI(의존성 주입)을 수행합니다.
	@Override
	public void contextInitialized(ServletContextEvent event) {
		Map<String, SubController> subControllers = new HashMap<String, SubController>();
		
		// index.do 처리를 위한 SubController
		subControllers.put("/index.do", new IndexController());
		
		
		
		event.getServletContext().setAttribute("subControllers", subControllers);
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent event) { }
}
