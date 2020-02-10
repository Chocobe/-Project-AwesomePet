package com.awesomePet.controllers.petBoardController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.PetBoardService;

public class PetTypeUpdateController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String typeName = request.getParameter("typeName");
		String originTypeName = request.getParameter("originTypeName");
		
		PetBoardService petBoardService = new PetBoardService();
		
		int result = petBoardService.updatePetType(typeName, originTypeName);
		
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			out.print("true");
			
		} else {
			out.print("false");
		}
		
		out.close();
	}
}
