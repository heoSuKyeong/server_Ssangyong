package com.test.ajax.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.ajax.DBUtil;
import com.test.ajax.model.EmpDTO;

public class EmpDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public EmpDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<EmpDTO> list(String department_id) {
		
		try {
			
			String sql = "select d.department_id, d.department_name, e.employee_id, (e.first_name||' '||e.last_name) as name, e.job_id, to_char(e.hire_date, 'yyyy-mm-dd') as hire_date from employees e inner join departments d on e.department_id = d.department_id where d.department_id = ?";
			System.out.println(department_id);
			System.out.println(sql);
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, department_id);
			
			rs = pstat.executeQuery();	
			
			ArrayList<EmpDTO> list = new ArrayList<EmpDTO>();
			
			while (rs.next()) {
				
				EmpDTO dto = new EmpDTO();
				
				dto.setEmployee_id(rs.getInt("employee_id"));
				dto.setName(rs.getString("name"));
				dto.setJob_id(rs.getString("job_id"));
				dto.setHire_date(rs.getString("hire_date"));
				
				list.add(dto);
			}	
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
}
