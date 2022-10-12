package doo.doo.dao;

import java.util.*;
import java.sql.*;
public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
	
	public MemberDAO() {
		try {
			//드라이버 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL,"root","happy");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void disConnection() {
		try {
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<MemberVO> memberListData(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			getConnection();
			String sql = "SELECT no, name, sex, content FROM member";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSex(rs.getString(3));
				vo.setContent(rs.getString(4));
				list.add(vo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
}