package com.awesomePet.controllers.petBoardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;

public class PetContentsWriterViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/views/petBoard/petContentsWriterView.jsp";
		String action = request.getParameter("action");
		
		request.setAttribute("action", action);
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
