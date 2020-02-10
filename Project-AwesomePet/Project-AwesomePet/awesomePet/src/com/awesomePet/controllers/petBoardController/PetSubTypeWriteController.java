package com.awesomePet.controllers.petBoardController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.PetBoardService;
import com.awesomePet.vo.PetSubTypeVO;

public class PetSubTypeWriteController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String typeName = request.getParameter("typeName");
		String subTypeName = request.getParameter("subTypeName");
		String subTypeComment = request.getParameter("subTypeComment");
		
		PetBoardService petBoardService = new PetBoardService();
		
		PetSubTypeVO petSubTypeVO = new PetSubTypeVO(typeName,
													 subTypeName,
													 subTypeComment);
		
		int result = petBoardService.writePetSubType(petSubTypeVO);
		
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			out.print("true");
			
		} else {
			out.print("false");
		}
		
		out.close();
	}
}
