package com.test.ajax.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.ajax.DBUtil;
import com.test.ajax.model.DeptDTO;

public class DeptDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public DeptDAO() {
		this.conn = DBUtil.open();
	}
	
	public ArrayList<DeptDTO> list() {
		
		try {
			
			String sql = "select * from departments";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<DeptDTO> list = new ArrayList<DeptDTO>();
			
			while (rs.next()) {
				
				DeptDTO dto = new DeptDTO();
				
				dto.setDepartment_id(rs.getInt("department_id"));
				dto.setDepartment_name(rs.getString("department_name"));
				
				list.add(dto);
			}	
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
