package com.awesomePet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.awesomePet.mainDBCP.DBConnectorJNDI;
import com.awesomePet.vo.QuestionReplyContentsVO;

public class QuestionReplyDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	
	// 쿼리 결과 제한값 입니다.
	private static final double QUERY_LIMIT;
	
	static {
		QUERY_LIMIT = 3;
	}
	
	
// DB연동을 위한 Connection, PreparedStatement 객체를 준비합니다.
	private void readyForQuery(String sql) throws SQLException {
		conn = DBConnectorJNDI.getDataSource().getConnection();
		pstmt = conn.prepareStatement(sql);
	}
	
	
// questionBoard의 특정 글에 대한 댓글 총 개수를 구합니다.
	public int selectTotalReplyCnt(int parentIDX) {
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS totalReplyCnt FROM questionReply ";
			sql += "WHERE parentIDX=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, parentIDX);
			
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				result = resultSet.getInt("totalReplyCnt");
			}
			
		} catch(SQLException e) {
			System.out.println("<QuestionReplyDAO - selectTotalReplyCnt() 에러> : " + e.getMessage());
			e.printStackTrace();

		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		System.out.println("총 댓글 개수 : " + result);
		return result;
	}
	
	
// questionBoard의 특정 글에 대한 댓글의 페이지 개수를 구합니다.
	public int selectTotalPageCnt(int parentIDX) {
		return (int)Math.ceil(selectTotalReplyCnt(parentIDX) / QUERY_LIMIT);
	}
	
	
// questionBoard의 특정 글에 대한 댓글을 조회하여 List로 반환합니다.
	public List<QuestionReplyContentsVO> selectReplyList(int parentIDX, int requestPage) {
		List<QuestionReplyContentsVO> replyList = new ArrayList<QuestionReplyContentsVO>();
		
		try {
			String sql = "SELECT * FROM questionReply ";
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
				QuestionReplyContentsVO questionReplyContentsVO = new QuestionReplyContentsVO(resultSet.getInt("replyIDX"), 
																							  resultSet.getInt("parentIDX"),
																							  resultSet.getString("writerID"),
																							  resultSet.getString("content"),
																							  resultSet.getDate("writeDate").toLocalDate());
				replyList.add(questionReplyContentsVO);
			}
					
					
		} catch(SQLException e) {
			System.out.println("<QuestionReplyDAO - selectReplyList() 에러> : " + e.getMessage());
			e.printStackTrace();

		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return replyList;
	}
}
