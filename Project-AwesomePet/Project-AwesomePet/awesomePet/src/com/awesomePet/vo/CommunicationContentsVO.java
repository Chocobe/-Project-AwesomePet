package com.awesomePet.vo;

import java.time.LocalDate;

public class CommunicationContentsVO {
	private int boardIDX;
	private String writerID;
	private String title;
	private String content;
	
	private String imgLocation_1;
	private String imgOriginLocation_1;
	private String imgLocation_2;
	private String imgOriginLocation_2;
	private String imgLocation_3;
	private String imgOriginLocation_3;
	
	private LocalDate writeDate;
	private int watch;
	private int replyCnt;
	
	
// 생성자
	public CommunicationContentsVO() {
		this(-1, 
			 null, 
			 null, 
			 null, 
			 
			 null,
			 null, 
			 null,
			 null, 
			 null, 
			 null, 
			 
			 null, 
			 -1,
			 0);
	}
	
	public CommunicationContentsVO(String writerID,
							  	   String title,
							  	   String content,
							  	   
							  	   String imgLocation_1,
							  	   String imgOriginLocation_1,
							  	   String imgLocation_2,
							  	   String imgOriginLocation_2,
							  	   String imgLocation_3,
							  	   String imgOriginLocation_3) {
		this(-1,
			 writerID,
			 title,
			 content,
			 
			 imgLocation_1,
			 imgOriginLocation_1,
			 imgLocation_2,
			 imgOriginLocation_2,
			 imgLocation_3,
			 imgOriginLocation_3,
			 
			 null,
			 -1,
			 0);
	}
	
	public CommunicationContentsVO(int boardIDX,
							  	   String title,
							  	   String content,
							  	   
							  	   String imgLocation_1,
							  	   String imgOriginLocation_1,
							  	   String imgLocation_2,
							  	   String imgOriginLocation_2,
							  	   String imgLocation_3,
							  	   String imgOriginLocation_3) {
		this(boardIDX,
			 null,
			 title,
			 content,
			 
			 imgLocation_1,
			 imgOriginLocation_1,
			 imgLocation_2,
			 imgOriginLocation_2,
			 imgLocation_3,
			 imgOriginLocation_3,
			 
			 null,
			 -1,
			 0);
	}
	
	public CommunicationContentsVO(int boardIDX,
							  	   String writerID,
							  	   String title,
							  	   String content,
							  	   
							  	   String imgLocation_1,
							  	   String imgOriginLocation_1,
							  	   String imgLocation_2,
							  	   String imgOriginLocation_2,
							  	   String imgLocation_3,
							  	   String imgOriginLocation_3,
							  	   
							  	   LocalDate writeDate,
							  	   int watch) {
		this(boardIDX,
			 writerID,
			 title,
			 content,
			 
			 imgLocation_1,
			 imgOriginLocation_1,
			 imgLocation_2,
			 imgOriginLocation_2,
			 imgLocation_3,
			 imgOriginLocation_3,
			 
			 writeDate,
			 watch,
			 0);
	}
	
	public CommunicationContentsVO(int boardIDX,
							  	   String writerID,
							  	   String title,
							  	   String content,
							  	   
							  	   String imgLocation_1,
							  	   String imgOriginLocation_1,
							  	   String imgLocation_2,
							  	   String imgOriginLocation_2,
							  	   String imgLocation_3,
							  	   String imgOriginLocation_3,
							  	   
							  	   LocalDate writeDate,
							  	   int watch,
							  	   int replyCnt) {
		this.boardIDX = boardIDX;
		this.writerID = writerID;
		this.title = title;
		this.content = content;
		
		this.imgLocation_1 = imgLocation_1;
		this.imgOriginLocation_1 = imgOriginLocation_1;
		this.imgLocation_2 = imgLocation_2;
		this.imgOriginLocation_2 = imgOriginLocation_2;
		this.imgLocation_3 = imgLocation_3;
		this.imgOriginLocation_3 = imgOriginLocation_3;
		
		this.writeDate = writeDate;
		this.watch = watch;
		this.replyCnt = replyCnt;
	}
	
	
// boardIDX
	public int getBoardIDX() {
		return boardIDX;
	}
	public void setBoardIDX(int boardIDX) {
		this.boardIDX = boardIDX;
	}
	
	
// writerID
	public String getWriterID() {
		return writerID;
	}
	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}
	
	
// title
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
// content
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
// imgLocation_1
	public String getImgLocation_1() {
		return imgLocation_1;
	}
	public void setImgLocation_1(String imgLocation_1) {
		this.imgLocation_1 = imgLocation_1;
	}
	
	
// imgOriginLocation_1
	public String getImgOriginLocation_1() {
		return imgOriginLocation_1;
	}
	public void setImgOriginLocation_1(String imgOriginLocation_1) {
		this.imgOriginLocation_1 = imgOriginLocation_1;
	}
	
	
// imgLocation_2
	public String getImgLocation_2() {
		return imgLocation_2;
	}
	public void setImgLocation_2(String imgLocation_2) {
		this.imgLocation_2 = imgLocation_2;
	}
	
	
// imgOriginLocation_2
	public String getImgOriginLocation_2() {
		return imgOriginLocation_2;
	}
	public void setImgOriginLocation_2(String imgOriginLocation_2) {
		this.imgOriginLocation_2 = imgOriginLocation_2;
	}
	
	
// imgLocation_2
	public String getImgLocation_3() {
		return imgLocation_3;
	}
	public void setImgLocation_3(String imgLocation_3) {
		this.imgLocation_3 = imgLocation_3;
	}
	
	
// imgOriginLocation_3
	public String getImgOriginLocation_3() {
		return imgOriginLocation_3;
	}
	public void setImgOriginLocation_3(String imgOriginLocation_3) {
		this.imgOriginLocation_3 = imgOriginLocation_3;
	}
	
	
// writeDate
	public LocalDate getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(LocalDate writeDate) {
		this.writeDate = writeDate;
	}
	
	
// watch
	public int getWatch() {
		return watch;
	}
	public void setWatch(int watch) {
		this.watch = watch;
	}
	
	
// replyCnt
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
}
