package com.awesomePet.controllers.communicationBoardControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.CommunicationBoardService;
import com.awesomePet.vo.CommunicationContentsVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CommunicationContentsUpdateController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/communicationContentsView.do";
		
		// multiaprt/form-data 로 부터 데이터를 읽어 옵니다.
		CommunicationContentsVO communicationContentsVO = readMultipartFormData(request);
		
		CommunicationBoardService communicationBoardService = new CommunicationBoardService();
		int result = communicationBoardService.updateCommunicationContents(communicationContentsVO);
		
		if(result == 1) {
			resultPagePath += "?requestBoardIDX=" + communicationContentsVO.getBoardIDX();
		}
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
	
	
	// multipart/form-data 로 부터 데이터를 가져 옵니다.
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
		
		int boardIDX = Integer.parseInt(multipart.getParameter("requestBoardIDX"));
		String title = multipart.getParameter("title");
		String content = multipart.getParameter("content");
		
		// 첫번째 업로드 이미지 데이터를 가져 옵니다.
		String imgLocation_1 = null;
		String imgOriginLocation_1 = null;
		
		if(multipart.getParameter("action_1").equals("fixed")) {
			imgOriginLocation_1 = multipart.getOriginalFileName("imgLocation_1");
			
			if(imgOriginLocation_1 != null) {
				imgLocation_1 = request.getContextPath() + 
									"/" + folderName + 
									"/" + multipart.getFilesystemName("imgLocation_1");
			}
			
			// 이전 이미지 파일은 삭제 합니다.
			ControllerUtil.removeImgFile(request, folderName, multipart.getParameter("beforeImgLocation_1"));
			
		} else {
			imgLocation_1 = multipart.getParameter("beforeImgLocation_1");
			imgOriginLocation_1 = multipart.getParameter("beforeImgOriginLocation_1");
		}
		
		// 두번째 업로드 이미지 데이터를 가져 옵니다.
		String imgLocation_2 = null;
		String imgOriginLocation_2 = null;
		
		if(multipart.getParameter("action_2").equals("fixed")) {
			imgOriginLocation_2 = multipart.getOriginalFileName("imgLocation_2");
			
			if(imgOriginLocation_2 != null) {
				imgLocation_2 = request.getContextPath() +
									"/" + folderName +
									"/" + multipart.getFilesystemName("imgLocation_2");
			}
			
			// 이전 이미지 파일은 삭제 합니다.
			ControllerUtil.removeImgFile(request, folderName, multipart.getParameter("beforeImgLocation_2"));
			
		} else {
			imgLocation_2 = multipart.getParameter("beforeImgLocation_2");
			imgOriginLocation_2 = multipart.getParameter("beforeImgOriginLocation_2");
		}
		
		// 세번째 업로드 이미지 데이터를 가져 옵니다.
		String imgLocation_3 = null;
		String imgOriginLocation_3 = null;
		
		if(multipart.getParameter("action_3").equals("fixed")) {
			imgOriginLocation_3 = multipart.getOriginalFileName("imgLocation_3");
			
			if(imgOriginLocation_3 != null) {
				imgLocation_3 = request.getContextPath() +
									"/" + folderName +
									"/" + multipart.getFilesystemName("imgLocation_3");
			}
			
			// 이전 이미지 파일은 삭제 합니다.
			ControllerUtil.removeImgFile(request, folderName, multipart.getParameter("beforeImgLocation_3"));
			
		} else {
			imgLocation_3 = multipart.getParameter("beforeImgLocation_3");
			imgOriginLocation_3 = multipart.getParameter("beforeImgOriginLocation_3");
		}

		CommunicationContentsVO communicationContentsVO = new CommunicationContentsVO(boardIDX, 
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
