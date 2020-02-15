package com.awesomePet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.awesomePet.mainDBCP.DBConnectorJNDI;
import com.awesomePet.vo.PetReplyContentsVO;


public class PetReplyDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	
	// 쿼리 결과 제한값 입니다.
	private static final double QUERY_LIMIT;
	
	static {
		QUERY_LIMIT = 5;
	}
	
	
// DB연동을 위한 Connection, PreparedStatement 객체를 준비합니다.
	private void readyForQuery(String sql) throws SQLException {
		conn = DBConnectorJNDI.getDataSource().getConnection();
		pstmt = conn.prepareStatement(sql);
	}
	
	
// petBoard의 특정 글에 대한 댓글 총 개수를 구합니다.
	public int selectTotalReplyCnt(int parentIDX) {
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS totalReplyCnt FROM petReply ";
			sql += "WHERE parentIDX=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, parentIDX);
			
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				result = resultSet.getInt("totalReplyCnt");
			}
			
		} catch(SQLException e) {
			System.out.println("<PetReplyDAO - selectTotalReplyCnt() 에러> : " + e.getMessage());
			e.printStackTrace();

		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		System.out.println("총 댓글 개수 : " + result);
		return result;
	}
	
	
// petBoard의 특정 글에 대한 댓글의 페이지 개수를 구합니다.
	public int selectTotalPageCnt(int parentIDX) {
		return (int)Math.ceil(selectTotalReplyCnt(parentIDX) / QUERY_LIMIT);
	}
	
	
// petBoard의 특정 글에 대한 댓글을 조회하여 List로 반환합니다.
	public List<PetReplyContentsVO> selectReplyList(int parentIDX, int requestPage) {
		List<PetReplyContentsVO> replyList = new ArrayList<PetReplyContentsVO>();
		
		try {
			String sql = "SELECT * FROM petReply ";
			sql += "WHERE parentIDX=? ";
			sql += "ORDER BY replyIDX DESC ";
			sql += "LIMIT ? OFFSET ?";
			
			int offset = (requestPage - 1) * (int)QUERY_LIMIT;
			
			readyForQuery(sql);
			pstmt.setInt(1, parentIDX);
			pstmt.setInt(2, (int)QUERY_LIMIT);
			pstmt.setInt(3, offset);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				PetReplyContentsVO petReplyContentsVO = 
								new PetReplyContentsVO(resultSet.getInt("replyIDX"), 
													   resultSet.getInt("parentIDX"),
													   resultSet.getString("writerID"),
													   resultSet.getString("content"),
													   resultSet.getDate("writeDate").toLocalDate());
				replyList.add(petReplyContentsVO);
			}
					
					
		} catch(SQLException e) {
			System.out.println("<PetReplyDAO - selectReplyList() 에러> : " + e.getMessage());
			e.printStackTrace();

		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return replyList;
	}
	
	
// PetBoard의 특정 글에 대한 댓글을 INSERT 합니다.
	public int insertPetReply(PetReplyContentsVO petReplyContentsVO) {
		int result = 0;
		
		try {
			String sql = "INSERT INTO petReply(parentIDX, writerID, content) ";
			sql += "VALUES(?, ?, ?)";
			
			readyForQuery(sql);
			pstmt.setInt(1, petReplyContentsVO.getParentIDX());
			pstmt.setString(2, petReplyContentsVO.getWriterID());
			pstmt.setString(3, petReplyContentsVO.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<PetReplyDAO - insertPetReply() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// PetBoard의 특정 댓글을 UPDATE 합니다.
	public int updatePetReply(PetReplyContentsVO petReplyContentsVO) {
		int result = -1;
		
		try {
			String sql = "UPDATE petReply SET ";
			sql += "content=? ";
			sql += "WHERE replyIDX=? ";
			
			readyForQuery(sql);
			pstmt.setString(1, petReplyContentsVO.getContent());
			pstmt.setInt(2, petReplyContentsVO.getReplyIDX());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<PetReplyDAO - updatePetReply() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn);
		}
		
		return result;
	}
	
	
// PetBoard의 특정 댓글 하나를 SELECT 합니다.
	public PetReplyContentsVO selectPetReply(int replyIDX) {
		PetReplyContentsVO resultVO = null;
		
		try {
			String sql = "SELECT * FROM petReply ";
			sql += "WHERE replyIDX=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, replyIDX);
			
			resultSet = pstmt.executeQuery();
			if(resultSet.next()) {
				resultVO = new PetReplyContentsVO(resultSet.getInt("replyIDX"),
												  resultSet.getInt("parentIDX"),
												  resultSet.getString("writerID"),
												  resultSet.getString("content"),
												  resultSet.getDate("writeDate").toLocalDate());
			}
			
		} catch(SQLException e) {
			System.out.println("<PetReplyDAO - selectPetReply() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return resultVO;
	}
	
	
// PetBoard의 특정 댓글 하나를 DELETE 합니다.
	public int deletePetReply(int replyIDX) {
		int result = 0;
		
		try {
			String sql = "DELETE FROM petReply ";
			sql += "WHERE replyIDX=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, replyIDX);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<PetReplyDAO - deletePetReply() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
}
