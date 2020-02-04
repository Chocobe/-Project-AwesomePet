package com.awesomePet.controllers.communicationBoardControllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.CommunicationBoardService;
import com.awesomePet.service.QuestionBoardService;
import com.awesomePet.vo.CommunicationContentsVO;

public class CommunicationContentsDeleteController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		CommunicationBoardService communicationBoardService = new CommunicationBoardService();
		
		int requestBoardIDX = Integer.parseInt(request.getParameter("requestBoardIDX"));
		
		CommunicationContentsVO communicationContentsVO = communicationBoardService.getCommunicationContents(requestBoardIDX);
		String imgLocation_1 = communicationContentsVO.getImgLocation_1();
		String imgLocation_2 = communicationContentsVO.getImgLocation_2();
		String imgLocation_3 = communicationContentsVO.getImgLocation_3();
		String folderName = "communicationUploadImages";
		
		ControllerUtil.removeImgFile(request, folderName, imgLocation_1);
		ControllerUtil.removeImgFile(request, folderName, imgLocation_2);
		ControllerUtil.removeImgFile(request, folderName, imgLocation_3);
		
		int result = communicationBoardService.deleteCommunicationContents(requestBoardIDX);
		
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
