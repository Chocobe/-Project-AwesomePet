package com.awesomePet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/test01")
public class Test01 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		doHandler(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		doHandler(request, response);
	}
	
	
	protected void doHandler(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String inputValue = request.getParameter("myTest");
		
		out.print("<pre>" + inputValue + "</pre>");
		System.out.println(inputValue);
		
		out.close();
	}
}
