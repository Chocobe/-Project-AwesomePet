package com.awesomePet.controllers.communicationBoardControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.CommunicationBoardService;
import com.awesomePet.vo.CommunicationContentsVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CommunicationContentsWriteController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/communicationContentsView.do";
		
		CommunicationBoardService communicationBoardService = new CommunicationBoardService();
		CommunicationContentsVO communicationContentsVO = null;
		
		// multipart/form-data 에서 데이터를 읽어 옵니다.
		communicationContentsVO = readMultipartFormData(request);
		
		int result = communicationBoardService.writeCommunicationContents(communicationContentsVO);
		
		if(result == 1) {
			String writerID = communicationContentsVO.getWriterID();
			String title = communicationContentsVO.getTitle();
			String content = communicationContentsVO.getContent();
			
			int boardIDX = communicationBoardService.getCommunicationContents(writerID, 
																			  title, 
																			  content).getBoardIDX();
			resultPagePath += "?requestBoardIDX=" + boardIDX;
		}
			
		ControllerUtil.forward(request, response, resultPagePath);
	}
	
	
	private CommunicationContentsVO readMultipartFormData(HttpServletRequest request) 
					throws IOException {
		String folderName = "communicationUploadImages";
		String folderPath = request.getServletContext().getRealPath(folderName);
		String encoding = (String)request.getServletContext().getAttribute("encoding");
		int maxFileSize = 1024 * 1024 * 10;
		
		MultipartRequest multipart = new MultipartRequest(request,
														  folderPath,
														  maxFileSize,
														  encoding,
														  new DefaultFileRenamePolicy());
		
		HttpSession session = request.getSession();
		String writerID = (String)session.getAttribute("memberLoginID");
		String title = multipart.getParameter("title");
		String content = multipart.getParameter("content");
		
		String imgLocation_1 = null;
		String imgOriginLocation_1 = multipart.getOriginalFileName("imgLocation_1");
		if(imgOriginLocation_1 != null) {
			imgLocation_1 = request.getContextPath() + 
								"/" + folderName + 
								"/" + multipart.getFilesystemName("imgLocation_1");
		}
		
		String imgLocation_2 = null;
		String imgOriginLocation_2 = multipart.getOriginalFileName("imgLocation_2");
		if(imgOriginLocation_2 != null) {
			imgLocation_2 = request.getContextPath() + 
								"/" + folderName + 
								"/" + multipart.getFilesystemName("imgLocation_2");
		}
		
		
		String imgLocation_3 = null;
		String imgOriginLocation_3 = multipart.getOriginalFileName("imgLocation_3");
		if(imgOriginLocation_3 != null) {
			imgLocation_3 = request.getContextPath() + 
								"/" + folderName + 
								"/" + multipart.getFilesystemName("imgLocation_3");
		}
		
		CommunicationContentsVO communicationContentsVO = new CommunicationContentsVO(writerID,
																					  title,
																					  content,
																					  
																					  imgLocation_1,
																					  imgOriginLocation_1,
																					  imgLocation_2,
																					  imgOriginLocation_2,
																					  imgLocation_3,
																					  imgOriginLocation_3);
		
		return communicationContentsVO;
	}
}
