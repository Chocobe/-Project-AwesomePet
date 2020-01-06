package com.awesomePet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test01")
public class Test01 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		
		out.print("<h1>Context Path : " + contextPath + "</h1>");
		out.print("<h1>Servlet Path : " + servletPath + "</h1>");
		out.print("<h1>Path Info : " + pathInfo + "</h1>");
		
		out.close();
	}
}
