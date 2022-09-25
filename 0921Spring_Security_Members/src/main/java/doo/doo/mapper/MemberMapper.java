package doo.doo.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import doo.doo.vo.*;

public interface MemberMapper {
	//회원가입 ==> 비밀번호 암호화
	@Select("SELECT COUNT(*) FROM spring_join WHERE id=#{id}")
	public int memberIdCheck(String id);
	@Insert("INSERT INTO spring_join VALUES("
			+ "#{id}, #{pwd}, #{name}, #{sex}, #{birthday}, #{email}, #{post},"
			+ "#{addr1}, #{addr2}, #{tel}, #{content}, '', null, 'ROLE_USER')")
	public void memberJoin(MemberVO vo);
	
	//로그인 처리 ==> 복호화 ==> 자동 로그인 처리
	@Select("SELECT pwd, name, role FROM spring_join "
			+ "WHERE id=#{id}")
	public MemberVO memberJoinInfoData(String id);
	
	//회원 탈퇴 ==> 복호화
	
	
	//회원 수정 ==> 복호화
	@Select("SELECT pwd FROM spring_join WHERE id=#{id}")
	public String memberGetPassword(String id);
	
	@Select("SELECT * FROM spring_join WHERE id=#{id}")
	public MemberVO memberUpdateData(String id);
	
	/*
	 * private String id;
	private String pwd;
	private String name;
	private String sex;
	private String birthday;
	private String email;
	private String post;
	private String addr1;
	private String addr2;
	private String tel, tel1, tel2;
	private String content;
	private String sessionId;
	private String role;
	private Date limited;
	 */
	@Update("UPDATE spring_join SET name=#{name}, sex=#{sex}, email=#{email}, post=#{post}, addr1=#{addr1},"
			+ "addr2=#{addr2}, tel=#{tel}, content=#{content} WHERE id=#{id}")
	public void memberUpdate(MemberVO vo);
}
