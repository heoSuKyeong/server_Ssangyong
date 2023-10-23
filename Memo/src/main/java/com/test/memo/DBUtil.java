package com.test.memo;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
	private static Connection conn;
	
	public static Connection open() {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pw = "java1234";
		
		try {
			
			//JDBC 드라이버를 로딩한다.(관련 클래스 정보를 확인한다)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Connection 객체 생성과 동시에 오라클 접속
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//오버로딩
	public static Connection open(String server, String id, String pw) {
		
		String url = "jdbc:oracle:thin:@"+ server +":1521:xe";
		
		try {
			
			//JDBC 드라이버를 로딩한다.(관련 클래스 정보를 확인한다)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Connection 객체 생성과 동시에 오라클 접속
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
