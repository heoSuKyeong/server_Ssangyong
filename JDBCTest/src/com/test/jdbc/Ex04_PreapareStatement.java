package com.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Ex04_PreapareStatement {
	public static void main(String[] args) {

		//m1();
		m2();
		
		
	}

	private static void m2() {
		
		// PrearedStatement
		// 장점
		//1. 매개변수 관리가 용이하다.
		//2. 매개변수를 자동으로 유효성 처리를 해준다.
		
		
		String name = "신짱아";
		String age = "2";
		String gender = "f";
		String address = "떡잎마을's";
		
		Connection conn = null;
		PreparedStatement pstat = null;
		
		try {
			
			conn = DBUtil.open();
			
			if (!conn.isClosed()) {
				
				//stat = conn.createStatement();
				
				// 동적 SQL
				String sql = "insert into tblAddress (seq, name, age, gender, address, regdate) values "
						+ "(seqAddress.nextVal, ?, ?, ?, ?, default)";
				
				//생성할 때부터 sql문을 매개변수로 필요로 함
				pstat = conn.prepareStatement(sql);
				
				//pstat : 매개변수를 관리하는 역할을 겸한다.
				//pstat.setXXX()
				pstat.setString(1, name);	//첫번째 ?에 name을 넣는다.
				pstat.setString(2, age);
				pstat.setString(3, gender);
				pstat.setString(4, address);
				
				int result = pstat.executeUpdate();
				
				if (result == 1) {
					System.out.println("삽입 성공");
				} else {
					System.out.println("삽입 실패");
				}
				
				pstat.close();
				conn.close();
				
			} else {
				System.out.println("DB 접속 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void m1() {

		// Statement vs PrearedStatement
		// Statement : 매개변수가 없는 정적 SQL이 편하다
		// PreparedStatement : 매개변수가 있는 동적 SQL이 편하다

		// insert + 사용자 입력 + Scanner
		String name = "신형만";
		String age = "32";
		String gender = "m";
		String address = "떡잎마을";
		
		//사용자 입력 값을 받을 때, 꽃따옴표(')가 들어가있으면 삽입되지않아 이스케이프시켜서 삽입해야한다.
		name = name.replace("'", "''");
		address = address.replace("'", "''");

		Connection conn = null;
		Statement stat = null;

		try {

			conn = DBUtil.open();

			if (!conn.isClosed()) {
				/*
				 * //정적 SQL String sql =
				 * "insert into tblAddress (seq, name, age, gender, address, regdate) values (seqAddress.nextVal, '이순신', 25, 'm', '서울시 강남구', default)"
				 * ;
				 * 
				 * //정적 SQL sql = String.
				 * format("insert into tblAddress (seq, name, age, gender, address, regdate) values "
				 * + "(seqAddress.nextVal, '%s', %s, '%s', '%s', default)", name, age, gender,
				 * address);
				 * 
				 * //동적 SQL : 오라클 변수 //?은 오라클 변수이다. 상황에 따라 값이 변한다고 인식한다. 코드를 부드럽게 적용하기 위해서 사용한다.
				 * sql = String.
				 * format("insert into tblAddress (seq, name, age, gender, address, regdate) values "
				 * + "(seqAddress.nextVal, ?, ?, ?, ?, default)", name, age, gender, address);
				 */

				stat = conn.createStatement();

				// 정적 SQL
				String sql = String.format("insert into tblAddress (seq, name, age, gender, address, regdate) values "
						+ "(seqAddress.nextVal, '%s', %s, '%s', '%s', default)", name, age, gender, address);

				int result = stat.executeUpdate(sql);

				if (result == 1) {
					System.out.println("삽입 성공");
				} else {
					System.out.println("삽입 실패");
				}

				stat.close();
				conn.close();

			} else {
				System.out.println("DB 접속 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
