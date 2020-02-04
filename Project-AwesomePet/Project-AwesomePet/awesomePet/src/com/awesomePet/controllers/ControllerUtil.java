package com.awesomePet.controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUtil {
	// SubController 객체에서 forward를 위한 유틸 메서드 입니다.
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) 
					throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	
	// SubController 객체에서 redirect를 위한 유틸 메서드 입니다.
	public static void redirect(HttpServletResponse response, String path) 
					throws ServletException, IOException {
		response.sendRedirect(path);
	}
	
	
	// SubController 객체에서 파일 삭제를 위한 유틸 메서드 입니다.
	public static void removeImgFile(HttpServletRequest request, String folderName, String imgLocation) {
		if(imgLocation != null && imgLocation.length() > 0) {
			String folderPath = request.getContextPath() + "/" + folderName + "/";
			int folderPathLength = folderPath.length();
			
			String imgFileName = imgLocation.substring(folderPathLength);
			String realPath = request.getServletContext().getRealPath(folderName) + "/" + imgFileName;
			
			File imgFile = new File(realPath);
			
			System.out.println("\n--- imgLocation값 : " + imgLocation);
			System.out.println("--- folderPath 값 : " + folderPath);
			System.out.println("--- imgFileName : " + imgFileName);
			
			System.out.println("--- realPath 값 : " + realPath + "\n");
			
			if(imgFile.exists()) {
				imgFile.delete();
				System.out.println("--- 이미지 삭제 완료");
				
			} else {
				System.out.println("--- 파일을 찾을 수 없습니다");
			}
		}
	}
}
