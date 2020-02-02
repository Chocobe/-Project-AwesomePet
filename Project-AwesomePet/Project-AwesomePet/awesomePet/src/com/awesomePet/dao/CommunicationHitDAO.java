package com.awesomePet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.awesomePet.mainDBCP.DBConnectorJNDI;
import com.awesomePet.vo.CommunicationHitVO;

public class CommunicationHitDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	
// Connection, PreparedStatement 객체를 준비 합니다. (객체생성)
	public void readyForQuery(String sql) throws SQLException {
		conn = DBConnectorJNDI.getDataSource().getConnection();
		pstmt = conn.prepareStatement(sql);
	}
	
	
// CommunicationHit 에 조건에 해당하는 튜플 개수를 구합니다.
	public int selectCount(CommunicationHitVO communicationHitVO) {
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS count FROM communicationHit ";
			sql += "WHERE boardIDX=? AND hitMemberID=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, communicationHitVO.getBoardIDX());
			pstmt.setString(2, communicationHitVO.getHitMemberID());
			
			resultSet = pstmt.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt("count");
				
			} else {
				result = 0;
			}
			
		} catch(SQLException e) {
			System.out.println("<CommunicationHitDAO - selectCount() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return result;
	}
	
	
// CommunicationHit 의 데이터를 DELETE 합니다.
	public int deleteHit(CommunicationHitVO communicationHitVO) {
		int result = 0;
		
		try {
			String sql = "DELETE FROM communicationHit ";
			sql += "WHERE boardIDX=? AND hitMemberID=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, communicationHitVO.getBoardIDX());
			pstmt.setString(2, communicationHitVO.getHitMemberID());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<CommunicationHitDAO - deleteHit() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// CommunicationHit 의 데이터를 INSERT 합니다.
	public int insertHit(CommunicationHitVO communicationHitVO) {
		int result = 0;
		
		try {
			String sql = "INSERT INTO communicationHit(boardIDX, hitMemberID) ";
			sql += "VALUES(?, ?)";
			
			readyForQuery(sql);
			pstmt.setInt(1, communicationHitVO.getBoardIDX());
			pstmt.setString(2, communicationHitVO.getHitMemberID());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<CommunicationHitDAO - insertHit() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
}
























