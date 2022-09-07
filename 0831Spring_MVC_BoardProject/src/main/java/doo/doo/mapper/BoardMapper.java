package doo.doo.mapper;

import java.util.*;
import doo.doo.dao.BoardVO;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface BoardMapper {
	//��� ���
	@Select("SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM spring_board ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	
	//�� ������
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_board")
	public int boardTotalPage();
	
	//�󼼺���
	@Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit "
			+ "FROM spring_board WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	//�Խù� �߰�
	//������ �ڵ�������ȣ. �긦 ���� �����ϰ� ������� int�� �޶�� ����
	@SelectKey(keyProperty="no", resultType=int.class, before=true,
			statement = "SELECT NVL(MAX(no)+1,1) as no FROM spring_board")
	@Insert("INSERT INTO spring_board VALUES("
			+ "#{no},#{name},#{subject},#{content},#{pwd},SYSDATE,0)")
	public void boardInsert(BoardVO vo);
	
	//�Խù� ����
	@Delete("DELETE FROM spring_board WHERE no=#{no}")
	public void boardDelete(int no);
	
	//�Խù� ���� - ��й�ȣ �˻�
	@Select("SELECT pwd FROM spring_board "
			+ "WHERE no=#{no}")
	public String boardGetPwd(int no);
	//�Խñ� ���� - ����ó��
	@Update("UPDATE spring_board SET "
			+ "name=#{name}, subject=#{subject}, content=#{content} "
			+ "WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	
	//��ȸ�� ����
	@Update("UPDATE spring_board SET "
			+ "hit=hit+1 WHERE no=#{no}")
	public void boardHitIncrement(int no);
	
	//�Խù� ã��(������ ������ XMl�� ó��)
	public List<BoardVO> boardFindData(Map map);
	
	/* �ƴ� �̷����ؾߵ�
	 * @Select("<script>"
	 		+ "SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday\r\n" + 
	 		"    FROM spring_board\r\n" + 
	 		"    WHERE \r\n" + 
	 		"    <trim prefix=\"(\" suffix=\")\" prefixOverrides=\"OR\">\r\n" + 
	 		"      <foreach collection=\"fsArr\" item=\"fd\">\r\n" + 
	 		"        <trim prefix=\"OR\">\r\n" + 
	 		"          <choose>\r\n" + 
	 		"            <when test=\"fd=='N'.toString()\">\r\n" + 
	 		"              name LIKE '%'||#{ss}||'%'\r\n" + 
	 		"            </when>\r\n" + 
	 		"            <when test=\"fd=='S'.toString()\">\r\n" + 
	 		"              subject LIKE '%'||#{ss}||'%'\r\n" + 
	 		"            </when>\r\n" + 
	 		"            <when test=\"fd=='C'.toString()\">\r\n" + 
	 		"              content LIKE '%'||#{ss}||'%'\r\n" + 
	 		"            </when>\r\n" + 
	 		"          </choose>\r\n" + 
	 		"        </trim>\r\n" + 
	 		"      </foreach>\r\n" + 
	 		"    </trim>" +
	 		+"</script>")
	 */
	 		
}
