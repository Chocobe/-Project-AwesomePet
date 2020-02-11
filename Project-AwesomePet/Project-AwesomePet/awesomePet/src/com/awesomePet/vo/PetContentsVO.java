package com.awesomePet.vo;

import java.time.LocalDate;

public class PetContentsVO {
	private int boardIDX;
	private String writerID;
	private int watch;
	private LocalDate writeDate;
	private String boardState;
	
	
// 생성자
	public PetContentsVO() { }
	
	public PetContentsVO(int boardIDX,
					  String writerID,
					  String boardState) {
		this(boardIDX,
			 writerID,
			 0,
			 null,
			 boardState);
	}
	
	public PetContentsVO(int boardIDX,
					  String writerID,
					  int watch,
					  LocalDate writeDate,
					  String boardState) {
		this.boardIDX = boardIDX;
		this.writerID = writerID;
		this.watch = watch;
		this.writeDate = writeDate;
		this.boardState = boardState;
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

	
// watch
	public int getWatch() {
		return watch;
	}
	public void setWatch(int watch) {
		this.watch = watch;
	}

	
// writeDate
	public LocalDate getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(LocalDate writeDate) {
		this.writeDate = writeDate;
	}

	
// boardState
	public String getBoardState() {
		return boardState;
	}
	public void setBoardState(String boardState) {
		this.boardState = boardState;
	}
}
