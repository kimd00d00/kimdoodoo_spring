package com.sist.web;
import java.util.*;
import java.sql.*;
public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private String username, url, password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public EmpDAO(String driver) {
		try {
			Class.forName(driver);
		}catch(Exception ex) {}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url,username,password);
		}catch(Exception ex) {}
	}
	public void disConnection() {
		try {
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex) {}
	}
	
	public List<EmpVO> empListData(){
		List<EmpVO> list = new ArrayList();
		try {
			getConnection();
			String sql = "SELECT empno, ename, job, hiredate, sal FROM emp";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setEmp(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				list.add(vo);
			}
		}catch(Exception ex) {
			
		}
		return list;
	}
}
