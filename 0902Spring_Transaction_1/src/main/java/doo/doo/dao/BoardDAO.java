package doo.doo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.stereotype.Repository;

import oracle.net.aso.r;

@Repository
public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	//드라이버 등록
	public BoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	
	//연결
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	//닫기
	public void disConnection() {
		try {
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex) {}
	}
	
	
	//목록
	public List<BoardVO> boardListData(int page){
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnection();
			String sql = "SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD'), hit, group_tab, num "
					+ "FROM (SELECT no, subject, name, regdate, hit, group_tab, rownum as num "
					+ "FROM (SELECT no, subject, name, regdate, hit, group_tab "
					+ "FROM spring_replyboard ORDER BY group_id DESC, group_step ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			int rowSize = 10;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setGroup_tab(rs.getInt(6));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	//총페이지
	public int boardTotalPage() {
		int total = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/10.0) FROM spring_replyboard";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return total;
	}
	//글쓰기
	public void boardInsert(BoardVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO spring_replyboard(no,name,subject,content,pwd,group_id) "
					+ "VALUES(sr1_no_seq.nextval,?,?,?,?,(SELECT NVL(MAX(group_id)+1,1) FROM spring_replyboard))";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
	//상세보기
	public BoardVO boardDetailData(int no) {
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			//조회수 증가
			String sql = "UPDATE spring_replyboard SET hit=hit+1 WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			//글 조회
			sql="SELECT no, name, subject, content, TO_CHAR(regdate,'YYYY-MM-DD'), hit "
					+ "FROM spring_replyboard "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDbday(rs.getString(5));
			vo.setHit(rs.getInt(6));
			
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
	//답변하기 --> 트랜잭션
	public void boardReplyInsert(int pno, BoardVO vo) {
		try {
			getConnection();
			conn.setAutoCommit(false);//@Around
			String sql = "SELECT group_id, group_step, group_tab "
					+ "FROM spring_replyboard WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int gi = rs.getInt(1);
			int gs = rs.getInt(2);
			int gt = rs.getInt(3);
			ps.close();
			rs.close();
			
			//group_step 변경
			sql = "UPDATE spring_replyboard SET "
					+ "group_step=group_step+1 "
					+ "WHERE group_id=? AND group_step>?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, gi);
			ps.setInt(2, gs);
			ps.executeUpdate();
			ps.close();
			
			//데이터 추가
			sql = "INSERT INTO spring_replyboard(no,name,subject,content,pwd,group_id,group_step,group_tab,root) "
					+ "VALUES(sr1_no_seq.nextval, ?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setInt(5, gi);
			ps.setInt(6, gs+1);
			ps.setInt(7, gt+1);
			ps.setInt(8, pno);
			ps.executeUpdate();
			ps.close();
			
			//depth증가 -> 댓글 증가여부 확인
			sql = "UPDATE spring_replyboard SET "
					+ "depth=depth+1 "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.executeUpdate();
			
			conn.commit();//@Around
		}catch(Exception ex) {
			//rollback 날려야 함 @AfterThrowing
			ex.printStackTrace();
			try {
				conn.rollback();
			}catch(Exception e) {
				e.printStackTrace();
				}
		}finally { //@After
			try {
				conn.setAutoCommit(true);
				disConnection();
			}catch(Exception ex) {
				ex.printStackTrace();
				}
		}
	}
	//전체 글 개수
	//총페이지
		public int boardCount() {
			int total = 0;
			try {
				getConnection();
				String sql = "SELECT COUNT(*) FROM spring_replyboard";
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				rs.next();
				total = rs.getInt(1);
				rs.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				disConnection();
			}
			return total;
		}
	
	//수정(화면)
	public BoardVO boardUpdateData(int no) {
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			//글 조회
			String sql="SELECT no, name, subject, content "
					+ "FROM spring_replyboard "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
	//수정 처리
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck = false;
		try {
			getConnection();
			String sql = "SELECT pwd FROM spring_replyboard "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd = rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(vo.getPwd())) {
				bCheck = true;
				sql = "UPDATE spring_replyboard SET "
						+ "name=?, subject=?, content=? "
						+ "WHERE no=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				
				ps.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return bCheck;
	}
	//삭제 --> 트랜잭션
	public boolean boardDelete(int no, String pwd) {
		boolean bCheck = false;
		try {
			getConnection();
			conn.setAutoCommit(false);
			//1.비밀번호 가져오기/확인
			String sql = "SELECT pwd FROM spring_replyboard "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd = rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(pwd)) {
				bCheck=true;
				//2.root, depth확인
				sql="SELECT root, depth FROM spring_replyboard WHERE no=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, no);
				rs = ps.executeQuery();
				rs.next();
				int root = rs.getInt(1);
				int depth = rs.getInt(2);
				rs.close();
				
				if(depth==0) {//2-1.depth==0 ->삭제
					sql = "DELETE FROM spring_replyboard "
							+ "WHERE no=?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, no);
					ps.executeUpdate();
				}else {//2-2.depth!=0 -> 관리자가 삭제한 게시물이라고 처리
					String msg="관리자가 삭제한 게시글입니다";
					sql = "UPDATE spring_replyboard SET subject=?, content=? WHERE no=?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, msg);
					ps.setString(2, msg);
					ps.setInt(3, no);
					ps.executeUpdate();
				}
				//3.depth 감소
				sql = "UPDATE spring_replyboard SET "
						+ "depth=depth-1 WHERE root=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, root);
				ps.executeUpdate();
			}
			conn.commit();
		}catch(Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}finally {
			disConnection();
		}
		return bCheck;
	}
}
