package com.awesomePet.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUtil {
	// SubController 객체에서 forward를 위한 유틸 메서드 입니다.
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) 
					throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
