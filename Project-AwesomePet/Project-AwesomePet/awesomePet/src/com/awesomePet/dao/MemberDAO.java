package com.awesomePet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.awesomePet.mainDBCP.DBConnectorJNDI;
import com.awesomePet.vo.MemberVO;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	

// 전역변수 conn과 pstmt를 초기화 합니다.
	private void readyForQuery(String sql) throws SQLException {
		conn = DBConnectorJNDI.getDataSource().getConnection();
		pstmt = conn.prepareStatement(sql);
	}
	
	
// 회원을 조회합니다.
	public MemberVO selectMember(MemberVO memberVO) {
		MemberVO resultVO = null;
		
		try {
			String sql = "SELECT * FROM awesomePetMember " +
						 "WHERE memberID=? AND memberPW=?";
			readyForQuery(sql);
			
			pstmt.setString(1, memberVO.getMemberID());
			pstmt.setString(2, memberVO.getMemberPW());
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				resultVO = new MemberVO(resultSet.getString("memberID"),
										resultSet.getString("memberPW"),
										resultSet.getString("memberName"),
										resultSet.getDate("memberBirthDay"),
										resultSet.getString("memberEmail"),
										resultSet.getString("memberPhone"),
										resultSet.getString("memberAddr"),
										resultSet.getInt("memberGrade"),
										resultSet.getDate("memberJoinDate"));
			}
			
		} catch(SQLException e) {
			System.out.println("<MemberDAO - selectMember() 에러> : " + e.getMessage());
			e.printStackTrace();

		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return resultVO;
	}
}
