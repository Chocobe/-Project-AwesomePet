package com.awesomePet.controllers.petBoardController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.PetBoardService;
import com.awesomePet.vo.PetSubTypeVO;

public class PetSubTypeUpdateController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String typeName = request.getParameter("typeName");
		String originTypeName = request.getParameter("originTypeName");
		
		String subTypeName = request.getParameter("subTypeName");
		String originSubTypeName = request.getParameter("originSubTypeName");
		
		String subTypeComment = request.getParameter("subTypeComment");
		
		PetSubTypeVO petSubTypeVO = new PetSubTypeVO(typeName, 
													 originTypeName, 
													 subTypeName, 
													 originSubTypeName, 
													 subTypeComment);
		
		PetBoardService petBoardService = new PetBoardService();
		PrintWriter out = response.getWriter();
		
		int result = petBoardService.updatePetSubType(petSubTypeVO);
		
		if(result == 1) {
			out.print("true");
			
		} else {
			out.print("false");
		}
		
		out.close();
	}
}
