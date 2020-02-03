package com.awesomePet.controllers.communicationBoardControllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.SubController;
import com.awesomePet.service.CommunicationBoardService;
import com.awesomePet.vo.CommunicationContentsVO;
import com.google.gson.Gson;

public class CommunicationContentsAjaxViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		int requestBoardIDX = Integer.parseInt(request.getParameter("requestBoardIDX"));
		
		CommunicationBoardService communicationBoardService = new CommunicationBoardService();
		CommunicationContentsVO communicationContentsVO = communicationBoardService.getCommunicationContents(requestBoardIDX);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(communicationContentsVO);
		
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		out.close();
	}
}
