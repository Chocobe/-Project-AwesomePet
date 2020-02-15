package com.awesomePet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.awesomePet.mainDBCP.DBConnectorJNDI;
import com.awesomePet.vo.PetBoardVO;
import com.awesomePet.vo.PetContentsImageVO;
import com.awesomePet.vo.PetContentsVO;
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
		QUERY_LIMIT = 8;
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
	public int insertPetBoard(PetContentsVO petBoardVO) {
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
	
	
// petContentsImage 테이블에 데이터를 INSERT 합니다.
	public int insertPetContentsImages(List<PetContentsImageVO> petContentsImagesList) {
		int result = 0;
		
		try {
			if(petContentsImagesList.size() < 1) {
				return result;
			}
			
			String sql = "INSERT INTO petContentsImage(boardIDX, " +
													  "orderNumber, " +
													  "imgLocation, " +
													  "imgOriginLocation) ";
			String valuesSQL = "#(?, ?, ?, ?)";
			
			for(int i = 0; i < petContentsImagesList.size(); i++) {
				sql += valuesSQL;
			}
			
			sql = sql.replaceFirst("#", "VALUES");
			sql = sql.replaceAll("#", ",");
			
			System.out.println("TEST SQL : " + sql);
			
			readyForQuery(sql);
			
			int sqlParamNumber = 0;
			for(PetContentsImageVO imageVO : petContentsImagesList) {
				pstmt.setInt(++sqlParamNumber, imageVO.getBoardIDX());
				pstmt.setInt(++sqlParamNumber, imageVO.getOrderNumber());
				pstmt.setString(++sqlParamNumber, imageVO.getImgLocation());
				pstmt.setString(++sqlParamNumber, imageVO.getImgOriginLocation());
			}
			
			System.out.println("--- sql query : " + sql);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - insertPetContentsImages() 에러> : " + e.getMessage());
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
	
	
// petType 테이블에 데이터를 DELETE 합니다.
	public int deletePetType(String typeName) {
		int result = 0;
		
		try {
			String sql = "DELETE FROM petType ";
			sql += "WHERE typeName=?";
			
			readyForQuery(sql);
			
			pstmt.setString(1, typeName);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<petBoardDAO - deletePetType() 에러> : " + e.getMessage());
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
	
	
// petBoard 게시판("공개")의 총 페이지 수를 구합니다.
	// petBoard 게시판("공개")의 총 페이지 수를 구합니다.
	public int selectTotalPublicPageCnt() {
		return (int)Math.ceil(selectTotalPublicContentsCnt() / QUERY_LIMIT);
	}
	
	// petBoard 게시글("공개") 총 개수를 구합니다.
	public int selectTotalPublicContentsCnt() {
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS totalContentsCnt FROM petBoard ";
			sql += "WHERE boardState=?";
			
			readyForQuery(sql);
			
			pstmt.setString(1, "공개");
			
			resultSet = pstmt.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt("totalContentsCnt");
			}
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectTotalPublicContentsCnt() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return result;
	}
	
	// petBoard 게시판("공개")의 총 페이지 수를 구합니다. (특정 대분류)
	public int selectTotalPublicPageCnt(String requestTypeName) {
		return (int)Math.ceil(selectTotalPublicContentsCnt(requestTypeName) / QUERY_LIMIT);
	}
	
	// petBoard 게시글("공개") 총 개수를 구합니다.
	public int selectTotalPublicContentsCnt(String requestTypeName) {
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS totalContentsCnt FROM petBoard ";
			sql += "WHERE boardState=? ";
			sql += "AND boardIDX IN " +
						"(SELECT petID FROM pet " +
						"WHERE subType IN " + 
								"(SELECT subTypeName FROM petSubType WHERE typeName=?))";
			
			readyForQuery(sql);
			
			pstmt.setString(1, "공개");
			pstmt.setString(2, requestTypeName);
			
			resultSet = pstmt.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt("totalContentsCnt");
			}
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectTotalPublicContentsCnt() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return result;
	}
	
	// petBoard 게시판("공개")의 총 페이지 수를 구합니다. (특정 대분류 & 특정 소분류)
	public int selectTotalPublicPageCnt(String requestTypeName, String requestSubTypeName) {
		return (int)Math.ceil(selectTotalPublicContentsCnt(requestTypeName, requestSubTypeName) / QUERY_LIMIT);
	}
	
	// petBoard 게시글("공개") 총 개수를 구합니다.
	public int selectTotalPublicContentsCnt(String requestTypeName, String requestSubTypeName) {
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS totalContentsCnt FROM petBoard ";
			sql += "WHERE boardState=? ";
			sql += "AND boardIDX IN " +
						"(SELECT petID FROM pet " +
						"WHERE subType=? " +
						"AND subType IN " +
								"(SELECT subTypeName FROM petSubType WHERE typeName=?))";
			
			readyForQuery(sql);
			
			pstmt.setString(1, "공개");
			pstmt.setString(2, requestSubTypeName);
			pstmt.setString(3, requestTypeName);
			
			resultSet = pstmt.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt("totalContentsCnt");
			}
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectTotalPublicContentsCnt() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return result;
	}
	
	
// "공개"인 petBoardView를 출력하기 위한 데이터를 조회 합니다. (pet, petContentsImage 테이블 데이터)
	// 대분류가 "전체"일 경우, 호출
	public PetBoardVO selectPublicPetBoard(int requestPage) {
		PetBoardVO petBoardVO = new PetBoardVO();
		List<PetVO> petList = new ArrayList<PetVO>();
		List<PetContentsVO> petContentsList = new ArrayList<PetContentsVO>();
		
		try {
			String sql = "SELECT pet.petID, " + 
								"pet.subType, " + 
								"pet.age, " +
								"pet.gender, " +
								"pet.price, " +
								"pet.vaccination, " +
								"pet.neutralization, " +
								
								"petBoard.boardIDX, " +
								"petBoard.writerID, " +
								"petBoard.boardState, " +
								
								"firstImage.boardIDX, " +
								"firstImage.imgLocation, " +
								"firstImage.imgOriginLocation, " +
								"firstImage.orderNumber ";
			
			sql += "FROM pet, petBoard LEFT JOIN ";
			
			sql += "(SELECT petContentsImage.boardIDX, " +
						   "petContentsImage.imgLocation, " +
						   "petContentsImage.imgOriginLocation, " +
						   "petContentsImage.orderNumber " +
					"FROM petContentsImage, (SELECT boardIDX, MIN(orderNumber) AS orderNumber " +
											"FROM petContentsImage " +
											"GROUP BY boardIDX) AS tempTable ";
			
			sql += "WHERE petContentsImage.boardIDX = tempTable.boardIDX " +
				   "AND petContentsImage.orderNumber = tempTable.orderNumber) AS firstImage ";
			
			sql += "ON petBoard.boardIDX = firstImage.boardIDX ";
			sql += "WHERE pet.petID = petBoard.boardIDX " +
				   "AND petBoard.boardState = ? ";
			
			sql += "ORDER BY pet.petID DESC ";
			sql += "LIMIT ? OFFSET ?";
			
			readyForQuery(sql);
			
			pstmt.setString(1, "공개");
			pstmt.setInt(2, (int)QUERY_LIMIT);
			pstmt.setInt(3, (int)QUERY_LIMIT * (requestPage - 1));
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				List<PetContentsImageVO> imageList = new ArrayList<PetContentsImageVO>();
				
				// 이미지 데이터
				PetContentsImageVO petContentsImageVO = new PetContentsImageVO(resultSet.getInt("firstImage.boardIDX"),
																			   resultSet.getInt("firstImage.orderNumber"),
																			   resultSet.getString("firstImage.imgLocation"),
																			   resultSet.getString("firstImage.imgOriginLocation"));
				
				imageList.add(petContentsImageVO);
				
				// 반려동물 데이터
				PetVO petVO = new PetVO(resultSet.getInt("pet.petID"), 
										resultSet.getString("pet.subType"),
										resultSet.getInt("pet.age"),
										resultSet.getString("pet.gender"),
										resultSet.getInt("pet.price"),
										resultSet.getString("pet.vaccination"),
										resultSet.getString("pet.neutralization"), 
										imageList);
				
				petList.add(petVO);
				
				// 반려동물 게시물 데이터
				PetContentsVO petContentsVO = new PetContentsVO(resultSet.getInt("petBoard.boardIDX"), 
																resultSet.getString("petBoard.writerID"),
																resultSet.getString("petBoard.boardState"));
				
				petContentsList.add(petContentsVO);
				
				petBoardVO = new PetBoardVO(petList, petContentsList);
			}
			
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectPublicPetBoard() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return petBoardVO;
	}
	
	// 대분류가 "전체"가 아니고, 소분류가 "전체"인 경우 호출
	public PetBoardVO selectPublicPetBoard(int requestPage, String requestTypeName) {
		PetBoardVO petBoardVO = new PetBoardVO();
		List<PetVO> petList = new ArrayList<PetVO>();
		List<PetContentsVO> petContentsList = new ArrayList<PetContentsVO>();
		
		try {
			String sql = "SELECT pet.petID, " + 
								"pet.subType, " + 
								"pet.age, " +
								"pet.gender, " +
								"pet.price, " +
								"pet.vaccination, " +
								"pet.neutralization, " +
								
								"petBoard.boardIDX, " +
								"petBoard.writerID, " +
								"petBoard.boardState, " +
								
								"firstImage.boardIDX, " +
								"firstImage.imgLocation, " +
								"firstImage.imgOriginLocation, " +
								"firstImage.orderNumber ";
			
			sql += "FROM pet, petSubType, petBoard LEFT JOIN ";
			
			sql += "(SELECT petContentsImage.boardIDX, " +
						   "petContentsImage.imgLocation, " +
						   "petContentsImage.imgOriginLocation, " +
						   "petContentsImage.orderNumber " +
					"FROM petContentsImage, (SELECT boardIDX, MIN(orderNumber) AS orderNumber " +
											"FROM petContentsImage " +
											"GROUP BY boardIDX) AS tempTable ";
			
			sql += "WHERE petContentsImage.boardIDX = tempTable.boardIDX " +
				   "AND petContentsImage.orderNumber = tempTable.orderNumber) AS firstImage ";
				   
			
			sql += "ON petBoard.boardIDX = firstImage.boardIDX ";
			sql += "WHERE pet.petID = petBoard.boardIDX " +
				   "AND petBoard.boardState = ? " +
				   "AND pet.subType = petSubType.subTypeName " +
				   "AND petSubType.typeName = ? ";
			
			sql += "ORDER BY pet.petID DESC ";
			sql += "LIMIT ? OFFSET ?";
			
			readyForQuery(sql);
			
			pstmt.setString(1, "공개");
			pstmt.setString(2, requestTypeName);
			pstmt.setInt(3, (int)QUERY_LIMIT);
			pstmt.setInt(4, (int)QUERY_LIMIT * (requestPage - 1));
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				List<PetContentsImageVO> imageList = new ArrayList<PetContentsImageVO>();
				
				// 이미지 데이터
				PetContentsImageVO petContentsImageVO = new PetContentsImageVO(resultSet.getInt("firstImage.boardIDX"),
																			   resultSet.getInt("firstImage.orderNumber"),
																			   resultSet.getString("firstImage.imgLocation"),
																			   resultSet.getString("firstImage.imgOriginLocation"));
				
				imageList.add(petContentsImageVO);
				
				// 반려동물 데이터
				PetVO petVO = new PetVO(resultSet.getInt("pet.petID"), 
										resultSet.getString("pet.subType"),
										resultSet.getInt("pet.age"),
										resultSet.getString("pet.gender"),
										resultSet.getInt("pet.price"),
										resultSet.getString("pet.vaccination"),
										resultSet.getString("pet.neutralization"), 
										imageList);
				
				petList.add(petVO);
				
				// 반려동물 게시물 데이터
				PetContentsVO petContentsVO = new PetContentsVO(resultSet.getInt("petBoard.boardIDX"), 
																resultSet.getString("petBoard.writerID"),
																resultSet.getString("petBoard.boardState"));
				
				petContentsList.add(petContentsVO);
				
				petBoardVO = new PetBoardVO(petList, petContentsList);
			}
			
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectPublicPetBoard() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return petBoardVO;
	}
	
	// 소분류가 "전체"가 아닐 경우, 호출
	public PetBoardVO selectPublicPetBoard(int requestPage, String requestTypeName, String requestSubTypeName) {
		PetBoardVO petBoardVO = new PetBoardVO();
		List<PetVO> petList = new ArrayList<PetVO>();
		List<PetContentsVO> petContentsList = new ArrayList<PetContentsVO>();
		
		try {
			String sql = "SELECT pet.petID, " + 
								"pet.subType, " + 
								"pet.age, " +
								"pet.gender, " +
								"pet.price, " +
								"pet.vaccination, " +
								"pet.neutralization, " +
								
								"petBoard.boardIDX, " +
								"petBoard.writerID, " +
								"petBoard.boardState, " +
								
								"firstImage.boardIDX, " +
								"firstImage.imgLocation, " +
								"firstImage.imgOriginLocation, " +
								"firstImage.orderNumber ";
			
			sql += "FROM pet, petSubType, petBoard LEFT JOIN ";
			
			sql += "(SELECT petContentsImage.boardIDX, " +
						   "petContentsImage.imgLocation, " +
						   "petContentsImage.imgOriginLocation, " +
						   "petContentsImage.orderNumber " +
					"FROM petContentsImage, (SELECT boardIDX, MIN(orderNumber) AS orderNumber " +
											"FROM petContentsImage " +
											"GROUP BY boardIDX) AS tempTable ";
			
			sql += "WHERE petContentsImage.boardIDX = tempTable.boardIDX " +
				   "AND petContentsImage.orderNumber = tempTable.orderNumber) AS firstImage ";
				   
			
			sql += "ON petBoard.boardIDX = firstImage.boardIDX ";
			sql += "WHERE pet.petID = petBoard.boardIDX " +
				   "AND petBoard.boardState = ? " +
				   "AND pet.subType = ? " +
				   "AND pet.subType = petSubType.subTypeName " +
				   "AND petSubType.typeName = ? ";
			
			sql += "ORDER BY pet.petID DESC ";
			sql += "LIMIT ? OFFSET ?";
			
			readyForQuery(sql);
			
			pstmt.setString(1, "공개");
			pstmt.setString(2, requestSubTypeName);
			pstmt.setString(3, requestTypeName);
			pstmt.setInt(4, (int)QUERY_LIMIT);
			pstmt.setInt(5, (int)QUERY_LIMIT * (requestPage - 1));
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				List<PetContentsImageVO> imageList = new ArrayList<PetContentsImageVO>();
				
				// 이미지 데이터
				PetContentsImageVO petContentsImageVO = new PetContentsImageVO(resultSet.getInt("firstImage.boardIDX"),
																			   resultSet.getInt("firstImage.orderNumber"),
																			   resultSet.getString("firstImage.imgLocation"),
																			   resultSet.getString("firstImage.imgOriginLocation"));
				
				imageList.add(petContentsImageVO);
				
				// 반려동물 데이터
				PetVO petVO = new PetVO(resultSet.getInt("pet.petID"), 
										resultSet.getString("pet.subType"),
										resultSet.getInt("pet.age"),
										resultSet.getString("pet.gender"),
										resultSet.getInt("pet.price"),
										resultSet.getString("pet.vaccination"),
										resultSet.getString("pet.neutralization"), 
										imageList);
				
				petList.add(petVO);
				
				// 반려동물 게시물 데이터
				PetContentsVO petContentsVO = new PetContentsVO(resultSet.getInt("petBoard.boardIDX"), 
																resultSet.getString("petBoard.writerID"),
																resultSet.getString("petBoard.boardState"));
				
				petContentsList.add(petContentsVO);
				
				petBoardVO = new PetBoardVO(petList, petContentsList);
			}
			
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectPublicPetBoard() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return petBoardVO;
	}
	
	
// petBoard 게시판("공개" & "비공개" & "분양완료")의 총 페이지 수를 구합니다.
	// petBoard 게시글("공개" & "비공개" & "분양완료") 총 개수를 구합니다.
	public int selectTotalPageCnt() {
		return (int)Math.ceil(selectTotalContentsCnt() / QUERY_LIMIT);
	}
	
	// petBoard 게시글("공개" & "비공개" & "분양완료") 총 개수를 구합니다.
	public int selectTotalContentsCnt() {
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS totalContentsCnt FROM petBoard ";
			
			readyForQuery(sql);
			
			resultSet = pstmt.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt("totalContentsCnt");
			}
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectTotalContentsCnt() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return result;
	}
	
	// petBoard 게시판("공개" & "비공개" & "분양완료")의 총 페이지 수를 구합니다. (특정 대분류)
	public int selectTotalPageCnt(String requestTypeName) {
		return (int)Math.ceil(selectTotalContentsCnt(requestTypeName) / QUERY_LIMIT);
	}
	
	// petBoard 게시글("공개" & "비공개" & "분양완료") 총 개수를 구합니다.
	public int selectTotalContentsCnt(String requestTypeName) {
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS totalContentsCnt FROM petBoard ";
			sql += "WHERE boardIDX IN " +
						"(SELECT petID FROM pet " +
						"WHERE subType IN " + 
								"(SELECT subTypeName FROM petSubType WHERE typeName=?))";
			
			readyForQuery(sql);
			
			pstmt.setString(1, requestTypeName);
			
			resultSet = pstmt.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt("totalContentsCnt");
			}
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectTotalContentsCnt() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return result;
	}
	
	// petBoard 게시판("공개")의 총 페이지 수를 구합니다. (특정 대분류 & 특정 소분류)
	public int selectTotalPageCnt(String requestTypeName, String requestSubTypeName) {
		return (int)Math.ceil(selectTotalContentsCnt(requestTypeName, requestSubTypeName) / QUERY_LIMIT);
	}
	
	// petBoard 게시글("공개") 총 개수를 구합니다.
	public int selectTotalContentsCnt(String requestTypeName, String requestSubTypeName) {
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS totalContentsCnt FROM petBoard ";
			sql += "WHERE boardIDX IN " +
						  "(SELECT petID FROM pet " +
						  "WHERE subType=? " +
						  "AND subType IN " +
						  		"(SELECT subTypeName FROM petSubType WHERE typeName=?))";
			
			readyForQuery(sql);
			
			pstmt.setString(1, requestSubTypeName);
			pstmt.setString(2, requestTypeName);
			
			resultSet = pstmt.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt("totalContentsCnt");
			}
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectTotalContentsCnt() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return result;
	}
	
	
	
