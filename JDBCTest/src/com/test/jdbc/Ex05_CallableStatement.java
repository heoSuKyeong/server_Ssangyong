package com.test.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

public class Ex05_CallableStatement {
	public static void main(String[] args) {
		
		//프로시저를 호출하는 전용 클래스
		//m1();
		//m2();
		//m3();
		//m4();
		m5();
	}
	
	private static void m5() {
		
		Connection conn = null;
		CallableStatement cstat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			
			String sql = "{call procM5(?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			//자바에는 cursor 자료형이 없어 object로 가져온다.
			//오라클 커서는 결과 테이블을 탐색하는 참조 객체이다.
			//ResultSet 또한 결과 테이블을 탐색하는 참조 객체이므로 오라클 커서와 ResultSet과 동일한 구조라고 볼 수 있다.
			rs = (ResultSet)cstat.getObject(1);
			
			while(rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("age"));
				System.out.println(rs.getString("address"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void m4() {
		
		// m4. 인자값(X), 반환값(O) 프로시저
		// out 파라미터 여려개
		
		Connection conn = null;
		CallableStatement cstat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			
			String sql = "{call procM4(?,?,?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.registerOutParameter(1, OracleTypes.VARCHAR);
			cstat.registerOutParameter(2, OracleTypes.NUMBER);
			cstat.registerOutParameter(3, OracleTypes.VARCHAR);
			
			cstat.executeUpdate();

			System.out.println(cstat.getString(1));
			System.out.println(cstat.getInt(2));
			System.out.println(cstat.getString(3));
			
			cstat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void m3() {
		
		//m3. 인자값(X), 반환값(O) 프로시저
		// out 파라미터 한개
		
		Connection conn = null;
		CallableStatement cstat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			
			//out 파마미터 하나 당 ? 하나식 표현해준다.
			String sql = "{call procM3(?)}";
			
			cstat = conn.prepareCall(sql);
			
			//out 매개변수 접근법
			cstat.registerOutParameter(1, OracleTypes.NUMBER);
			
			//방법1
			//int result = cstat.executeUpdate();
			
			//방법2
			//executeUpdate 사용하면 ResultSet는 안받는다.
			cstat.executeUpdate();
			
			//rs.getInt(1); 와 유사하지만 rs, cstat는 포지션이 다르다.
			int cnt = cstat.getInt(1);	//out 매개변수 읽기
			
			System.out.println(cnt);
			
			cstat.close();
			conn.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void m2() {
		
		//m2. 인자값(o), 반환값(X) 프로시저

		Connection conn = null;
		CallableStatement cstat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			
			String sql = "{call procM2(?,?,?,?)}";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, "김철수");
			cstat.setString(2, "5");
			cstat.setString(3, "m");
			cstat.setString(4, "떡잎마을");
			
			int result = cstat.executeUpdate();
			
			System.out.println(result);
			
			cstat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m1() {
		
		//m1. 인자값(X), 반환값(X) 프로시저
		
		Connection conn = null;
		CallableStatement cstat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			
			//call 프로시저명
			String sql = "{ call procM1 }";
			cstat = conn.prepareCall(sql);
			
			int result = cstat.executeUpdate();
			
			System.out.println(result);
			
			cstat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
