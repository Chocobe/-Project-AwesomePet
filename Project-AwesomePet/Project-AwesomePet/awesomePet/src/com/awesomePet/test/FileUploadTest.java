package com.awesomePet.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@SuppressWarnings("serial")
@WebServlet("/uploadTest")
public class FileUploadTest extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		doHandle(request, response);
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		doHandle(request, response);
	}
	
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");		
		
		String folderName = "communicationUploadImages";
		String realPath = request.getServletContext().getRealPath(folderName);
		int fileSize = 1024 * 1024 * 10;
		String encoding = "UTF-8";
		
		
		MultipartRequest multi = new MultipartRequest(request,
													  realPath,
													  fileSize,
													  encoding,
													  new DefaultFileRenamePolicy());
		String myFile = multi.getFilesystemName("myFile");
		String myFileOrigin = multi.getOriginalFileName("myFile");
		String hiddenValue = multi.getParameter("hiddenValue");
		
		
		PrintWriter out = response.getWriter();
		
		File uploadedFile = new File(realPath + "/" + "nothing~");
		if(uploadedFile.exists()) {
			out.print("<h1>업로드가 정상 수행 되었습니다</h1>");
			
		} else {
			out.print("<h1>업로드 실패!</h1>");
		}
		
		out.print("<h3>업로드 파일명(변경정책 적용) : " + myFile + "</h3>");
		out.print("<h3>업로드 파일명(원본) : " + myFileOrigin + "</h3>");
		out.print("<hr/>");
		out.print("<p>전체경로 : " + realPath + "/" + myFile + "</p>");
		out.print("<img src='" + folderName + "/" + myFile + "'>");
		out.print("<h3>히든값 : " + hiddenValue + "</h3>");
		
		out.close();
	}
}
































