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
	public List<QuestionContentsVO> selectContents(int requestPage) {
		List<QuestionContentsVO> contentsList = new ArrayList<QuestionContentsVO>();
		
		try {
			String sql = "SELECT * FROM questionBoard LIMIT ? OFFSET ?";
			int offset = (requestPage - 1) * (int)QUERY_LIMIT;
			
			readyForQuery(sql);
			
			pstmt.setInt(1, (int)QUERY_LIMIT);
			pstmt.setInt(2, offset);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				QuestionContentsVO questionContentsVO = new QuestionContentsVO();
				questionContentsVO.setBoardIDX(resultSet.getInt("boardIDX"));
				questionContentsVO.setWriterID(resultSet.getString("writerID"));
				questionContentsVO.setTitle(resultSet.getString("title"));
				questionContentsVO.setContent(resultSet.getString("content"));
				questionContentsVO.setWriteDate(resultSet.getDate("writeDate").toLocalDate());
				questionContentsVO.setWatch(resultSet.getInt("watch"));
				
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
}
