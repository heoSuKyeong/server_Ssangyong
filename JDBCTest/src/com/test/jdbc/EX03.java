package com.test.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class EX03 {

	public static void main(String[] args) {
		
		//m1();
		//m2();
		//m3();
		//m4();
		//m5();
		//m6();
		//m7();
		//m8();
		m9();
		
	}
	

	
	private static void m9() {
		
		//반환값이 있는 쿼리(select 문)
		//4. 다중값 반환(n행 n열)
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select * from tblAddress";
			
			rs = stat.executeQuery(sql);
			
			System.out.println("[번호]\t[이름]\t[나이]\t[주소]");
			while(rs.next()) {
				System.out.printf("%s\t%s\t%s\t%s\n"
									,rs.getString("seq")
									,rs.getString("name")
									,rs.getString("age")
									,rs.getString("address"));
			}
			
			
			rs.close();
			stat.close();
			conn.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void m8() {
		
		//반환값이 있는 쿼리(select 문)
		//3. 다중값 반환(n행 1열) - 모든 직원의 이름
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select name from tblAddress";
			
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getString("name"));
			}
			

			rs.close();
			stat.close();
			conn.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void m7() {
		
		//반환값이 있는 쿼리(select 문)
		//2. 다중값 반환(1행 n열) - 홍길동에 대한 정보
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("번호: ");
		String seq = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select * from tblAddress where seq = " + seq;
			
			rs = stat.executeQuery(sql);
			
			//결과데이터가 있을수도 있고 없을수도 있음
			if (rs.next()) {
				
				System.out.println("번호: " + rs.getString("seq"));
				System.out.println("이름: " + rs.getString("name"));
				System.out.println("나이: " + rs.getString("age"));
				System.out.println("주소: " + rs.getString("address"));
				
			} else {
				System.out.printf("입력한 %s번의 데이터가 없습니다.\n", seq);
			}
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void m6() {
		
		//반환값이 있는 쿼리(select 문)
		//1. 단일값 반환(1행 1열) - 집계함수
		//2. 다중값 반환(1행 n열) - 홍길동에 대한 정보
		//3. 다중값 반환(n행 1열) - 모든 직원의 이름
		//4. 다중값 반환(n행 n열)
		
		//1. 단일값 반환(1행 1열) - 집계함수
		Connection conn = null;
		Statement stat = null;
		//결과테이블을 받아주는 클래스
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select count(*) as cnt from tblAddress";
			
			rs = stat.executeQuery(sql);
			
			//System.out.println(rs);
			
			//ResultSet은 Interator, 향상된 for, 스트림, 커서와 같은 방식으로 불러온다.
			
			rs.next();  //커서가 BOF에 있는 것을 한 칸 전진하여 실제 데이터가 있는 레코드로 위치한게 한다.
			
			//현재 커서가 가르키고 있는 레코드의 원하는 컬럼을 접근하여 데이터를 가져오면 된다.
			//접근 방법
			//rs.getXXX()
			//int : 첫번재 컬럼값 (1부터 시작)
			//int count = rs.getInt(1);
			
			//String : 컬럼의 이름 >  alias 사용할 것
			//String 활용하는게 가독성이 높아서 더 많이 사용한다.
			int count = rs.getInt("cnt");
			
			System.out.println(count);
			
			//생성 역순으로 자원 해제하기
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private static void m5() {
		
		//사용자 값 입력받아 insert
		
		Scanner scan = new Scanner(System.in);
		String name = "";
		String age = "";
		String gender = "";
		String address = "";
		
		Connection conn = null;
		Statement stat = null;
				
		try {
			
			System.out.print("이름: ");
			name = scan.nextLine();
			
			System.out.print("나이: ");
			age = scan.nextLine();
			
			System.out.print("성별(m,f): ");
			gender = scan.nextLine();
			
			System.out.print("주소: ");
			address = scan.nextLine();
			
			
			conn = DBUtil.open();
			
			if (!conn.isClosed()) {
				System.out.println("DB 접속 성공");
				
				String sql = String.format("insert into tblAddress (seq, name, age, gender, address, regdate) values "
						+ "(seqAddress.nextVal, '%s', %s, '%s', '%s', default)", name, age, gender, address);
				
				stat = conn.createStatement();
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

	private static void m4() {
		
		//반환값이 없는 쿼리
		//create 실행
		
		Connection conn = null;
		Statement stat = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "create table tblAddress (\r\n"
					+ "    seq number primary key,\r\n"
					+ "    name varchar2(30) not null,\r\n"
					+ "    age number not null,\r\n"
					+ "    gender char(1) not null,\r\n"
					+ "    address varchar2(300) not null,\r\n"
					+ "    regdate date default sysdate not null\r\n"
					+ ")";
			
			int result = stat.executeUpdate(sql);
			
			//테이블 생성은 라인이 삽입되는 것이 아닐 result는 0이다.
			/*
			if (result > 0) {
				System.out.println("생성 성공");
			} else {
				System.out.println("생성 실패");
			}
			*/
			
			stat.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void m3() {
		
		//반환값이 없는 쿼리
		//delete 실행
		
		Connection conn = null;
		Statement stat = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "delete from tblAddress where seq = 1";
			
			int result = stat.executeUpdate(sql);
			
			if (result > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
			
			
			stat.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static void m2() {
		
		//반환값이 없는 쿼리
		//update 실행
		
		Connection conn = null;
		Statement stat = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "update tblAddress set age = age + 1";
			
			int result = stat.executeUpdate(sql);
			
			if (result > 0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
			
			
			stat.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void m1() {

		//반환값이 없는 쿼리
		//select 문이 없는 쿼리
		//insert 실행
		
		Connection conn = null;
		Statement stat = null;
				
		try {
			
			conn = DBUtil.open();
			
			//DB 연결이 확실히 되어 있어야 작업을 할 수 있음
			if (!conn.isClosed()) {
				System.out.println("DB 접속 성공");
				
				//SQL 실행 
				//자바는 SQL을 이해하지 못한다. 그래서 쿼리를 문자열로 취급한다.
				//같은 경우 : 서블릿(자바)가 HTML, CSS, JavaScript를 이해하지못해 태그를 문자열로 만들었듯이 자바도 문자열로 쿼리문을 작성한다.
				
				//긴 문장의 경우 sql developer에서 작성하고 붙여넣으면 편하다
				String sql = "insert into tblAddress (seq, name, age, gender, address, regdate) values (seqAddress.nextVal, '이순신', 25, 'm', '서울시 강남구', default)";
				
				//stat : SQL 실행하는 역할
				stat = conn.createStatement();
				
				//1. 반환값이 없는 쿼리
				//실행할 쿼리를 인자값으로 넘김
				//int stat.executeUpdate(String sql)
				
				//2. 반환값이 있는 쿼리
				//ResultSet stat.executeQuery(String sql)
				
				//sql developer에서 코드 실행한 것과 같은 과정이 벌어진다.
				//생성된 행의 개수를 반환한다.
				int result = stat.executeUpdate(sql);
				
				if (result == 1) {
					System.out.println("삽입 성공");
				} else {
					System.out.println("삽입 실패");
				}
				
				//자원 해제(정리)
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
