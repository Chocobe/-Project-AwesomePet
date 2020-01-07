package com.awesomePet.controllers.memberControllers;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;

public class LoginViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String path = "/views/login.jsp";
		ControllerUtil.forward(request, response, path);
	}
}