// "공개"인 petBoardView를 출력하기 위한 데이터를 조회 합니다. (pet, petContentsImage 테이블 데이터)
	// 대분류가 "전체"일 경우, 호출
	public PetBoardVO selectPetBoard(int requestPage) {
		PetBoardVO petBoardVO = new PetBoardVO();
		List<PetVO> petList = new ArrayList<PetVO>();
		List<PetContentsVO> petContentsList = new ArrayList<PetContentsVO>();
		
		try {
			String sql = "SELECT pet.petID, " + 
								"pet.subType, " + 
								"pet.age, " +
								"pet.gender, " +
								"pet.price, " +
								"pet.vaccination, " +
								"pet.neutralization, " +
								
								"petBoard.boardIDX, " +
								"petBoard.writerID, " +
								"petBoard.boardState, " +
								
								"firstImage.boardIDX, " +
								"firstImage.imgLocation, " +
								"firstImage.imgOriginLocation, " +
								"firstImage.orderNumber ";
			
			sql += "FROM pet, petBoard LEFT JOIN ";
			
			sql += "(SELECT petContentsImage.boardIDX, " +
						   "petContentsImage.imgLocation, " +
						   "petContentsImage.imgOriginLocation, " +
						   "petContentsImage.orderNumber " +
					"FROM petContentsImage, (SELECT boardIDX, MIN(orderNumber) AS orderNumber " +
											"FROM petContentsImage " +
											"GROUP BY boardIDX) AS tempTable ";
			
			sql += "WHERE petContentsImage.boardIDX = tempTable.boardIDX " +
				   "AND petContentsImage.orderNumber = tempTable.orderNumber) AS firstImage ";
			
			sql += "ON petBoard.boardIDX = firstImage.boardIDX ";
			sql += "WHERE pet.petID = petBoard.boardIDX ";
			
			sql += "ORDER BY pet.petID DESC ";
			sql += "LIMIT ? OFFSET ?";
			
			readyForQuery(sql);
			
			pstmt.setInt(1, (int)QUERY_LIMIT);
			pstmt.setInt(2, (int)QUERY_LIMIT * (requestPage - 1));
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				List<PetContentsImageVO> imageList = new ArrayList<PetContentsImageVO>();
				
				// 이미지 데이터
				PetContentsImageVO petContentsImageVO = new PetContentsImageVO(resultSet.getInt("firstImage.boardIDX"),
																			   resultSet.getInt("firstImage.orderNumber"),
																			   resultSet.getString("firstImage.imgLocation"),
																			   resultSet.getString("firstImage.imgOriginLocation"));
				
				imageList.add(petContentsImageVO);
				
				// 반려동물 데이터
				PetVO petVO = new PetVO(resultSet.getInt("pet.petID"), 
										resultSet.getString("pet.subType"),
										resultSet.getInt("pet.age"),
										resultSet.getString("pet.gender"),
										resultSet.getInt("pet.price"),
										resultSet.getString("pet.vaccination"),
										resultSet.getString("pet.neutralization"), 
										imageList);
				
				petList.add(petVO);
				
				// 반려동물 게시물 데이터
				PetContentsVO petContentsVO = new PetContentsVO(resultSet.getInt("petBoard.boardIDX"), 
																resultSet.getString("petBoard.writerID"),
																resultSet.getString("petBoard.boardState"));
				
				petContentsList.add(petContentsVO);
				
				petBoardVO = new PetBoardVO(petList, petContentsList);
			}
			
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectPetBoard() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return petBoardVO;
	}
	
	// 대분류가 "전체"가 아니고, 소분류가 "전체"인 경우 호출
	public PetBoardVO selectPetBoard(int requestPage, String requestTypeName) {
		PetBoardVO petBoardVO = new PetBoardVO();
		List<PetVO> petList = new ArrayList<PetVO>();
		List<PetContentsVO> petContentsList = new ArrayList<PetContentsVO>();
		
		try {
			String sql = "SELECT pet.petID, " + 
								"pet.subType, " + 
								"pet.age, " +
								"pet.gender, " +
								"pet.price, " +
								"pet.vaccination, " +
								"pet.neutralization, " +
								
								"petBoard.boardIDX, " +
								"petBoard.writerID, " +
								"petBoard.boardState, " +
								
								"firstImage.boardIDX, " +
								"firstImage.imgLocation, " +
								"firstImage.imgOriginLocation, " +
								"firstImage.orderNumber ";
			
			sql += "FROM pet, petSubType, petBoard LEFT JOIN ";
			
			sql += "(SELECT petContentsImage.boardIDX, " +
						   "petContentsImage.imgLocation, " +
						   "petContentsImage.imgOriginLocation, " +
						   "petContentsImage.orderNumber " +
					"FROM petContentsImage, (SELECT boardIDX, MIN(orderNumber) AS orderNumber " +
											"FROM petContentsImage " +
											"GROUP BY boardIDX) AS tempTable ";
			
			sql += "WHERE petContentsImage.boardIDX = tempTable.boardIDX " +
				   "AND petContentsImage.orderNumber = tempTable.orderNumber) AS firstImage ";
				   
			
			sql += "ON petBoard.boardIDX = firstImage.boardIDX ";
			sql += "WHERE pet.petID = petBoard.boardIDX " +
				   "AND pet.subType = petSubType.subTypeName " +
				   "AND petSubType.typeName = ? ";
			
			sql += "ORDER BY pet.petID DESC ";
			sql += "LIMIT ? OFFSET ?";
			
			readyForQuery(sql);
			
			pstmt.setString(1, requestTypeName);
			pstmt.setInt(2, (int)QUERY_LIMIT);
			pstmt.setInt(3, (int)QUERY_LIMIT * (requestPage - 1));
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				List<PetContentsImageVO> imageList = new ArrayList<PetContentsImageVO>();
				
				// 이미지 데이터
				PetContentsImageVO petContentsImageVO = new PetContentsImageVO(resultSet.getInt("firstImage.boardIDX"),
																			   resultSet.getInt("firstImage.orderNumber"),
																			   resultSet.getString("firstImage.imgLocation"),
																			   resultSet.getString("firstImage.imgOriginLocation"));
				
				imageList.add(petContentsImageVO);
				
				// 반려동물 데이터
				PetVO petVO = new PetVO(resultSet.getInt("pet.petID"), 
										resultSet.getString("pet.subType"),
										resultSet.getInt("pet.age"),
										resultSet.getString("pet.gender"),
										resultSet.getInt("pet.price"),
										resultSet.getString("pet.vaccination"),
										resultSet.getString("pet.neutralization"), 
										imageList);
				
				petList.add(petVO);
				
				// 반려동물 게시물 데이터
				PetContentsVO petContentsVO = new PetContentsVO(resultSet.getInt("petBoard.boardIDX"), 
																resultSet.getString("petBoard.writerID"),
																resultSet.getString("petBoard.boardState"));
				
				petContentsList.add(petContentsVO);
				
				petBoardVO = new PetBoardVO(petList, petContentsList);
			}
			
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectPetBoard() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return petBoardVO;
	}
	
	// 소분류가 "전체"가 아닐 경우, 호출
	public PetBoardVO selectPetBoard(int requestPage, String requestTypeName, String requestSubTypeName) {
		PetBoardVO petBoardVO = new PetBoardVO();
		List<PetVO> petList = new ArrayList<PetVO>();
		List<PetContentsVO> petContentsList = new ArrayList<PetContentsVO>();
		
		try {
			String sql = "SELECT pet.petID, " + 
								"pet.subType, " + 
								"pet.age, " +
								"pet.gender, " +
								"pet.price, " +
								"pet.vaccination, " +
								"pet.neutralization, " +
								
								"petBoard.boardIDX, " +
								"petBoard.writerID, " +
								"petBoard.boardState, " +
								
								"firstImage.boardIDX, " +
								"firstImage.imgLocation, " +
								"firstImage.imgOriginLocation, " +
								"firstImage.orderNumber ";
			
			sql += "FROM pet, petSubType, petBoard LEFT JOIN ";
			
			sql += "(SELECT petContentsImage.boardIDX, " +
						   "petContentsImage.imgLocation, " +
						   "petContentsImage.imgOriginLocation, " +
						   "petContentsImage.orderNumber " +
					"FROM petContentsImage, (SELECT boardIDX, MIN(orderNumber) AS orderNumber " +
											"FROM petContentsImage " +
											"GROUP BY boardIDX) AS tempTable ";
			
			sql += "WHERE petContentsImage.boardIDX = tempTable.boardIDX " +
				   "AND petContentsImage.orderNumber = tempTable.orderNumber) AS firstImage ";
				   
			
			sql += "ON petBoard.boardIDX = firstImage.boardIDX ";
			sql += "WHERE pet.petID = petBoard.boardIDX " +
				   "AND pet.subType = ? " +
				   "AND pet.subType = petSubType.subTypeName " +
				   "AND petSubType.typeName = ? ";
			
			sql += "ORDER BY pet.petID DESC ";
			sql += "LIMIT ? OFFSET ?";
			
			readyForQuery(sql);
			
			pstmt.setString(1, requestSubTypeName);
			pstmt.setString(2, requestTypeName);
			pstmt.setInt(3, (int)QUERY_LIMIT);
			pstmt.setInt(4, (int)QUERY_LIMIT * (requestPage - 1));
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				List<PetContentsImageVO> imageList = new ArrayList<PetContentsImageVO>();
				
				// 이미지 데이터
				PetContentsImageVO petContentsImageVO = new PetContentsImageVO(resultSet.getInt("firstImage.boardIDX"),
																			   resultSet.getInt("firstImage.orderNumber"),
																			   resultSet.getString("firstImage.imgLocation"),
																			   resultSet.getString("firstImage.imgOriginLocation"));
				
				imageList.add(petContentsImageVO);
				
				// 반려동물 데이터
				PetVO petVO = new PetVO(resultSet.getInt("pet.petID"), 
										resultSet.getString("pet.subType"),
										resultSet.getInt("pet.age"),
										resultSet.getString("pet.gender"),
										resultSet.getInt("pet.price"),
										resultSet.getString("pet.vaccination"),
										resultSet.getString("pet.neutralization"), 
										imageList);
				
				petList.add(petVO);
				
				// 반려동물 게시물 데이터
				PetContentsVO petContentsVO = new PetContentsVO(resultSet.getInt("petBoard.boardIDX"), 
																resultSet.getString("petBoard.writerID"),
																resultSet.getString("petBoard.boardState"));
				
				petContentsList.add(petContentsVO);
				
				petBoardVO = new PetBoardVO(petList, petContentsList);
			}
			
			
		} catch(SQLException e) {
			System.out.println("<PetBoardDAO - selectPetBoard() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return petBoardVO;
	}
	
	
// 특정 글 페이지를 SELECT 합니다.
	public PetBoardVO selectPetContents(int requestBoardIDX) {
		PetBoardVO petBoardVO = null;
		
		try {
			String sql = "SELECT pet.petID, " + 
						 		"pet.subType, " + 
						 		"pet.age, " +
						 		"pet.gender, " +
						 		"pet.price, " +
						 		"pet.vaccination, " +
						 		"pet.neutralization, " +
						 
						 		"petBoard.boardIDX, " +
						 		"petBoard.writerID, " +
						 		"petBoard.watch, " +
						 		"petBoard.writeDate, " +
						 		"petBoard.boardState, " + 
						 
						 		"petSubType.typeName, " +
						 		"petSubType.subTypeName, " +
						 		"petSubType.subTypeComment ";

			sql += "FROM pet, petSubType, petBoard ";
			
			sql += "WHERE pet.petID = ? " +
				   "AND pet.petID = petBoard.boardIDX " +
				   "AND pet.subType = petSubType.subTypeName ";
			
			readyForQuery(sql);
			
			pstmt.setInt(1, requestBoardIDX);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				List<PetVO> petList = new ArrayList<PetVO>();
				List<PetContentsVO> petContentsList = new ArrayList<PetContentsVO>(); 
				
				// 반려동물 데이터
				PetVO petVO = new PetVO(resultSet.getInt("pet.petID"), 
										resultSet.getString("pet.subType"),
										resultSet.getInt("pet.age"),
										resultSet.getString("pet.gender"),
										resultSet.getInt("pet.price"),
										resultSet.getString("pet.vaccination"),
										resultSet.getString("pet.neutralization"));
				
				petList.add(petVO);
				
				// 반려동물 게시물 데이터
				PetContentsVO petContentsVO = new PetContentsVO(resultSet.getInt("petBoard.boardIDX"), 
																resultSet.getString("petBoard.writerID"),
																resultSet.getInt("petBoard.watch"),
																resultSet.getDate("petBoard.writeDate").toLocalDate(),
																resultSet.getString("petBoard.boardState"));
				
				petContentsList.add(petContentsVO);
				
				petBoardVO = new PetBoardVO(petList, petContentsList);
				
				petBoardVO.setCurrentTypeName(resultSet.getString("petSubType.typeName"));
				petBoardVO.setCurrentSubTypeName(resultSet.getString("petSubType.subTypeName"));
				petBoardVO.setCurrentSubTypeComment(resultSet.getString("petSubType.subTypeComment"));
			}
			
		} catch(SQLException e) {
			System.out.println("<petBoardDAO - selectPetContents() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return petBoardVO;
	}
	
	
// 특정 글 페이지의 모든 "이미지" 조회 합니다.
	public List<PetContentsImageVO> selectPetContentsImageList(int requestBoardIDX) {
		List<PetContentsImageVO> imageList = new ArrayList<PetContentsImageVO>();
		
		try {
			String sql = "SELECT boardIDX, " +
								 "orderNumber, " +
								 "imgLocation, " +
								 "imgOriginLocation ";
			sql += "FROM petContentsImage ";
			sql += "WHERE boardIDX=? ";
			
			sql += "ORDER BY boardIDX ASC";

			readyForQuery(sql);
			
			pstmt.setInt(1, requestBoardIDX);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				PetContentsImageVO imageVO = new PetContentsImageVO(resultSet.getInt("boardIDX"),
																	resultSet.getInt("orderNumber"),
																	resultSet.getString("imgLocation"),
																	resultSet.getString("imgOriginLocation"));
				
				imageList.add(imageVO);
			}
			
		} catch(SQLException e) {
			System.out.println("<petBoardDAO - selectPetContentsImageList() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt, resultSet);
		}
		
		return imageList;
	}
	
	
// 특정 글을 DELETE 합니다.
	public int deletePetContents(int requestBoardIDX) {
		int result = 0;
		
		try {
			String sql = "DELETE FROM pet ";
			sql += "WHERE petID=?";
			
			readyForQuery(sql);
			pstmt.setInt(1, requestBoardIDX);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<petBoardDAO - deletePetContents() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
	
	
// 특정 글의 조회수를 "증가" 시킵니다.
	public int updateWatch(int requestBoardIDX) {
		int result = 0;
		
		try {
			String sql = "UPDATE petBoard ";
			sql += "SET watch = watch + 1 ";
			sql += "WHERE boardIDX=?";
			
			readyForQuery(sql);
			
			pstmt.setInt(1, requestBoardIDX);
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("<petBoardDAO - updateWatch() 에러> : " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			DBConnectorJNDI.close(conn, pstmt);
		}
		
		return result;
	}
}












