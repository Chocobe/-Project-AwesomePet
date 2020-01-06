package com.awesomePet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {
	// web.xml에서 encoding값을 설정하지 않았을 경우, "UTF-8"을 기본값으로 설정합니다.
	// (web.xml에서 encoding값을 설정할 것을 권장합니다)
	private String encoding = "UTF-8";
	
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("EncodingFilter 초기화 테스트 출력");
		
		String initParam = config.getInitParameter("encoding");
		if(initParam != null && !initParam.equals("")) {
			encoding = initParam;
			System.out.println("<EncodingFilter 알림> : 설정된 Encoding값 - " + encoding);
		}
	}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
					throws IOException, ServletException {
		// request, response 객체의 Encoding값을 설정합니다.
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset=" + encoding);
		
		chain.doFilter(request, response);
		
		System.out.println("<EncodingFilter 알림> : EncodingFilter가 정상 동작 하였습니다");
	}
	
	
	@Override
	public void destroy() { }
}
