package com.awesomePet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.awesomePet.mainDBCP.DBConnectorJNDI;
import com.awesomePet.vo.PetBoardImageVO;
import com.awesomePet.vo.PetBoardVO;
import com.awesomePet.vo.PetSubTypeVO;
import com.awesomePet.vo.PetTypeVO;
import com.awesomePet.vo.PetVO;

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
	
	
// pet 테이블에 데이터를 INSERT 합니다.
	public int insertPet(PetVO petVO) {
		int result = 0;
		
		try {
			String sql = "INSERT INTO pet(subType, " +
										 "age, " +
										 "gender, " +
										 "price, " +
										 "vaccination, " +
										 "neutralization) ";
			sql += "VALUES(?, ?, ?, ?, ?, ?)";
			
			readyForQuery(sql);
			
			pstmt.setString(1, petVO.getSubTypeName());
			pstmt.setInt(2, petVO.getAge());
			pstmt.setString(3, petVO.getGender());
			pstmt.setInt(4, petVO.getPrice());
			pstmt.setString(5, petVO.getVaccination());
			pstmt.setString(6, petVO.getNeutralization());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - insertPet() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// pet 테이블에서 petID를 SELECT 합니다.
	public int selectPetID(PetVO petVO) {
		int petID = 0;
		
		try {
			String sql = "SELECT petID FROM pet WHERE ";
			sql += "subType=? AND " +
				   "age=? AND " +
				   "gender=? AND " +
				   "price=? AND " +
				   "vaccination=? AND " +
				   "neutralization=? ";
			sql += "ORDER BY petID DESC ";
			sql += "LIMIT 1";
			
			readyForQuery(sql);
			
			pstmt.setString(1, petVO.getSubTypeName());
			pstmt.setInt(2, petVO.getAge());
			pstmt.setString(3, petVO.getGender());
			pstmt.setInt(4, petVO.getPrice());
			pstmt.setString(5, petVO.getVaccination());
			pstmt.setString(6, petVO.getNeutralization());
			
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				petID = resultSet.getInt("petID");
			}
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectPetID() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return petID;
	}
	
	
// petBoard 테이블에 데이터를 INSERT 합니다.
	public int insertPetBoard(PetBoardVO petBoardVO) {
		int result = 0;
		
		try {
			String sql = "INSERT INTO petBoard(boardIDX, " +
											  "writerID, " +
											  "boardState) ";
			sql += "VALUES(?, ?, ?)";
			
			readyForQuery(sql);
			
			pstmt.setInt(1, petBoardVO.getBoardIDX());
			pstmt.setString(2, petBoardVO.getWriterID());
			pstmt.setString(3, petBoardVO.getBoardState());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - insertPetBoard() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// petBoardImage 테이블에 데이터를 INSERT 합니다.
	public int insertPetBoardImages(List<PetBoardImageVO> petBoardImagesList) {
		int result = 0;
		
		try {
			if(petBoardImagesList.size() < 1) {
				return result;
			}
			
			String sql = "INSERT INTO petBoardImage(boardIDX, " +
												   "orderNumber, " +
												   "imgLocation, " +
												   "imgOriginLocation) ";
			String valuesSQL = "#(?, ?, ?, ?)";
			
			for(int i = 0; i < petBoardImagesList.size(); i++) {
				sql += valuesSQL;
			}
			
			sql = sql.replaceFirst("#", "VALUES");
			sql = sql.replaceAll("#", ",");
			
			System.out.println("TEST SQL : " + sql);
			
			readyForQuery(sql);
			
			int sqlParamNumber = 0;
			for(PetBoardImageVO imageVO : petBoardImagesList) {
				pstmt.setInt(++sqlParamNumber, imageVO.getBoardIDX());
				pstmt.setInt(++sqlParamNumber, imageVO.getOrderNumber());
				pstmt.setString(++sqlParamNumber, imageVO.getImgLocation());
				pstmt.setString(++sqlParamNumber, imageVO.getImgOriginLocation());
			}
			
			System.out.println("--- sql query : " + sql);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - insertPetBoardImages() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// petType 테이블에 데이터를 INSERT 합니다.
	public int insertPetType(String typeName) {
		int result = 0;
		
		try {
			String sql = "INSERT INTO petType(typeName) ";
			sql += "VALUES(?)";
			
			readyForQuery(sql);
			
			pstmt.setString(1, typeName);
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<petBoardDAO - insertPetType() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// petType 테이블에 데이터를 UPDATE 합니다.
	public int updatePetType(String typeName, String originTypeName) {
		int result = 0;
		
		try {
			String sql = "UPDATE petType SET ";
			sql += "typeName=? ";
			sql += "WHERE typeName=?";
			
			readyForQuery(sql);
			
			pstmt.setString(1, typeName);
			pstmt.setString(2, originTypeName);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<petBoardDAO - updatePetType() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// petSubType 테이블에 데이터를 INSERT 합니다.
	public int insertPetSubType(PetSubTypeVO petSubTypeVO) {
		int result = 0;
		
		try {
			String sql = "INSERT INTO petSubType(typeName, " +
												"subTypeName, " +
												"subTypeComment) ";
			sql += "VALUES(?, ?, ?)";
			
			readyForQuery(sql);
			
			pstmt.setString(1, petSubTypeVO.getTypeName());
			pstmt.setString(2, petSubTypeVO.getSubTypeName());
			pstmt.setString(3, petSubTypeVO.getSubTypeComment());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - insertPetSubType() 에러> : " + e.getMessage());
			e.printStackTrace();

		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// petSubType 테이블에 데이터를 UPDATE 합니다.
	public int updatePetSubType(PetSubTypeVO petSubTypeVO) {
		int result = 0;
		
		try {
			String sql = "UPDATE petSubType SET ";
			sql += "typeName=?, " +
				   "subTypeName=?, " +
				   "subTypeComment=? ";
			sql += "WHERE typeName=? AND subTypeName=?";
			
			readyForQuery(sql);
			
			pstmt.setString(1, petSubTypeVO.getTypeName());
			pstmt.setString(2, petSubTypeVO.getSubTypeName());
			pstmt.setString(3, petSubTypeVO.getSubTypeComment());
			pstmt.setString(4, petSubTypeVO.getOriginTypeName());
			pstmt.setString(5, petSubTypeVO.getOriginSubTypeName());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<petBoardDAO - updatePetSubType() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// petSubType 테이블에 데이터를 DELETE 합니다.
	public int deletePetSubType(PetSubTypeVO petSubTypeVO) {
		int result = 0;
		
		try {
			String sql = "DELETE FROM petSubType ";
			sql += "WHERE typeName=? AND subTypeName=?";
			
			readyForQuery(sql);
			
			pstmt.setString(1, petSubTypeVO.getTypeName());
			pstmt.setString(2, petSubTypeVO.getSubTypeName());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<petBoardDAO - deletePetSubType() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
}












