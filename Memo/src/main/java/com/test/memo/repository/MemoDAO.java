package com.test.memo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.memo.DBUtil;
import com.test.memo.model.MemoDTO;

public class MemoDAO {
	//코드 분산이 핵심!
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public MemoDAO() {
		
		//만들어질 때 Conncetion 작업을 함녀 add를 호출했을 때 이미 연결된 상태가 된다.
		this.conn = DBUtil.open();
	}
	
	public int add(MemoDTO dto) {
		//name, pw, memo 를 DB에 insert하기		
		
		try {
			
			String sql = "insert into tblMemo(seq, name, pw, memo, regdate) values (seqMemo.nextVal, ?, ?, ?, default)";
			
			pstat = conn.prepareStatement(sql);
			
			//dto 상자를 열어 사용한다.
			pstat.setString(1, dto.getName());	
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getMemo());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//catch 절을 통해 내려왔으므로 0을 반환한다.
		return 0;
		
	}

	public ArrayList<MemoDTO> list() {
		
		//결과테이블을 돌려줘야하는데 ResultSet이 아닌 JDBC가 쓸 수 있는 자료형으로 돌려줄 수 있을까 > ArrayList로 옮겨 담아 반환한다.
		ArrayList<MemoDTO> list = new ArrayList<MemoDTO>();
		
		try {
			
			String sql = "select * from tblMemo order by seq desc";
			
			stat = conn.createStatement();
			
			//결과목록
			rs = stat.executeQuery(sql);
			
			//rs를 list로 옮겨담는 작업
			while (rs.next()) {
				
				//루프돌때마다 레코드 1줄을 MemoDTO 1개씩 맵핑
				MemoDTO dto = new MemoDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setPw(rs.getString("pw"));
				dto.setMemo(rs.getString("memo"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}

	public MemoDTO get(String seq) {
		
		try {
			
			String sql = "select * from tblMemo where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			MemoDTO dto = new MemoDTO();
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setPw(rs.getString("pw"));
				dto.setMemo(rs.getString("memo"));
				dto.setRegdate(rs.getString("regdate"));
			
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int edit(MemoDTO dto) {
		
		try {
			
			String sql = "update tblMemo set name=?, memo = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getMemo());
			pstat.setString(3, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public boolean check(MemoDTO dto) {

		try {

			String sql = "select count(*) as cnt from tblMemo where seq = ? and pw = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSeq());
			pstat.setString(2, dto.getPw());
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("cnt") == 1 ? true : false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public int del(String seq) {
		
		try {
			
			String sql = "delete from tblMemo where seq = ?"; 
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}


}
