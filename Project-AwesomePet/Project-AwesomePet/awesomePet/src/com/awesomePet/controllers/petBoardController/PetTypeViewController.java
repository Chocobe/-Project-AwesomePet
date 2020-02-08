package com.awesomePet.controllers.petBoardController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.PetBoardService;
import com.awesomePet.vo.PetTypeVO;
import com.google.gson.Gson;

public class PetTypeViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		PetBoardService petBoardService = new PetBoardService();
		
		List<PetTypeVO> petTypeList = petBoardService.getPetTypeList();
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(petTypeList);
		
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		out.close();
	}
}
