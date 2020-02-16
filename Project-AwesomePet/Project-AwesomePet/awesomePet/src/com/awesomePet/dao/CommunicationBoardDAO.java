package com.awesomePet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.awesomePet.mainDBCP.DBConnectorJNDI;
import com.awesomePet.vo.CommunicationContentsVO;

public class CommunicationBoardDAO {
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
	
	
// CommunicationBoard 게시글 총 개수를 구합니다.
	public int selectTotalContentsCnt() {
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS totalContentsCnt FROM communicationBoard";
			
			readyForQuery(sql);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				result = resultSet.getInt("totalContentsCnt");
			}
			
		} catch(SQLException e) {
			System.out.println("<CommunicationBoardDAO - selectTotalContentsCnt() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		System.out.println("<TEST> CommunicationBoard 글 개수 : " + result);
		
		return result;
	}
	
	
// CommunicationBoard 총 페이지 개수를 구합니다.
	public int selectTotalPageCnt() {
		return (int)Math.ceil(selectTotalContentsCnt() / QUERY_LIMIT);
	}
	
	
	// 좋아요 개수 추가
// CommunicationBoard 글 목록을 조회합니다.
	public List<CommunicationContentsVO> selectContentsList(int requestPage) {
		List<CommunicationContentsVO> contentsList = new ArrayList<CommunicationContentsVO>();
		
		try {
			String sql = "SELECT contents.boardIDX, " +
								 "writerID, " +
								 "title, " +
								 "content, " +
								 
								 "imgLocation_1, " +
								 "imgOriginLocation_1, " +
								 "imgLocation_2, " +
								 "imgOriginLocation_2, " +
								 "imgLocation_3, " +
								 "imgOriginLocation_3, " +
								 
								 "writeDate, " +
								 "watch, " +
								 "replyCnt, " +
								 "hit.hitCount AS hitCnt ";
			sql += "FROM communicationBoard AS contents ";
			
			sql += "LEFT JOIN ";
			sql += "(SELECT boardIDX, " +
						   "COUNT(*) AS hitCount ";
			sql += "FROM communicationHit ";
			sql += "GROUP BY boardIDX) AS hit ";
			
			sql += "ON contents.boardIDX = hit.boardIDX ";
			
			sql += "ORDER BY contents.boardIDX DESC ";
			sql += "LIMIT ? OFFSET ?";
			
			
			int offset = (requestPage - 1) * (int)QUERY_LIMIT;
			
			readyForQuery(sql);
			
			pstmt.setInt(1, (int)QUERY_LIMIT);
			pstmt.setInt(2, offset);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				CommunicationContentsVO communicationContentsVO = new CommunicationContentsVO(resultSet.getInt("boardIDX"),
																			   				  resultSet.getString("writerID"),
																			   				  resultSet.getString("title"),
																			   				  resultSet.getString("content"),
																			   				  
																			   				  resultSet.getString("imgLocation_1"),
																			   				  resultSet.getString("imgOriginLocation_1"),
																			   				  resultSet.getString("imgLocation_2"),
																			   				  resultSet.getString("imgOriginLocation_2"),
																			   				  resultSet.getString("imgLocation_3"),
																			   				  resultSet.getString("imgOriginLocation_3"),
																			   				  
																			   				  resultSet.getDate("writeDate").toLocalDate(),
																			   				  resultSet.getInt("watch"),
																			   				  resultSet.getInt("replyCnt"),
																			   				  resultSet.getInt("hitCnt"));
				
				contentsList.add(communicationContentsVO);
			}
			
		} catch(SQLException e) {
			System.out.println("<CommunicationBoardDAO - selectContents() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return contentsList;
	}
	
	
	// 좋아요 개수 추가
// CommunicationBoard의 특정 글을 조회 합니다.
	public CommunicationContentsVO selectContents(int requestBoardIDX) {
		CommunicationContentsVO resultVO = null;
		
		try {
			String sql = "SELECT contents.boardIDX, " +
								 "writerID, " +
								 "title, " +
								 "content, " +
								 
								 "imgLocation_1, " +
								 "imgOriginLocation_1, " +
								 "imgLocation_2, " +
								 "imgOriginLocation_2, " +
								 "imgLocation_3, " +
								 "imgOriginLocation_3, " +
								 
								 "writeDate, " +
								 "watch, " +
								 "replyCnt, " +
								 "hit.hitCnt AS hitCnt ";
			sql += "FROM communicationBoard AS contents ";
			
			sql += "LEFT JOIN ";
			sql += "(SELECT boardIDX, " +
						   "COUNT(*) AS hitCnt ";
			sql += "FROM communicationHit ";
			sql += "GROUP BY boardIDX) AS hit ";
			
			sql += "ON contents.boardIDX = hit.boardIDX ";
			
			sql += "WHERE contents.boardIDX=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, requestBoardIDX);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				resultVO = new CommunicationContentsVO(resultSet.getInt("boardIDX"),
												  	   resultSet.getString("writerID"),
												  	   resultSet.getString("title"),
												  	   resultSet.getString("content"),
												  	   
												  	   resultSet.getString("imgLocation_1"),
												  	   resultSet.getString("imgOriginLocation_1"),
												  	   resultSet.getString("imgLocation_2"),
												  	   resultSet.getString("imgOriginLocation_2"),
												  	   resultSet.getString("imgLocation_3"),
												  	   resultSet.getString("imgOriginLocation_3"),
												  	   
												  	   resultSet.getDate("writeDate").toLocalDate(),
												  	   resultSet.getInt("watch"),
												  	   resultSet.getInt("replyCnt"),
												  	   resultSet.getInt("hitCnt"));
			}
			
		} catch(SQLException e) {
			System.out.println("<CommunicationBoardDAO - selectContents() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return resultVO;
	}
	
	
// CommunicationBoard 에 글을 작성한 직 후, 다시 조회 합니다.
	public CommunicationContentsVO selectContents(String writerID, String title, String content) {
		CommunicationContentsVO communicationContentsVO = null;
		
		try {
			String sql = "SELECT * FROM communicationBoard " +
						 "WHERE writerID=? AND title=? AND content=? " +
						 "ORDER BY boardIDX DESC " +
						 "LIMIT 1";
			readyForQuery(sql);
			pstmt.setString(1, writerID);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				communicationContentsVO = new CommunicationContentsVO(resultSet.getInt("boardIDX"),
																	  resultSet.getString("writerID"),
																	  resultSet.getString("title"),
																	  resultSet.getString("content"),
																	  
																	  resultSet.getString("imgLocation_1"),
																	  resultSet.getString("imgOriginLocation_1"),
																	  resultSet.getString("imgLocation_2"),
																	  resultSet.getString("imgOriginLocation_2"),
																	  resultSet.getString("imgLocation_3"),
																	  resultSet.getString("imgOriginLocation_3"),
																	  
																	  resultSet.getDate("writeDate").toLocalDate(),
																	  resultSet.getInt("watch"),
																	  resultSet.getInt("replyCnt"),
																	  0);
			}
			
		} catch(SQLException e) {
			System.out.println("<communicationBoardDAO - selectContents() 에러> : " + e.getMessage());
			e.printStackTrace();
		}
		
		return communicationContentsVO;
	}
	
	
// CommunicationBoard 글을 작성합니다.
	public int insertCommunicationContents(CommunicationContentsVO communicationContentsVO) {
		int result = -1;
		
		try {
			String sql = "INSERT INTO communicationBoard(writerID, " +
														"title, " + 
														"content, " + 
														
														"imgLocation_1, " + 
														"imgOriginLocation_1, " + 
														"imgLocation_2, " + 
														"imgOriginLocation_2, " + 
														"imgLocation_3, " + 
														"imgOriginLocation_3) ";
			sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			readyForQuery(sql);
			
			pstmt.setString(1, communicationContentsVO.getWriterID());
			pstmt.setString(2, communicationContentsVO.getTitle());
			pstmt.setString(3, communicationContentsVO.getContent());
			
			pstmt.setString(4, communicationContentsVO.getImgLocation_1());
			pstmt.setString(5, communicationContentsVO.getImgOriginLocation_1());
			pstmt.setString(6, communicationContentsVO.getImgLocation_2());
			pstmt.setString(7, communicationContentsVO.getImgOriginLocation_2());
			pstmt.setString(8, communicationContentsVO.getImgLocation_3());
			pstmt.setString(9, communicationContentsVO.getImgOriginLocation_3());
			
			result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("<CommunicationBoardDAO - insertCommunicationContents() 알림> : 글 작성 완료!");
			}
			
		} catch(SQLException e) {
			System.out.println("<CommunicationBoardDAO - insertCommunicationContents() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// CommunicationBoard 글을 수정합니다.
	public int updateCommunicationContents(CommunicationContentsVO communicationContentsVO) {
		int result = -1;
		
		try {
			String sql = "UPDATE communicationBoard ";
			sql += "SET title=?, " + 
					   "content=?, " +
					   
					   "imgLocation_1=?, " +
					   "imgOriginLocation_1=?, " +
					   "imgLocation_2=?, " +
					   "imgOriginLocation_2=?, " +
					   "imgLocation_3=?, " +
					   "imgOriginLocation_3=? "; 
			
			sql += "WHERE boardIDX=?";
			
			readyForQuery(sql);
			
			pstmt.setString(1, communicationContentsVO.getTitle());
			pstmt.setString(2, communicationContentsVO.getContent());
			
			pstmt.setString(3, communicationContentsVO.getImgLocation_1());
			pstmt.setString(4, communicationContentsVO.getImgOriginLocation_1());
			pstmt.setString(5, communicationContentsVO.getImgLocation_2());
			pstmt.setString(6, communicationContentsVO.getImgOriginLocation_2());
			pstmt.setString(7, communicationContentsVO.getImgLocation_3());
			pstmt.setString(8, communicationContentsVO.getImgOriginLocation_3());
			
			pstmt.setInt(9, communicationContentsVO.getBoardIDX());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<CommunicationBoardDAO - updateCommunicationContents() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// CommunicationBoard 글을 삭제합니다.
	public int deleteCommunicationContents(int boardIDX) {
		int result = -1;
		
		try {
			String sql = "DELETE FROM communicationBoard ";
			sql += "WHERE boardIDX=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, boardIDX);
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<CommunicationBoardDAO - deleteCommunicationContents() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// CommunicationBoard 글의 조회수를 1 증가시킵니다.
	public int updateWatch(int boardIDX) {
		int result = -1;
		
		try {
			String sql = "UPDATE communicationBoard ";
			sql += "SET watch = watch + 1 " +
				   "WHERE boardIDX=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, boardIDX);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<CommunicationBoardDAO - updateWatch() 에러> : " + e.getMessage());
			e.printStackTrace();

		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// CommunicationBoard 글의 댓글수를 갱신 합니다. 
	public void updateReplyCnt(int boardIDX, int value) {
		try {
			String sql = "UPDATE communicationBoard ";
			sql += "SET replyCnt = replyCnt+? ";
			sql += "WHERE boardIDX=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, value);
			pstmt.setInt(2, boardIDX);
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<CommunicationBoardDAO - updateReply 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
	}
}
