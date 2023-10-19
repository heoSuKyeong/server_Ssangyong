package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Ex01 {
	
	public static void main(String[] args) {
		
		//db접
		/*
		2. DB 서버 접속
		JDBC의 Connection 클래스를 사용하여 아래 정보로 오라클에 접속한다.
		호스트명 : localhost
		포트번호: 1521
		SID : xe
		드라이버 : thin
		사용자 : hr
		암호 : java1234
		*/
		
		//jdbc와 관련된 모든 클래스는 import java.sql.Connection;에 있다
		
		Connection conn = null;
		
		//연결 문자열, Connection String
		//sql developer에서는 ui로 작성했지만 자바에서 코드로 작성하기에 연결문자열을 사용한다.
		//id와 pw 외 나머지 정보를 url에 합친다.
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pw = "java1234";
		
		
		//외부입출력 작업은 예러가 생길 수 있으므로 예외 처리가 필수다
		try {
			//JDBC 드라이버를 로딩한다.(관련 클래스 정보를 확인한다)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Connection 객체 생성과 동시에 오라클 접속
			conn = DriverManager.getConnection(url, id, pw);
			
			//접속 상태 확인하는 방식
			System.out.println(conn.isClosed());  //false
			System.out.println("질의 실행"); 
			
			//접속 종료
			conn.close();
			
			System.out.println(conn.isClosed());
			
			/*
			자주 발생하는 에러들
			
			
			
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
