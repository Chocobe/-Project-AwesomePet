package com.awesomePet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.awesomePet.mainDBCP.DBConnectorJNDI;
import com.awesomePet.vo.PetSubTypeVO;
import com.awesomePet.vo.PetTypeVO;

public class PetBoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	
	// 쿼리 결과 제한값 입니다.
	public static final double QUERY_LIMIT;
	
	static {
		QUERY_LIMIT = 10;
	}
	
	
// Connection, PreparedStatement 객체를 준비합니다. (객체 생성)
	private void readyForQuery(String sql) throws SQLException {
		conn = DBConnectorJNDI.getDataSource().getConnection();
		pstmt = conn.prepareStatement(sql);
	}
	
	
// petType 의 전체 데이터를 조회 합니다.
	public List<PetTypeVO> selectTotalPetType() {
		List<PetTypeVO> resultList = new ArrayList<PetTypeVO>();
	
		try {
			List<String> petTypeList = new ArrayList<String>();
			
			// petType 조회
			String sql = "SELECT * FROM petType";
			
			readyForQuery(sql);
			
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				petTypeList.add(resultSet.getString("typeName"));
			}
			
			DBConnectorJNDI.close(null, null, resultSet);
			
			// petSubType 조회
			for(String typeName : petTypeList) {
				List<PetSubTypeVO> petSubTypeList = new ArrayList<PetSubTypeVO>();
				sql = "SELECT * FROM petSubType WHERE typeName='" + typeName + "'"; 
				
				resultSet = pstmt.executeQuery(sql);
				
				while(resultSet.next()) {
					PetSubTypeVO petSubTypeVO = new PetSubTypeVO(resultSet.getString("typeName"),
																 resultSet.getString("subTypeName"),
																 resultSet.getString("subTypeComment"));
					
					petSubTypeList.add(petSubTypeVO);
				}
				
				PetTypeVO petTypeVO = new PetTypeVO(typeName, petSubTypeList);
				resultList.add(petTypeVO);
				
				DBConnectorJNDI.close(null, null, resultSet);
			}
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectTotalPetType() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return resultList;
	}
}
































