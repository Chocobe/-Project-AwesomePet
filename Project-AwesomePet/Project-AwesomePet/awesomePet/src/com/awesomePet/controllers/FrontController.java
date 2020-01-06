package com.awesomePet.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private Map<String, SubController> subControllers;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// Collection 객체의 형변환 경고는 무시해도 무방하다는 것을 stackoverflow.com 사이트에서 알게 되었습니다.
		@SuppressWarnings("unchecked")
		Map<String, SubController> initControllers = (Map<String, SubController>)config.getServletContext().getAttribute("subControllers");
		
		subControllers = initControllers;
		System.out.println("<FrontController 알림> : 초기화 완료");
	}
	
	
	// GET, POST에 따른 처리가 아닌, 요청한 서비스에 따른 처리를 위해 service()메소드를 오버라이딩 합니다.
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		// GET, POST 방식을 사용하지 않기 때문에 부모클래스의 초기화를 하지 않습니다.
		// 초기화 할 경우 GET(), POST() 메소드를 사용해야만 합니다.
		// super.service(request, response);
		
		String servletPath = request.getServletPath();
		System.out.println("<FrontController 알림> : " + servletPath + " 이(가) 요청 되었습니다.");
		
		// 요청한 서비스에 따른 SubController 객체로 처리를 위임합니다.
		SubController subController = subControllers.get(servletPath);
		
		if(subController == null) {
			subController = subControllers.get("/index.do");
		}
		
		subController.execute(request, response);
	}
}
