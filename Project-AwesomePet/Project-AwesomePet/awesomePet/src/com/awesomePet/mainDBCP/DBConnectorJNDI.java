package com.awesomePet.mainDBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// JNDI를 이용하여 DataSource객체를 생성합니다.
// DataSource객체 생성을 번복할 필요가 없다고 판단하여 "SingleTone Pattern"으로 구현 하였습니다.
public class DBConnectorJNDI {
	private static DataSource dataSource;
	
	// tomcat서버상 resource 경로 입니다.
	private static final String RESOURCE_DIR;
	
	// tomcat서버에 생성된 resource 이름 입니다.
	private static final String RESOURCE_NAME;
	
	static {
		RESOURCE_DIR = "java:/comp/env";
		RESOURCE_NAME = "jdbc/mysql/awesomePet";
		dataSource = initDataSource();
	}
	
	
// 생성자
	private DBConnectorJNDI() { }
	
	
// DataSource 객체를 생성(초기화) 합니다.
	private static DataSource initDataSource() {
		DataSource ds = null;
		
		try {
			Context initContext = new InitialContext();
			
			// resource 경로를 가진 객체를 생성합니다.
			Context envContext = (Context)initContext.lookup(RESOURCE_DIR);
			
			// 경로상에서 resource명으로 객체를 가져옵니다.
			ds = (DataSource)envContext.lookup(RESOURCE_NAME);
			
			System.out.println("<DBConnectorJNDI 알림> : DataSource 객체 생성을 정상 완료 하였습니다.");
			
		} catch(NamingException e) {
			System.out.println("<DBConnectorJNDI 에러> : " + e.getMessage());
			e.printStackTrace();
		}
		
		return ds;
	}
	
	
// 생성된 DataSource 객체 접근자(getter) 입니다. - 쿼리 메서드 -
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	
// 자원해제를 위한 정적 메서드 입니다.
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			
			close(conn, pstmt);
			
		} catch(SQLException e) {
			System.out.println("<DBConnectorJNDI 에러> : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null) {
				pstmt.close();
			}
			
			close(conn);
			
		} catch(SQLException e) {
			System.out.println("<DBConnectorJNDI 에러> : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
			
			System.out.println("<DBConnectorJNDI 알림> : 모든 자원해제가 완료 되었습니다.");
			
		} catch(SQLException e) {
			System.out.println("<DBConnectorJNDI 에러> : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
