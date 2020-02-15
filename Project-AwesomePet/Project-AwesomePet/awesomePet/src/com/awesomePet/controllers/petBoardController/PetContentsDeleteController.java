package com.awesomePet.controllers.petBoardController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.PetBoardService;

public class PetContentsDeleteController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		int requestBoardIDX = Integer.parseInt(request.getParameter("requestBoardIDX"));
		
		PetBoardService petBoardService = new PetBoardService();
		int result = petBoardService.deletePetContents(requestBoardIDX);
		
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			out.print("true");
			
		} else {
			out.print("false");
		}
		
		out.close();
	}
}
