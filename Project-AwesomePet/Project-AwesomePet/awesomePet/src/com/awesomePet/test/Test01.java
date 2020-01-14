package com.awesomePet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

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
		doHandler(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		doHandler(request, response);
	}
	
	
	protected void doHandler(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		Date date = Date.valueOf("1987-5-17");
		out.print("<h1>Date객체 값</h1>");
		out.print("<p>" + date.getYear() + "년 " + date.getMonth() + "월 " + date.getDate() + "일</p>");
		
		LocalDate local = date.toLocalDate();
		out.print("<h1>LocalDate객체 값</h1>");
		out.print("<p>" + local.getYear() + "년 " + local.getMonthValue() + "월 " + local.getDayOfMonth() + "일</p>");
		
		out.print("<hr/><hr/>");
		
		String testString = "";
		for(int i = 0; i < 3; i++) {
			testString += "*";
		}
		out.print("<p>마스킹 테스트 : " + testString + "</p>");
		
		out.close();
	}
}
