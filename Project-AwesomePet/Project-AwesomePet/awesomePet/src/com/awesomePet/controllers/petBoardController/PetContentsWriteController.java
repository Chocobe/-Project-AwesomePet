package com.awesomePet.controllers.petBoardController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awesomePet.controllers.ControllerUtil;
import com.awesomePet.controllers.SubController;
import com.awesomePet.service.PetBoardService;
import com.awesomePet.vo.PetContentsImageVO;
import com.awesomePet.vo.PetContentsVO;
import com.awesomePet.vo.PetVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class PetContentsWriteController implements SubController {
	private static final String FOLDER_NAME;
	private static final int MAX_FILE_SIZE;
	
	static {
		FOLDER_NAME = "petUploadImages";
		MAX_FILE_SIZE = 1024 * 1024 * 10;
	}
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String resultPagePath = "/petContentsView.do";
		
		PetBoardService petBoardService = new PetBoardService();
		
		String folderPath = request.getServletContext().getRealPath(FOLDER_NAME);
		String encoding = (String)request.getServletContext().getAttribute("encoding");
		
		MultipartRequest multipart = new MultipartRequest(request,
														  folderPath,
														  MAX_FILE_SIZE,
														  encoding,
														  new DefaultFileRenamePolicy());
		
		PetVO petVO = readPetInfo(multipart);
		int result = petBoardService.writePet(petVO);
		
		// pet 테이블에 INSERT가 "성공" 했다면,
		if(result == 1) {
			int boardIDX = petBoardService.getPetID(petVO);
			
			PetContentsVO petBoardVO = readPetBoardInfo(boardIDX, request, multipart);
			result = petBoardService.writePetBoard(petBoardVO);
			
			// petBoard 테이블에 INSERT가 "성공" 했다면,
			if(result == 1) {
				List<PetContentsImageVO> imagesInfoList = readImagesInfo(boardIDX, request, multipart);
				result = petBoardService.writePetContentsImages(imagesInfoList);
				
				System.out.println("--- 저장된 이미지 개수 : " + result);
				
//				if(result != petBoardImagesInfoList.size()) {
//					
//				}
				
			} else {
				// pet 에 저장했던 데이터 지우기 (CASCADE)
			}

		} else {
			// 컨트롤러 작성 할 것
			resultPagePath = "/petContentsWriterView.do";
		}
		
//		ControllerUtil.forward(request, response, resultPagePath);
	}
	
	
// DB의 "pet" 테이블에 대한 데이터를 JSP에서 읽어 옵니다.
	private PetVO readPetInfo(MultipartRequest multipart) {
		return new PetVO(multipart.getParameter("subTypeName"),
 						 Integer.parseInt(multipart.getParameter("age")),
						 multipart.getParameter("gender"),
						 Integer.parseInt(multipart.getParameter("price")),
						 multipart.getParameter("vaccination"),
						 multipart.getParameter("neutralization"));
	}
	

// DB의 "petBoard" 테이블에 대한 데이터를 JSP에서 읽어 옵니다.
	private PetContentsVO readPetBoardInfo(int boardIDX, HttpServletRequest request, MultipartRequest multipart) {
		HttpSession session = request.getSession();
		String memberID = (String)session.getAttribute("memberLoginID");
		String boardState = multipart.getParameter("boardState");
		
		return new PetContentsVO(boardIDX,
							  memberID,
							  boardState);
	}
	
	
// DB의 "petBoardImages" 테이블에 대한 데이터를 JSP에서 읽어 옵니다.
	private List<PetContentsImageVO> readImagesInfo(int boardIDX, HttpServletRequest request, MultipartRequest multipart) {
		List<PetContentsImageVO> petBoardImagesInfoList = new ArrayList<PetContentsImageVO>();
		
		// MultipartRequest객체의 getFileNames() 메서드의 와일드카드가 설정되지 않은 상태라서 "unchecked"를 사용하였습니다.
		@SuppressWarnings("unchecked")
		Enumeration<String> fileNames = multipart.getFileNames();
		
		List<String> fileNamesList = new ArrayList<String>();
		while(fileNames.hasMoreElements()) {
			fileNamesList.add(fileNames.nextElement());
		}
		
		int totalFileCnt = fileNamesList.size();
		int lastFileIdx = totalFileCnt - 1;
		int orderNumber = 0;
		
		for(int i = 0; i < fileNamesList.size(); i++) {
			String imgLocation = null;
			String imgOriginLocation = multipart.getOriginalFileName(fileNamesList.get(lastFileIdx - i));
			if(imgOriginLocation != null && imgOriginLocation.length() > 0) {
				imgLocation = request.getContextPath() +
								"/" + FOLDER_NAME +
								"/" + multipart.getFilesystemName(fileNamesList.get(lastFileIdx - i));
				
				PetContentsImageVO petBoardImageVO = new PetContentsImageVO(boardIDX,
																	  ++orderNumber,
																	  imgLocation,
																	  imgOriginLocation);
				
				petBoardImagesInfoList.add(petBoardImageVO);
			}
		}
		
		return petBoardImagesInfoList;
	}
}
