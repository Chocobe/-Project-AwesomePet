package com.awesomePet.vo;

import java.time.LocalDate;

public class QuestionReplyContentsVO {
	private int replyIDX;
	private int parentIDX;
	private String writerID;
	private String content;
	private LocalDate writeDate;

	
// 생성자
	public QuestionReplyContentsVO() { }

	public QuestionReplyContentsVO(int replyIDX,
								   String content) {
		this(replyIDX,
			 -1,
			 null,
			 content,
			 null);
	}
	
	public QuestionReplyContentsVO(int parentIDX,
								   String writerID,
								   String content) {
		this(-1,
			 parentIDX,
			 writerID,
			 content,
			 null);
	}
	
	public QuestionReplyContentsVO(int replyIDX,
								   int parentIDX,
								   String writerID,
								   String content,
								   LocalDate writeDate) {
		this.replyIDX = replyIDX;
		this.parentIDX = parentIDX;
		this.writerID = writerID;
		this.content = content;
		this.writeDate = writeDate;
	}
	
	
// replyIDX
	public int getReplyIDX() {
		return replyIDX;
	}
	public void setReplyIDX(int replyIDX) {
		this.replyIDX = replyIDX;
	}

	
// parentIDX
	public int getParentIDX() {
		return parentIDX;
	}
	public void setParentIDX(int parentIDX) {
		this.parentIDX = parentIDX;
	}

	
// writerID
	public String getWriterID() {
		return writerID;
	}
	public void setWriterID(String writerID) {
		this.writerID = writerID;
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
}
