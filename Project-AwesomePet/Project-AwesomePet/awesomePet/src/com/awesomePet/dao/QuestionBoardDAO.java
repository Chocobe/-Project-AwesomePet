package com.awesomePet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.awesomePet.mainDBCP.DBConnectorJNDI;
import com.awesomePet.vo.QuestionContentsVO;

public class QuestionBoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	
	// 쿼리 결과 제한값 입니다.
	public static final double QUERY_LIMIT;
	
	static {
		QUERY_LIMIT = 10;
	}
	
	
// Connection, PreparedStatement 객체를 준비 합니다. (객체 생성)
	public void readyForQuery(String sql) throws SQLException {
		conn = DBConnectorJNDI.getDataSource().getConnection();
		pstmt = conn.prepareStatement(sql);
	}
	
	
// QuestionBoard 게시글 총 개수를 구합니다.
	public int selectTotalContentsCnt() {
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS totalContentsCnt FROM questionBoard";
			
			readyForQuery(sql);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				result = resultSet.getInt("totalContentsCnt");
			}
			
		} catch(SQLException e) {
			System.out.println("<QuestionBoardDAO - selectTotalContentsCnt() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		System.out.println("<TEST> QuestionBoard 글 개수 : " + result);
		
		return result;
	}
	
	
// QuestionBoard 총 페이지 개수를 구합니다.
	public int selectTotalPageCnt() {
		return (int)Math.ceil(selectTotalContentsCnt() / QUERY_LIMIT);
	}
	
	
// QuestionBoard 글 목록을 조회합니다.
	public List<QuestionContentsVO> selectContentsList(int requestPage) {
		List<QuestionContentsVO> contentsList = new ArrayList<QuestionContentsVO>();
		
		try {
			String sql = "SELECT * FROM questionBoard " + 
						 "ORDER BY boardIDX DESC " +
						 "LIMIT ? OFFSET ?";
			int offset = (requestPage - 1) * (int)QUERY_LIMIT;
			
			readyForQuery(sql);
			
			pstmt.setInt(1, (int)QUERY_LIMIT);
			pstmt.setInt(2, offset);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				QuestionContentsVO questionContentsVO = new QuestionContentsVO(resultSet.getInt("boardIDX"),
																			   resultSet.getString("writerID"),
																			   resultSet.getString("title"),
																			   resultSet.getString("content"),
																			   resultSet.getDate("writeDate").toLocalDate(),
																			   resultSet.getInt("watch"),
																			   resultSet.getInt("replyCnt"));
				
				contentsList.add(questionContentsVO);
			}
			
		} catch(SQLException e) {
			System.out.println("<QuestionBoardDAO - selectContents() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return contentsList;
	}
	
	
// QuestionBoard의 특정 글을 조회 합니다.
	public QuestionContentsVO selectContents(int requestBoardIDX) {
		QuestionContentsVO resultVO = null;
		
		try {
			String sql = "SELECT * FROM questionBoard " +
						 "WHERE boardIDX=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, requestBoardIDX);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				resultVO = new QuestionContentsVO(resultSet.getInt("boardIDX"),
												  resultSet.getString("writerID"),
												  resultSet.getString("title"),
												  resultSet.getString("content"),
												  resultSet.getDate("writeDate").toLocalDate(),
												  resultSet.getInt("watch"),
												  resultSet.getInt("replyCnt"));
			}
			
		} catch(SQLException e) {
			System.out.println("<QuestionBoardDAO - selectContents() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return resultVO;
	}
	
	
	public QuestionContentsVO selectContents(String writerID, String title, String content) {
		QuestionContentsVO questionContentsVO = null;
		
		try {
			String sql = "SELECT * FROM questionBoard " +
						 "WHERE writerID=? AND title=? AND content=? " +
						 "ORDER BY boardIDX DESC " +
						 "LIMIT 1";
			readyForQuery(sql);
			pstmt.setString(1, writerID);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				questionContentsVO = new QuestionContentsVO(resultSet.getInt("boardIDX"),
															resultSet.getString("writerID"),
															resultSet.getString("title"),
															resultSet.getString("content"),
															resultSet.getDate("writeDate").toLocalDate(),
															resultSet.getInt("watch"),
															resultSet.getInt("replyCnt"));
			}
			
		} catch(SQLException e) {
			System.out.println("<questionBoardDAO - selectContents() 에러> : " + e.getMessage());
			e.printStackTrace();
		}
		
		return questionContentsVO;
	}
	
	
// QuestionBoard 글을 작성합니다.
	public int insertQuestionContents(QuestionContentsVO questionContentsVO) {
		int result = -1;
		
		try {
			String sql = "INSERT INTO questionBoard(writerID, title, content) " +
						 "VALUES(?, ?, ?)";
			readyForQuery(sql);
			
			pstmt.setString(1, questionContentsVO.getWriterID());
			pstmt.setString(2, questionContentsVO.getTitle());
			pstmt.setString(3, questionContentsVO.getContent());
			
			result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("<QuestionBoardDAO - insertQuestionContents() 알림> : 글 작성 완료!");
			}
			
		} catch(SQLException e) {
			System.out.println("<QuestionBoardDAO - insertQuestionContents() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// QuestionBoard 글을 수정합니다.
	public int updateQuestionContents(QuestionContentsVO questionContentsVO) {
		int result = -1;
		
		try {
			String sql = "UPDATE questionBoard " +
						 "SET title=?, content=? " +
						 "WHERE boardIDX=?";
			
			readyForQuery(sql);
			pstmt.setString(1, questionContentsVO.getTitle());
			pstmt.setString(2, questionContentsVO.getContent());
			pstmt.setInt(3, questionContentsVO.getBoardIDX());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<QuestionBoardDAO - updateQuestionContents() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// QuestionBoard 글을 삭제합니다.
	public int deleteQuestionContents(int boardIDX) {
		int result = -1;
		
		try {
			String sql = "DELETE FROM questionBoard ";
			sql += "WHERE boardIDX=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, boardIDX);
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<QuestionBoardDAO - deleteQuestionContents() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// QuestionBoard 글의 조회수를 1 증가시킵니다.
	public int updateWatch(int boardIDX) {
		int result = -1;
		
		try {
			String sql = "UPDATE questionBoard ";
			sql += "SET watch = watch + 1 " +
				   "WHERE boardIDX=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, boardIDX);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<QuestionBoardDAO - updateWatch() 에러> : " + e.getMessage());
			e.printStackTrace();

		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// QuestionBoard 글의 댓글수를 갱신 합니다. 
	public void updateReplyCnt(int boardIDX, int value) {
		try {
			String sql = "UPDATE questionBoard ";
			sql += "SET replyCnt=replyCnt+? ";
			sql += "WHERE boardIDX=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, value);
			pstmt.setInt(2, boardIDX);
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<QuestionBoardDAO - updateReply 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
	}
}
























