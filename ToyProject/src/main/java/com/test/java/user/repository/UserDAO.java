package com.test.java.user.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.java.model.UserDTO;
import com.test.toy.DBUtil;

public class UserDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public UserDAO() {
		this.conn = DBUtil.open("localhost", "toy", "java1234");
	}

	public int register(UserDTO dto) {

		/*
		템플릿 만들기
		1. 매개변수 유/무
		
		2. 반환값 유/무
			유 : 단일값, DTO, List<DTO>
			
		queryParamNoReturn
		
		queryNoparamTokenReturn
		queryParamTokenReturn
		
		queryNoparamDTOReturn
		queryparamDTOReturn
		
		queryNoparamListReturn
		queryparamListReturn
		
		*/
		
		
		
		try {
			
			String sql = "insert into tblUser(id, pw, name, email, pic, lv, intro, ing) values (?, ?, ?, ?, ?, 1, ?, default)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getEmail());
			pstat.setString(5, dto.getPic());
			pstat.setString(6, dto.getIntro());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public UserDTO login(UserDTO dto) {
		
		try {
			
			String sql = "select * from tblUser where id=? and pw = ? and ing='y'";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				UserDTO result = new UserDTO();
				
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
				result.setLv(rs.getString("lv"));
				
				return result;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int unregister(String id) {
		
		try {
			//탈퇴처리는 id를 제외한 모든 정보를 null처리한다.
			String sql = "update tblUser set ing = 'n' where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public UserDTO get(String id) {
		
		try {
			
			String sql = "select * from tblUser where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				UserDTO dto = new UserDTO();
				
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setPic(rs.getString("pic"));
				dto.setIntro(rs.getString("intro"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
