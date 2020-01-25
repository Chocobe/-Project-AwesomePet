package com.awesomePet.vo;

import java.time.LocalDate;

public class QuestionContentsVO {
	private int boardIDX;
	private String writerID;
	private String title;
	private String content;
	private LocalDate writeDate;
	private int watch;
	
	
// 생성자
	public QuestionContentsVO() {
		this(-1, 
			 null, 
			 null, 
			 null, 
			 null, 
			 -1);
	}
	
	public QuestionContentsVO(String writerID,
							  String title,
							  String content) {
		this(-1,
			 writerID,
			 title,
			 content,
			 null,
			 -1);
	}
	
	public QuestionContentsVO(int boardIDX,
							  String title,
							  String content) {
		this(boardIDX,
			 null,
			 title,
			 content,
			 null,
			 -1);
	}
	
	public QuestionContentsVO(int boardIDX,
							  String writerID,
							  String title,
							  String content,
							  LocalDate writeDate,
							  int watch) {
		this.boardIDX = boardIDX;
		this.writerID = writerID;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.watch = watch;
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
}
